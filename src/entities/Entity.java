package entities;

//subclasses should redefine render, update, getHit
import java.awt.Rectangle;
import java.util.ArrayList;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import sidescroller.Animation;
import sidescroller.ImagePanel;
import sidescroller.MyMap;
import skills.Skill;

/**
 * Jefferson Wang
 * This is stores all the non-unique information required for every entity
 * entities are basically anything not the map and background on the screen
 *
 * should be simple interface
 * allowing atribs: image, position
 * allowing behavior: animate, move
 *
 * ...specify into specific classes for interaction...
 * ex. creature, item, static(non interactive), effect(skills)
 * creature: health, interaction
 * item: itemNo, interaction
 * static: (basically an entity)
 * effect: interaction
 */
public abstract class Entity extends Rectangle {// Things that have a hitbox
	public float xSpeed = 2;//horizontal speed
	public float xFloat, yFloat;// Position in floats in
	public int health = 100;
	public float ySpeed = 0;// used for acceleration
	public MyMap mapInside;// the map the image is currently in
	protected String name;// the image name
	public int alliance;// who shot the bullet
	private boolean canJump = false, isRising = false;
	public float airTime = 333;// a counter
	private int jumpTimer = 0;// a counter
	protected int maxAirTime = 400;// how long you can be in the air
	public int canAct = 0;// when under 0, can use skills
	public Animation anim;
	private int isMoving = 0;// 0 for not moving, 1 for east, -1 for west
	public int direction = -1;// West, direction currently facing
	protected int idleFrames = 1;
	protected int movingFrames = 0;// number of frames in the moving animation
	protected int jumpingFrames = 1;
	public int invincible = 0;// how long you are invincible for
	public int invisible = -1;// how long you are invis for

	// sets the map, drawScreen, name, starting positions, width, height, and
	// alliance
	public Entity(String name, float x_pos, float y_pos, int width, int height, int ally, MyMap map) {
		try {
			anim = new Animation(new SpriteSheet(name + "_SS.png",
					width, height));

		} catch (SlickException e) {
			e.printStackTrace();
		}
		mapInside = map;
		this.name = name;
		xFloat = x_pos;
		yFloat = y_pos;
		this.height = height;
		this.width = width;
		super.x = (int) xFloat;
		super.y = (int) yFloat;
		this.alliance = ally;
	}


	// uses the skill, will need to add more later by making skill classes
	public void useSkill(Skill skill) {
		mapInside.entities.add(skill);
		canAct = skill.delay;
	}

	public boolean update(int delta) {// false is unload
		canAct-=delta;
		invincible -= delta;
		invisible -= delta;
		jump(delta);
		animate();
		super.x = (int) (xFloat);
		super.y = (int) (yFloat);
		if (health <= 0)
			return false;
		else
			return true;
	}

	public void move(int direction, int delta) {// moving left and right
		if (canMove((float) getCenterX() + xSpeed * direction + width / 2
				* direction)) {
			isMoving = direction;
			this.xFloat += xSpeed * direction * delta / 17;
			if (isMoving != 0)
				this.direction = isMoving;
		}
	}

	public abstract void getHit(Entity ent);

	public abstract int damage();

	public void die(int i) {
		mapInside.entities.remove(i);
	}

	public boolean tryJump() {
		if (canJump && jumpTimer < 0) {
			isRising = true;
			return true;
		}
		return false;
	}

	public void stopJump() {
		isRising = false;
	}

	public boolean canMove(float x) {
		if (/* (canAct>0 && canJump) || */(mapInside.getPosition(x,
				(float) getMinY() + 10)[2] == 1
				|| mapInside.getPosition(x, (float) getMaxY() - 8)[2] == 1 || mapInside
					.getPosition(x, (float) getCenterY())[2] == 1)) {
			return false;
		}
		return true;
	}

	public boolean canRise(float y) {
		if (mapInside.getPosition((float) getMinX() + 5, y)[2] == 1
				|| mapInside.getPosition((float) getMaxX() - 5, y)[2] == 1)
			return false;
		return true;
	}

	public boolean canFall(float yS) {
		if ((mapInside.getPosition((float) getMinX() + 5, (float) getMaxY()+5)[1] == 1
				&& ((int) (getMaxY() / 40) != (int) (getMaxY() - mapInside.floorSize) / 40) || (mapInside
				.getPosition((float) getMaxX() - 5, (float) getMaxY())[1] == 1 && ((int) (getMaxY() / 40) != (int) (getMaxY() - mapInside.floorSize) / 40)))
				|| (mapInside.getPosition((float) getCenterX(),
						(float) getMaxY())[1] == 1 && ((int) (getMaxY() / 40) != (int) (getMaxY() - mapInside.floorSize) / 40))) {
			return false;
		}
		return true;
	}

	public void jump(int delta) {
		if (canFall(ySpeed)) {// in the air
			fall(delta);
		} else {// on the ground
			if (!isRising)// make sure you can't phase though times
				// Infinitely
				airTime = (maxAirTime);// stop jumping
			ySpeed = 0;
			canJump = true;
			yFloat = ((int) getMaxY() / 40) * 40 - height;// land
			jumpTimer-=delta;

		}
		if (airTime > 0 && isRising) {
			if (canRise(yFloat - airTime/1000*60 / 3)) {
				yFloat -= (airTime/1000*60 / 3);
				airTime-=delta;
				System.out.println(airTime);//works up to here
			} else {
				airTime = 0;
			}
		} else {
			stopJump();
		}
	}

	public void animate() {// shouldn't affect gamePlay
		if (!canJump) {
			anim.setAction(2, 5, jumpingFrames);
		} else if (isMoving != 0) {
			anim.setAction(1, 5, movingFrames);
		} else {
			anim.setAction(0, 5, idleFrames);
		}
	}

	public void render(Graphics g, float cameraX, float cameraY) {
		if (invisible < 0) {
		if (direction == -1)
			anim.flipped = false;
		else
			anim.flipped = true;
		anim.draw(xFloat - cameraX, yFloat - cameraY);
		}
	}

	public void fall(int delta) {
		jumpTimer = 66;// time until can jump again after landing
		canJump = false;
		yFloat += ySpeed*delta/17;
		if (ySpeed < 8 && !isRising)
			ySpeed += .4*delta/17;
	}
}

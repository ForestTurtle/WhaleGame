package skills;



import org.newdawn.slick.Graphics;

import entities.Entity;
public abstract class Skill extends Entity {
	protected float startX, startY;//the starting position of the skill
	protected float time = 0;//timer, time since spawned
	protected int direction;//direction facing
	public int maxTime = 10;// time until unload.
	public int delay = 0;//time until the player cna use another skill
	Entity source;//who threw the spear?

	//sets direction and draws image
	//name of skill in file, direction to be facing
	//sets the map, drawScreen, name, width, height, and alliance(from source entity)
	//places the skill in the Entities position, and records the starting positions
	public Skill(String name, Entity source, int width, int height) {
		super(name, source.xFloat+source.width/2, source.yFloat+source.height/2,width, height, source.alliance, source.mapInside);
		this.source = source;
		xFloat -= width/2;
		yFloat -= height/2;
		startX = xFloat;
		startY = yFloat;
		this.direction = -1;
		if (this.direction != source.direction) {
			//image = image.getFlippedCopy(true, false);
			anim.flipped = true;
			this.direction = source.direction;
		}
	}
	public void render(Graphics g, float cameraX, float cameraY){
		anim.draw(xFloat-cameraX, yFloat-cameraY);
	}
	//updates the x and y, counts time, and checks if unload
	public void updatePos(){
		super.x = (int) xFloat;
		super.y = (int) yFloat;
	}
	public abstract boolean update(int delta);
}
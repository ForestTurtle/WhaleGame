package skills;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import entities.Entity;

public class ThrowSpear extends Skill {
	private float xDis = 15, yDis = 1f;// speeds/distances want to travel
	final int cap = 100;// max height
	// sets direction and draws image
	// the direction meant to face
	public float angle = 0;
	Image img;

	public ThrowSpear(Entity source) {
		super("Weapon_1", source,70,13);// Change the 1 later(alliance)
		delay = 666;
		maxTime = 1166;
		update(1);
		health = 1;
		if (anim.flipped)
			img = anim.getImage().getFlippedCopy(true, false);
		else
			img = anim.getImage();
	}

	// makes parabola
	public boolean update(int delta) {// example update
		xFloat = startX + time/1000*60 / 2 * xDis * direction;
		yFloat = startY + ((time/1000*60 / 2 / yDis - 7) * (time/1000*60 / 2 / yDis - 7))
				- (49);
		angle = (float) Math.atan((2 * ((time/1000*60 / 2 - 7) / (xDis * direction))));
		time+=delta;
		super.updatePos();
		if (time > maxTime || health <= 0) {
			return (false);
		}
		return (true);
	}

	public void render(Graphics g, float cameraX, float cameraY) {
		// image.setRotation((float) (angle*180/Math.PI));
		img.setRotation((float) (angle * 180 / Math.PI));
		img.draw(xFloat - cameraX, yFloat - cameraY);
	}

	public void getHit(Entity damage) {
		health = 0;
	}

	@Override
	public int damage() {
		return 20;
	}
}
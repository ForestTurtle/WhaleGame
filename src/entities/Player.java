package entities;

import main.Data;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import sidescroller.DamageNumber;
import sidescroller.MyMap;

public class Player extends Entity {

	// calls super's constructor
	public Player(float x_pos, float y_pos, int ally, MyMap map) {
		super("sprites/Char", x_pos, y_pos, 28, 70, ally, map);
		health = 1000;
		movingFrames = 6;
	}

	// calls update camera and supers update
	public boolean update(int delta) {
		return (super.update(delta));
	}

	public void getHit(Entity ent) {
		if (invincible <= 0) {
			health -= ent.damage();
			mapInside.statics.add(new DamageNumber(ent.damage() + "", xFloat,
					yFloat - 20, Data.font, Color.red));
			invincible = 400;
			float moveTo;
			if (ent.xFloat > xFloat)
				moveTo = xFloat - 10;
			else
				moveTo = xFloat + 10;
			if (canMove(moveTo)) {
				xFloat = moveTo;
			}
			if (canRise(yFloat + 5)) {
				// yFloat-=200; doesnt work on ground b/c ground is setting
			}
		}
	}

	public void render(Graphics g, float cameraX, float cameraY) {
		// super.render(g, cameraX, cameraY);
		if (invisible < 0) {
			super.render(g, cameraX, cameraY);
			g.drawString(health + "", xFloat - cameraX, yFloat - cameraY - 23);
			g.fillRect(xFloat - cameraX, yFloat - cameraY, health / 20, 3);
		}
	}

	@Override
	public int damage() {
		return 0;
	}
}

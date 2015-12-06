package entities;


import main.Data;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import sidescroller.Animation;
import sidescroller.DamageNumber;
import sidescroller.MyMap;
import sidescroller.StaticImage;

public class Slime extends Entity {
	//time until action changes
	int timeUntilChange = 10;
	int timeUntilJump = 100;
	//is it aggro?
	boolean aggro = false;
	int dirToMove = 1;//can't use direction b/c of turning
	private Entity tempEnt;
	//set moving frames to 4
	public Slime(float x_pos, float y_pos, int ally, MyMap map) {
		super("sprites/Slime", x_pos, y_pos,40,23, ally, map);
		movingFrames = 4;
		xSpeed = 1.5f;
		health = 50;
	}
//ehhh..... decide on something later
	public void render(Graphics g, float cameraX,float cameraY)
	{
		g.drawString(health+"", xFloat-cameraX, yFloat-cameraY-23);
		super.render(g, cameraX, cameraY);
		g.fillRect(xFloat-cameraX, yFloat-cameraY,health, 3);
	}
	public boolean update(int delta) {
		if (!aggro) {
			if (timeUntilChange < 0) {
				dirToMove *= -1;
				timeUntilChange = (int) (Math.random() * 1666) + 166;
			}
			if(timeUntilJump <0){
				timeUntilJump = (int)(Math.random()*6666);
			} else if(timeUntilJump <500){
				tryJump();
			}
			move(dirToMove, delta);
			timeUntilJump-=delta;
			timeUntilChange-=delta;
		}
		return(super.update(delta));
	}
	@Override
	public void getHit(Entity ent) {// cant get hit by same damage in a row.
		if (tempEnt != ent) {
			if (ent.damage() != 0) {//this donet happen when he hits me
				tempEnt = ent;
				health -= ent.damage();
				mapInside.statics.add(new DamageNumber(ent.damage()
						+ "", xFloat, yFloat-20,Data.font, Color.blue));
				float moveTo;
				if (ent.xFloat > xFloat)
					moveTo = xFloat - 5;
				else
					moveTo = xFloat + 5;
				if (canMove(moveTo)) {
					xFloat = moveTo;
				}
			}
		}

	}
	@Override
	public int damage() {
		// TODO Auto-generated method stub
		return 20;
	}
	@Override
	public void die(int i) {
		try {
			mapInside.statics.add(new StaticImage(new Animation(new SpriteSheet("sprites/Explosion_SS.png",40,50)), xFloat, yFloat, 1, 5));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mapInside.entities.remove(i);		
	}

}

package entities;


import java.awt.Font;

import main.Data;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import sidescroller.DamageNumber;
import sidescroller.MyMap;
import skills.ShootSlime;

public class BossSlime extends Entity {
	float scale = 3;
	// time until action changes
	int timeUntilChange = 166;
	int timeUntilJump = 1666;
	// is it aggro?
	boolean aggro = false;
	int dirToMove = 1;// can't use direction b/c of turning
	private boolean charging = false;
	int damageOnTouch = 1;
	private int stunned = 0;
	public boolean invincible = false;
	Entity tempEnt;

	// set moving frames to 4
	public BossSlime(float x_pos, float y_pos, int ally, MyMap map) {
		super("sprites/Slime", x_pos, y_pos,39 , 23, ally, map);
		// image = image.getScaledCopy(scale);
		// setDimentions();
		movingFrames = 4;
		xSpeed = 1.5f;
		health = 2000;
		maxAirTime = 500;
	}

	// ehhh..... decide on something later
	public void render(Graphics g, float cameraX, float cameraY) {
		g.drawString(health + "", xFloat - cameraX, yFloat - cameraY - 23);
		g.fillRect(xFloat - cameraX, yFloat - cameraY, health / 20, 3);
		if (direction == -1)
			anim.flipped = false;
		else
			anim.flipped = true;
		anim.drawScaled(xFloat - cameraX, yFloat - cameraY, scale);
	}

	public boolean update(int delta) {
		height = anim.getImage().getScaledCopy(scale).getHeight();
		width = anim.getImage().getScaledCopy(scale).getWidth();// for
																		// changing
																		// dimentions
		if (!aggro) {
			if (timeUntilChange < 0) {// change movement dir
				charging = false;
				dirToMove *= -1;
				timeUntilChange = (int) (Math.random() * 3500) + 200;
			}
			if (timeUntilChange < delta && width > 20 && height > 20) {
				useSkill(new ShootSlime(this));// shoot a slime
				stunned = 333;
				timeUntilChange-=delta;
				health -= 10;
				scale -= .05;
			}
			if (timeUntilJump < 0) {
				timeUntilJump = (int) (Math.random() * 6666);
			} else if (timeUntilJump < 500) {
				tryJump();// jump
			} else if (timeUntilJump >= 4000) {
				charging = true;// use charge attack
			} else if (timeUntilJump <= 3500) {
				charging = false;// use charge attack
			}
			if (charging) {// specify charge attack
				damageOnTouch = 50;
				xSpeed = 10;
			} else {
				damageOnTouch = 30;
				xSpeed = 1.5f;
			}
			if (stunned > 0) {// specify stunned effects
				stunned-=delta;
				xSpeed = 0;
				invincible = true;
			} else {
				invincible = false;
				timeUntilJump-=delta;
				timeUntilChange-=delta;
			}
			move(dirToMove, delta);// move and check move
		}
		return (super.update(delta));
	}

	@Override
	public void getHit(Entity ent) {// cant get hit by same damage in a row.
		if (!invincible && tempEnt != ent) {
			if (ent.damage() != 0) {// this doesn't happen when he hits me
				tempEnt = ent;
				charging = false;
				stunned = 50;
				health -= ent.damage();
				mapInside.statics.add(new DamageNumber(ent.damage()
						+ "", xFloat, yFloat-20, Data.font, Color.blue));
				float moveTo;
				if (ent.xFloat > xFloat)
					moveTo = xFloat - 10;
				else
					moveTo = xFloat + 10;
				if (canMove(moveTo)) {
					xFloat = moveTo;
				}
			}
		}

	}

	@Override
	public int damage() {
		return damageOnTouch;
	}

	@Override
	public void die(int i) {
		mapInside.entities.remove(i);
	}

}

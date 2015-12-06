package skills;

import entities.Entity;
public class Dash extends Skill{

	public Dash(Entity source) {
		super("Weapon_2", source,70,30);
		//anim.setAction(1,5,6);
		delay = 1000;
		maxTime = 500;
		
		source.invincible = source.invisible = 500;
		yFloat = source.yFloat+source.height/2 - height/2;
		startX = source.xFloat+source.width/2 - width/2;
		update(1);
	}
	public boolean update(int delta){
			if(canMove(time/10*5*direction+startX)){
				source.xFloat = xFloat = time/10*5*direction+startX;
				source.yFloat = yFloat-40;//the -40 is the difference between the players height and this
			}
			else {
				return false;
			}
		//yFloat = source.yFloat+source.height/2 - height/2;
		time+=delta;
		super.updatePos();
		if (time > maxTime || health<=0) {
			return (false);
		}
		return (true);
	}
	public void getHit(Entity damage) {
		//source.invincible = source.invisible = 0;
		//health = 0;test	
	}
	public int damage() {
		return 50;
	}
}

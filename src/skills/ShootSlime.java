package skills;

import entities.Entity;
import entities.Slime;

public class ShootSlime extends Skill{

	public ShootSlime( Entity source) {
		super("sprites/Slime", source,40,23);
		anim.setAction(1, 3, 4);
		delay = 1000;
		maxTime = 2833;
		yFloat = source.yFloat+source.height/2 - height/2;
		startX = source.xFloat+source.width/2 - width/2;
		update(1);
	}
	public boolean update(int delta){
		if(time<2833){
			if(canMove(time/1000*60*5*direction+startX))
				xFloat = time/1000*60*5*direction+startX;
			else {
				return false;
			}
		}
		time+=delta;
		super.updatePos();
		if (time > maxTime || health<=0) {
			return (false);
		}
		return (true);
	}
	public void getHit(Entity damage) {
		health = 0;	
	}
	public int damage() {
		return 50;
	}
	public void die(int i) {
		mapInside.entities.add(new Slime(xFloat,yFloat-10, alliance, mapInside));//+10 for when the slime becomes small
		mapInside.entities.remove(i);		
	}
}

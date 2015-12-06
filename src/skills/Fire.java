package skills;

import entities.Entity;
public class Fire extends Skill{

	public Fire(Entity source) {
		super("sprites/Fire", source,20,20);
		anim.setAction(0, 5, 5);
		delay = 1166;
		maxTime = 1666;
		yFloat = source.yFloat+source.height/2 - height/2;
		startX = source.xFloat+source.width/2 - width/2;
		update(1);
	}
	public boolean update(int delta){
			if(canMove(time/1000*60*5*direction+startX))
				xFloat = time/1000*60*5*direction+startX;
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
		health = 0;	
	}
	public int damage() {
		return 50;
	}
}

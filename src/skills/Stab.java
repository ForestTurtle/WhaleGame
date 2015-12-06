package skills;

import entities.Entity;

public class Stab extends Skill{
	int hits = 3;
	private int attack = 30;
	public Stab(Entity source) {
		super("Weapon_2", source,70,30);
		delay = 583;
		maxTime = 250;
		yFloat -= 20;
		update(1);
	}
	public boolean update(int delta){
		if(time<166){
		xFloat = time/1000*60*7*direction+source.xFloat+source.width/2 - width/2;
		}
		yFloat = source.yFloat+source.height/2 - height/2+5;
		time+=delta;
		super.updatePos();
		if (time > maxTime) {
			return (false);
		}
		return (true);
	}
	@Override
	public void getHit(Entity damage) {
		//hits--;
		if(hits <= 0){
			attack = 0;
		}
	}
	@Override
	public int damage() {
		return attack;
	}
}

package sidescroller;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class DamageNumber extends StaticImage {

	int time;
	TrueTypeFont font;
	Color color;
	public DamageNumber(String str, float x, float y,  TrueTypeFont font, Color c) {
		super(str, x, y, 1);
		this.font = font;
		this.color = c;
	}
	
	public boolean render(Graphics g, float cameraX, float cameraY){
		font.drawString(x-cameraX, y-time-cameraY, str, color);
		//g.drawString(str, x-cameraX, y-time-cameraY);
		time++;
		if(time>50){
			return false;
		}
		return true;
	}
}

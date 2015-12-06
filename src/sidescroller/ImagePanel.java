package sidescroller;
import java.awt.Font;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

//draws everything in arraylists at positions in arraylists and deals with the camera
public class ImagePanel {
	public MyMap currMap;
	public float cameraX = 0, cameraY = 0;
	//the position of the camera relative to 0,0

	//so hits don't lag, needs to be here b/c slick is kind of dumb
	//public TrueTypeFont font;
	//Font awtFont;
	public ImagePanel(){
		//awtFont = new Font("Tahoma", Font.BOLD, 20);
		//font = new TrueTypeFont(awtFont, false);
	}
	public void removeAll() {// clears the images
		currMap.statics.clear();
	}
	public void setMap(MyMap map){
		currMap = map;
	}
	//only the map so far. things that don't have to change
	public void renderStatic(Graphics g) {
		for (int i = 0; i < currMap.statics.size(); i++) {
			if(!currMap.statics.get(i).render(g, cameraX, cameraY)){
				currMap.statics.remove(i);
				i--;
			}
		}
	}
	//the entities
	public void renderEntities(Graphics g) {
		for (int i = 0; i < currMap.entities.size(); i++) {
			currMap.entities.get(i).render(g, cameraX, cameraY);
		}
	}
}

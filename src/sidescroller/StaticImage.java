package sidescroller;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
//those images that don't move along with the camera
public class StaticImage {
	Image img = null;
	float x, y;
	String str;
	int inCamera = 0;
	Animation anim;
	boolean animated = false;
	// moves with camera, 0 is false
	public StaticImage(Animation img, float x, float y , int inCamera, int pause) {
		animated = true;
		anim = img;
		anim.timeUntilChange = pause;
		this.inCamera = inCamera;
		this.x = x;
		this.y = y;
	}

	public StaticImage(Image img, float x, float y , int inCamera) {
		this.img = img;
		this.inCamera = inCamera;
		this.x = x;
		this.y = y;
	}

	public StaticImage(String str, float x, float y,  int inCamera) {
		this.str = str;
		this.inCamera = inCamera;
		this.x = x;
		this.y = y;
	}

	public boolean render(Graphics g, float cameraX, float cameraY) {//if any cahnge in x/y is needed, put it here
		if(animated){
			return(!anim.draw(x - cameraX*inCamera, y - cameraY*inCamera));
		} else {
		if (img != null)
			img.draw(x - cameraX*inCamera, y - cameraY*inCamera);
		if (str != null)
			g.drawString(str, x - cameraX*inCamera, y - cameraY*inCamera);
		}
		return true;
		}
}

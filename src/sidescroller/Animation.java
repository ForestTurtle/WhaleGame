package sidescroller;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Animation {
	SpriteSheet img;
	int row, col;// current row and col
	int timeUntilChange;
	int time = 0;

	int frames;// the number of columns
	public boolean flipped;

	public Animation(SpriteSheet img) {
		this.img = img;
		setAction(0, 0, img.getHorizontalCount());// defaults to idle
	}

	
	public void setAction(int action, int delay, int frames) {// set row position
		if (row != action) {
			time = delay;// ensure update is called
			row = action;
		}
		this.frames = frames;
		timeUntilChange = delay;

	}
	public Image getImage(){
		return(img.getSubImage(col, row));
	}
	// calls update then draws the sprite in specified coords
	public boolean draw(float x, float y) {// returns true id finished a loop
		update();
		if (!flipped)
			img.getSubImage(col, row).draw(x, y);
		else
			img.getSubImage(col, row).getFlippedCopy(true, false).draw(x, y);
		if (col == img.getHorizontalCount() - 1)
			return true;
		return false;
	}
	public boolean drawScaled(float x, float y, float scale) {// returns true id finished a loop
		update();
		if (!flipped)
			img.getSubImage(col, row).getScaledCopy(scale).draw(x, y);
		else
			img.getSubImage(col, row).getScaledCopy(scale).getFlippedCopy(true, false).draw(x, y);
		if (col == img.getHorizontalCount() - 1)
			return true;
		return false;
	}
	// set update position
	private void update() {// sets row and col to correct values.
		if (time >= timeUntilChange) {
			time = 0;
			if (col + 1 < frames)
				col++;
			else
				col = 0;
		} else
			time++;
	}
}

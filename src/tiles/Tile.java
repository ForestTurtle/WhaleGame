package tiles;
import java.awt.Rectangle;

public abstract class Tile extends Rectangle{
	boolean moveable, fall;//can you fall/move through it 
	int image;
	float x, y;
	public Tile(float x, float y, int size, int image, boolean move, boolean fall){
		this.x = x;
		this.y = y;
		super.x = (int)x;
		super.y = (int)y;
		moveable = move;
		this.fall = fall;
		super.height =  super.width = size;
		this.image = image;
	}
}

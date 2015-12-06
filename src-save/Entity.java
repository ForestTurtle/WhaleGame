import java.awt.Rectangle;

//reusable
public class Entity extends Rectangle {// Things that can move around the
										// screen, starting with player one.
	private ImagePanel drawScreen;
	private String name;
	private float speed = 4;
	float xFloat, yFloat;// Position in floats
	private int indexPos;// position in imagePanel
	private int health = 100;
	private int shootTimer = 0;
	private int ally;// who shot the bullet
	public boolean canJump = false, isRising = false;
	private int airTime = 20;
	int jumpTimer = 0;
	float ySpeed = 0;
	int direction = -1;// West
	int timeMoving = 0;
	int animate = -1;

	public float getXFloat() {
		return xFloat;
	}

	public void setX(float x) {
		this.xFloat = x;
	}

	public float getYFloat() {
		return yFloat;
	}

	public void setY(float y) {
		this.yFloat = y;
	}

	public int getAlly() {
		return ally;
	}

	public void setAlly(int ally) {
		this.ally = ally;
	}

	public boolean isCanJump() {
		return canJump;
	}

	public void setCanJump(boolean canJump) {
		this.canJump = canJump;
	}

	public boolean isRising() {
		return isRising;
	}

	public void setJumping(boolean isRising) {
		this.isRising = isRising;
	}

	public int getAirTime() {
		return airTime;
	}

	public void setAirTime(int airTime) {
		this.airTime = airTime;
	}

	public Entity(String name, float x_pos, float y_pos, ImagePanel drawScreen,
			int ally) {
		this.drawScreen = drawScreen;
		this.name = name;
		xFloat = x_pos;
		yFloat = y_pos;
		height = 70;
		width = 40;
		super.x = (int) xFloat;
		super.y = (int) yFloat;
		this.ally = ally;
	}

	public void createImage() {
		indexPos = drawScreen.addImage(name, Math.round(xFloat),
				Math.round(yFloat));
		// drawScreen.getNumImages() - 1;
	}

	public void updateChar() {// only called for chars
		shootTimer--;
		super.x = (int) xFloat;
		super.y = (int) yFloat;
		drawScreen.changePosition(indexPos, Math.round(xFloat),
				Math.round(yFloat));
	}

	public void updateBullet() {// called for bullets
		super.x = (int) xFloat;
		super.y = (int) yFloat;
	}

	public void removeImage() {
		drawScreen.removeImage(indexPos);
	}

	public void move(int direction) {// moving left and right
		if (animate < 0) {
			animate = 4;
			timeMoving++;
			if (timeMoving > 1)
				timeMoving = timeMoving % 1;
			if (timeMoving != 0)
				drawScreen.changeImage("sprites/Char_Move_" + timeMoving + ".png",
						indexPos);
			else
				drawScreen.changeImage("sprites/Char.png", indexPos);
			this.direction = -1;
		} else {
			animate --;
		}
		if (this.direction != direction) {
			drawScreen.flipImage(indexPos);
			this.direction = direction;
		}
		this.xFloat += speed * this.direction;
		drawScreen.repaint();
	}

	public void jump() {
		if (airTime > 0 && isRising) {
			yFloat -= (getAirTime() / 3);
			airTime--;
		} else {
			isRising = false;
		}
	}

	public void fall() {
		jumpTimer = 4;// time until can jump again
		canJump = false;
		yFloat += ySpeed;
		if (ySpeed < 8 && !isRising)
			ySpeed += .4;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int f) {
		// TODO Auto-generated method stub
		health = f;
	}
}

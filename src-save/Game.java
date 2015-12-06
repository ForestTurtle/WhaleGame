//not reusable
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Game extends Thread {// for customizing the game
	private ImagePanel drawScreen;
	private boolean leftIsDown = false, rightIsDown = false, upIsDown = false,
			downIsDown = false, pIsDown = false, zIsDown = false,
			sIsDown = false, aIsDown = false, dIsDown = false,
			ctrIsDown = false, spaceIsDown = false;
	private int state = 1;// menu = 0, battle mode = 1, textMode = 2, startMenu
							// = 3

	MyMap currMap;
	Entity hero;
	int text = 2;// the text file to be read.
	// StartMenu variables
	int startPosition, cursorIndex;

	public void run() {
		long time = 0;
		while (true) {
			time = System.currentTimeMillis();
			switch (state) {
			case 0:
				updateMenu();
				break;
			case 1:
				updateBattle();
				break;
			case 2:
				updateText();
				break;
			case 3:
				updateStartMenu();
				break;
			}
			time = System.currentTimeMillis() - time;
			if (time > 20)
				time = 0;
			try {
				Thread.sleep(20 - time);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void changeState(int state) {
		drawScreen.removeAll();
		this.state = state;
		if (state == 0) {

		} else if (state == 1) {
			currMap = new MyMap(1, drawScreen);
			currMap.createMapImage();
			hero = new Entity("sprites/Char.png", 50, 50, drawScreen, 1);
			hero.createImage();
		} else if (state == 3) {
			drawScreen.addImage("StartMenu_1.jpg", 0, 0);
			cursorIndex = drawScreen.addImage("Char2.png", 150, 185);
			startPosition = 1;
		}
	}

	private void updateStartMenu() {
		// new game, load game
		if (downIsDown) {
			startPosition = 2;
			drawScreen.changePosition(cursorIndex, 150, 225);
		} else if (upIsDown) {
			startPosition = 1;
			drawScreen.changePosition(cursorIndex, 150, 185);
		}
		if (zIsDown) {
			zIsDown = false;
			if (startPosition == 1) {
				text = 1;
				changeState(2);
			} else if (startPosition == 2) {
				text = 2;
				changeState(2);
			}
		}
		drawScreen.repaint();
	}

	private void updateText() {// must end with #end, can use " " to make a
								// new line
		String str;
		int canRead = 10;
		int yPos = 450;
		int backIndex = drawScreen.addImage("Back_1.jpg", 0, 0);
		int picIndex = drawScreen.addImage("pic_0.png", 150, 0);
		drawScreen.addImage("textBox.jpg", 0, 400);
		try {

			BufferedReader inStream = new BufferedReader(new FileReader("text/Text_"
					+ text + ".txt"));
			while ((str = inStream.readLine()) != null) {
				if (str.equals("")) {

					while (!zIsDown || canRead > 0) {
						Thread.sleep(20);
						canRead--;
					}
					yPos = 450;
					drawScreen.removeStrings();
					// str = inStream.readLine();
				} else if (str.equals("#end")) {
					while (!zIsDown || canRead > 0) {
						Thread.sleep(20);
						canRead--;
					}
					break;
				} else if (str.length() == 6
						&& str.substring(0, 5).equals("#back")) {
					drawScreen.changeImage("Back_" + str.substring(5) + ".jpg",
							backIndex);
					// str = inStream.readLine();
				} else if (str.length() == 6
						&& str.substring(0, 5).equals("#pict")) {
					drawScreen.changeImage("Pic_" + str.substring(5) + ".png",
							picIndex);
					// str = inStream.readLine();
				} else {
					drawScreen.addString(str, 150, yPos);
					yPos += 25;
					canRead = 15;
				}
				drawScreen.repaint();
			}
			inStream.close();
			// changeState(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zIsDown = false;
		changeState(3);
		// text++;
	}

	public void updateMenu() {
		drawScreen.repaint();
		if (rightIsDown) {
			changeState(2);
		} else if (leftIsDown) {
			changeState(1);
		}
	}

	// gets arrow keys to move the hero&checks stuff
	public void moveHero() {// calls hero.move, hero.jump, and hero.fall
		if (rightIsDown) {
			hero.move(1);
		} else if (leftIsDown) {
			hero.move(-1);
		}
		if (upIsDown) {
			if (hero.canJump && hero.jumpTimer < 0) {
				hero.isRising = true;
			}
		} else {
			hero.isRising = false;
		}
		// if on the ground
		if (currMap.getPosition((int) hero.getCenterX(), (int) hero.getMaxY())[1] == 1
				&& currMap.getPosition((int) hero.getCenterX(),
						(int) hero.getMaxY() - 8)[1] != 1) {// on the ground
			if (!hero.isRising)// make sure you can't phase though times
								// Infinitely
				hero.setAirTime(20);
			hero.ySpeed = 0;
			hero.canJump = true;
			hero.yFloat = ((int) hero.getMaxY() / 40) * 40 - 70;// land
			hero.jumpTimer--;

		} else {// in the air
			hero.fall();
		}
		hero.jump();
	}

	public void updateBattle()// state 1
	{
		moveHero();
		hero.updateChar();
		drawScreen.repaint();
		if (downIsDown) {
			changeState(0);
			return;
		}
	}

	public void initialize(ImagePanel drawScreen)// Run once at the start of the
													// game
	{
		this.drawScreen = drawScreen;
		changeState(state);
	}

	public void setSpaceIsDown(boolean b) {
		spaceIsDown = b;
	}

	public void setAIsDown(boolean b) {
		aIsDown = b;
	}

	public void setZIsDown(boolean b) {
		zIsDown = b;
	}

	public void setSIsDown(boolean b) {
		sIsDown = b;
	}

	public void setDIsDown(boolean b) {
		dIsDown = b;
	}

	public void setCtrIsDown(boolean b) {
		ctrIsDown = b;
	}

	public void setPIsDown(boolean b) {
		pIsDown = b;
	}

	public void setLeftIsDown(boolean leftIsDown) {
		this.leftIsDown = leftIsDown;
	}

	public void setRightIsDown(boolean rightIsDown) {
		this.rightIsDown = rightIsDown;
	}

	public void setUpIsDown(boolean upIsDown) {
		this.upIsDown = upIsDown;
	}

	public void setDownIsDown(boolean downIsDown) {
		this.downIsDown = downIsDown;
	}
}
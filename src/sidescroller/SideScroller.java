package sidescroller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import main.Data;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import skills.*;
import entities.Player;

public class SideScroller extends BasicGameState {
	// side scroller runner
	// well, background info and stuff
	MyMap currMap;// the abstract map on the screen
	Player hero;// Who the player has control over
	ImagePanel drawScreen;// the actual image on the screen
	final private int ID = 2;// required for slick, state ID
	private boolean paused = false, canPause = true;// pausing the scdescroller
	String menuImage = "PauseMenuStatus.png";
	
	public void init(GameContainer arg0, StateBasedGame arg1)// required for
																// slick
			throws SlickException {
	}

	// creates map and entities
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		drawScreen = new ImagePanel();// create visuals on screen
		currMap = new MyMap(2, drawScreen);// debug
		drawScreen.setMap(currMap);
		currMap.createMapImage();
		hero = new Player(50, 50, 1, currMap);
		currMap.entities.add(hero);
		drawScreen.cameraX = hero.xFloat - 450;
		drawScreen.cameraY = hero.yFloat - 350;
	}

	// does nothing
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		drawScreen.renderStatic(g);
		drawScreen.renderEntities(g);
		if (paused){
			g.drawImage(new Image("PauseMenu.png"), 0, 0);
			g.drawImage(new Image(menuImage), 0, 100);
		}
	}

	// updates entities and skills and get keyboard input for hero
	// delta is in milliseconds (17 at 60fps)
	public void update(GameContainer gc, StateBasedGame parent, int delta)
			throws SlickException {
		Input in = gc.getInput();

		if (in.isKeyDown(Input.KEY_BACK)) {
			if (canPause) {
				paused = !paused;
				canPause = false;
			}
		} else {
			canPause = true;
		}
		if (!paused) {
			moveHero(in, delta);
			updateCamera(delta);
			currMap.update(delta);
			if ((hero.canAct <= 0)) {
				if (in.isKeyDown(Input.KEY_Z)) {
					hero.useSkill(new Stab(hero));
				} else if (in.isKeyDown(Input.KEY_X)) {
					hero.useSkill(new ThrowSpear(hero));
				}
			}
			if (in.isKeyDown(Input.KEY_T)) {
				parent.enterState(4);// debug
			}
		} else {// menu
			updateMenu(in);
		}
	}
	public void updateMenu(Input in){
		if (in.isMousePressed(in.MOUSE_LEFT_BUTTON)) {//TEMPORARY, SWITCH TO ARRAY OF POSITIONS FOR CURSOR(like in the startmenu)
			if (in.getMouseY() < 90){//tab is pressed
				if(in.getMouseX()<185){//Status
				//	System.out.println("STSUA");
					menuImage = "PauseMenuStatus.png";
				} else if(in.getMouseX()<327){//Skills
					menuImage = "PauseMenuSkills.png";
				} else if(in.getMouseX()<535){//Key Bindings
					menuImage = "PauseMenuKeyBindings.png";
				} else {//System Ooptions
					menuImage = "PauseMenuSystemOptions.png";
				}
			}
		}
	}
	// gets the input to move the hero
	public void moveHero(Input in, int delta) {
		if (in.isKeyDown(Input.KEY_C)) {
			hero.xSpeed = 5;
		} else
			hero.xSpeed = 3;
		if (in.isKeyDown(Input.KEY_RIGHT)) {
			hero.move(1, delta);
		} else if (in.isKeyDown(Input.KEY_LEFT)) {
			hero.move(-1, delta);
		} else
			hero.move(0, delta);
		if (in.isKeyDown(Input.KEY_UP)) {
			hero.tryJump();
		} else {
			hero.stopJump();
		}
	}
	// changes the visible screen so that the player is in the center 200x200
	// box
	public void updateCamera(int delta) {
		if (hero.xFloat - (drawScreen.cameraX + 400) > 150) {//too close to edge of screen
			drawScreen.cameraX += hero.xSpeed * delta / 8;
		} else if (hero.xFloat - (drawScreen.cameraX + 400) < -150) {
			drawScreen.cameraX -= hero.xSpeed * delta / 8;
		} else if (hero.xFloat - (drawScreen.cameraX + 400) > 125) {//at edge
			drawScreen.cameraX += hero.xSpeed * delta / 17;
		} else if (hero.xFloat - (drawScreen.cameraX + 400) < -125) {
			drawScreen.cameraX -= hero.xSpeed * delta / 17;
		}
		if (hero.yFloat - (drawScreen.cameraY + 300) > 75) {
			drawScreen.cameraY += hero.ySpeed * delta / 17;
		} else if (hero.yFloat - (drawScreen.cameraY + 300) < -75) {
			drawScreen.cameraY -= (hero.airTime * 60 / 1000 / 3) * delta / 17;
		}
	}

	// leaving the state
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		drawScreen.removeAll();
	}

	public int getID() {
		return ID;
	}

}
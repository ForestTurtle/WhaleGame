package main;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class StartMenu extends BasicGameState{
	final private int ID = 1;
	int canChange;
	float yPos;
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub		
	}
	public void enter(GameContainer arg0, StateBasedGame arg1){
		canChange = 0;
		yPos = 460;
	}
	public void render(GameContainer gc, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		Image back = new Image("StartMenu.jpg");
		back.draw(0,0);		
		Image select = new Image("Cursor.png");
		select = select.getFlippedCopy(true, false);
		select.draw(20,yPos,1);
	}

	public void update(GameContainer gc, StateBasedGame parent, int arg2)
			throws SlickException {
		
		Input in = gc.getInput();
		if(in.isKeyPressed(Input.KEY_UP) && canChange <0){
			yPos = 460;
		} else if(in.isKeyPressed(Input.KEY_DOWN) && canChange <0){
			yPos = 540;
		}
		canChange--;
		if(in.isKeyPressed(Input.KEY_Z)){
			if(yPos == 460){//new Game	
				parent.enterState(4);
			
			} else {//load game
				parent.enterState(4);
			}
		}
	}


	//560, 640
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}

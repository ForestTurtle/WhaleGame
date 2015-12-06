package main;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

//The script for the game... how the game flows and what to go to next.
//probably add loading, too.
//can convert to a level map later
public class Loading extends BasicGameState {
	int[][] mapPos = { { 100, 200 }, { 200, 200 }, { 300, 200 }, { 400, 200 },
			{ 500, 200 } };// array of X and Ys for the cursor to point to
	int currPos = 0;
	int[][] next = { { 1, 1 }, { 3, 1 }, { 3, 2 }, { 2, 2 }, { 4, 0 } };//the information of the next state

	// index = position in story
	// col1: 1 is SM,2 is SS,3 is TR, 4 is Load
	// col2: text/map number
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	public void enter(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer arg0, StateBasedGame parent, Graphics arg2)
			throws SlickException {
		Image back = new Image("WorldMap_1.jpg");
		back.draw(0, 0);
		Image select = new Image("Cursor.png");
		select.draw(mapPos[currPos][0], mapPos[currPos][1], 1);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame parent, int arg2)
			throws SlickException {
		Input in = gc.getInput();
		if (in.isKeyPressed(in.KEY_RIGHT)) {
			if (currPos < Data.unlocked)
				currPos++;
		} else if (in.isKeyPressed(in.KEY_LEFT)) {
			if (currPos > 0)
				currPos--;
		} else if (in.isKeyPressed(in.KEY_Z)){
			Data.text = Data.map = next[currPos][1];
			parent.enterState(next[currPos][0]);
		}
		// Data.text = Data.map = next[Data.storyPosition][1];
		// parent.enterState(next[Data.storyPosition][0]);

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

}

package main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class TextReader extends BasicGameState {
	final private int ID = 3;
	//the current paragraph to be printed
	String paragraph;
	//the text file to read
	int text;
	//counter/timer
	int canRead;
	//for waiting to continue
	boolean paused;
	//images
	Image background;
	Image picture;
	Image textBox;
	//the reader
	BufferedReader inStream;

	//gets text from data and initialized everything to starting values
	public void enter(GameContainer arg0, StateBasedGame arg1) {
		canRead = 20;
		text = Data.text;
		paused = false;
		paragraph = "";
		try {
			background = new Image("textImage/Back_1.jpg");
			picture = new Image("textImage/Pic_0.png");
			textBox = new Image("textImage/textBox.jpg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			inStream = new BufferedReader(new FileReader("text/Text_" + text
					+ ".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
	}

	//draws everything including the strings onto the screen
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		background.draw(0, 0);
		picture.draw(100, 0);
		textBox.draw(0, 400);
		//g.setFont(Font("Hellvatica", Font.BOLD, 20));
		Color temp = g.getColor();
		g.setColor(Color.black);
		g.drawString(paragraph, 150, 450);
		g.setColor(temp);
	}

	//makes the paragraph and checks to pause
	public void update(GameContainer gc, StateBasedGame parent, int delta)
			throws SlickException {
		try {
			Input in = gc.getInput();
			String str;
			if (paused) {
				if (in.isKeyPressed(Input.KEY_Z) && canRead <= 0) {
					paused = false;
					paragraph = "";
				} else
					canRead--;
			} else {
				if ((str = inStream.readLine()) != null) {// as long as there
															// is text
					if (str.equals("") || str.equals("#end")) {
						paused = true;
						// paragraph = "";
						// break;
						// drawScreen.removeStrings();
					} else if (str.length() == 6
							&& str.substring(0, 5).equals("#back")) {
						background = new Image("textImage/Back_" + str.substring(5)
								+ ".jpg");
					} else if (str.length() == 6
							&& str.substring(0, 5).equals("#pict")) {
						picture = new Image("textImage/Pic_" + str.substring(5) + ".png");
					} else {
						paragraph += str + "\n";
						canRead = 15;
					}
				} else {
					inStream.close();
					parent.enterState(4);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}

package main;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import sidescroller.SideScroller;
//next->make an actual exit for the side scrolling(exit entity)(also, exp entity)
//Craft skills with items that are special types experience
//(unlock skills with special experience)
//special types of enemies(ex. bosses)
//menu needs work
//spawning algorithm needs work(might put in data)
public class Whale2D extends StateBasedGame {
	// sets title
	public Whale2D() {
		super("Phallainon");
		
	}
	//uses drawScreen of everything that is affected by a camera
	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g);
		}

	//esc = exit program
	public void update(GameContainer gc, int delta) throws SlickException {
		super.update(gc, delta);
		Input in = gc.getInput();
		if(in.isKeyDown(Input.KEY_ESCAPE)){
			System.exit(0);
		}
			
	}
	//initial setup: sets size to 900x700 and framerate to 60
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Whale2D());
		app.setDisplayMode(800, 600, false);
		app.setTargetFrameRate(60);
		app.setAlwaysRender(true);// keeps drawing game when unfocued
		app.setUpdateOnlyWhenVisible(false);// same as above
		// app.setShowFPS(false);//disables framerate showing
		app.setIcon("Icon.png");//must be 32x32
		app.start(); //starts update and render loops, starting with init states list
	}

	//states
	public void initStatesList(GameContainer app) throws SlickException {
		this.addState(new StartMenu());	
		this.addState(new Loading());
		this.addState(new SideScroller());	
		this.addState(new TextReader());
	}
}
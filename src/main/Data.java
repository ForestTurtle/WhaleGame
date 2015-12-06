package main;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.newdawn.slick.TrueTypeFont;

import skills.Skill;

//The class that stores game data for passage through states
//Also stores saved data

public class Data {
	static Font awtFont = new Font("Tahoma", Font.BOLD, 20);
	public static TrueTypeFont font = new TrueTypeFont(awtFont, false);
	public static int text = 0;// which text to read
	public static int map = 0;// which map to load
	//public static int storyPosition = 0;// where left off in story
	public static int unlocked = 3;//starting at 0, unlocked nodes in the world map
	public static void saveGame() throws IOException {
		FileOutputStream fout;
		try {
			fout = new FileOutputStream("testSave.sav");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(new Data());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void load() throws IOException {
		FileInputStream fis = new FileInputStream(new File("testSave.sav"));
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			Data save = (Data) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ois.close();
	}
}

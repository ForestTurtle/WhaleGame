package sidescroller;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entities.BossSlime;
import entities.Entity;

public class MyMap {
	private int rows; // rows in map
	private int cols; // columns in map
	private int[][][] mapBody;	//theoretical map, [x][y][properties]
	// 0 Grass-0 Water-1 Road-2
	// 1 Can't land on - 0, land on -1
	// 2 Walk through - 0 , can't walk through -1
	//public ImagePanel drawScreen;
	int sideLength = 40;// the length of each tile
	int mapNo;
	public float floorSize = 8;// the no. of pixels down in a block where a character will be considered being on the floor
	public List<Entity> entities = new ArrayList<Entity>();	// list of entities currently in the map
	public List<StaticImage> statics = new ArrayList<StaticImage>();

	int spawnTime = 0;//default debug values
	int maxEntities = 100;
	private Image mapI;//the image that is used to make the map

	// sets text doc into array
	public MyMap(int num, ImagePanel drawScreen) {// creates a new Map
		//.drawScreen = drawScreen;
		mapNo = num;
		try {
			mapI = new Image("maps/map" + mapNo + ".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		setBounds();
		mapBody = new int[rows][cols][3];
		setArray();
	}

	// things that should happen to the map
	// make faster later
	public void update(int delta) {
		spawnEntities(delta);
		updateEntities(delta);

	}
	public void spawnEntities(int delta) {
		if (spawnTime < 0) {
			if (entities.size() < maxEntities) {
				entities.add(new BossSlime((float) (Math.random() * cols * 40),
						(float) (Math.random() * 500), 2, this));
				maxEntities--;
			}
			spawnTime = 1000000;// in milliseconds
		} else
			spawnTime -= delta;
	}
	public void updateEntities(int delta) {	// updates entities
		for (int i = 0; i < entities.size(); i++) {
			for (int j = i; j < entities.size(); j++) {// if i hits j
				if (entities.get(i).alliance != entities.get(j).alliance
						&& entities.get(i).intersects(entities.get(j))) {
					entities.get(i).getHit(entities.get(j));
					entities.get(j).getHit(entities.get(i));
					}
			}

			if (!entities.get(i).update(delta)) {// if they should die
				entities.get(i).die(i);
				i--;
			}
		}
	}

	private void setBounds() {
		rows = mapI.getHeight();
		cols = mapI.getWidth();
	}

	public void createMapImage() {// Sets the map actual image to the background
									// one
		try {
			Image mapImage = new Image(cols * sideLength, rows * sideLength);
			Image back = new Image("maps/map" + mapNo + "_Back.jpg");// the
																	// background,
																	// doesn't
																	// move
			Image fore = new Image("maps/map" + mapNo + "_Fore.png");// testing,
			statics.add(new StaticImage(back, 0, 0, 0));
			statics.add(new StaticImage(fore, 0, 0, 0));
			Graphics g = mapImage.getGraphics();
			for (int j = 0; j < rows; j++) {
				for (int i = 0; i < cols; i++) {
					if (mapBody[j][i][0] != 0) {
						g.drawImage(new Image("tiles/img_" + mapBody[j][i][0]
								+ ".png"), i * sideLength, j * sideLength, null);
					}
				}
			}
			g.flush();// required
			statics.add(new StaticImage(mapImage, 0, 0, 1));
		} catch (SlickException e1) {
			e1.printStackTrace();
		}
	}

	public int[] getPosition(float x, float y) {// gets the array position of an
												// x
		// and y and flips maps to coord. system
		int i = (int) ((x) / sideLength);
		int j = (int) ((y) / sideLength);
		if (i < 0 || i >= cols || j >= rows || j < 0 || x < 0 || y < 0) {
			i = 0;
			j = 0;
			int array[] = { 1, 1, 1 };// a new array with a can't be moved onto
										// property
			return array;
		} else {
			return mapBody[j][i];// flips it into coordinate system
		}
	}

	private void setArray() {
		// Getting pixel color at position x, y (width, height) & making mar array
		Color color = mapI.getColor(0, 0);
		System.out.println("Red colour component = " + color.getRed());
		System.out.println("Green colour component = " + color.getGreen());
		System.out.println("Blue colour component = " + color.getBlue());
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols; i++) {
				color = mapI.getColor(i, j);
				if (color.getRed() == 255) {
					mapBody[j][i][0] = 0;
				} else
					mapBody[j][i][0] = color.getRed();
				if (mapBody[j][i][0] == 1) {
					mapBody[j][i][1] = 1;
					mapBody[j][i][2] = 1;
				} else if (mapBody[j][i][0] == 2) {
					mapBody[j][i][1] = 1;
					mapBody[j][i][2] = 0;
				}
			}
		}
	}

	public void print() {// displays the text file
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols; i++) {
				System.out.print(mapBody[j][i][0] + " ");
			}
			System.out.println();
		}
	}
}
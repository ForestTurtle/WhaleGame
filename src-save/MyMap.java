import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyMap {// The map properties
	private int rows = 20;
	private int cols = 20;
	private int[][][] mapBody = new int[rows][cols][2];
	// Grass-0 Water-1 Road-2
	ImagePanel drawScreen;
	int sideLength = 40;
	
	public MyMap(int num, ImagePanel drawScreen) {
		// mapNo = num;
		this.drawScreen = drawScreen;
		setArray(num);
	}

	public void createMapImage() {//adds the map images to the imageplanel
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols; i++) {
				drawScreen.addImage("maps/img_" + mapBody[i][j][0] + ".jpg", i * sideLength,
						j * sideLength);
			}
		}
	}

	public int[] getPosition(int x, int y) {//gets the array position of an x and y
		int i = x / sideLength;
		int j = y / sideLength;
		if (i < 0 || i >= rows || j >= cols || j < 0) {
			i = 0;
			j = 0;
			int array[] = { 1, 1 };// a new array with a can't be moved onto
									// property
			return array;
		} else {
			return mapBody[i][j];
		}
	}

	private void setArray(int mapNo) {//sets txt doc into array
		File map = new File("map_" + mapNo + ".txt");
		try {
			Scanner rowScanner = new Scanner(map).useDelimiter("\n");
			for (int j = 0; j < rows; j++) {
				String row = rowScanner.nextLine();
				Scanner colScanner = new Scanner(row).useDelimiter(",");
				for (int i = 0; i < cols; i++) {
					String element = colScanner.next();
					mapBody[i][j][0] = Integer.parseInt(element);
					if (mapBody[i][j][0] == 1) {
						mapBody[i][j][1] = 1;
					}
				}
				colScanner.close();
			}
			rowScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void print() {
		// TODO Auto-generated method stub
		for(int i = 0; i<cols; i++){
			System.out.print(mapBody[i][0][0]);
		}
	}
}
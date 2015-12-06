//reusable
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
	private List<Integer> x_loc = new ArrayList<Integer>();
	private List<Integer> y_loc = new ArrayList<Integer>();
	private int numImages = 0;
	private List<BufferedImage> buffImages = new ArrayList<BufferedImage>();
	private List<Double> rotations = new ArrayList<Double>();
	private List<Integer> stringX = new ArrayList<Integer>();
	private List<Integer> stringY = new ArrayList<Integer>();
	private int numStrings = 0;
	private List<String> strings = new ArrayList<String>();
	private List<Font> fonts = new ArrayList<Font>();

	// Font font = new Font("Arial", Font.PLAIN, 15);
	
	public void flipImage(int index){
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-buffImages.get(index).getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		buffImages.set(index, op.filter(buffImages.get(index), null));
	}
	public int addImage(String imageName, int x, int y) {// adds one more image to be drawn
		try {
			x_loc.add(x);
			y_loc.add(y);
			rotations.add((double) 0);
			buffImages.add(ImageIO.read(new File(imageName)));
			numImages++;
		} catch (IOException e) {
		}
		return(buffImages.size()-1);
	}

	public int addString(String str, int x, int y) {// adds one more image to be drawn
		stringX.add(x);
		stringY.add(y);
		strings.add(str);
		fonts.add(new Font("Hellvatica", Font.BOLD, 20));
		numStrings++;
		return(strings.size()-1);
	}

	public void addString(String str, int x, int y, Font font) {
		stringX.add(x);
		stringY.add(y);
		strings.add(str);
		fonts.add(font);
		numStrings++;
	}

	public void changeImage(String imageName, int index) {
		try {
			buffImages.add(index, ImageIO.read(new File(imageName)));
			buffImages.remove(index+1);
		} catch (IOException e) {
		}
	}

	public void changePosition(int index, int x, int y) {
		x_loc.set(index, x);
		y_loc.set(index, y);
	}

	public void changeAngle(int index, double ang) {
		rotations.set(index, ang);
	}

	public void removeImage(int i) {
		numImages--;
		buffImages.remove(i);
		x_loc.remove(i);
		y_loc.remove(i);
		rotations.remove(i);
	}

	public void removeString(int i) {
		numStrings--;
		strings.remove(i);
		stringX.remove(i);
		stringY.remove(i);
		fonts.remove(i);
	}

	public void removeAll() {// clears the images
		numImages = 0;
		numStrings = 0;
		buffImages.clear();
		x_loc.clear();
		y_loc.clear();
		rotations.clear();
		strings.clear();
		stringX.clear();
		stringY.clear();
		fonts.clear();
	}
	public void removeStrings() {// clears the Strings
		numStrings = 0;
		strings.clear();
		stringX.clear();
		stringY.clear();
		fonts.clear();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < numImages; i++) {
			g2.rotate(rotations.get(i), x_loc.get(i) + 10, y_loc.get(i) + 10);
			g2.drawImage(buffImages.get(i), x_loc.get(i), y_loc.get(i), null);
			g2.rotate(-rotations.get(i), x_loc.get(i) + 10, y_loc.get(i) + 10);
		}
		//g2.setColor((Color.ORANGE));
		for (int j = 0; j < numStrings; j++) {
			g2.setFont(fonts.get(j));
			g2.drawString(strings.get(j), stringX.get(j), stringY.get(j));
		}
	}

	public int getNumImages() {
		return numImages;
	}

	public int getNumStrings() {
		return numStrings;
	}
}

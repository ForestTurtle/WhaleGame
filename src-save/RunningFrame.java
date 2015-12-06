//reusable, do not edit
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;

public class RunningFrame extends JFrame {
	private int state = 0;
	private ImagePanel drawScreen = new ImagePanel();

	public RunningFrame(String title) {// makes maximized window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(title);
		this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setResizable(false);
		this.setAutoRequestFocus(true);
		this.setLocation(50,50);
		getContentPane().add(drawScreen);
		
		JMenuBar bar = new JMenuBar();
		JProgressBar loadingBar = new JProgressBar();
		loadingBar.setIndeterminate(true);
		this.setJMenuBar(bar);
		JMenu file = new JMenu("File");
		bar.add(file);
		bar.add(loadingBar);
		JMenuItem close = new JMenuItem("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		file.add(close);
		
	}

	public RunningFrame(String title, int width, int height) {
		this(title);
		this.setSize(width, height);
	}

	public void startRun(Controls controls, Game game) {// the loop
		this.addKeyListener(controls);
		game.initialize(drawScreen);
		this.validate();
		game.start();

	}
	
}
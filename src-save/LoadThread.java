import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;


public class LoadThread extends Thread{
RunningFrame frame;
	public LoadThread(RunningFrame frame){
		this.frame = frame;
	}
	public void run() {

		JMenuBar bar = new JMenuBar();
		JProgressBar loadingBar = new JProgressBar();
		frame.setJMenuBar(bar);
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
		int timeCount = 0;
		long time = 0;;
		while(true){
			if (time > 53){
				loadingBar.setIndeterminate(true);
				timeCount -= 3;
			} else {
				timeCount++;
				if(timeCount>10){
					loadingBar.setIndeterminate(false);
					timeCount = 10;
				}
			}
			time = System.currentTimeMillis();
			try {
				Thread.sleep(50);
				time = System.currentTimeMillis()-time;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

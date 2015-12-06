
public class testThread extends Thread{
	ImagePanel draw;
	
	public testThread(ImagePanel draw){
		this.draw = draw;
	}
	public void run(){
		for(int i = 0;i<700;i++){
			draw.addImage("img_0.jpg",0,i);
			draw.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void selectionSort1() {
		int[] x = new int[100000];
		for(int i = 0; i<x.length;i++)
			x[i] = (int) (Math.random()*10000);
			
	    for (int i=0; i<x.length-1; i++) {
	        for (int j=i+1; j<x.length; j++) {
	            if (x[i] > x[j]) {
	                //... Exchange elements
	                int temp = x[i];
	                x[i] = x[j];
	                x[j] = temp;
	            }
	        }
	    }
	}
}

package desktopApp;

import javax.swing.JCheckBox;

public class Alive implements Runnable{
	
	 private volatile boolean isRunning = true;
	 JCheckBox bx =null;
	 Alive(JCheckBox bx){this.bx=bx;}
	
	
	@Override
	public void run() {
		int delay=1000*60*5;
		int tries=0;
		isRunning = true;
		
		while(isRunning && tries++<3) {
		// make API Call
			
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		}
		
		bx.setSelected(false);
		
	};

	
	
	public void stopCall() {this.isRunning=false;}
	

}

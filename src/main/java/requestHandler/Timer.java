package requestHandler;

import desktopApp.RoundButton;

import java.awt.*;

public class Timer implements Runnable{
	// this class takes care of the keep api alive button
	RoundButton element=null;
	public Timer(RoundButton element){
		this.element=element;
	}
	public volatile boolean allowTimer=true;
	public void setAllowTimer(boolean x) {allowTimer=x;}
	
	
	@Override
	public void run() {
		String ans="";
		while(allowTimer && !ans.equals("Api is alive")){
			try {
				ans=Calls.makePingRequest();
				}catch(Exception e){
				System.out.println("Thread.fail");

			}
		}
		if(ans.equals("Api is alive")){
			element.setBackground(new Color(53, 240, 122));
		}else{element.setBackground(Color.RED);}
		System.out.println(ans);
		int tries=1;
		while(allowTimer) {
			if(tries%200==0){
			try {
			System.out.println(Calls.makePingRequest());}catch(Exception e){
				System.out.println("Thread.fail");
				
			}}
			tries++;
			try{
				Thread.sleep(1000);}catch (Exception e){System.out.println("Thread sleep 2");}


		}
		if(!allowTimer){
			try{Calls.makePingRequest();
			System.out.println("Last call");}catch(Exception e){
				System.out.println("Last call failed");
			}
		}
		System.out.println("Deactivated");
	}
	
	
}	
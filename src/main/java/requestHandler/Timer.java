package requestHandler;

public class Timer implements Runnable{
	
	
	public volatile boolean allowTimer=true;
	public void setAllowTimer(boolean x) {allowTimer=x;}
	
	
	@Override
	public void run() {
		while(allowTimer) {
			try {
			Calls.makePingRequest();Thread.sleep(1000*3);}catch(Exception e){
				System.out.println("Thread.fail");
				
			}
		}
		System.out.println("Deactivated");
	}
	
	
}	
package de.breakout.prototype;

public class Logic implements Runnable{
	
	Prototype proto;
	private boolean run;

	public Logic(Prototype prototype){
		this.proto = prototype;
	}
	public void run() {
		while(run){
		proto.moveBall();
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		// TODO Auto-generated method stub
		
	}
	
	public void setRun(boolean run){
		this.run = run;
	}

}

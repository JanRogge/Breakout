package de.breakout.prototype;

public class Logic implements Runnable{
	
	Prototype proto;

	public Logic(Prototype prototype){
		this.proto = prototype;
	}
	public void run() {
		while(true){
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

}

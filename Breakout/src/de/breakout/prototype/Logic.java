package de.breakout.prototype;

public class Logic implements Runnable{
	
	Prototype proto;
	private boolean run;
	Ball ball;

	public Logic(Prototype prototype){
		this.proto = prototype;
		ball = new Ball(this);
	}
	public void run() {
		while(run){
			//System.out.println(proto.getPaddleX());
		if(ball.move(proto.getBlocks(),proto.getPaddleX())){
			proto.decreaseLive();
			proto.testmove(1000, 1000);
			ball = new Ball(this);
			run = false;
		}
		proto.testmove(ball.getX(), ball.getY());
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
	public boolean getRun(){
		return run;
	}
	public void blockhit(int column, int row){
		proto.removeBlock(column, row);
	}

}

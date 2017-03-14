package de.breakout.prototype;


import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Prototype extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5517963883061423236L;
	private Image img = null;
	private JLabel ball;
	private JLabel paddle;
	private int z = 1;
	private int y = 1;
	private Block[][] labellist;
	public static final int HEIGHT = 30;
	public static final int WIDHT = 50;
	public static final int SPACE = 0;
	/**
	 * Create the Panel.
	 */
	public Prototype() {
		
		setSize(800, 600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		paddle = new JLabel("");
		paddle.setOpaque(true);
		paddle.setBackground(Color.BLACK);
		paddle.setBounds(350, 570, 130, 25);
		ImageIcon imageForOne = new ImageIcon("images/stick.png");
		paddle.setIcon(imageForOne);
		add(paddle);
		
		ball = new JLabel("");
		ball.setOpaque(true);
		ball.setBackground(Color.BLACK);
		ball.setBounds(350, 500, 25, 25);
		ImageIcon imageForTwo = new ImageIcon("images/ball.png");
		ball.setIcon(imageForTwo);
		add(ball);
		
		try {
			ImageIcon u = new ImageIcon("images/background.png");
			img = u.getImage();
		} catch (Exception e) {
			System.out
					.println("<ERROR> Kein Bild f�r diese Aktion vorhanden!!!");
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D f2 = (Graphics2D) g;
		f2.drawImage(img, 0, 0, null);
		paintChildren(g);
		g.setColor(Color.RED);
	}
	
	public void moveBall(){
			ball.setLocation((int) ball.getLocation().getX()-y, (int) ball.getLocation().getY());
			if(hit()){
				y=y*(-1);
			} else if(hit() && ball.getLocation().getX() == 0){
				y=y*(-1);
			}
			repaint();
			revalidate();
			
	}
	/**
	 * 
	 * @param direction true = left
	 * 					false = right
	 */
	public void movePaddle(boolean direction){
		if(direction)
			paddle.setLocation((int) paddle.getLocation().getX()-1, (int) paddle.getLocation().getY());
		else
			paddle.setLocation((int) paddle.getLocation().getX()+1, (int) paddle.getLocation().getY());
	}
	public void removeBlock(int column, int row){
		remove(labellist[row][column]);
		repaint();
		revalidate();
		//call block destoryed
	}
	public void gameende(){
		JOptionPane.showMessageDialog(null, "Ende", "You lose!", JOptionPane.ERROR_MESSAGE);
	}
	public boolean hit(){
		//Paddle hit
		if(ball.getLocation().getY()+1+ball.getHeight() == paddle.getLocation().getY() && 
				(ball.getLocation().getX() >= paddle.getLocation().getX() && ball.getLocation().getX()+ball.getWidth() <= paddle.getLocation().getX()+paddle.getWidth())){
			return true;
		}
		//Block hit
		if(ball.getLocation().getY() < 325){
			for(int y = 1;y <=labellist.length;y++){
				for(int x = 1;x <=labellist[y-1].length;x++){
					if(labellist[y-1][x-1] != null){
						if(ball.getLocation().getY() == labellist[y-1][x-1].getY()+labellist[y-1][x-1].getHeight() &&
							(ball.getLocation().getX() >= labellist[y-1][x-1].getLocation().getX() && ball.getLocation().getX()+ball.getWidth() <= labellist[y-1][x-1].getLocation().getX()+labellist[y-1][x-1].getWidth())){
							System.out.println("hit");
							if(labellist[y-1][x-1].getHealth() > 1){
								labellist[y-1][x-1].reduceHealth();
							} else{
								removeBlock(x-1, y-1);
								labellist[y-1][x-1]=null;
							}
							
							return true;
						}
					}
					
				}
			}
				
		} else if(ball.getLocation().getY() == 600){
			gameende();
		}
		//Wall hit
		if(ball.getLocation().getY() == 0){
			return true;
		}
		if(ball.getLocation().getX() == 0.0){
			System.out.println("hey");
			return true;
		}
		if(ball.getLocation().getX()+ball.getWidth() == 800){
			return true;
		}
		return false;
	}
	public void generateBlocks(int[][] list){
		labellist = new Block[10][16];
		for(int y = 1;y <=list.length;y++){
			for(int x = 1;x <=list[y-1].length;x++){
				if(list[y-1][x-1] == 1){
					labellist[y-1][x-1] = new Block(x,y,1);
					add(labellist[y-1][x-1]);
				} else if(list[y-1][x-1] == 2){
					labellist[y-1][x-1] = new Block(x,y,2);
					add(labellist[y-1][x-1]);
				} else if(list[y-1][x-1] == 3){
					labellist[y-1][x-1] = new Block(x,y,3);
					add(labellist[y-1][x-1]);
				} else if(list[y-1][x-1] == 4){
					labellist[y-1][x-1] = new Block(x,y,4);
					add(labellist[y-1][x-1]);
				} 
			}
		}
		repaint();
		revalidate();
	}
	public void switchBack(){
		((MainFrame) this.getParent().getParent().getParent()).switchPanel();
	}
}

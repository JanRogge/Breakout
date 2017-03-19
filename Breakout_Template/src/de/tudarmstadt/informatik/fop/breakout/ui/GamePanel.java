package de.tudarmstadt.informatik.fop.breakout.ui;


import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import de.tudarmstadt.informatik.fop.breakout.logic.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * Anzeige des Spiels
 * 
 * @author Jan Rogge
 *
 */
public class GamePanel extends JPanel {

	private static final long serialVersionUID = -5517963883061423236L;
	private Image img = null;
	private JLabel ball;
	private JLabel paddle;
	private JLabel blocksdestoryed;
	private JLabel live;
	private int counter = 0;
	private int lives = 3;
	private Block[][] labellist;
	public static final int HEIGHT = 30;
	public static final int WIDHT = 50;
	public static final int SPACE = 0;
	/**
	 * Create the Panel.
	 */
	public GamePanel() {
		
		setSize(800, 600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		ball = new JLabel("");
		ball.setOpaque(false);
		ball.setBackground(Color.BLACK);
		ball.setBounds(388, 540, 25, 25);
		ImageIcon imageForTwo = new ImageIcon("images/ball.png");
		ball.setIcon(imageForTwo);
		add(ball);
		
		blocksdestoryed = new JLabel("0 Bloecke zerstoert");
		blocksdestoryed.setForeground(Color.WHITE);
		blocksdestoryed.setFont(new Font("Arial", Font.PLAIN, 20));
		blocksdestoryed.setBounds(5, 520, 180, 20);
		add(blocksdestoryed);
		
		live = new JLabel("3 Leben");
		live.setForeground(Color.WHITE);
		live.setFont(new Font("Arial", Font.PLAIN, 20));
		live.setBounds(5, 550, 180, 20);
		add(live);
		
		
		
		try {
			ImageIcon u = new ImageIcon("images/background.png");
			img = u.getImage();
		} catch (Exception e) {
			System.out
					.println("<ERROR> Kein Bild fuer diese Aktion vorhanden!!!");
		}
	}
	
	/**
	 * Zeigt den Spieler auf dem panel an 
	 * @param player Player Object(JLabel)
	 */
	public void showPlayer(Player player){
		if(paddle != null){
			remove(paddle);
		}
		paddle = player;
		add(paddle);
	}
	
	/**
	 * Fuegt dem Counter der zerstörten Bloecke eins zu
	 */
	public void addCounter(){
		counter ++;
		blocksdestoryed.setText(counter + " Bloecke zerstoert");
	}
	
	/**
	 * Setzt die Anzeige der Leben auf die uebergebene Anzahk
	 * @param lives Leben des Spielers
	 */
	public void livecounter(int lives){
		live.setText(lives + " Leben");
	}
	
	/**
	 * Neue Paint methode die ein Hintergrundbild einfuegt
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D f2 = (Graphics2D) g;
		f2.drawImage(img, 0, 0, null);
		paintChildren(g);
		g.setColor(Color.RED);
	}
	
	/**
	 * Bewegt den Ball an die uebergebene Position
	 * @param x X-Position des Balls
	 * @param y Y-Position des Balls
	 */
	public void moveBall(int x, int y){
		ball.setLocation(x, y);
		repaint();
		revalidate();
	}
	/**
	 * Bewegt die schlaeger in die uebergebene richtung
	 * 
	 * @param direction true = left
	 * 					false = right
	 */
	public void movePaddle(boolean direction){
		if(direction && paddle.getLocation().getX()-5 > 0)
			paddle.setLocation((int) paddle.getLocation().getX()-5, (int) paddle.getLocation().getY());
		else if(!direction && paddle.getLocation().getX()+5 < 670)
			paddle.setLocation((int) paddle.getLocation().getX()+5, (int) paddle.getLocation().getY());
	}
	/**
	 * Entfernt einen Block vom Panel
	 * 
	 * @param column X-Position des Blocks im Array
	 * @param row Y-Position des Blocks im Array
	 */
	public void removeBlock(int column, int row){
		remove(labellist[row][column]);
		labellist[row][column] = null; //NEU
		repaint();
		revalidate();
		addCounter();
	}
	
	/**
	 * Zeigt eine End Nachricht mit der Informationen ob der Spieler gewonnen(win = true) oder verloren(win = false) hat
	 * Das Panel wird zum Menu Panel gewechselt
	 * @param win boolean ob der Spieler gewonnen hat
	 */
	public void gameende(boolean win){
		
		if(win){
			JOptionPane.showMessageDialog(null, "Ende", "You win!", JOptionPane.INFORMATION_MESSAGE);
		} else{
			JOptionPane.showMessageDialog(null, "Ende", "You lose!", JOptionPane.ERROR_MESSAGE);
		}
		((MainFrame) this.getParent().getParent().getParent()).setfirstGame(true);;
		switchBack();
	}
	
	/**
	 * Fuegt die Liste der Bloecke zum Spielfeld hinzu
	 * @param list
	 */
	public void generateBlocks(Block[][] list){
		labellist = list;
		for(int y = 1;y <=list.length;y++){
			for(int x = 1;x <=list[y-1].length;x++){
				if(labellist[y-1][x-1] != null){
					add(labellist[y-1][x-1]);
				}
					
			} 
		}
		repaint();
		revalidate();
	}
	
	/**
	 * Wechselt das Panel zum Menupanel
	 */
	public void switchBack(){
		((MainFrame) this.getParent().getParent().getParent()).switchPanel();
	}
	
	/**
	 * Gibt die Liste an Bloecken zurueck
	 * @return labelList liste an Bloecken
	 */
	public Block[][] getBlocks(){
		return labellist;
		
	}
	
	/**
	 * Setzt die anzeige für ein neues Spiel zueurck
	 */
	public void newGame(){
		lives = 3;
		counter = 0;
		live.setText(lives + " Leben");
		blocksdestoryed.setText(counter + " Bloecke zerstoert");
		clear();
	}
	
	/**
	 * Loescht alle Bloecke vom Spielfeld
	 */
	public void clear(){
		if(labellist != null){
			for(int y = 1;y <=labellist.length;y++){
				for(int x = 1;x <=labellist[y-1].length;x++){
					if(labellist[y-1][x-1] != null){
						remove(labellist[y-1][x-1]);
					}
				}
			}
		}
		
	}
}

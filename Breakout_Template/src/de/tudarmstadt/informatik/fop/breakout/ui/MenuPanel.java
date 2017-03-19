package de.tudarmstadt.informatik.fop.breakout.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Anzeige des Menues
 * 
 * @author Jan Rogge
 *
 */
public class MenuPanel extends JPanel{

	
	private static final long serialVersionUID = -1194014926350278896L;
	private Image img = null;
	
	/**
	 * Create the Panel.
	 * 
	 * Buttons:
	 * New Game, Resume und Exit mit Funktionen
	 * 
	 * Laedt alle Bilder
	 * 
	 */
	public MenuPanel(){
		setSize(800, 600);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(null);
	
		
		ImageIcon imageForOne = new ImageIcon("images/entry.png");
		
		
		JButton start = new JButton("New Game", imageForOne);
		start.setHorizontalTextPosition(SwingConstants.CENTER);
		start.setBounds(40, 130, 360, 95);
		start.setForeground(Color.WHITE);
		start.setFont(new Font("Arial", Font.PLAIN, 40));
		start.setOpaque(false);
		start.setFocusPainted(false);
		start.setBorderPainted(false);
		start.addActionListener(e ->{
			((MainFrame) this.getParent().getParent().getParent()).newGame();
			((MainFrame) this.getParent().getParent().getParent()).switchPanel();
		});
		start.setContentAreaFilled(false);
		start.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		this.add(start);
		
		JButton play = new JButton("Resume", imageForOne);
		play.setHorizontalTextPosition(SwingConstants.CENTER);
		play.setBounds(40, 230, 360, 95);
		play.setForeground(Color.WHITE);
		play.setFont(new Font("Arial", Font.PLAIN, 40));
		play.setOpaque(false);
		play.setFocusPainted(false);
		play.setBorderPainted(false);
		play.addActionListener(e ->{
			if(((MainFrame) this.getParent().getParent().getParent()).getfirstGame()){
				((MainFrame) this.getParent().getParent().getParent()).newGame();
			}
			((MainFrame) this.getParent().getParent().getParent()).switchPanel();
			
		});
		play.setContentAreaFilled(false);
		play.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		this.add(play);
		
		JButton close = new JButton("Exit", imageForOne);
		close.setHorizontalTextPosition(SwingConstants.CENTER);
		close.setBounds(40, 480, 360, 95);
		close.setForeground(Color.WHITE);
		close.setFont(new Font("Arial", Font.PLAIN, 40));
		close.setOpaque(false);
		close.setFocusPainted(false);
		close.setBorderPainted(false);
		close.addActionListener(e ->{
			System.exit(0);
		});
		close.setContentAreaFilled(false);
		close.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		this.add(close);
		
		try {
			ImageIcon u = new ImageIcon("images/menu.png");
			img = u.getImage();
		} catch (Exception e) {
			System.out
					.println("<ERROR> Kein Bild f�r diese Aktion vorhanden!!!");
		}
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
}

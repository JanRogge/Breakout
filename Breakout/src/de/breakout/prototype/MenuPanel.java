package de.breakout.prototype;

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

public class MenuPanel extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1194014926350278896L;
	private Image img = null;
	
	
	public MenuPanel(){
		setSize(800, 600);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(null);
	
		
		ImageIcon imageForOne = new ImageIcon("images/entry.png");
		
		
		JButton test = new JButton("Start", imageForOne);
		test.setHorizontalTextPosition(SwingConstants.CENTER);
		test.setBounds(40, 150, 360, 95);
		test.setForeground(Color.WHITE);
		test.setFont(new Font("Arial", Font.PLAIN, 40));
		test.setOpaque(false);
		test.setFocusPainted(false);
		test.setBorderPainted(false);
		test.addActionListener(e ->{
			((MainFrame) this.getParent().getParent().getParent()).switchPanel();
		});
		test.setContentAreaFilled(false);
		test.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		this.add(test);
		
		try {
			ImageIcon u = new ImageIcon("images/menu.png");
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
}

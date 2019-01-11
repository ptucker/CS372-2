import java.awt.*; 
import java.awt.event.*;
import java.net.URL;

import javax.swing.*; 

public class Conversion implements MouseMotionListener {
	JFrame frame; 
	JLayeredPane pane; 
	Point diff; 
	JLabel label; 
	
	Conversion() {
		initialize(); 
	}
	
	private void initialize() {
		// creating frame 
		frame = new JFrame(); 
		frame.setBounds(0, 0, 1200, 331);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create layered pane
		pane = new JLayeredPane(); 
		pane.addMouseMotionListener(this);
		
		createBackground(); 
		
		// creating jlabel 
		label = new JLabel(); 
		label.setBounds(30, 50, 90, 30);
		label.setOpaque(true);
		label.setBackground(Color.orange);
		label.setText("Hello World");
		pane.add(label, new Integer(2)); 
		
		// adding 
		frame.add(pane); 
		frame.setVisible(true);
	}
	
	private void createBackground() {
		createIcon("/resources/cityHallresize.png", -30, -10, 331, 331, 1); 
		createIcon("/resources/bg-middle.jpg", 240, 0, 650, 331, 1); 
		createIcon("/resources/school.jpg", 830, 0, 441, 331, 1);
	}
	
	private void createIcon(String loc, int x, int y, int width, int height, Integer layer) {
		try {
			URL resource = getClass().getResource(loc); 
			ImageIcon img = new ImageIcon(resource); 
			JLabel bgImg = new JLabel(img); 
			bgImg.setBounds(x, y, width, height); 
			pane.add(bgImg, layer);
		} catch (NullPointerException e) {
			System.out.println("Obtaining the URL of the file at " + loc + " failed");
			e.printStackTrace();
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		System.out.println("dragging");
		JLabel l = null; 
		if (label.getBounds().contains(e.getPoint())) {
			l = label; 
		}
		if (l != null) {
			if (diff == null) {
				diff = new Point(e.getX() - l.getBounds().x, e.getY() - l.getBounds().y); 
			}
			l.setBounds(e.getX() - diff.x, e.getY() - diff.y, l.getBounds().width, l.getBounds().height);
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		diff = null; 
	}
}

package utils;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class RotateLabel extends JLabel{
	private int degree;
	private int height = 100;
	
	public RotateLabel(int degree, int width){
		this.degree = degree;  
		this.setPreferredSize(new Dimension(width, height));
	}
	@Override
	public void paintComponent(Graphics g) {
	    Graphics2D g2 = (Graphics2D) g;
	    int x = this.getWidth()/2;
	    int y = this.getHeight()/2;
	    g2.rotate(Math.toRadians(degree), x, y);
	    super.paintComponent(g2);    
	    setSize(100, 100);
	}
}

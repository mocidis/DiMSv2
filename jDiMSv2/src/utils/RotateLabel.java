package utils;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class RotateLabel extends JLabel{
	private int degree;
<<<<<<< HEAD
	private int height = (int)(0.1302*ScreenSpecification.MONITOR_HEIGHT);
	private int afterSize = (int)(0.1302*ScreenSpecification.MONITOR_HEIGHT);
=======
	private int height = 100;
	
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
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
<<<<<<< HEAD
	    setSize(afterSize, afterSize);
=======
	    setSize(100, 100);
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	}
}

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import utils.ColorBG;
<<<<<<< HEAD
import utils.ScreenSpecification;

public class PanelTopLogin extends PanelTop {
	private double height = 0.4*ScreenSpecification.MONITOR_HEIGHT;
	public PanelTopLogin() {
		JLabel logo = new JLabel(new ImageIcon(new ImageIcon(this.getClass().getResource("/logo.png")).getImage().getScaledInstance((int)(0.3*ScreenSpecification.MONITOR_WIDTH), (int)(height/2), Image.SCALE_SMOOTH)));
//		JLabel logo = new JLabel(new ImageIcon(new ImageIcon("icon/logo.png").getImage().getScaledInstance((int)(0.3*ScreenSpecification.MONITOR_WIDTH), (int)(height/2), Image.SCALE_SMOOTH)));
		this.setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(JFrame.MAXIMIZED_HORIZ, (int)height));
		//Them logo vao top
		this.add(logo, BorderLayout.CENTER);
=======

public class PanelTopLogin extends PanelTop {
	private int height = 300;
	public PanelTopLogin(){
//		JLabel logo = new JLabel(new ImageIcon(new ImageIcon(this.getClass().getResource("/logo.png")).getImage().getScaledInstance(400, 150, Image.SCALE_SMOOTH)));
		JLabel logo = new JLabel(new ImageIcon(new ImageIcon("icon/logo.png").getImage().getScaledInstance(400, 150, Image.SCALE_SMOOTH)));
		this.setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(JFrame.MAXIMIZED_HORIZ, height));
		//Them logo vao top
		this.add(logo, BorderLayout.CENTER);

>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	}
}

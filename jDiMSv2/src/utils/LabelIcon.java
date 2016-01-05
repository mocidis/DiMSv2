package utils;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LabelIcon extends JLabel {
	private String rootURL = "/";
	public LabelIcon(String imageName, int width, int height) {
		this.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(rootURL+imageName)).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}
}

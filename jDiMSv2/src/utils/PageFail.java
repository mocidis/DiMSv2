package utils;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PageFail extends JPanel {
	public PageFail() {
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		JLabel failLabel = new JLabel("Nothing to show");
		this.add(failLabel);
	}
}

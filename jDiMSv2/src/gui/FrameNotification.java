package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class FrameNotification{
	public FrameNotification(String status) {
		JOptionPane container = new JOptionPane();
		JLabel content = new JLabel("<html><body style = 'width: 400 px'>"+status+"</body></html>");
		JOptionPane.showMessageDialog(null, content);
	}
}

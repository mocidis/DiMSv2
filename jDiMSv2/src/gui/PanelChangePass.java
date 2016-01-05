package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import events.ActionChangePass;
import utils.ColorBG;
import utils.ScreenSpecification;

public class PanelChangePass extends JPanel {
	private ActionChangePass listener = new ActionChangePass();
	private Dimension box = new Dimension((int)(0.44*ScreenSpecification.MONITOR_WIDTH), (int)(0.44*ScreenSpecification.MONITOR_WIDTH));
	private int titleSize = (int)(0.012*ScreenSpecification.MONITOR_WIDTH);
	private int field_width = (int)(0.015*ScreenSpecification.MONITOR_WIDTH);
	private int submit_width = (int)(0.066*ScreenSpecification.MONITOR_WIDTH);
	public PanelChangePass(){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		JPanel box = new JPanel(new BorderLayout());
		
		JPanel smallBox = new JPanel();
		smallBox.setLayout(new GridBagLayout());
		smallBox.setPreferredSize(this.box);
		smallBox.setMaximumSize(smallBox.getPreferredSize());
		smallBox.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		JLabel title = new JLabel("Change Password Center");
		title.setFont(new Font(title.getFont().getName(), Font.BOLD, titleSize));
		JLabel oldLB = new JLabel("Current password ");
		JLabel newLB = new JLabel("New password ");
		JLabel reLB = new JLabel("Re-type new password ");
		JLabel notifLB = new JLabel("");
		notifLB.setForeground(Color.decode(ColorBG.CRITICAL));
		
		JPasswordField oldTF = new JPasswordField(field_width);
		JPasswordField newTF = new JPasswordField(field_width);
		JPasswordField reTF = new JPasswordField(field_width);
		
		JButton submit = new JButton("Change");
		submit.addActionListener(listener);
		submit.setBackground(Color.decode(ColorBG.SUBMIT));
		submit.setForeground(Color.decode(ColorBG.BACKGROUND));
		submit.setPreferredSize(new Dimension(submit_width, field_width));
		submit.setMaximumSize(submit.getPreferredSize());
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		smallBox.add(title, constraints);
		
		constraints.gridx=0;
		constraints.gridy = 1;
		smallBox.add(new JLabel(" "), constraints);
		
		constraints.gridx= 1;
		constraints.gridy = 2;
		smallBox.add(notifLB, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.ipadx = 15;
		smallBox.add(oldLB, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		smallBox.add(oldTF, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.ipadx = 15;
		smallBox.add(newLB, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 4;
		smallBox.add(newTF, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.ipadx = 15;
		smallBox.add(reLB, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 5;
		smallBox.add(reTF, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 6;
		smallBox.add(submit, constraints);
		
		//Components of Event
		listener.setComponents(oldTF, newTF, reTF, notifLB);
		box.add(smallBox, BorderLayout.PAGE_START);
		this.add(box, BorderLayout.CENTER);
	}
}

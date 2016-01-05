package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import events.MouseAdminSubMenu;
import utils.ColorBG;
import utils.LabelIcon;
<<<<<<< HEAD
import utils.ScreenSpecification;

public class PanelAdminSubMenu extends JPanel{
	private MouseAdminSubMenu listener = new MouseAdminSubMenu();
	private int width = (int)(0.15*ScreenSpecification.MONITOR_WIDTH);
	private int height = (int)(0.068*ScreenSpecification.MONITOR_HEIGHT);
	private Dimension SUB_MENU = new Dimension(width, (int)(0.033*ScreenSpecification.MONITOR_HEIGHT));
	private int iconSize = (int)(0.017*ScreenSpecification.MONITOR_HEIGHT);
=======

public class PanelAdminSubMenu extends JPanel{
	private Dimension SUB_MENU = new Dimension(200, 25);
	private MouseAdminSubMenu listener = new MouseAdminSubMenu();
	private int width = 200;
	private int height = 52;
	private int iconWidth = 13;
	private int iconHeight = 13;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	public PanelAdminSubMenu(){
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(width, height));
		this.setMaximumSize(this.getPreferredSize());
		
		JPanel changePassPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		changePassPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		changePassPanel.setPreferredSize(SUB_MENU);
		changePassPanel.setMaximumSize(changePassPanel.getPreferredSize());
		changePassPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY));
		changePassPanel.setBackground(Color.decode(ColorBG.SUBMENU));
<<<<<<< HEAD
		JLabel changePassIcon = new LabelIcon("changepass.png", iconSize, iconSize); 
=======
		JLabel changePassIcon = new LabelIcon("changepass.png", iconWidth, iconHeight); 
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		changePassIcon.setText("Change password");
		changePassIcon.setName("changepassword");
		changePassIcon.addMouseListener(listener);
		changePassIcon.setFont(new Font(changePassIcon.getFont().getName(), changePassIcon.getFont().getStyle(), changePassIcon.getFont().getSize()-1));
		changePassPanel.add(new JLabel(" "));
		changePassPanel.add(changePassIcon);
		
		JPanel logoutPanel = new JPanel();
		logoutPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		logoutPanel.setPreferredSize(SUB_MENU);
		logoutPanel.setMaximumSize(logoutPanel.getPreferredSize());
		logoutPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY));
		logoutPanel.setBackground(Color.decode(ColorBG.SUBMENU));
<<<<<<< HEAD
		JLabel logoutIcon = new LabelIcon("logout.png", iconSize, iconSize);
=======
		JLabel logoutIcon = new LabelIcon("logout.png", iconWidth, iconHeight);
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		logoutIcon.setText("Logout");
		logoutIcon.setName("logout");
		logoutIcon.addMouseListener(listener);
		logoutIcon.setFont(new Font(logoutIcon.getFont().getName(), logoutIcon.getFont().getStyle(), logoutIcon.getFont().getSize()-1));
		
		logoutPanel.add(new JLabel (" "));
		logoutPanel.add(logoutIcon);
		
		//Add event components
		listener.setComponents(changePassPanel);
		
		this.add(changePassPanel, BorderLayout.NORTH);
		this.add(logoutPanel);
	}
}

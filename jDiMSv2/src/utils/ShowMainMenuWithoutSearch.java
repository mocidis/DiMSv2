package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import events.MouseMenuLeft;
import gui.PanelAdminSubMenu;
import gui.PanelMenuLeftComponent;
import gui.PanelMenuLeftSpace;

public class ShowMainMenuWithoutSearch extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MouseMenuLeft listener = new MouseMenuLeft();
	private int iconWidth = 15;
	private int iconHeight = 15;
	private int errorWidth = 25;
	private int errorHeight = 20;
	public ShowMainMenuWithoutSearch() throws SQLException{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(196, JFrame.MAXIMIZED_VERT));
		
		JPanel monitoring = new PanelMenuLeftComponent();
		monitoring.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		JPanel error = new JPanel(new BorderLayout());
		error.setPreferredSize(new Dimension(errorWidth, errorHeight));
		error.setMaximumSize(error.getPreferredSize());
		error.setBackground(Color.decode(ColorBG.CRITICAL));
		error.setOpaque(true);
		String mountOfError = DIMS.getInstance().getServicesMatrix().numberError;
		JLabel number_error = new JLabel(mountOfError);
		number_error.setForeground(Color.decode(ColorBG.BACKGROUND));
		error.add(number_error);
		
		JLabel monitoringIcon = new LabelIcon("dashboard.png", iconWidth, iconHeight);
		monitoringIcon.setText("Monitoring");
		monitoringIcon.setName("0");
		monitoringIcon.addMouseListener(listener);
		monitoring.add(monitoringIcon);
		monitoring.add(error);
		
		JPanel admin = new PanelMenuLeftComponent();
		JLabel adminIcon = new LabelIcon("user.png", iconWidth, iconHeight);	
		adminIcon.setText("Admin");
		adminIcon.setName("1");
		adminIcon.addMouseListener(listener);
		admin.add(adminIcon);
		
		//Admin sub menu
		JPanel adminSubMenu = new PanelAdminSubMenu();
		adminSubMenu.setVisible(false);
		
		JPanel fillSpace = new PanelMenuLeftSpace();
		
		JPanel [] listPanel = {monitoring, admin};
		JLabel [] listLabel = {monitoringIcon, adminIcon};
		
		listener.setComponents(listPanel, listLabel, adminSubMenu);
		
		this.add(monitoring);
		this.add(admin);
		this.add(adminSubMenu);
		this.add(fillSpace);
	}

}

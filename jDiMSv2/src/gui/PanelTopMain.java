package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.ColorBG;
import utils.ShowHostGroups;
import utils.SubDimension;

public class PanelTopMain extends PanelTop {
	private Dimension logo = new Dimension(196, 80);
	private int height = 80;
	public PanelTopMain() throws SQLException {
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setPreferredSize(new Dimension(JFrame.MAXIMIZED_HORIZ, height));
		this.setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
		
		JPanel logoPart = new JPanel(new BorderLayout());
		logoPart.setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
		logoPart.setPreferredSize(logo);
		logoPart.setMaximumSize(logoPart.getPreferredSize());
		JLabel logo = new JLabel(new ImageIcon(new ImageIcon(this.getClass().getResource("/logo.png")).getImage().getScaledInstance(110, 45, Image.SCALE_SMOOTH)));
		logoPart.add(logo, BorderLayout.CENTER);
		
		JPanel menuSitePart = new JPanel(new BorderLayout());
		menuSitePart.setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
		ShowHostGroups groups = new ShowHostGroups();
		JPanel menuSite = groups.getMenuSite();
		menuSitePart.add(menuSite, BorderLayout.LINE_START);
		
		this.add(logoPart);
		this.add(menuSitePart);
	}
}

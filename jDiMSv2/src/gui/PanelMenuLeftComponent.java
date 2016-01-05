package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import utils.SubDimension;

public class PanelMenuLeftComponent extends JPanel{
	private Dimension MAIN_MENU_COMPONENT = new Dimension(200 ,30);
	public PanelMenuLeftComponent(){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize(MAIN_MENU_COMPONENT);
		this.setMaximumSize(this.getPreferredSize());
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY));
	}
}

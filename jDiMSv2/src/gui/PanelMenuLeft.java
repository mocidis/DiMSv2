package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.prompt.PromptSupport;

import events.ActionSearch;
import utils.DIMS;
import utils.LabelIcon;
import utils.ScreenSpecification;
import utils.ShowMainMenuWithoutSearch;

public class PanelMenuLeft extends JPanel {
	private ActionSearch searchListener = new ActionSearch();
	private JPanel otherComponents;
	private int iconSize = (int)(0.02*ScreenSpecification.MONITOR_HEIGHT);
	private String search_bg = "#ededed";
	private int widthLength = (int)(0.143*ScreenSpecification.MONITOR_WIDTH);
	public PanelMenuLeft() throws SQLException {
		DIMS.getInstance().setMainMenu(this);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(widthLength, JFrame.MAXIMIZED_VERT));
		
		//Search Panel
		JPanel search = new PanelMenuLeftComponent();
		JLabel searchIcon = new LabelIcon("search.png", iconSize, iconSize);
		JTextField keyword = new JTextField(iconSize);
		keyword.getDocument().addDocumentListener(searchListener);
		searchListener.setComponents(keyword);
		keyword.requestFocus(false);
		keyword.setBackground(Color.decode(search_bg));
		keyword.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(search_bg)));
		keyword.setRequestFocusEnabled(true);
		PromptSupport.setPrompt("search...", keyword);
		PromptSupport.setForeground(Color.BLACK, keyword);
		search.add(searchIcon);
		search.add(keyword);
		
		//Monitoring Panel
		otherComponents = new ShowMainMenuWithoutSearch();
		
		//Add components
		this.add(search);
		this.add(otherComponents);
	}
	
}
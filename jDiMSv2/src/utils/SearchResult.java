package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import gui.PanelMainContent;

public class SearchResult extends PanelMainContent{
	public SearchResult(String keyword) throws SQLException{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		JPanel boxContainer = new JPanel();
		boxContainer.setLayout(new BoxLayout(boxContainer, BoxLayout.Y_AXIS));
		
		JPanel hostGroupBox = new SearchHostGroup(keyword);
		JPanel serviceGroupBox = new SearchServiceGroup(keyword);
		JPanel serviceBox = new SearchService(keyword);
		JPanel hostBox = new SearchHost(keyword);
				
		boxContainer.add(hostGroupBox);
		boxContainer.add(serviceGroupBox);
		boxContainer.add(serviceBox);
		boxContainer.add(hostBox);
		
		this.add(boxContainer, BorderLayout.PAGE_START);
	}
}

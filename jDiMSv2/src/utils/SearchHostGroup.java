package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JPanel;

import events.MouseHostGroups;

public class SearchHostGroup extends JPanel{
	private String [][] data;
	
	public SearchHostGroup(String keyword) throws SQLException{
		//Data
		data = DIMS.getInstance().getPicker().searchHostGroupByName(keyword);
		
		//Layout
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		JPanel boxContent = new SearchGroups(data, "Host Groups", Glossary.HOST_GROUP_ICON, keyword, "hostgroups");
		this.add(boxContent, BorderLayout.LINE_START);
	}
}

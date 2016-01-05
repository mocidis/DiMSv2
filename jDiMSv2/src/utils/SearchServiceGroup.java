package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchServiceGroup extends JPanel{
private String [][] data;
	
	public SearchServiceGroup(String keyword) throws SQLException{
		//Data
		data = DIMS.getInstance().getPicker().searchServiceGroupByName(keyword);
		int size = data.length;
		//Layout
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		JPanel boxContent = new SearchGroups(data, "Service Groups", Glossary.SERVICE_GROUP_ICON, keyword, "servicegroups");
		
		this.add(boxContent, BorderLayout.LINE_START);
	}
}

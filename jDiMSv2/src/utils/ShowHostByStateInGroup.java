package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JPanel;

import gui.PanelMainContent;

public class ShowHostByStateInGroup extends PanelMainContent{
	public ShowHostByStateInGroup(int hg_object_id) throws SQLException{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		String [][] data = DIMS.getInstance().getPicker().getHostByStateInGroup(hg_object_id, DIMS.getInstance().getState());
		int [] countData = DIMS.getInstance().getPicker().countConfiguredHostInGroup(hg_object_id);
		
		//Box contains panels
		JPanel boxbox = new ReachHosts(data, countData, data[0][6]);
		this.add(boxbox, BorderLayout.PAGE_START);
	}
}

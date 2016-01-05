package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JPanel;

import gui.PanelMainContent;

public class ShowHostByStateInGroup extends PanelMainContent{
	public ShowHostByStateInGroup(int hg_object_id, String state, String total) throws SQLException{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		String [][] data_ori = DIMS.getInstance().getCurrentData();
		int length = Integer.parseInt(total);
		String [][] data = Classification.filterData(data_ori, state, 1, length);
		int [] countData = Classification.stateToHost(data_ori, 1);
		
		//Box contains panels
		JPanel boxbox = new ReachHosts(data, countData, data[0][6]);
		this.add(boxbox, BorderLayout.PAGE_START);
	}
}

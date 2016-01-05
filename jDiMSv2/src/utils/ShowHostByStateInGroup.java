package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JPanel;

import gui.PanelMainContent;

public class ShowHostByStateInGroup extends PanelMainContent{
<<<<<<< HEAD
	public ShowHostByStateInGroup(int hg_object_id, String state, String total) throws SQLException{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		String [][] data_ori = DIMS.getInstance().getCurrentData();
		int length = Integer.parseInt(total);
		String [][] data = Classification.filterData(data_ori, state, 1, length);
		int [] countData = Classification.stateToHost(data_ori, 1);
=======
	public ShowHostByStateInGroup(int hg_object_id) throws SQLException{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		String [][] data = DIMS.getInstance().getPicker().getHostByStateInGroup(hg_object_id, DIMS.getInstance().getState());
		int [] countData = DIMS.getInstance().getPicker().countConfiguredHostInGroup(hg_object_id);
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		
		//Box contains panels
		JPanel boxbox = new ReachHosts(data, countData, data[0][6]);
		this.add(boxbox, BorderLayout.PAGE_START);
	}
}

package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import events.MouseReachServices;
import gui.PanelMainDetailContent;

public class ShowServiceByStateInHost extends PanelMainDetailContent{
	private String state;
	private String total;
	public ShowServiceByStateInHost(String state, String total) {
		this.state = state;
		this.total = total;
	}
	
	@Override
	public JPanel createContent(int host_object_id) throws SQLException{
		int position = FindPosition.toServiceInHost(host_object_id);
		String [][] data_ori = DIMS.getInstance().getCurrentData();
		int length = Integer.parseInt(total);
		String [][] data = Classification.filterData(data_ori, state, 1, length);
		int [] countData = Classification.stateToService(data_ori, 1);
		
		//Box contains panels
		JPanel boxbox = new ReachServices(data, countData, DIMS.getInstance().getHostPage().getPartOne(position)[3]);
		return boxbox;
	}
}

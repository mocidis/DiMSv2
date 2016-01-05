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
<<<<<<< HEAD
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
=======
	@Override
	public JPanel createContent(int host_object_id) throws SQLException{
		int position = 0;
		for(int i = 0; i < DIMS.getInstance().getHostPage().getHostId().length; i++){
			if(host_object_id == Integer.parseInt(DIMS.getInstance().getHostPage().getHostId()[i])) {
				position = i;
				break;
			}
		}
		String [][] data = DIMS.getInstance().getPicker().getServiceByStateInHost(host_object_id, DIMS.getInstance().getState());
		int [] countData = DIMS.getInstance().getPicker().countConfiguredServiceInHost(host_object_id);
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		
		//Box contains panels
		JPanel boxbox = new ReachServices(data, countData, DIMS.getInstance().getHostPage().getPartOne(position)[3]);
		return boxbox;
	}
<<<<<<< HEAD
=======

>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
}

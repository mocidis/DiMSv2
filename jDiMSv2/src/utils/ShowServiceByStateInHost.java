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
		
		//Box contains panels
		JPanel boxbox = new ReachServices(data, countData, DIMS.getInstance().getHostPage().getPartOne(position)[3]);
		return boxbox;
	}

}

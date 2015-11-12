package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import events.MouseReachServices;
import gui.PanelMainDetailContent;

public class ShowServiceInHost extends PanelMainDetailContent{
	
	@Override
	public JPanel createContent(int host_object_id) throws SQLException{
		int position = 0;
		for(int i = 0; i < DIMS.getInstance().getHostPage().getHostId().length; i++){
			if(host_object_id == Integer.parseInt(DIMS.getInstance().getHostPage().getHostId()[i])) {
				position = i;
				break;
			}
		}
		int RowSize = DIMS.getInstance().getServiceInHost().state[position].length;
		String [][] data = new String [RowSize][5];
		for(int i = 0; i < RowSize; i++){
			data[i][0] = DIMS.getInstance().getServiceInHost().service_object_id[position][i];
			data[i][1] = DIMS.getInstance().getServiceInHost().state[position][i];
			data[i][2] = DIMS.getInstance().getServiceInHost().since[position][i];
			data[i][3] = DIMS.getInstance().getServiceInHost().serviceName[position][i];
			data[i][4] = DIMS.getInstance().getServiceInHost().output[position][i];
		}
		int [] countData = DIMS.getInstance().getPicker().countConfiguredServiceInHost(host_object_id);
		
		//Box contains panels
		JPanel boxbox = new ReachServices(data, countData, DIMS.getInstance().getHostPage().getPartOne(position)[3]);
		return boxbox;
	}

}

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
		int position = FindPosition.toServiceInHost(host_object_id);
		
		int RowSize = DIMS.getInstance().getServiceInHost().getState()[position].length;
		String [][] data = new String [RowSize][5];
		for(int i = 0; i < RowSize; i++){
			data[i][0] = DIMS.getInstance().getServiceInHost().getServiceObjectId()[position][i];
			data[i][1] = DIMS.getInstance().getServiceInHost().getState()[position][i];
			data[i][2] = DIMS.getInstance().getServiceInHost().getTime()[position][i];
			data[i][3] = DIMS.getInstance().getServiceInHost().getName()[position][i];
			data[i][4] = DIMS.getInstance().getServiceInHost().getMessage()[position][i];
		}
		DIMS.getInstance().setCurrentData(data);
		int [] countData = Classification.stateToService(data, 1);
		
		//Box contains panels
		JPanel boxbox = new ReachServices(data, countData, DIMS.getInstance().getHostPage().getPartOne(position)[3]);
		return boxbox;
	}

}

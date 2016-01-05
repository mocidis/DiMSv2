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
<<<<<<< HEAD
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
=======
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
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		
		//Box contains panels
		JPanel boxbox = new ReachServices(data, countData, DIMS.getInstance().getHostPage().getPartOne(position)[3]);
		return boxbox;
	}

}

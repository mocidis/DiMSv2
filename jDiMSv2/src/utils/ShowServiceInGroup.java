package utils;

import java.sql.SQLException;

import javax.swing.JPanel;

import gui.PanelMainDetailContent;

public class ShowServiceInGroup extends PanelMainDetailContent{
	
	@Override
	public JPanel createContent(int sgroup_object_id) throws SQLException{
<<<<<<< HEAD
		int position = FindPosition.toServiceInGroup(sgroup_object_id);
		int rowSize = DIMS.getInstance().getServiceInGroup().getName()[position].length;
		String [][] data = new String [rowSize][7];
		for(int i = 0; i < rowSize; i++) {
			data[i][0] = DIMS.getInstance().getServiceInGroup().getServiceObjectId()[position][i];
			data[i][1] = DIMS.getInstance().getServiceInGroup().getState()[position][i];
			data[i][2] = DIMS.getInstance().getServiceInGroup().getTime()[position][i]; 
			data[i][3] = DIMS.getInstance().getServiceInGroup().getName()[position][i];
			data[i][4] = DIMS.getInstance().getServiceInGroup().getAlias()[position][i];
			data[i][5] = DIMS.getInstance().getServiceInGroup().getMessage()[position][i];
			data[i][6] = DIMS.getInstance().getServiceInGroup().getGroups()[position][i];
		}
		DIMS.getInstance().setCurrentData(data);
		int [] countData = Classification.stateToService(data, 1);
=======
		String [][] data = DIMS.getInstance().getPicker().getServiceInGroup(sgroup_object_id);
		int [] countData = DIMS.getInstance().getPicker().countConfiguredServiceInGroup(sgroup_object_id);
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		
		//Box contains panels
		JPanel boxbox = new ReachServices(data, countData, data[0][6]);
		return boxbox;
	}
}

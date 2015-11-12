package utils;

import java.sql.SQLException;

import javax.swing.JPanel;

import gui.PanelMainDetailContent;

public class ShowServiceByStateInGroup extends PanelMainDetailContent{
	@Override
	public JPanel createContent(int sg_object_id) throws SQLException{
		String [][] data = DIMS.getInstance().getPicker().getServiceByStateInGroup(sg_object_id, DIMS.getInstance().getState());
		int [] countData = DIMS.getInstance().getPicker().countConfiguredServiceInGroup(sg_object_id);
		
		//Box contains panels
		JPanel boxbox = new ReachServices(data, countData, data[0][6]);
		return boxbox;
	}
}

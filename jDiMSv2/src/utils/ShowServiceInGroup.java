package utils;

import java.sql.SQLException;

import javax.swing.JPanel;

import gui.PanelMainDetailContent;

public class ShowServiceInGroup extends PanelMainDetailContent{
	
	@Override
	public JPanel createContent(int sgroup_object_id) throws SQLException{
		String [][] data = DIMS.getInstance().getPicker().getServiceInGroup(sgroup_object_id);
		int [] countData = DIMS.getInstance().getPicker().countConfiguredServiceInGroup(sgroup_object_id);
		
		//Box contains panels
		JPanel boxbox = new ReachServices(data, countData, data[0][6]);
		return boxbox;
	}
}

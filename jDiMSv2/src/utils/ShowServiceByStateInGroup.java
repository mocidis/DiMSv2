package utils;

import java.sql.SQLException;

import javax.swing.JPanel;

import gui.PanelMainDetailContent;

public class ShowServiceByStateInGroup extends PanelMainDetailContent{
<<<<<<< HEAD
	private String state;
	private String total;
	
	public ShowServiceByStateInGroup(String state, String total) {
		this.state = state;
		this.total = total;
	}
	
	@Override
	public JPanel createContent(int sg_object_id) throws SQLException{
		String [][] data_ori = DIMS.getInstance().getCurrentData();
		int length = Integer.parseInt(total);
		String [][] data = Classification.filterData(data_ori, state, 1, length);
		int [] countData = Classification.stateToService(data_ori, 1);
=======
	@Override
	public JPanel createContent(int sg_object_id) throws SQLException{
		String [][] data = DIMS.getInstance().getPicker().getServiceByStateInGroup(sg_object_id, DIMS.getInstance().getState());
		int [] countData = DIMS.getInstance().getPicker().countConfiguredServiceInGroup(sg_object_id);
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		
		//Box contains panels
		JPanel boxbox = new ReachServices(data, countData, data[0][6]);
		return boxbox;
	}
}

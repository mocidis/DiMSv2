package utils;

import java.sql.SQLException;

import javax.swing.JPanel;

import gui.PanelMainDetailContent;

public class ShowServiceByStateInGroup extends PanelMainDetailContent{
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
		
		//Box contains panels
		JPanel boxbox = new ReachServices(data, countData, data[0][6]);
		return boxbox;
	}
}

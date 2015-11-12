package models;

import java.sql.SQLException;

import utils.DIMS;
import utils.RetrieveData;

public class HostInGroup {
	public String [][] host_object_id;
	
	public String [][] state;
	
	public String [][] since;
	
	public String [][] hostName;
	
	public String [][] output;
	
	public String [][] alias;
	
	public HostInGroup() throws SQLException{
		RetrieveData picker = DIMS.getInstance().getPicker();
		String [][] hostGroup = DIMS.getInstance().getHostGroups().HostGroups;
		int RowSize = hostGroup.length;
		host_object_id = new String [RowSize][];
		state = new String [RowSize][];
		since = new String [RowSize][];
		hostName = new String [RowSize][];
		output = new String [RowSize][];
		alias = new String [RowSize][];
		for(int row = 0; row < RowSize; row++)
		{
			int ColumnSize = picker.getHostsInGroup(Integer.parseInt(hostGroup[row][1])).length;
			host_object_id[row] = new String [ColumnSize];
			state[row] = new String [ColumnSize];
			since[row] = new String [ColumnSize];
			hostName[row] = new String [ColumnSize];
			output[row] = new String [ColumnSize];
			alias[row] = new String [ColumnSize];
		}
		
		for(int row = 0; row < RowSize; row++){
			String [][] data = picker.getHostsInGroup(Integer.parseInt(hostGroup[row][1]));
			for(int col = 0; col < data.length; col++)
			{
				host_object_id[row][col] = data[col][0];
				state[row][col] = data[col][1];
				since[row][col] = data[col][2];
				hostName[row][col] = data[col][3];
				alias[row][col] = data[col][4];
				output[row][col] = data[col][5];
			}
		}
		
	}
}

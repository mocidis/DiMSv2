package models;

import java.sql.SQLException;

import utils.DIMS;
import utils.RetrieveData;

public class HostInGroup {
<<<<<<< HEAD
	private String [][] host_object_id;
	
	private String [][] state;
	
	private String [][] since;
	
	private String [][] hostName;
	
	private String [][] output;
	
	private String [][] alias;
	
	private String [][] hostgroup;
	public HostInGroup() throws SQLException{
		RetrieveData picker = DIMS.getInstance().getPicker();
		String [][] hostGroup = DIMS.getInstance().getHostGroups().getHostGroup();
=======
	public String [][] host_object_id;
	
	public String [][] state;
	
	public String [][] since;
	
	public String [][] hostName;
	
	public String [][] output;
	
	public String [][] alias;
	
	public HostInGroup() throws SQLException{
		RetrieveData picker = DIMS.getInstance().getPicker();
		String [][] hostGroup = DIMS.getInstance().getHostGroups().HostGroups;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		int RowSize = hostGroup.length;
		host_object_id = new String [RowSize][];
		state = new String [RowSize][];
		since = new String [RowSize][];
		hostName = new String [RowSize][];
		output = new String [RowSize][];
		alias = new String [RowSize][];
<<<<<<< HEAD
		hostgroup = new String [RowSize][];
=======
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		for(int row = 0; row < RowSize; row++)
		{
			int ColumnSize = picker.getHostsInGroup(Integer.parseInt(hostGroup[row][1])).length;
			host_object_id[row] = new String [ColumnSize];
			state[row] = new String [ColumnSize];
			since[row] = new String [ColumnSize];
			hostName[row] = new String [ColumnSize];
			output[row] = new String [ColumnSize];
			alias[row] = new String [ColumnSize];
<<<<<<< HEAD
			hostgroup[row] = new String [ColumnSize];
=======
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
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
<<<<<<< HEAD
				hostgroup[row][col] = data[col][6];
=======
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
			}
		}
		
	}
<<<<<<< HEAD

	public String [][] getHostObjectId() { 
		return host_object_id;
	}
	
	public String [][] getState() {
		return state;
	}
	
	public String [][] getTime() {
		return since;
	}
	
	public String [][] getName() {
		return hostName;
	}
	
	public String [][] getMessage() {
		return output;
	}
	
	public String [][] getAlias() {
		return alias;
	}
	
	public String [][] getGroup() {
		return hostgroup;
	}
}


=======
}
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d

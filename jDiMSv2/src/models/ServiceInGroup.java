package models;

import java.sql.SQLException;

import utils.DIMS;
import utils.RetrieveData;

public class ServiceInGroup {
	private String [][] serviceObjectId;
	
	private String [][] currentState;
	
	private String [][] lastChange;
	
	private String [][] displayName;
	
	private String [][] alias;
	
	private String [][] output;
	
	private String [][] groups;
	
	public ServiceInGroup() throws SQLException{
		RetrieveData picker = DIMS.getInstance().getPicker();
		String [] group = DIMS.getInstance().getServiceGroups().getServiceGroupsID();
		int rowSize = group.length;
		serviceObjectId = new String [rowSize][];
		currentState = new String [rowSize][];
		lastChange = new String [rowSize][];
		displayName = new String [rowSize][];
		alias = new String [rowSize][];
		output = new String [rowSize][];
		groups = new String [rowSize][];
		
		for(int row = 0; row < group.length; row++) {
			String [][] data = picker.getServiceInGroup(Integer.parseInt(group[row]));
			int columnSize = data.length;
			serviceObjectId[row] = new String [columnSize];
			currentState[row] = new String [columnSize];
			lastChange[row] = new String [columnSize];
			displayName[row] = new String [columnSize];
			alias[row] = new String [columnSize];
			output[row] = new String [columnSize];
			groups[row] = new String [columnSize];
		}
		for(int row = 0; row < group.length; row++) {
			String [][] data = picker.getServiceInGroup(Integer.parseInt(group[row]));
			for(int col = 0; col < data.length; col++) {
				serviceObjectId[row][col] = data[col][0];
				currentState[row][col] = data[col][1];
				lastChange[row][col] = data[col][2];
				displayName[row][col] = data[col][3];
				alias[row][col] = data[col][4];
				output[row][col] = data[col][5];
				groups[row][col] = data[col][6];
			}
		}
	}
	
	public String [][] getServiceObjectId() {
		return serviceObjectId;
	}
	
	public String [][] getState() {
		return currentState;
	}
	
	public String [][] getTime() {
		return lastChange;
	}
	
	public String [][] getName() {
		return displayName;
	}
	
	public String [][] getAlias() {
		return alias;
	}
	
	public String [][] getMessage() {
		return output;
	}
	
	public String [][] getGroups() {
		return groups;
	}
}

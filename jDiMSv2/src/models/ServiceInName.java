package models;

import java.sql.SQLException;

import utils.DIMS;
import utils.RetrieveData;

public class ServiceInName {
	private String [][] serviceObjectId;
	
	private String [][] currentState;
	
	private String [][] lastChange;
	
	private String [][] displayName;
	
	private String [][] alias;
	
	private String [][] output;
	
	public ServiceInName() throws SQLException{
		RetrieveData picker = DIMS.getInstance().getPicker();
		String [] title = DIMS.getInstance().getServicesMatrix().getServiceTitle();
		int rowSize = title.length;
		serviceObjectId = new String [rowSize][];
		currentState = new String [rowSize][];
		lastChange = new String [rowSize][];
		displayName = new String [rowSize][];
		alias = new String [rowSize][];
		output = new String [rowSize][];
		
		for(int row = 0; row < rowSize; row++) {
			String [][] data = picker.getServiceByName(title[row]);
			int colSize = data.length;
			serviceObjectId[row] = new String [colSize];
			currentState[row] = new String [colSize];
			lastChange[row] = new String [colSize];
			displayName[row] = new String [colSize];
			alias[row] = new String [colSize];
			output[row] = new String [colSize];
		}
		
		for(int row = 0; row < rowSize; row++) {
			String [][] data = picker.getServiceByName(title[row]);
			for(int col = 0; col < data.length; col++) {
				serviceObjectId[row][col] = data[col][0];
				currentState[row][col] = data[col][1];
				lastChange[row][col] = data[col][2];
				displayName[row][col] = data[col][3];
				alias[row][col] = data[col][4];
				output[row][col] = data[col][5];
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
}

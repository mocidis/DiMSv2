package models;

import java.sql.SQLException;

import utils.DIMS;

public class ServiceHistory {
	private String [][] service_object_id; 
	
	private String [][] state;
	
	private String [][] state_time;
	
	private String [][] display_name;
	
	private String [][] check_attempt;
	
	private String [][] output;
	
	private boolean [] has_history;
	public ServiceHistory() throws SQLException{
		String [] services = DIMS.getInstance().getServicePage().getServicesId();
		int rowLength = services.length;
		service_object_id = new String [rowLength][];
		state = new String [rowLength][];
		state_time = new String [rowLength][];
		display_name = new String [rowLength][];
		check_attempt = new String [rowLength][];
		output = new String [rowLength][];
		has_history = new boolean [rowLength];
		
		for(int row = 0; row < rowLength; row++){
			String [][] data = DIMS.getInstance().getPicker().getServiceHistory(Integer.parseInt(services[row]));
			if(data != null){
				int colLength = data.length;
				service_object_id[row] = new String [colLength];
				state[row] = new String [colLength];
				state_time[row] = new String [colLength];
				display_name[row] = new String [colLength];
				check_attempt[row] = new String [colLength];
				output[row] = new String [colLength];
			}
			else service_object_id[row] = new String [1];
		}
		
		//Grant data
		for(int row = 0; row < state.length; row++){
			String [][] data = DIMS.getInstance().getPicker().getServiceHistory(Integer.parseInt(services[row]));
			if(data != null){
				has_history[row] = true;
				for(int col = 0; col < data.length; col++){
					service_object_id[row][col] = data[col][0];
					state[row][col] = data[col][1];
					state_time[row][col] = data[col][2];
					display_name[row][col] = data[col][3];
					check_attempt[row][col] = data[col][4];
					output[row][col] = data[col][5];
				}
			}
			else{
				service_object_id[row][0] = services[row];
				has_history[row] = false;
			}
		}
	}
	
	public String [][] getServiceObjectId() {
		return service_object_id;
	}
	
	public String [][] getState() {
		return state;
	}
	
	public String [][] getStateTime() {
		return state_time;
	}
	
	public String [][] getName() {
		return display_name;
	}
	
	public String [][] getAttempt() {
		return check_attempt;
	}
	
	public String [][] getMessage() {
		return output;
	}
	
	public boolean [] hasHistory() {
		return has_history;
	}
}

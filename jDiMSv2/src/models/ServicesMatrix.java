package models;

import java.sql.SQLException;

import utils.DIMS;
import utils.RetrieveData;

public class ServicesMatrix {
<<<<<<< HEAD
	private String numberError;
	
	private String [][] hostTitle;
	
	private String [] serviceTitle;
	
	private String [][] serviceState;
=======
	public String numberError;
	
	public String [][] hostTitle;
	
	public String [] serviceTitle;
	
	public String [][] serviceState;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	
	public ServicesMatrix() throws SQLException{
		RetrieveData picker = DIMS.getInstance().getPicker();
		numberError = picker.countError();
		
		hostTitle = picker.getHostTitle();
		
		serviceTitle = picker.getServiceTitle();
		
		serviceState = picker.getServiceState(serviceTitle, hostTitle);
	}
	
<<<<<<< HEAD
	public String getError() {
		return numberError;
	}
	
	public String [][] getHostTitle() {
		return hostTitle;
	}
	
	public String [] getServiceTitle() {
		return serviceTitle;
	}
	
	public String [][] getState() {
		return serviceState;
	}
=======
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
}

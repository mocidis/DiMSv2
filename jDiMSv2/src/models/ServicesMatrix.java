package models;

import java.sql.SQLException;

import utils.DIMS;
import utils.RetrieveData;

public class ServicesMatrix {
	private String numberError;
	
	private String [][] hostTitle;
	
	private String [] serviceTitle;
	
	private String [][] serviceState;
	
	public ServicesMatrix() throws SQLException{
		RetrieveData picker = DIMS.getInstance().getPicker();
		numberError = picker.countError();
		
		hostTitle = picker.getHostTitle();
		
		serviceTitle = picker.getServiceTitle();
		
		serviceState = picker.getServiceState(serviceTitle, hostTitle);
	}
	
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
}

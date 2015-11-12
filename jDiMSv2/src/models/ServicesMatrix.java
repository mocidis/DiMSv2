package models;

import java.sql.SQLException;

import utils.DIMS;
import utils.RetrieveData;

public class ServicesMatrix {
	public String numberError;
	
	public String [][] hostTitle;
	
	public String [] serviceTitle;
	
	public String [][] serviceState;
	
	public ServicesMatrix() throws SQLException{
		RetrieveData picker = DIMS.getInstance().getPicker();
		numberError = picker.countError();
		
		hostTitle = picker.getHostTitle();
		
		serviceTitle = picker.getServiceTitle();
		
		serviceState = picker.getServiceState(serviceTitle, hostTitle);
	}
	
}

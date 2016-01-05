package models;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import utils.DIMS;
import utils.RetrieveData;

public class HostGroups {
<<<<<<< HEAD
	private String [][] HostGroups;
=======
	public String [][] HostGroups;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	
	public HostGroups() throws SQLException{
		RetrieveData picker = new RetrieveData();
		HostGroups = picker.getHostGroups();
	}
	
	public String [][] getHostGroup(){
		return HostGroups;
	}
}

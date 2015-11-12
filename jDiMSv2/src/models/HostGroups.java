package models;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import utils.DIMS;
import utils.RetrieveData;

public class HostGroups {
	public String [][] HostGroups;
	
	public HostGroups() throws SQLException{
		RetrieveData picker = new RetrieveData();
		HostGroups = picker.getHostGroups();
	}
	
	public String [][] getHostGroup(){
		return HostGroups;
	}
}

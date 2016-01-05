package models;

import java.sql.SQLException;

import utils.DIMS;

public class ServiceGroups {
	private String [] servicegroup_object_id;
	
	public ServiceGroups() throws SQLException{
		servicegroup_object_id = DIMS.getInstance().getPicker().getServiceGroups();
	}
	
	public String [] getServiceGroupsID(){
		return servicegroup_object_id;
	}
}

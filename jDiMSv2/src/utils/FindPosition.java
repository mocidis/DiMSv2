package utils;

import java.sql.SQLException;

public class FindPosition {
	public static int toHostInGroup(int group_object_id) throws SQLException{
		int position = 0;
		for(int i = 0; i < DIMS.getInstance().getHostGroups().getHostGroup().length; i++){
			if(group_object_id == Integer.parseInt(DIMS.getInstance().getHostGroups().getHostGroup()[i][1])) {
				position = i;
				break;
			}
		}
		return position;
	}
	
	public static int toHost(int host_object_id) throws SQLException{
		int position = 0;
		String [] hosts = DIMS.getInstance().getHostPage().getHostId();
		for(int i = 0; i < hosts.length; i++){
			if(host_object_id == Integer.parseInt(hosts[i])){
				position = i;
				break;
			}
		}
		return position;
	}
	
	public static int toServiceInHost(int host_object_id) throws SQLException {
		int position = 0;
		for(int i = 0; i < DIMS.getInstance().getHostPage().getHostId().length; i++){
			if(host_object_id == Integer.parseInt(DIMS.getInstance().getHostPage().getHostId()[i])) {
				position = i;
				break;
			}
		}
		return position;
	}
	
	public static int toService(int service_object_id) throws SQLException {
		int position = 0;
		String [] servicesId = DIMS.getInstance().getServicePage().getServicesId();
		for(int i = 0; i < servicesId.length; i++){
			if(Integer.parseInt(servicesId[i]) == service_object_id) {
				position = i;
				break;
			}
		}
		return position;
	}
	
	public static int toServiceInGroup(int group_object_id) throws SQLException {
		int position = 0;
		String [] groups = DIMS.getInstance().getServiceGroups().getServiceGroupsID();
		for(int col = 0; col < groups.length; col++) {
			if(Integer.parseInt(groups[col]) == group_object_id) {
				position = col;
				break;
			}
		}
		return position;
	}
	
	public static int toServiceInName(String name) throws SQLException {
		int position = 0;
		String [][] services = DIMS.getInstance().getServiceInName().getName();
		for(int row = 0; row < services.length; row++) {
			if(name.equals(services[row][0])) {
				position = row;
				break;
			}
		}
		return position;
	}
}

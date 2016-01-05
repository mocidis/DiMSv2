package models;

import java.sql.SQLException;

import utils.DIMS;
import utils.RetrieveData;

public class MonitorService {
	private String [] hostgroup_object_id;
	
	private String [] service_object_id;
	
	private String [] host_object_id;
	
	private String [] host_alias;
	
	private String [] state;
	
	private String [] since;
	
	private String [] output;
	
	private String [] display_name;
	
	private String [] service_alias;
	
	private String [] hostName;
	
	private String [] perf_data;
	
	private String [] check_source;
	
	private String [] is_reachable;
	
	private String [] serviceGroup1;
	
	private String [] serviceGroup2;
	
	private String [] last_check;
	
	private String [] next_check;
	
	private String [] check_attempts;
	
	private String [] check_execution_time;
	
	private String [] servicegroup1_object_id;
	
	private String [] servicegroup2_object_id;
	
	public MonitorService() throws SQLException{
		String [][] services = DIMS.getInstance().getServiceInHost().getServiceObjectId();
		int totalService = 0;
		for(int i = 0; i < services.length; i++) totalService += services[i].length;
		
		hostgroup_object_id = new String [totalService];
		service_object_id = new String [totalService];
		host_object_id = new String [totalService];
		host_alias = new String [totalService];
		state = new String [totalService];
		since = new String [totalService];
		output = new String [totalService];
		display_name = new String [totalService];
		service_alias = new String [totalService];
		hostName = new String [totalService];
		perf_data = new String [totalService];
		check_source = new String [totalService];
		is_reachable = new String [totalService];
		serviceGroup1 = new String [totalService];
		serviceGroup2 = new String [totalService];
		last_check = new String [totalService];
		next_check = new String [totalService];
		check_attempts = new String [totalService];
		check_execution_time = new String [totalService];
		servicegroup1_object_id = new String [totalService];
		servicegroup2_object_id = new String [totalService];
		
		int position = 0;
		RetrieveData picker = DIMS.getInstance().getPicker();
		for(int i = 0; i < services.length; i++){
			for(int j = 0; j < services[i].length; j++) {
				String [] data1 = picker.getDetailServicePartOne(Integer.valueOf(services[i][j]));
				String data2 = picker.getDetailServicePartTwo(Integer.valueOf(services[i][j]));
				String [] data3 = picker.getDetailServicePartThree(Integer.valueOf(services[i][j]));
				service_object_id[position] = data1[0];
				host_object_id[position] = data1[1];
				state[position] = data1[2];
				since[position] = data1[3];
				display_name[position] = data1[4];
				service_alias[position] = data1[5];
				hostName[position] = data1[6];
				host_alias[position] = data1[7];
				hostgroup_object_id[position] = data1[8];
				
				output[position] = data2;
				
				perf_data[position] = data3[0];
				check_source[position] = data3[1];
				is_reachable[position] = data3[2];
				serviceGroup1[position] = data3[3];
				servicegroup1_object_id[position] = data3[4];
				if(data3.length > 9){
					serviceGroup2[position] = data3[9];
					servicegroup2_object_id[position] = data3[10];
				}
				last_check[position] = data3[5];
				next_check[position] = data3[6];
				check_attempts[position] = data3[7];
				check_execution_time[position] = data3[8];
				position++;
			}
		}
	}
	
	public String [] getServicesId(){
		return service_object_id;
	}
	
	public String [] getPartOne(int position){
		String [] partOne = {service_object_id[position], host_object_id[position], state[position], since[position], display_name[position], service_alias[position], hostName[position], host_alias[position]};
		return partOne;
	}
	
	public String getPartTwo(int position){
		String partTwo = this.output[position];
		return partTwo;
	}
	
	public String [] getPartThree(int position){
		String [] partThree = {perf_data[position], check_source[position], is_reachable[position], serviceGroup1[position], serviceGroup2[position], last_check[position], next_check[position], check_attempts[position], check_execution_time[position]};
		return partThree;
	}
	
	public String getServiceGroupId1(int position){
		String id = servicegroup1_object_id[position];
		return id;
	}
	
	public String getServiceGroupId2(int position){
		String id = null;
		if(servicegroup2_object_id[position] != null) id = servicegroup2_object_id[position];
		return id;
	}
	
	public String getHostGroupId(int position) {
		return hostgroup_object_id[position];
	}
}


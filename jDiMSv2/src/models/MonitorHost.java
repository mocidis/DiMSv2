package models;

import java.sql.SQLException;

import utils.DIMS;
import utils.RetrieveData;

public class MonitorHost {
	
	private String [] host_object_id;
	
	private String [] state;
	
	private String [] since;
	
	private String [] output;
	
	private String [] display_name;
	
	private String [] alias;
	
	private String [] check_source;
	
	private String [] is_reachable;
	
	private String [] hostgroup;
	
	private String [] hostgroup_object_id;
	
	private String [] last_check;
	
	private String [] next_check;
	
	private String [] check_attempts;
	
	private String [] check_execution_time;

	private int [][] statistic_service;

	//Object [] components = {state, since, output, display_name, alias, check_source, is_reachable, host_group, last_check, next_check, check_attempts, check_execution_time, statistic_service};
	
	public MonitorHost() throws SQLException{
		String [][] hosts = DIMS.getInstance().getHostInGroup().host_object_id;
		int totalHost = 0;
		for(int i = 0; i < hosts.length; i++) totalHost += hosts[i].length;
		
		host_object_id = new String [totalHost];
		state = new String [totalHost];
		since = new String [totalHost];
		output = new String [totalHost];
		display_name = new String [totalHost];
		alias = new String [totalHost];
		check_source = new String [totalHost];
		is_reachable = new String [totalHost];
		hostgroup = new String [totalHost];
		hostgroup_object_id = new String [totalHost];
		last_check = new String [totalHost];
		next_check = new String [totalHost];
		check_attempts = new String [totalHost];
		check_execution_time = new String [totalHost];
		statistic_service = new int [totalHost][5];
		int position = 0;
		for(int i = 0; i < hosts.length; i++){
			for(int j = 0; j < hosts[i].length; j++){
				RetrieveData picker = DIMS.getInstance().getPicker();
				String [] data1 = picker.getDetailHostPartOne(Integer.parseInt(hosts[i][j]));
				int [] data2 = picker.countConfiguredServiceInHost(Integer.parseInt(hosts[i][j]));
				String [] data3 = picker.getDetailHostPartThree(Integer.parseInt(hosts[i][j]));
				String data4 = picker.getDetailHostPartTwo(Integer.parseInt(hosts[i][j]));				
				
				host_object_id[position] = data1[0];
				state[position] = data1[1];
				since[position] = data1[2];
				display_name[position] = data1[3];
				alias[position] = data1[4];
				
				output[position] = data4;
				
				check_source[position] = data3[0];
				is_reachable[position] = data3[1];
				hostgroup[position] = data3[2];
				hostgroup_object_id[position] = data3[3];
				last_check[position] = data3[4];
				next_check[position] = data3[5];
				check_attempts[position] = data3[6];
				check_execution_time[position] = data3[7];
				
				statistic_service[position][0] = data2[0];
				statistic_service[position][1] = data2[1];
				statistic_service[position][2] = data2[2];
				statistic_service[position][3] = data2[3];
				statistic_service[position][4] = data2[4];
				position++;
			}
			
		}
	}
	
	public String [] getHostId(){
		return host_object_id;
	}
	
	public String [] getPartOne(int position){
		String [] partOne = {host_object_id[position], state[position], since[position], display_name[position], alias[position]};
		return partOne;
	}
	
	public int [] getNumberOfService(int position){
		int [] statistic = {statistic_service[position][0], statistic_service[position][1], statistic_service[position][2], statistic_service[position][3], statistic_service[position][4]};
		return statistic;
	}
	
	public String getPartTwo(int position){
		String partTwo = output[position];
		return partTwo;
	}
	
	public String [] getPartThree(int position){
		String [] partThree = {check_source[position], is_reachable[position], hostgroup[position], last_check[position], next_check[position], check_attempts[position], check_execution_time[position]};
		return partThree;
	}
	
	public String getGroupId(int position){
		return hostgroup_object_id[position];
	}
}

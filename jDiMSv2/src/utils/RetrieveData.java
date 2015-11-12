package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class RetrieveData {
	
	public String [][] getAccount() throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		ResultSet rs = connector.Query("select * from dims_account");
		int rowSize  = 0;
		while(rs.next()) rowSize++;
		String [][] account = new String [rowSize][2];
		rs.close();
		rs = connector.Query("select * from dims_account");
		int row = 0;
		while(rs.next()) {
			account[row][0] = rs.getString("username");
			account[row][1] = rs.getString("password");
			row++;
		}
		return account;
	}
	
	public String [][] getHostGroups() throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [][] HostGroups = null;
		int rowSize = 0;
		ResultSet rs = connector.Query("select distinct(hostgroup_id) from icinga_hostgroup_members");
		while(rs.next()) rowSize++;
		HostGroups = new String [rowSize][2];
		rs.close();
		rs = connector.Query("select distinct(ihg.hostgroup_object_id) as hostgroup_object_id, ihg.alias from icinga_hostgroups ihg join icinga_hostgroup_members ihgm on ihg.hostgroup_id = ihgm.hostgroup_id");
		int row = 0;
		while(rs.next()){
			String name = rs.getString("alias");
			HostGroups[row][0] = name;
			HostGroups[row][1] = rs.getString("hostgroup_object_id");
			row++;
		}
		return HostGroups;
	}
	
	public String [] getServiceGroups() throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [] serviceGroups = null;
		int rowSize = 0;
		ResultSet rs = connector.Query("select * from icinga_servicegroups");
		while(rs.next()) rowSize++;
		serviceGroups = new String [rowSize];
		rs.close();
		rs = connector.Query("select * from icinga_servicegroups");
		int row = 0;
		while(rs.next()){
			serviceGroups[row] = rs.getString("servicegroup_object_id");
			row++;
		}
		return serviceGroups;
	}
	
	public String countError() throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String sql = "select count(servicestatus_id) as total from icinga_servicestatus where current_state = 2";
		ResultSet rs = null;
		String total = null;
		rs = connector.Query(sql);
		while(rs.next()){
			total = rs.getString("total");
		}
		return total;
	}
	
	public String [][] getHostTitle() throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [][] hostName = null;
		ResultSet rs;
		int rowSize = 0;
		rs = connector.Query("select alias from icinga_hosts");			
		while(rs.next()) rowSize++;
		hostName = new String [rowSize][3];
		rs.close();
		int row = 0;
		rs = connector.Query("select ihs.current_state, ih.host_object_id, ih.display_name from icinga_hosts ih join icinga_hoststatus ihs on ihs.host_object_id = ih.host_object_id");
		while(rs.next()){
			hostName[row][0] = rs.getString("display_name");
			hostName[row][1] = rs.getString("host_object_id");
			hostName[row][2] = rs.getString("current_state");
			row++;
		}
		return hostName;
	}
	
	public String [] getServiceTitle() throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [] serviceName = null; 
		ResultSet rs;
		rs = connector.Query("select distinct display_name from icinga_services");
		int rowSize = 0;
		while(rs.next()) rowSize++;
		serviceName = new String [rowSize];
		rs.close();
		int row = 0;
		rs = connector.Query("select distinct display_name from icinga_services");
		while(rs.next()){
			serviceName[row] = rs.getString("display_name");
			row++;
		}
		return serviceName;
	}
	
	public String [][] getServiceState(String [] serviceName, String [][] hostName) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [][] state = new String [hostName.length][serviceName.length];
		int row = 0;
		int column = 0;
		for(row = 0; row < hostName.length; row++){
			for(column = 0; column < serviceName.length; column++){				
				String sql = "select ss.current_state, s.service_object_id from icinga_servicestatus ss join icinga_services s "
						+ "on ss.service_object_id = s.service_object_id join icinga_hosts h "
						+ "on h.host_object_id = s.host_object_id "
						+ "where h.host_object_id = "+Integer.parseInt(hostName[row][1])+" and s.display_name = "+"'"+serviceName[column]+"'";
				ResultSet rs = connector.Query(sql);
				boolean check = rs.next();
				if(check == false) state[row][column] = ".";
				else {
						String status = rs.getString("current_state");
						String service_object_id = rs.getString("service_object_id");
						state[row][column] = status + "-" +service_object_id;
				}
			}
		}
		return state;
	}
	
	
	public String [][] getHostsInGroup(int hostgroup_object_id) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [][] data = null;
		String sql = "select count(*) as sum from icinga_hoststatus ihs "
				+ "join icinga_hosts ih on ihs.host_object_id = ih.host_object_id "
				+ "join icinga_hostgroup_members ihm on ih.host_object_id = ihm.host_object_id "
				+ "join icinga_hostgroups ihg on ihm.hostgroup_id = ihg.hostgroup_id where ihg.hostgroup_object_id = "+hostgroup_object_id;
		ResultSet rss;
		
		rss = connector.Query(sql);
		int total = 0;
		while(rss.next()){
			total = rss.getInt("sum");
		}
		data = new String [total][6]; 
		rss.close();
		
		sql = "select ih.host_object_id, ihs.output, ihs.current_state, ihs.last_state_change, ih.display_name, ih.alias "
				+ "from icinga_hoststatus ihs join icinga_hosts ih on ihs.host_object_id = ih.host_object_id "
				+ "join icinga_hostgroup_members ihm on ih.host_object_id = ihm.host_object_id "
				+ "join icinga_hostgroups ihg on ihm.hostgroup_id = ihg.hostgroup_id where ihg.hostgroup_object_id = "+hostgroup_object_id;
		rss = connector.Query(sql);
		int row = 0;
		int column = 0;
		while(rss.next()){
			data[row][column] = rss.getString("host_object_id");
			column++;
			String status = rss.getString("current_state");
			data[row][column] = status;
			column++;
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			String time = formatter.format(rss.getDate("last_state_change"));
			data[row][column] = time;
			column++;
			String hostname = rss.getString("display_name");
			data[row][column] = hostname;
			column++;
			String alias = rss.getString("alias");
			data[row][column] = alias;
			column++;
			String output = rss.getString("output");
			data[row][column] = output;
			row++;
			column = 0;
		}
		
		return data;
	}
	
	public String [] getDetailHostPartOne(int host_object_id) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [] data = new String [5];
		String sql = "select h.host_object_id, hs.current_state, hs.last_state_change, h.display_name, h.alias from icinga_hosts h "
				+ "join icinga_hoststatus hs on h.host_object_id = hs.host_object_id "
				+ "where h.host_object_id = "+host_object_id;
		ResultSet rs = connector.Query(sql);
		int row = 0;
		while(rs.next()){
			data[row] = rs.getString("host_object_id");
			row++;
			data[row] = rs.getString("current_state");
			row++;
			String next = rs.getString("last_state_change");
			String [] split = next.split(" ");
			split[0] = split[0].substring(2, 7);
			split[1] = split[1].substring(0, 5);
			data[row] = split[0] + " " +split[1];
			row++;
			data[row] = rs.getString("display_name");
			row++;
			data[row] = rs.getString("alias");
		}
		return data;
	}
	
	public int [] countConfiguredServiceInHost(int host_object_id) throws SQLException{
		//
		DBConnection connector = DIMS.getInstance().getConnector();
		int [] data = new int [5];
		String sql = "select count(servicestatus_id) as total from icinga_servicestatus iss "
				+ "join icinga_services isv on iss.service_object_id = isv.service_object_id "
				+ "join icinga_hosts ih on isv.host_object_id = ih.host_object_id where ih.host_object_id = "+host_object_id+" and iss.current_state=0";
		ResultSet rs;
		//Count OK
		rs = connector.Query(sql);
		while(rs.next()){
			data[0] = rs.getInt("total");
		}
		rs.close();
		
		//Count warning
		sql = "select count(servicestatus_id) as total from icinga_servicestatus iss "
			+ "join icinga_services isv on iss.service_object_id = isv.service_object_id "
			+ "join icinga_hosts ih on isv.host_object_id = ih.host_object_id where ih.host_object_id = "+host_object_id+" and iss.current_state=1";
		rs = connector.Query(sql);
		while(rs.next()) data[1] = rs.getInt("total");
		rs.close();
		
		//Count critical
		sql = "select count(servicestatus_id) as total from icinga_servicestatus iss "
			+ "join icinga_services isv on iss.service_object_id = isv.service_object_id "
			+ "join icinga_hosts ih on isv.host_object_id = ih.host_object_id where ih.host_object_id = "+host_object_id+" and iss.current_state=2";
		rs = connector.Query(sql);
		while(rs.next()) data[2] = rs.getInt("total");
		rs.close();
		
		//Count pending
		sql = "select count(servicestatus_id) as total from icinga_servicestatus iss "
			+ "join icinga_services isv on iss.service_object_id = isv.service_object_id "
			+ "join icinga_hosts ih on isv.host_object_id = ih.host_object_id where ih.host_object_id = "+host_object_id+" and iss.current_state=3";
		rs = connector.Query(sql);
		while(rs.next()) data[3] = rs.getInt("total");
		rs.close();
		//Count unknown
			sql = "select count(servicestatus_id) as total from icinga_servicestatus iss "
			+ "join icinga_services isv on iss.service_object_id = isv.service_object_id "
			+ "join icinga_hosts ih on isv.host_object_id = ih.host_object_id where ih.host_object_id = "+host_object_id+" and iss.current_state not between 0 and 3";
		rs = connector.Query(sql);
		while(rs.next()) data[4] = rs.getInt("total");

		// TODO Auto-generated catch block
		return data;
	}
	
	public int [] countConfiguredHostInGroup(int hostgroup_object_id) throws SQLException{
		//
		DBConnection connector = DIMS.getInstance().getConnector();
		int [] data = new int [2];
		String sql = "select count(host_id) as total from icinga_hoststatus ihs "
				+ "join icinga_hosts ih on ihs.host_object_id = ih.host_object_id "
				+ "join icinga_hostgroup_members ihgm on ihgm.host_object_id = ih.host_object_id join icinga_hostgroups ihg on ihgm.hostgroup_id = ihg.hostgroup_id where ihg.hostgroup_object_id = "+hostgroup_object_id+" and ihs.current_state=0";
		ResultSet rs;
		//Count Up
		rs = connector.Query(sql);
		while(rs.next()){
			data[0] = rs.getInt("total");
		}
		rs.close();
		
		//Count down
		sql = "select count(host_id) as total from icinga_hoststatus ihs "
				+ "join icinga_hosts ih on ihs.host_object_id = ih.host_object_id "
				+ "join icinga_hostgroup_members ihgm on ihgm.host_object_id = ih.host_object_id join icinga_hostgroups ihg on ihgm.hostgroup_id = ihg.hostgroup_id where ihg.hostgroup_object_id = "+hostgroup_object_id+" and ihs.current_state=1";
		rs = connector.Query(sql);
		while(rs.next()) data[1] = rs.getInt("total");
		rs.close();

		// TODO Auto-generated catch block
		return data;
	}
	
	public String getDetailHostPartTwo (int host_object_id) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String data = null;
		String sql = "select output from icinga_hoststatus "
				+ "join icinga_hosts on icinga_hoststatus.host_object_id = icinga_hosts.host_object_id where icinga_hosts.host_object_id ="+host_object_id;
		ResultSet rs = connector.Query(sql);
		while(rs.next()) data = rs.getString("output");
		return data;
	}
	
	public String [] getDetailHostPartThree(int host_object_id) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [] data = new String [8];
		String sql = "select ihg.hostgroup_object_id, hs.check_source, hs.is_reachable, hs.last_check, hs.next_check, "
				+ "hs.current_check_attempt, hs.max_check_attempts, hs.execution_time, ihg.alias as hostgroup_name "
				+ "from icinga_hoststatus hs join icinga_hosts ih on hs.host_object_id = ih.host_object_id "
				+ "join icinga_hostgroup_members ihm on ih.host_object_id = ihm.host_object_id "
				+ "join icinga_hostgroups ihg on ihm.hostgroup_id = ihg.hostgroup_id where ih.host_object_id = "+host_object_id;
		ResultSet rs = connector.Query(sql);
		int row = 0;
		while(rs.next()){
			data[row] = rs.getString("check_source");
			row++;
			if(rs.getString("is_reachable").equals("1")) data[row] = "Yes";
			else data[row] = "No";
			row++;
			data[row] = rs.getString("hostgroup_name");
			row++;
			data[row] = rs.getString("hostgroup_object_id");
			row++;
			String last = "";
//			if(rs.getString("last_check").equals("0000-00-00 00:00:00")) last = "0000-00-00 00:00:00";
			last = rs.getString("last_check");
			String [] parts = last.split(" ");
			parts[1] = parts[1].substring(0, 8);
			data[row] = parts[1];
			row++;
			String next = "";
//			if(rs.getString("next_check").equals("0000-00-00 00:00:00")) next = "0000-00-00 00:00:00";
			next = rs.getString("next_check");
			String [] split = next.split(" ");
			split[1] = split[1].substring(0, 8);
			data[row] = split[1];
			row++;
			String state = " (soft state)";
			if(rs.getInt("current_check_attempt") == rs.getInt("max_check_attempts")) state = " (hard state)";
			data[row] = rs.getString("current_check_attempt")+"/"+rs.getString("max_check_attempts")+state;
			row++;
			data[row] = rs.getString("execution_time");
			data[row] = data[row].substring(0, 6) + " s";
			row++;
		}
		return data;
	}
	
	public String [][] getServicesInHost(int host_object_id) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [][] data = null;
		String sql =  "select isv.service_object_id, isv.display_name, io.name2, iss.current_state, iss.last_state_change, iss.output, iss.long_output "
				+ "from icinga_servicestatus iss join icinga_services isv on iss.service_object_id = isv.service_object_id "
				+ "join icinga_hosts ih on isv.host_object_id = ih.host_object_id join icinga_objects io on io.object_id = isv.service_object_id where ih.host_object_id = "+host_object_id;
		ResultSet rs = connector.Query(sql);
		int rowSize = 0;
		while(rs.next()) rowSize++;
		data = new String [rowSize][6];
		int row = 0;
		int column = 0;
		rs.close();
		rs = connector.Query(sql);
		while(rs.next()){
			data[row][column] = rs.getString("service_object_id");
			column++;
			data[row][column] = rs.getString("current_state");
			column++;
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			data[row][column] = formatter.format(rs.getDate("last_state_change"));
			column++;
			data[row][column] = rs.getString("display_name");
			column++;
			data[row][column] = rs.getString("name2");
			column++;
			data[row][column] = rs.getString("output");
			row++;
			column = 0;
		}
		return data;
	}	
	public String [] getDetailServicePartOne(int service_object_id) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [] data = new String [9];
		
		String sql = "select ihg.hostgroup_object_id, ih.host_object_id, ih.alias, ih.display_name as host_name, io.name2, isv.service_object_id, isv.display_name, iss.current_state, iss.last_state_change from icinga_services isv "
				+ "join icinga_servicestatus iss on isv.service_object_id = iss.service_object_id "
				+ "join icinga_objects io on isv.service_object_id = io.object_id "
				+ "join icinga_hosts ih on isv.host_object_id = ih.host_object_id "
				+ "join icinga_hostgroup_members ihgm on ihgm.host_object_id = ih.host_object_id "
				+ "join icinga_hostgroups ihg on ihg.hostgroup_id = ihgm.hostgroup_id "
				+ "where isv.service_object_id = "+service_object_id;
		ResultSet rs = connector.Query(sql);
		int row = 0;
		while(rs.next()){
			data[row] = rs.getString("service_object_id");
			row++;
			data[row] = rs.getString("host_object_id");
			row++;
			data[row] = rs.getString("current_state");
			row++;
			String next = rs.getString("last_state_change");
			String [] split = next.split(" ");
			split[0] = split[0].substring(2, 7);
			split[1] = split[1].substring(0, 5);
			data[row] = split[0] + " " +split[1];
			row++;
			data[row] = rs.getString("display_name");
			row++;
			data[row] = rs.getString("name2");
			row++;
			data[row] = rs.getString("host_name");
			row++;
			data[row] = rs.getString("alias");
			row++;
			data[row] = rs.getString("hostgroup_object_id");
		}
		return data;
	}
	
	public String getDetailServicePartTwo(int service_object_id) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String data = null;
		String sql = "select iss.output, iss.current_state, iss.long_output from icinga_services isv "
				+ "join icinga_servicestatus iss on isv.service_object_id = iss.service_object_id "
				+ "join icinga_hosts ih on isv.host_object_id = ih.host_object_id "
				+ "where isv.service_object_id = "+service_object_id;
		ResultSet rs = connector.Query(sql);
		while(rs.next()){
			if(rs.getString("output") == null) return "";
			if(rs.getInt("current_state") < 3)
			{
				String output = rs.getString("long_output");
				output = output.replaceAll("(\\\\n)", "<br>");
				data = "<HTML>"+rs.getString("output")+"<br>"+output+"</HTML>";
			}
			else data = rs.getString("output");
		}
		return data;
		
	}
	
	public String [] getDetailServicePartThree(int service_object_id) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		//Count size
		String sql = "select iss.current_state, isg.servicegroup_object_id, isg.alias as service_group, iss.perfdata, iss.check_source, iss.is_reachable, iss.last_check, iss.next_check, iss.current_check_attempt, iss.max_check_attempts, iss.execution_time "
				+ "from icinga_services isv join icinga_servicestatus iss on isv.service_object_id = iss.service_object_id "
				+ "join icinga_hosts ih on isv.host_object_id = ih.host_object_id "
				+ "join icinga_servicegroup_members ism on isv.service_object_id = ism.service_object_id "
				+ "join icinga_servicegroups isg on ism.servicegroup_id = isg.servicegroup_id "
				+ "where isv.service_object_id = "+service_object_id;
		ResultSet rs = connector.Query(sql);
		int circle = 0;
		int row = 0;
		int totalSize = 0;
		while(rs.next()) totalSize++;
		rs.close();
		int sizeExtended = (totalSize-1)*2;
		String data [] = new String[9+sizeExtended];
		//Query
		rs = connector.Query(sql);
		while(rs.next()){
			if(circle > 0){
				data[row] = rs.getString("service_group");
				row++;
				data[row] = rs.getString("servicegroup_object_id");
				row++;
				continue;
			}
			
			String perf_data = rs.getString("perfdata");
			if(perf_data != null){
				perf_data = perf_data.replaceAll(" \' ", "<br>");
				data[row] = "<HTML>"+perf_data+"</HTMTL>";
				row++;
			}
			else{
				data[row] = "";
				row++;
			}
			
			data[row] = rs.getString("check_source");
			row++;
			data[row] = rs.getString("is_reachable");
			row++;
			data[row] = rs.getString("service_group");
			row++;
			data[row] = rs.getString("servicegroup_object_id");
			row++;
			if(rs.getInt("current_state") < 3 || rs.getString("last_check") != null)
			{	
				String last = "";
				last = rs.getString("last_check");
				String [] split = last.split(" ");
				data[row] = split[1];
				data[row] = data[row].substring(0, 8);
			}
			else{
				data[row] = "waiting";
			}
			row++;
			String next = "";
			next = rs.getString("next_check");
			if(rs.getInt("current_state") < 3 || rs.getString("next_check") != null){
				String [] split = next.split(" ");
				data[row] = split[1];
				data[row] = data[row].substring(0, 8);
			}
			else data[row] = "waiting";
			row++;
			
			data[row] = rs.getString("current_check_attempt")+"/"+rs.getString("max_check_attempts");
			if(rs.getString("current_check_attempt").equals(rs.getString("max_check_attempts"))) data[row] += " (hard state)";
			else data[row] += " (soft state)";
			row++;
			
			data[row] = rs.getString("execution_time");
			row++;
			circle++;
		}
		return data;
	}
	
	public String [][] getHostHistory(int host_object_id) throws SQLException{
		String [][] data = null;
		DBConnection connector = DIMS.getInstance().getConnector();
		String sql = "select ih.host_object_id, ih.display_name, ish.state, ish.state_time, ish.output, ish.current_check_attempt, ish.max_check_attempts from icinga_statehistory ish join icinga_hosts ih on ish.object_id = ih.host_object_id where ish.object_id = "+host_object_id+" limit 10";
		ResultSet rs = connector.Query(sql);
		int DataSize = 0;
		while(rs.next()) DataSize++;
		if(DataSize == 0) return null;
		data = new String[DataSize][6];
		rs.close();
		rs = connector.Query(sql);
		int position = 0;
		while(rs.next()){
			data[position][0] = rs.getString("host_object_id");
			data[position][1] = rs.getString("state");
			String next = rs.getString("state_time");
			String [] split = next.split(" ");
			split[0] = split[0].substring(2, 7);
			split[1] = split[1].substring(0, 5);
			data[position][2] = split[0] + " " + split[1];
			data[position][3] = rs.getString("display_name");
			data[position][4] = "["+rs.getString("current_check_attempt") + "/" +rs.getString("max_check_attempts")+"]";
			data[position][5] = rs.getString("output");
			position++;
		}
		return data;
	}
	
	public String [][] getServiceHistory(int service_object_id) throws SQLException{
		String [][] data = null;
		DBConnection connector = DIMS.getInstance().getConnector();
		String sql = "select ih.service_object_id, ih.display_name, ish.state, ish.state_time, ish.output, ish.current_check_attempt, ish.max_check_attempts from icinga_statehistory ish join icinga_services ih on ish.object_id = ih.service_object_id where ish.object_id = "+service_object_id+" limit 15";
		ResultSet rs = connector.Query(sql);
		int DataSize = 0;
		while(rs.next()) DataSize++;
		
		if(DataSize == 0) return null;
		
		data = new String[DataSize][6];
		rs.close();
		rs = connector.Query(sql);
		int position = 0;
		while(rs.next()){
			data[position][0] = rs.getString("service_object_id");
			data[position][1] = rs.getString("state");
			String next = rs.getString("state_time");
			String [] split = next.split(" ");
			split[0] = split[0].substring(2, 7);
			split[1] = split[1].substring(0, 5);
			data[position][2] = split[0] + " " + split[1];
			
			data[position][3] = rs.getString("display_name");
			data[position][4] = "["+rs.getString("current_check_attempt") + "/"+rs.getString("max_check_attempts")+"]";
			data[position][5] = rs.getString("output");
			position++;
		}
		return data;
	}
	
	public String [][] getServiceByStateInHost(int host_object_id, int status) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [][] data = null;
		String sql =  "select isv.service_object_id, isv.display_name, io.name2, iss.current_state, iss.last_state_change, iss.output, iss.long_output "
				+ "from icinga_servicestatus iss join icinga_services isv on iss.service_object_id = isv.service_object_id "
				+ "join icinga_hosts ih on isv.host_object_id = ih.host_object_id join icinga_objects io on io.object_id = isv.service_object_id where ih.host_object_id = "+host_object_id+" and iss.current_state = "+status;
		ResultSet rs = connector.Query(sql);
		int rowSize = 0;
		while(rs.next()) rowSize++;
		data = new String [rowSize][6];
		int row = 0;
		int column = 0;
		rs.close();
		rs = connector.Query(sql);
		while(rs.next()){
			data[row][column] = rs.getString("service_object_id");
			column++;
			data[row][column] = rs.getString("current_state");
			column++;
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			data[row][column] = formatter.format(rs.getDate("last_state_change"));
			column++;
			data[row][column] = rs.getString("display_name");
			column++;
			data[row][column] = rs.getString("name2");
			column++;
			data[row][column] = rs.getString("output");
			row++;
			column = 0;
		}
		return data;
	}
	
	public String [][] getHostByStateInGroup(int hostgroup_object_id, int status) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [][] data = null;
		String sql =  "select ihg.alias as hostgroup_name, ih.host_object_id, ih.alias, ih.display_name, ihs.current_state, ihs.last_state_change, ihs.output "
				+ "from icinga_hoststatus ihs join icinga_hosts ih on ihs.host_object_id = ih.host_object_id "
				+ "join icinga_hostgroup_members ihgm on ihgm.host_object_id = ih.host_object_id join icinga_hostgroups ihg on ihg.hostgroup_id = ihgm.hostgroup_id where ihg.hostgroup_object_id = "+hostgroup_object_id+" and ihs.current_state = "+status;
		ResultSet rs = connector.Query(sql);
		int rowSize = 0;
		while(rs.next()) rowSize++;
		data = new String [rowSize][7];
		int row = 0;
		int column = 0;
		rs.close();
		rs = connector.Query(sql);
		while(rs.next()){
			data[row][column] = rs.getString("host_object_id");
			column++;
			data[row][column] = rs.getString("current_state");
			column++;
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			data[row][column] = formatter.format(rs.getDate("last_state_change"));
			column++;
			data[row][column] = rs.getString("display_name");
			column++;
			data[row][column] = rs.getString("alias");
			column++;
			data[row][column] = rs.getString("output");
			column++;
			data[row][column] = rs.getString("hostgroup_name");
			row++;
			column = 0;
		}
		return data;
	}
	
	public String [][] getServiceByName(String display_name) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [][] data = null;
		String sql =  "select isv.service_object_id, isv.display_name, io.name2, iss.current_state, iss.last_state_change, iss.output, iss.long_output "
				+ "from icinga_servicestatus iss join icinga_services isv on iss.service_object_id = isv.service_object_id "
				+ "join icinga_hosts ih on isv.host_object_id = ih.host_object_id join icinga_objects io on io.object_id = isv.service_object_id where isv.display_name = '"+display_name+"'";
		ResultSet rs = connector.Query(sql);
		int rowSize = 0;
		while(rs.next()) rowSize++;
		data = new String [rowSize][6];
		int row = 0;
		int column = 0;
		rs.close();
		rs = connector.Query(sql);
		while(rs.next()){
			data[row][column] = rs.getString("service_object_id");
			column++;
			data[row][column] = rs.getString("current_state");
			column++;
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			data[row][column] = formatter.format(rs.getDate("last_state_change"));
			column++;
			data[row][column] = rs.getString("display_name");
			column++;
			data[row][column] = rs.getString("name2");
			column++;
			data[row][column] = rs.getString("output");
			row++;
			column = 0;
		}
		return data;
	}
	
	
	public String [][] getServiceInGroup(int sgroup_object_id) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [][] data = null;
		String sql = "select isg.alias, isv.service_object_id, isv.display_name, io.name2, iss.current_state, iss.last_state_change, iss.output "
				+ "from icinga_servicestatus iss join icinga_services isv on iss.service_object_id = isv.service_object_id "
				+ "join icinga_servicegroup_members isgm on isv.service_object_id = isgm.service_object_id join icinga_servicegroups isg on isgm.servicegroup_id = isg.servicegroup_id join icinga_objects io on io.object_id = isv.service_object_id where isg.servicegroup_object_id = "+sgroup_object_id;
		ResultSet rs = connector.Query(sql);
		int rowSize = 0;
		while(rs.next()) rowSize++;
		data = new String [rowSize][7];
		int row = 0;
		int column = 0;
		rs.close();
		rs = connector.Query(sql);
		while(rs.next()){
			data[row][column] = rs.getString("service_object_id");
			column++;
			data[row][column] = rs.getString("current_state");
			column++;
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			data[row][column] = formatter.format(rs.getDate("last_state_change"));
			column++;
			data[row][column] = rs.getString("display_name");
			column++;
			data[row][column] = rs.getString("name2");
			column++;
			data[row][column] = rs.getString("output");
			column++;
			data[row][column] = rs.getString("alias");
			row++;
			column = 0;
		}
		return data;
	}
	
	public int [] countConfiguredServiceInGroup(int sgroup_object_id) throws SQLException{
		//
		DBConnection connector = DIMS.getInstance().getConnector();
		int [] data = new int [5];
		String sql = "select count(*) as total from icinga_servicestatus iss join icinga_services isv on iss.service_object_id = isv.service_object_id join icinga_servicegroup_members isgm on isv.service_object_id = isgm.service_object_id join icinga_servicegroups isg on isgm.servicegroup_id = isg.servicegroup_id join icinga_objects io on io.object_id = isv.service_object_id where isg.servicegroup_object_id = "+sgroup_object_id+" and iss.current_state = 0";
		ResultSet rs;
		//Count OK
		rs = connector.Query(sql);
		while(rs.next()){
			data[0] = rs.getInt("total");
		}
		rs.close();
		
		//Count warning
		sql = "select count(*) as total from icinga_servicestatus iss join icinga_services isv on iss.service_object_id = isv.service_object_id join icinga_servicegroup_members isgm on isv.service_object_id = isgm.service_object_id join icinga_servicegroups isg on isgm.servicegroup_id = isg.servicegroup_id join icinga_objects io on io.object_id = isv.service_object_id where isg.servicegroup_object_id = "+sgroup_object_id+" and iss.current_state = 1";
		rs = connector.Query(sql);
		while(rs.next()) data[1] = rs.getInt("total");
		rs.close();
		
		//Count critical
		sql = "select count(*) as total from icinga_servicestatus iss join icinga_services isv on iss.service_object_id = isv.service_object_id join icinga_servicegroup_members isgm on isv.service_object_id = isgm.service_object_id join icinga_servicegroups isg on isgm.servicegroup_id = isg.servicegroup_id join icinga_objects io on io.object_id = isv.service_object_id where isg.servicegroup_object_id = "+sgroup_object_id+" and iss.current_state = 2";
		rs = connector.Query(sql);
		while(rs.next()) data[2] = rs.getInt("total");
		rs.close();
		
		//Count pending
		sql = "select count(*) as total from icinga_servicestatus iss join icinga_services isv on iss.service_object_id = isv.service_object_id join icinga_servicegroup_members isgm on isv.service_object_id = isgm.service_object_id join icinga_servicegroups isg on isgm.servicegroup_id = isg.servicegroup_id join icinga_objects io on io.object_id = isv.service_object_id where isg.servicegroup_object_id = "+sgroup_object_id+" and iss.current_state = 3";
		rs = connector.Query(sql);
		while(rs.next()) data[3] = rs.getInt("total");
		rs.close();
		//Count unknown
		sql = "select count(*) as total from icinga_servicestatus iss join icinga_services isv on iss.service_object_id = isv.service_object_id join icinga_servicegroup_members isgm on isv.service_object_id = isgm.service_object_id join icinga_servicegroups isg on isgm.servicegroup_id = isg.servicegroup_id join icinga_objects io on io.object_id = isv.service_object_id where isg.servicegroup_object_id = "+sgroup_object_id+" and iss.current_state not between 0 and 3";
		rs = connector.Query(sql);
		while(rs.next()) data[4] = rs.getInt("total");
		// TODO Auto-generated catch block
		return data;
	}
	
	public String [][] getServiceByStateInGroup(int sg_object_id, int status) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String [][] data = null;
		String sql =  "select isg.alias, isv.service_object_id, isv.display_name, io.name2, iss.current_state, iss.last_state_change, iss.output "
				+ "from icinga_servicestatus iss join icinga_services isv on iss.service_object_id = isv.service_object_id "
				+ "join icinga_servicegroup_members isgm on isgm.service_object_id = isv.service_object_id join icinga_servicegroups isg on isgm.servicegroup_id = isg.servicegroup_id join icinga_objects io on io.object_id = isv.service_object_id where isg.servicegroup_object_id = "+sg_object_id+" and iss.current_state = "+status;
		ResultSet rs = connector.Query(sql);
		int rowSize = 0;
		while(rs.next()) rowSize++;
		data = new String [rowSize][7];
		int row = 0;
		int column = 0;
		rs.close();
		rs = connector.Query(sql);
		while(rs.next()){
			data[row][column] = rs.getString("service_object_id");
			column++;
			data[row][column] = rs.getString("current_state");
			column++;
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			data[row][column] = formatter.format(rs.getDate("last_state_change"));
			column++;
			data[row][column] = rs.getString("display_name");
			column++;
			data[row][column] = rs.getString("name2");
			column++;
			data[row][column] = rs.getString("output");
			column++;
			data[row][column] = rs.getString("alias");
			row++;
			column = 0;
		}
		return data;
	}
	
	public String [][] searchHostGroupByName(String keyword) throws SQLException{
		int totalSize = 0;
		int row = 0;
		DBConnection connector = DIMS.getInstance().getConnector();
		String [][] data = null;
		String sql = "select count(alias) as total from icinga_hostgroups where alias like \'%"+keyword+"%'";
		ResultSet rs = connector.Query(sql);
		
		while(rs.next()) totalSize = rs.getInt("total");
		data = new String [totalSize][2];
		rs.close();
		sql = "select alias, hostgroup_object_id from icinga_hostgroups where alias like \'%"+keyword+"%'";
		rs = connector.Query(sql);
		
		while(rs.next()){
			data[row][0] = rs.getString("hostgroup_object_id");
			data[row][1] = rs.getString("alias");
			row++;
		}
		return data;
	}
	
	public String [][] searchServiceGroupByName(String keyword) throws SQLException{
		int totalSize = 0;
		int row = 0;
		DBConnection connector = DIMS.getInstance().getConnector();
		String [][] data = null;
		String sql = "select count(alias) as total from icinga_servicegroups where alias like \'%"+keyword+"%\'";
		ResultSet rs = connector.Query(sql);
		
		while(rs.next()) totalSize = rs.getInt("total");
		data = new String [totalSize][2];
		rs.close();
		sql = "select alias, servicegroup_object_id from icinga_servicegroups where alias like \'%"+keyword+"%\'";
		rs = connector.Query(sql);
		
		while(rs.next()){
			data[row][0] = rs.getString("servicegroup_object_id");
			data[row][1] = rs.getString("alias");
			row++;
		}
		return data;
	}
	
	public String [][] searchHostByName(String keyword) throws SQLException{
		int totalSize = 0;
		int row = 0;
		DBConnection connector = DIMS.getInstance().getConnector();
		String data [][] = null;
		String sql = "select count(*) as total from icinga_hosts ih join icinga_hoststatus ihs on ih.host_object_id = ihs.host_object_id where ih.alias like \'%"+keyword+"%\' or ih.display_name like \'%"+keyword+"%\'";
		ResultSet rs = connector.Query(sql);
		while(rs.next())  totalSize = rs.getInt("total");
		data = new String [totalSize][6];
		rs.close();
		sql = "select ih.host_object_id, ih.alias, ih.display_name, ihs.current_state, ihs.last_state_change, ihs.output from icinga_hosts ih join icinga_hoststatus ihs on ih.host_object_id = ihs.host_object_id where ih.alias like \'%"+keyword+"%\' or ih.display_name like \'%"+keyword+"%\'";
		rs = connector.Query(sql);
		while(rs.next()){
			data[row][0] = rs.getString("host_object_id");
			data[row][1] = rs.getString("current_state");
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			data[row][2] = formatter.format(rs.getDate("last_state_change"));
			data[row][3] = rs.getString("display_name");
			data[row][4] = rs.getString("alias");
			data[row][5] = rs.getString("output");
			row++;
		}
		return data;
	}
	
	public String [][] searchServiceByName(String keyword) throws SQLException{
		int totalSize = 0;
		int row = 0;
		DBConnection connector = DIMS.getInstance().getConnector();
		String data [][] = null;
		String sql = "select count(*) as total from icinga_services isv join icinga_objects io on isv.service_object_id = io.object_id where isv.display_name like \'%"+keyword+"%\' or io.name2 like \'%"+keyword+"%\'";
		ResultSet rs = connector.Query(sql);
		while(rs.next())  totalSize = rs.getInt("total");
		data = new String [totalSize][6];
		rs.close();
		sql = "select isv.service_object_id, io.name2 as alias, isv.display_name, iss.current_state, iss.last_state_change, iss.output from icinga_services isv join icinga_servicestatus iss on isv.service_object_id = iss.service_object_id join icinga_objects io on isv.service_object_id = io.object_id where isv.display_name like \'%"+keyword+"%\' or io.name2 like \'%"+keyword+"%\'";
		rs = connector.Query(sql);
		while(rs.next()){
			data[row][0] = rs.getString("service_object_id");
			data[row][1] = rs.getString("current_state");
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			data[row][2] = formatter.format(rs.getDate("last_state_change"));
			data[row][3] = rs.getString("display_name");
			data[row][4] = rs.getString("alias");
			data[row][5] = rs.getString("output");
			row++;
		}
		return data;
	}
}

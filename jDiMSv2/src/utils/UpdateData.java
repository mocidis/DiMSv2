package utils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class UpdateData {
	public static void updateHostSchedule(int host_object_id, String date) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String sql = "update icinga_hoststatus set next_check = \'"+date+ "\' where host_object_id = "+host_object_id;
		connector.Update(sql);
	}
	
	public static void updateServiceSchedule(int service_object_id, String date) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String sql = "update icinga_servicestatus set next_check = \'"+date+ "\' where service_object_id = "+service_object_id;
		connector.Update(sql);
	}
	
	public static void updatePassword(String username, String password) throws SQLException{
		DBConnection connector = DIMS.getInstance().getConnector();
		String sql = "update dims_account set password = \"" +password+ "\" where username = \"" +username+ "\"";
		connector.Update(sql);
	}
}

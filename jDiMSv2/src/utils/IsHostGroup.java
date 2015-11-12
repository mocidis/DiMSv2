package utils;

import java.sql.SQLException;

public final class IsHostGroup {
	public static boolean check(int group_id) throws SQLException{
		String [][] hostGroup = DIMS.getInstance().getHostGroups().HostGroups;
		for(int i = 0; i < hostGroup.length; i++){
			if(group_id == Integer.parseInt(hostGroup[i][1])) return true;
		}
		return false;
	}
}

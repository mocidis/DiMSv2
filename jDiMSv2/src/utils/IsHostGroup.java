package utils;

import java.sql.SQLException;

public final class IsHostGroup {
	public static boolean check(int group_id) throws SQLException{
<<<<<<< HEAD
		String [][] hostGroup = DIMS.getInstance().getHostGroups().getHostGroup();
=======
		String [][] hostGroup = DIMS.getInstance().getHostGroups().HostGroups;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		for(int i = 0; i < hostGroup.length; i++){
			if(group_id == Integer.parseInt(hostGroup[i][1])) return true;
		}
		return false;
	}
}

package utils;

import java.sql.SQLException;

public final class IsHost {
	public static boolean check(int host_object_id) throws SQLException{
		String [][] host = DIMS.getInstance().getHostInGroup().getHostObjectId();
		for(int i = 0; i < host.length; i++){
			for(int j = 0; j < host[i].length; j++){
				if(host_object_id == Integer.parseInt(host[i][j])) return true;
			}
		}
		return false;
	}
}

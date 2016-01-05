package utils;

import java.sql.SQLException;

public final class IsHost {
	public static boolean check(int host_object_id) throws SQLException{
<<<<<<< HEAD
		String [][] host = DIMS.getInstance().getHostInGroup().getHostObjectId();
=======
		String [][] host = DIMS.getInstance().getHostInGroup().host_object_id;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		for(int i = 0; i < host.length; i++){
			for(int j = 0; j < host[i].length; j++){
				if(host_object_id == Integer.parseInt(host[i][j])) return true;
			}
		}
		return false;
	}
}

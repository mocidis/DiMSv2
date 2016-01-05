package utils;

import java.sql.SQLException;

public final class IsService {
	public static boolean check(int service_object_id) throws SQLException{
<<<<<<< HEAD
		String [][] service = DIMS.getInstance().getServiceInHost().getServiceObjectId();
=======
		String [][] service = DIMS.getInstance().getServiceInHost().service_object_id;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		for(int i = 0; i < service.length; i++){
			for(int j = 0; j < service[i].length; j++){
				if(service_object_id == Integer.parseInt(service[i][j])) return true;
			}
		}
		return false;
	}
}

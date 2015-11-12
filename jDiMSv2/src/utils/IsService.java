package utils;

import java.sql.SQLException;

public final class IsService {
	public static boolean check(int service_object_id) throws SQLException{
		String [][] service = DIMS.getInstance().getServiceInHost().service_object_id;
		for(int i = 0; i < service.length; i++){
			for(int j = 0; j < service[i].length; j++){
				if(service_object_id == Integer.parseInt(service[i][j])) return true;
			}
		}
		return false;
	}
}

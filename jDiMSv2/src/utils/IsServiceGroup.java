package utils;

import java.sql.SQLException;

public class IsServiceGroup {
	public static boolean check(String sg_oid) throws SQLException{
		for(int i = 0; i < DIMS.getInstance().getServiceGroups().getServiceGroupsID().length; i++){
			if(sg_oid.equals(DIMS.getInstance().getServiceGroups().getServiceGroupsID()[i])) return true;
		}
		return false;
	}
}

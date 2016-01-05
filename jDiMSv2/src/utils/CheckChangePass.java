package utils;


public class CheckChangePass {
	public static int check(String oldPass, String newPass, String rePass) throws Exception {
		if(oldPass.equals(DIMS.getInstance().getPassword()) && newPass.equals(rePass)) {
			UpdateData.updatePassword(DIMS.getInstance().getUser(), HashingPassword.hash(newPass));
			return 0;
		}
		else if(!oldPass.equals(DIMS.getInstance().getPassword())) return 1;
		else if(!newPass.equals(rePass)) return 2;
		else return 3;
	}
}

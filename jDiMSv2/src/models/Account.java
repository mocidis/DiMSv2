package models;

import java.sql.SQLException;

import utils.DIMS;
import utils.RetrieveData;

public class Account {
<<<<<<< HEAD
	private String [] username;
	private String [] password;
=======
	public String [] username;
	public String [] password;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	
	public Account() throws SQLException{
		RetrieveData picker = new RetrieveData();
		String [][] account = picker.getAccount();
		int size = account.length;
		username = new String [size];
		password = new String [size];
		for(int i = 0; i < size; i++){
			username[i] = account[i][0];
			password[i] = account[i][1];
		}
	}
<<<<<<< HEAD
	
	public String [] getUsername() {
		return username;
	}
	
	public String [] getPassword() {
		return password;
	}
=======
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
}

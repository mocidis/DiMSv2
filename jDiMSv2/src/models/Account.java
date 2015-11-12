package models;

import java.sql.SQLException;

import utils.DIMS;
import utils.RetrieveData;

public class Account {
	public String [] username;
	public String [] password;
	
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
}

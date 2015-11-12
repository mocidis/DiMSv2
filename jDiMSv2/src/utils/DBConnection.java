package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class DBConnection {
	// TODO:
	private Connection connector;
	private Statement statement;
	private java.sql.PreparedStatement preStatement;
	public DBConnection(String username, String password, String address, int port) throws SQLException{	
		connector = DriverManager.getConnection
				("jdbc:mysql://"+address+":"+port+"/icinga?zeroDateTimeBehavior=convertToNull&user="+username+"&password="+password);
		statement = connector.createStatement();
	}
	
	public ResultSet Query(String sql) throws SQLException{
		ResultSet rs = statement.executeQuery(sql);
		return rs;
	}
	
	public void Update(String sql) throws SQLException{
	    preStatement = connector.prepareStatement(sql);
	    preStatement.executeUpdate();
	}
}

package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import gui.FrameNotification;

public class ParseConfiguration {
	private static ParseConfiguration instance;
	private String resource;
	private String configurationFile = "DiMS.conf";
	private BufferedReader reader;
	
<<<<<<< HEAD
	private String dbType = null;
=======
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	private String dbAddress = null;
	private String dbPort = null;
	private String dbUsername = null;
	private String dbPassword = null;
	private String socketPort = null;
	private String externalPipeLink = null;
	private String maximumThread = null;
	
<<<<<<< HEAD
	/**
	 * 
	 */
=======
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	public ParseConfiguration() {
		try {
	        File jarPath = new File(ParseConfiguration.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	        resource = jarPath.getParentFile().getPath(); //JAR
<<<<<<< HEAD
//	        resource = jarPsath.getParentFile().getParentFile().getPath(); //IDE
			BufferedReader reader = new BufferedReader(new FileReader(new File(this.resource+"/"+this.configurationFile)));
			
			String currentLine = null;
			String [] fields = {"DBType", "DBAddress", "DBPort", "DBUsername", "DBPassword", "SocketPort", "IcingaExternalCommandPipe", "MaxThread"};
			String [] values = {dbType, dbAddress, dbPort, dbUsername, dbPassword, socketPort, externalPipeLink, maximumThread};
=======
//	        resource = jarPath.getParentFile().getParentFile().getPath(); //IDE
			BufferedReader reader = new BufferedReader(new FileReader(new File(this.resource+"/"+this.configurationFile)));
			
			String currentLine = null;
			String [] fields = {"DBAddress", "DBPort", "DBUsername", "DBPassword", "SocketPort", "IcingaExternalCommandPipe", "MaxThread"};
			String [] values = {dbAddress, dbPort, dbUsername, dbPassword, socketPort, externalPipeLink, maximumThread};
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
			while((currentLine = reader.readLine()) != null) {
				 String [] field = currentLine.split("=");
				 if(field.length > 1) {
					 field[0] = field[0].trim();
					 field[1] = field[1].trim();
					 for(int col = 0; col < fields.length; col++) {
						 if(field[0].equals(fields[col])) {
							 values[col] = field[1];
							 break;
						 }
					 }
				 }
				 else{
					 field[0] = field[0].trim();
					 String currentValue = "";
					 for(int col = 0; col < fields.length; col++) {
						 if(field[0].equals(fields[col])) {
							 values[col] = currentValue;
							 break;
						 }
					 }
				 }
			}
<<<<<<< HEAD
			dbType = values[0];
			dbAddress = values[1];
			dbPort = values[2];
			dbUsername = values[3];
			dbPassword= values[4];
			socketPort = values[5];
			externalPipeLink = values[6];
			maximumThread = values[7];
=======
			dbAddress = values[0];
			dbPort = values[1];
			dbUsername = values[2];
			dbPassword= values[3];
			socketPort = values[4];
			externalPipeLink = values[5];
			maximumThread = values[6];
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d

		} catch (Exception e) {
			new FrameNotification(e.getMessage());
		}
		
	}
	
	public static ParseConfiguration getInstance() {
		if(instance == null) instance = new ParseConfiguration();
		return instance;
	}
	
<<<<<<< HEAD
	public String getDbType() {
		return dbType;
	}
	
=======
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	public String getDbAddress() {
		return dbAddress;
	}
	
	public int getDbPort() {
		return Integer.parseInt(dbPort);
	}
	
	public String getDbUsername() {
		return dbUsername;
	}
	
	public String getDbPassword() {
		return dbPassword;
	}
	
	public int getSocketPort() {
		return Integer.parseInt(socketPort);
	}
	
	public String getExternalPipe() {
		return externalPipeLink;
	}
	
	public int getMaxThread() {
		return Integer.parseInt(maximumThread);
	}
	
}

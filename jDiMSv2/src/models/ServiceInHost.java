package models;

import java.sql.SQLException;

import utils.DIMS;
import utils.RetrieveData;

public class ServiceInHost {

<<<<<<< HEAD
	private String [][] service_object_id;

	private String [][] state;

	private String [][] since;

	private String [][] serviceName;

	private String [][] alias;

	private String [][] output;

	public ServiceInHost() throws SQLException {
		RetrieveData picker = DIMS.getInstance().getPicker();
		String [][] Host = DIMS.getInstance().getHostInGroup().getHostObjectId();
=======
	public String [][] service_object_id;

	public String [][] state;

	public String [][] since;

	public String [][] serviceName;

	public String [][] alias;

	public String [][] output;


	public ServiceInHost() throws SQLException {
		RetrieveData picker = DIMS.getInstance().getPicker();
		String [][] Host = DIMS.getInstance().getHostInGroup().host_object_id;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		int rowSize = 0;
		for (int i = 0; i < Host.length; i++) {
			rowSize += Host[i].length;
		}
		service_object_id = new String [rowSize][];
		state = new String[rowSize][];
		since = new String[rowSize][];
		serviceName = new String[rowSize][];
		alias = new String[rowSize][];
		output = new String[rowSize][];
		int position = 0;
		for (int i = 0; i < Host.length; i++) {
			for (int j = 0; j < Host[i].length; j++) {
				String [][] data = picker.getServicesInHost(Integer.parseInt(Host[i][j]));
				int size = data.length;
				service_object_id[position] = new String [size];
				state[position] = new String[size];
				since[position] = new String[size];
				serviceName[position] = new String[size];
				alias[position] = new String[size];
				output[position] = new String[size];
				position++;
			}

		}

		position = 0;
		for (int i = 0; i < Host.length; i++) {
			for (int j = 0; j < Host[i].length; j++) {
				String [][] data = picker.getServicesInHost(Integer.parseInt(Host[i][j]));
				for (int m = 0; m < data.length; m++) {
					service_object_id[position][m] = data[m][0];
					state[position][m] = data[m][1];
					since[position][m] = data[m][2];
					serviceName[position][m] = data[m][3];
					alias[position][m] = data[m][4];
					output[position][m] = data[m][5];
				}
				position++;
			}
		}
	}
<<<<<<< HEAD
	
	public String [][] getServiceObjectId() {
		return service_object_id;
	}
	
	public String [][] getState() {
		return state;
	}
	
	public String [][] getTime() {
		return since;
	}
	
	public String [][] getName() {
		return serviceName;
	}
	
	public String [][] getMessage() {
		return output;
	}
	
	public String [][] getAlias() {
		return alias;
	}
=======
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
}

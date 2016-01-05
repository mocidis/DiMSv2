package utils;

public class Classification {
	public static int [] stateToHost(String [][] data, int statePosition) {
		int [] rsData = new int [2];
		int up = 0;
		int down = 0;
		for(int row = 0; row < data.length; row++) {
			if(data[row][statePosition].equals("0")) up++;
			else if(data[row][statePosition].equals("1")) down++;
		}
		rsData[0] = up;
		rsData[1] = down;
		return rsData;
	}
	
	public static int [] stateToService(String [][] data, int statePosition) {
		int [] rsData = new int [5];
		int ok = 0;
		int warning = 0;
		int critical = 0;
		int pending = 0;
		int unknown = 0;
		for(int row = 0; row < data.length; row++) {
			if(data[row][statePosition].equals("0")) ok++;
			else if(data[row][statePosition].equals("1")) warning++;
			else if(data[row][statePosition].equals("2")) critical++;
			else if(data[row][statePosition].equals("3")) pending++;
			else unknown++;
		}
		rsData[0] = ok;
		rsData[1] = warning;
		rsData[2] = critical;
		rsData[3] = pending;
		rsData[4] = unknown;
		return rsData;
	}
	
	public static String [][] filterData(String [][] data, String state, int statePosition, int length) {
		String [][] rsData = new String [length][];
		int rsRow = 0;
		for(int row = 0; row < data.length; row++) {
			if(data[row][statePosition].equals(state)) {
				rsData[rsRow] = data[row];
				rsRow++;
			}
		}
		return rsData;
	}
}

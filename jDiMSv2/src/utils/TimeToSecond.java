package utils;

import java.io.File;
import java.util.Date;

public class TimeToSecond {
	public static String getTime() {
		Date time = new Date();
		long timeToSecond = time.getTime();
		String strTime = String.valueOf(timeToSecond);
		strTime = strTime.substring(0, 10);
		
		return strTime;
	}
}

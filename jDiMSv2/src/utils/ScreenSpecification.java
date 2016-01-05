package utils;

import java.awt.Toolkit;

public class ScreenSpecification {
	public static final int CHANGEPASSWORD = -1;
	
	public static final int MATRIX = 0;
	
	public static final int HOST_TAB = 1;
	
	public static final int SERVICE_TAB = 2;
	
	public static final int HISTORY_TAB = 3;
	
	public static final double MONITOR_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	
	public static final double MONITOR_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
}

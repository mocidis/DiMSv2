package utils;

import java.io.File;
import java.io.IOException;

import gui.FrameNotification;

public class ExternalCommandPipe {
	private static String prefixLink = new File(ExternalCommandPipe.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getPath() +"/scripts/"; //JAR
//	private static String prefixLink = new File(ExternalCommandPipe.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getParentFile().getPath() +"/scripts/"; //IDE
	
	public static final void forceCheckHost(String hostName) {
		String secondTime = TimeToSecond.getTime();
		String message = "["+secondTime+"] SCHEDULE_FORCED_HOST_CHECK;"+hostName+";"+secondTime;
		WriteToPipe pipeWriter = new WriteToPipe(message);
	}
	
	public static final void rescheduleHost(String hostName, String time) {
		String message = "["+time+"] SCHEDULE_HOST_CHECK;"+hostName+";"+time;
		WriteToPipe pipeWriter = new WriteToPipe(message);
	}
	
	public static final void forceCheckService(String hostName, String serviceName) {
		String secondTime = TimeToSecond.getTime();
		String message = "["+secondTime+"] SCHEDULE_FORCED_SVC_CHECK;"+hostName+";"+serviceName+";"+secondTime;
		WriteToPipe pipeWriter = new WriteToPipe(message);
	}
	
	public static final void rescheduleService(String hostName, String serviceName, String time) {
		String message = "["+time+"] SCHEDULE_SVC_CHECK;"+hostName+";"+serviceName+";"+time;
		WriteToPipe pipeWriter = new WriteToPipe(message);
	}
}

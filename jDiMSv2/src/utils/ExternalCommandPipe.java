package utils;

import java.io.File;
import java.io.IOException;

import gui.FrameNotification;

public class ExternalCommandPipe {
<<<<<<< HEAD
=======
	private static String coreExternalPipe = ParseConfiguration.getInstance().getExternalPipe();
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	private static String prefixLink = new File(ExternalCommandPipe.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getPath() +"/scripts/"; //JAR
//	private static String prefixLink = new File(ExternalCommandPipe.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getParentFile().getPath() +"/scripts/"; //IDE
	
	public static final void forceCheckHost(String hostName) {
<<<<<<< HEAD
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
=======
		try {
			Runtime rt = Runtime.getRuntime();
			String message = prefixLink +"forceHost.sh " +hostName+ " " +coreExternalPipe;
			rt.exec(message);
		} catch (IOException e) {
			new FrameNotification("Maybe you've had some problems with core external command pipe(ECP) or programs write to ECP<br>"+e.getMessage());
		}
	}
	
	public static final void rescheduleHost(String hostName, long time) {
		try {
			Runtime rt = Runtime.getRuntime();
			String message = prefixLink +"rescheduleHost.sh " +hostName+ " " +time+ " " +coreExternalPipe;
			rt.exec(message);
		} catch (IOException e) {
			new FrameNotification("Maybe you've had some problems with core external command pipe(ECP) or programs write to ECP<br>"+e.getMessage());
		}
	}
	
	public static final void forceCheckService(String hostName, String serviceName) {
		try {
			Runtime rt = Runtime.getRuntime();
			String message = prefixLink+ "forceService.sh " +hostName+ " " +serviceName+ " " +coreExternalPipe;
			rt.exec(message);
		} catch (IOException e) {
			new FrameNotification("Maybe you've had some problems with core external command pipe(ECP) or programs write to ECP<br>"+e.getMessage());
		}
	}
	
	public static final void rescheduleService(String hostName, String serviceName, long time) {
		try {
			Runtime rt = Runtime.getRuntime();
			String message = prefixLink+ "rescheduleService.sh " +hostName+ " " +serviceName+ " " +time+ " " +coreExternalPipe;
			rt.exec(message);
		} catch (IOException e) {
			new FrameNotification("Maybe you've had some problems with core external command pipe(ECP) or programs write to ECP<br>"+e.getMessage());
		}
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	}
}

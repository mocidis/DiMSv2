package utils;

import java.io.File;
import java.io.IOException;

import gui.FrameNotification;

public class ExternalCommandPipe {
	private static String coreExternalPipe = ParseConfiguration.getInstance().getExternalPipe();
	private static String prefixLink = new File(ExternalCommandPipe.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getPath() +"/scripts/"; //JAR
//	private static String prefixLink = new File(ExternalCommandPipe.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getParentFile().getPath() +"/scripts/"; //IDE
	
	public static final void forceCheckHost(String hostName) {
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
	}
}

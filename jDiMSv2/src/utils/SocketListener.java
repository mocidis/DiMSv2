package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.swing.Timer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.

 *
 * @author Admin*/
public class SocketListener {
	private DatagramSocket serverSocket;
	private int port;
	private int maxThread;
	private int miniSecondCycle;
	private int currentThread;
	public SocketListener() throws IOException{
		currentThread = 0;
		miniSecondCycle = 60000;
		port = ParseConfiguration.getInstance().getSocketPort();
		maxThread = ParseConfiguration.getInstance().getMaxThread();
		if(maxThread > 12) maxThread = 12;
	}
	
	public void start() throws Exception {
		Timer timer = new Timer(miniSecondCycle, new ActionListener() {
		  @Override
		  public void actionPerformed(ActionEvent arg0) {
		    // Code to be executed
			  currentThread = 0;
		  }
		});
		timer.setRepeats(true); // Only execute once
		timer.start();
		
		serverSocket = new DatagramSocket(port);
		while(true)
		{
			if(currentThread < maxThread) {
				byte[] receiveData = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				String message = new String(receivePacket.getData());
		        message = message.replaceAll("\\\\n", "");
		        message = message.trim();
		        ThreadHandler.process(message);
				if(message.equals("update")) currentThread++;
			}
		}
	}
	
	public static void checkSystemCore() throws IOException{
//		String prefixLink = new File(ExternalCommandPipe.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getParentFile().getPath() +"/scripts/"; //IDE
		String prefixLink = new File(ExternalCommandPipe.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getPath() +"/scripts/"; //JAR
		Runtime rt = Runtime.getRuntime();
		String message = prefixLink +"uptime.sh";
		rt.exec(message);
	}

}  

package utils;

import gui.FrameNotification;

public class Main {

	public static void main(String[] args){
		try {
			DIMS app = DIMS.getInstance();
			app.initModels();
			app.showMainScreen();
			SocketListener socket = new SocketListener();
			socket.start();
		} catch (Exception e){
			new FrameNotification(e.getMessage());
		}
	    
	}
}

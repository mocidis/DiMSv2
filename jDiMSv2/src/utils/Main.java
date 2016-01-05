package utils;

import gui.FrameNotification;

public class Main {
<<<<<<< HEAD
=======

>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	public static void main(String[] args){
		try {
			DIMS app = DIMS.getInstance();
			app.initModels();
			app.showMainScreen();
			SocketListener socket = new SocketListener();
			socket.start();
<<<<<<< HEAD
			
		} catch (Exception e){
			new FrameNotification(e.getMessage());
			e.printStackTrace();
=======
		} catch (Exception e){
			new FrameNotification(e.getMessage());
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		}
	    
	}
}

package utils;

import gui.FrameNotification;
import gui.PanelCenterMain;
import gui.PanelMenuLeft;

public class ThreadHandler {
	private static String okStatus = "OK";
	private static String problemStatus = "DOWN";
	
	public static final void process(String message){
		try {
			PanelCenterMain center = DIMS.getInstance().getCenterMain();
			PanelMenuLeft newLeft = new PanelMenuLeft();
			if(message.equals("core-well")) {
				DIMS.getInstance().setCoreStatus(okStatus);
				center.reloadLeft(newLeft);
			}
			else if(message.equals("core-death")) {
				DIMS.getInstance().setCoreStatus(problemStatus);
				center.reloadLeft(newLeft);
			}
			else if(message.equals("update")){
<<<<<<< HEAD
				Thread.sleep(1500);
=======
				Thread.sleep(1000);
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
				DIMS myApp = DIMS.getInstance();
				UpdateWholeSystem updater = new UpdateWholeSystem();
				updater.update(myApp);
			}
		}catch(Exception e){
			new FrameNotification("Maybe something went wrong with thread incoming to your application <br>" +e.getMessage());
		}
	}
}

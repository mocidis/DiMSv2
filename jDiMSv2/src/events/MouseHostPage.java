package events;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.FrameNotification;
import gui.PanelCenterMain;
import gui.PanelMainContent;
import utils.DIMS;
import utils.ExternalCommandPipe;
import utils.TextLabelStyle;
import utils.UpdateWholeSystem;

public class MouseHostPage implements MouseListener{

	private JPanel Reschedule;
	private int active = 0;
	
	public void setComponents(JPanel reschedule){
		this.Reschedule = reschedule;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		
		try {
			//Check now
			if(now.getName().equals("checknow")){
				now.setText("Checking...");
				now.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				ExternalCommandPipe.forceCheckHost(DIMS.getInstance().getCurrentHostName());
				Thread.sleep(1000);
				
				DIMS myApp = DIMS.getInstance();
				UpdateWholeSystem updater = new UpdateWholeSystem();
				updater.update(myApp);
			}
			
			//Reschedule
			else if(now.getName().equals("reschedule")){
				if(active == 0){
					Reschedule.setVisible(true);
					active = 1;
				}
				else {
					Reschedule.setVisible(false);
					active = 0;
				}
			}
			
			//Host group
			else{
				PanelCenterMain CenterMain = DIMS.getInstance().getCenterMain();
				PanelMainContent newCenter = new PanelMainContent(Integer.parseInt(now.getName()));
				CenterMain.reloadCenter(newCenter);
				DIMS.getInstance().setCurrentObjectID(Integer.parseInt(now.getName()));
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			new FrameNotification("Maybe something went wrong when you were trying to schedule for a host<br>" +e1.getMessage());
		}
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		now.setCursor(new Cursor(Cursor.HAND_CURSOR));
		TextLabelStyle.makeUnderLine(now);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		TextLabelStyle.makeStrikeThrough(now);
	}

}

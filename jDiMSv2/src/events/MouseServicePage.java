package events;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.FrameNotification;
import utils.DIMS;
import utils.ExternalCommandPipe;
import utils.IsServiceGroup;
import utils.ShowServiceHistory;
import utils.ShowServiceInGroup;
import utils.TextLabelStyle;
import utils.UpdateWholeSystem;

public class MouseServicePage implements MouseListener{
	private JPanel reschedule;
	private int active = 0;
	
	public void setComponents(JPanel reschedule){
		this.reschedule = reschedule;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		try{
		//Check now
			if(now.getName().equals("checknow")) {
				now.setText("Checking ...");
				now.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				ExternalCommandPipe.forceCheckService(DIMS.getInstance().getCurrentHostName(), DIMS.getInstance().getCurrentServiceName());
				Thread.sleep(1000);
				
				DIMS myApp = DIMS.getInstance();
				UpdateWholeSystem updater = new UpdateWholeSystem();
				updater.update(myApp);
			}
		
			//Reschedule
			else if(now.getName().equals("reschedule")){
				if(active == 0){
					reschedule.setVisible(true);
					active = 1;
				}
				else{
					reschedule.setVisible(false);
					active = 0;
				}
			}
		
			//History
			else if(now.getName().equals("history")){
				try {
					ShowServiceHistory shower = new ShowServiceHistory();
					DIMS.getInstance().getDetailContent().reloadContent(shower);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					new FrameNotification("Maybe something went wrong when you were trying to open a service<br>" +e1.getMessage());
				}
			}
		
			//Service group		
			else{
				if(IsServiceGroup.check(now.getName())){
					DIMS.getInstance().setCurrentObjectID(Integer.parseInt(now.getName()));
					ShowServiceInGroup shower = new ShowServiceInGroup();
					DIMS.getInstance().getDetailContent().reloadContent(shower);
				}
			}
		}catch(Exception e1){
			new FrameNotification("Maybe something went wrong when you were trying to open a service<br>" +e1.getMessage());
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

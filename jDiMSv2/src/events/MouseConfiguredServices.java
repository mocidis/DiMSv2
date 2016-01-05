package events;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JLabel;

import gui.FrameNotification;
import utils.DIMS;
import utils.IsServiceGroup;
import utils.ShowServiceByStateInGroup;
import utils.ShowServiceByStateInHost;
import utils.TextLabelStyle;

public class MouseConfiguredServices implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		
		try{
			if(IsServiceGroup.check(String.valueOf(DIMS.getInstance().getCurrentOID()))){
				if(Integer.parseInt(now.getText()) > 0 ){
						DIMS.getInstance().setState(Integer.parseInt(now.getName()));
<<<<<<< HEAD
						ShowServiceByStateInGroup newCenter = new ShowServiceByStateInGroup(now.getName(), now.getText());
=======
						ShowServiceByStateInGroup newCenter = new ShowServiceByStateInGroup();
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
						DIMS.getInstance().getDetailContent().reloadContent(newCenter);
				}
			}
			else{
				if(Integer.parseInt(now.getText()) > 0 ){
					try {
						DIMS.getInstance().setState(Integer.parseInt(now.getName()));
<<<<<<< HEAD
						ShowServiceByStateInHost newCenter = new ShowServiceByStateInHost(now.getName(), now.getText());
=======
						ShowServiceByStateInHost newCenter = new ShowServiceByStateInHost();
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
						DIMS.getInstance().getDetailContent().reloadContent(newCenter);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						new FrameNotification("Maybe something went wrong when you were trying to open services were having same state<br>" +e1.getMessage());
					}
				}
			}
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			new FrameNotification("Maybe something went wrong when you were trying to open services were having same state<br>" +e1.getMessage());
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
		TextLabelStyle.makeUnderLine(now);
		now.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		TextLabelStyle.makeStrikeThrough(now);
	}

}

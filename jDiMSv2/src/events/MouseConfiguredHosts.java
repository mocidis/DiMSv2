package events;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JLabel;

import gui.FrameNotification;
import gui.PanelCenterMain;
import gui.PanelMainContent;
import utils.DIMS;
import utils.ShowHostByStateInGroup;
import utils.TextLabelStyle;

public class MouseConfiguredHosts implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		
		try{
			if(Integer.parseInt(now.getText()) > 0 ){
					DIMS.getInstance().setState(Integer.parseInt(now.getName()));
					PanelCenterMain CenterMain = DIMS.getInstance().getCenterMain();
					PanelMainContent newCenter = new ShowHostByStateInGroup(DIMS.getInstance().getCurrentOID());
					CenterMain.reloadCenter(newCenter);
			}
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			new FrameNotification("Maybe something went wrong when you were trying to open hosts were having same state<br>" +e1.getMessage());
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

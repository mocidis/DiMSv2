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
import utils.TextLabelStyle;

public class MousePageTitle implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			PanelCenterMain CenterMain = DIMS.getInstance().getCenterMain();
			PanelMainContent newCenter = new PanelMainContent(DIMS.getInstance().getCurrentOID());
			CenterMain.reloadCenter(newCenter);
		} catch (NumberFormatException | SQLException e1) {
			// TODO Auto-generated catch block
			new FrameNotification(e1.getMessage());
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

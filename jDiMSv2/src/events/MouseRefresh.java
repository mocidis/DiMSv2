package events;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.FrameNotification;
import utils.DIMS;
import utils.UpdateWholeSystem;

public class MouseRefresh implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			DIMS myApp = DIMS.getInstance();
			UpdateWholeSystem updater = new UpdateWholeSystem();
			updater.update(myApp);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			new FrameNotification("Maybe something went wrong when you were trying to refresh the whole of system<br>" +e1.getMessage());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		now.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/refresh2.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		now.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/refresh.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		now.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

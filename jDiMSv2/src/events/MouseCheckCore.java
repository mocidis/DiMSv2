package events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import gui.PanelTopMain;
import utils.DIMS;

public class MouseCheckCore implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			PanelTopMain topMain = DIMS.getInstance().getPanelTopMain();
			topMain.reloadCoreStatus();
		} catch (SQLException e1) {
			e1.printStackTrace();
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
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}

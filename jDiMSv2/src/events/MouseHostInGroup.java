package events;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.FrameNotification;
import gui.PanelCenterMain;
import gui.PanelMainContent;
import utils.ColorBG;
import utils.DIMS;

public class MouseHostInGroup implements MouseListener{
	private PanelCenterMain CenterMain;
	private JPanel [] state, info;
	private JLabel [] ID;
	private int current_host, old_host;
	
	public void setComponents(JPanel [] left, JPanel [] right, JLabel [] ID){
		state = left;
		info = right;
		this.ID = ID;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		try {
			CenterMain = DIMS.getInstance().getCenterMain();
			PanelMainContent newCenter = new PanelMainContent(Integer.parseInt(now.getName()));
			CenterMain.reloadCenter(newCenter);
			DIMS.getInstance().setCurrentObjectID(Integer.parseInt(now.getName()));
			DIMS.getInstance().setCurrentHostID(Integer.parseInt(now.getName()));
		} catch (NumberFormatException | SQLException e1) {
			// TODO Auto-generated catch block
			new FrameNotification("Maybe something went wrong when you were trying to open a host<br>" +e1.getMessage());
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
		int current = 0, old = 0;
		for(int i = 0; i < state.length; i++){
			if(now.getName().equals(ID[i].getName())){
				current = i;
				break;
			}
		}
		info[current].setBackground(state[current].getBackground());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		int current = 0, old = 0;
		for(int i = 0; i < state.length; i++){
			if(now.getName().equals(ID[i].getName())){
				current = i;
				break;
			}
		}
		info[current].setBackground(Color.decode(ColorBG.BACKGROUND));
	}

}

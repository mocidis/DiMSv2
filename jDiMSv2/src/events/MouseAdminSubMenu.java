package events;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.FrameMain;
import gui.FrameNotification;
import gui.PanelCenterLogin;
import gui.PanelMainContent;
import gui.PanelTopLogin;
import utils.ColorBG;
import utils.DIMS;
import utils.ScreenIndex;
import utils.TextLabelStyle;

public class MouseAdminSubMenu implements MouseListener{
	private JPanel changePass;
	public void setComponents(JPanel changepass){
		changePass = changepass;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		try{
			if(now.getName().equals("changepassword")){
				PanelMainContent newCenter = new PanelMainContent(ScreenIndex.CHANGEPASSWORD);
				DIMS.getInstance().getCenterMain().reloadCenter(newCenter);
				changePass.setBackground(Color.decode(ColorBG.BACKGROUND));
			}
			else {
				FrameMain window = DIMS.getInstance().getWindow();
				window.reloadTopPanel(new PanelTopLogin());
				window.reloadCenterPanel(new PanelCenterLogin());
				window.pack();
				window.setTitle("Login");
			}
		}catch (SQLException e1){
			new FrameNotification("Maybe something went wrong when you were trying to open admin menu<br>" +e1.getMessage());
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

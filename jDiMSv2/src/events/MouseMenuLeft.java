package events;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.FrameNotification;
import gui.PanelMainContent;
import utils.ColorBG;
import utils.DIMS;
<<<<<<< HEAD
import utils.ScreenSpecification;
=======
import utils.ScreenIndex;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
import utils.TextLabelStyle;

public class MouseMenuLeft implements MouseListener{

	private JPanel [] menuPanel;
	private JLabel [] menuLabel;
	private JPanel subMenu;
	private int current_menu, old_menu = 0;
	
	public void setComponents(JPanel [] menuPanel, JLabel [] menuLabel, JPanel subMenu){
		this.menuPanel = menuPanel;
		this.menuLabel = menuLabel;
		this.subMenu = subMenu;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		JPanel classicPanel = new JPanel();
		for(int i = 0; i < menuPanel.length; i++){
			if(now.getName().equals(menuLabel[i].getName())) {
				current_menu = i;
				break;
			}
		}
		menuPanel[old_menu].setBackground(classicPanel.getBackground());
		menuPanel[current_menu].setBackground(Color.decode(ColorBG.BACKGROUND));
		if(current_menu == 0) {
			try{
<<<<<<< HEAD
				PanelMainContent newCenter = new PanelMainContent(ScreenSpecification.MATRIX);
				DIMS.getInstance().getCenterMain().reloadCenter(newCenter);
				DIMS.getInstance().setCurrentObjectID(ScreenSpecification.MATRIX);
=======
				PanelMainContent newCenter = new PanelMainContent(ScreenIndex.MATRIX);
				DIMS.getInstance().getCenterMain().reloadCenter(newCenter);
				DIMS.getInstance().setCurrentObjectID(ScreenIndex.MATRIX);
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
			}catch(SQLException e1){ 
				new FrameNotification("Maybe something went wrong when you were trying to open something from menu on the left<br>" +e1.getMessage());
			} 
		}
		if(current_menu == 1) subMenu.setVisible(true);
		else subMenu.setVisible(false);
		old_menu = current_menu;
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

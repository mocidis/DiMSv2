package events;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.FrameNotification;
import gui.PanelCenterMain;
import gui.PanelMainContent;
import utils.ColorBG;
import utils.DIMS;
import utils.TextLabelStyle;

public class MouseHostGroups implements MouseListener{
	private PanelCenterMain CenterMain;
	private JPanel [] hostGroupPanel;
	private JLabel [] hostGroupLabel;
	private int current_tab, old_tab = 0;
	
	public void setComponents(JPanel [] hostGroups, JLabel [] hostGroupId){
		hostGroupPanel = hostGroups;
		hostGroupLabel = hostGroupId;
	}
	
	@Override
	public void mouseClicked(MouseEvent e){
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		for(int i = 0; i < hostGroupLabel.length; i++){
			if(now.getName().equals(hostGroupLabel[i].getName())){
				current_tab = i;
				break;
			}
		}
		hostGroupPanel[old_tab].setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
		hostGroupLabel[old_tab].setForeground(Color.decode(ColorBG.BACKGROUND));
		hostGroupPanel[current_tab].setBackground(Color.decode(ColorBG.BACKGROUND));
		hostGroupLabel[current_tab].setForeground(Color.BLACK);
		old_tab = current_tab;
		
		try {
			CenterMain = DIMS.getInstance().getCenterMain();
			PanelMainContent newCenter = new PanelMainContent(Integer.parseInt(now.getName()));
			CenterMain.reloadCenter(newCenter);
			DIMS.getInstance().setCurrentObjectID(Integer.parseInt(now.getName()));
		} catch (NumberFormatException | SQLException e1) {
			// TODO Auto-generated catch block
			new FrameNotification("Maybe something went wrong when you were trying to open hosts in a group<br>" +e1.getMessage());
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

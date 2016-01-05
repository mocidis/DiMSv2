package events;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.FrameNotification;
import utils.ColorBG;
import utils.DIMS;
import utils.ShowHostHistory;
import utils.ShowHostPage;
import utils.ShowServiceInHost;

public class MouseMenuRight implements MouseListener{

	private JPanel [] tabPanel;
	private JLabel [] tab;
	private JLabel selectingTab;
	public void setComponent(JPanel [] panel, JLabel [] label){
		this.tabPanel = panel;
		this.tab = label;
	}
	public void setSelectingTab(JLabel now){
		selectingTab = now;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		for(int i = 0; i < tab.length; i++){
			if(now.getName().equals(tab[i].getName())){
				selectingTab = now;
				tabPanel[i].setPreferredSize(new Dimension(110, 70));
				tabPanel[i].setMaximumSize(tabPanel[i].getPreferredSize());
				now.setText(now.getName());
				now.setForeground(Color.decode(ColorBG.BACKGROUND));
				for(int j = 0; j < tab.length; j++){
					if(j != i){
						tabPanel[j].setPreferredSize(new Dimension(30, 70));
						tabPanel[j].setMaximumSize(tabPanel[j].getPreferredSize());
						tab[j].setText("");
					}
				}
				try {
					if(now.getName().equals("Device")){
						DIMS.getInstance().setCurrentObjectID(DIMS.getInstance().getCurrentHostID());
						ShowHostPage shower = new ShowHostPage();
						DIMS.getInstance().getDetailContent().reloadContent(shower);
					}
					else if(now.getName().equals("Services")){
						DIMS.getInstance().setCurrentObjectID(DIMS.getInstance().getCurrentHostID());
						ShowServiceInHost shower = new ShowServiceInHost();
						DIMS.getInstance().getDetailContent().reloadContent(shower);
					}
					else{
						DIMS.getInstance().setCurrentObjectID(DIMS.getInstance().getCurrentHostID());
						ShowHostHistory shower = new ShowHostHistory();
						DIMS.getInstance().getDetailContent().reloadContent(shower);
					}
					break;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					new FrameNotification("Maybe something went wrong when you were trying to open something from menu on the right<br>" +e1.getMessage());
				}
			}
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
		for(int i = 0; i < tab.length; i++){
			if(now.getName().equals(tab[i].getName())){
				tabPanel[i].setPreferredSize(new Dimension(110, 70));
				tabPanel[i].setMaximumSize(tabPanel[i].getPreferredSize());
				now.setText(now.getName());
				now.setForeground(Color.decode(ColorBG.BACKGROUND));
				break;
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel now = (JLabel)e.getSource();
		for(int i = 0; i < tab.length; i++){
			if((now.getName().equals(tab[i].getName()) && !(selectingTab.getName().equals(now.getName())))){
				tabPanel[i].setPreferredSize(new Dimension(30, 70));
				tabPanel[i].setMaximumSize(tabPanel[i].getPreferredSize());
				now.setText("");
				break;
			}
		}
	}

}

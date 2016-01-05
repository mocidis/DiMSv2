package events;

import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.FrameNotification;
import gui.PanelCenterMain;
import gui.PanelMainContent;
import utils.DIMS;
import utils.SearchResult;

public class ActionSearch implements DocumentListener{
	private JTextField search;
	
	public void setComponents(JTextField search){
		this.search = search;
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if(search.getText() != null){
			try{
				PanelCenterMain CenterMain = DIMS.getInstance().getCenterMain();
				PanelMainContent newCenter = new SearchResult(search.getText());
				CenterMain.reloadCenter(newCenter);
			}catch(SQLException e1){
				new FrameNotification("Maybe something went wrong when you were trying to search" +e1.getMessage());
			}
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		try{
			if(search.getText().length() > 0){
			PanelCenterMain CenterMain = DIMS.getInstance().getCenterMain();
			PanelMainContent newCenter = new SearchResult(search.getText());
			CenterMain.reloadCenter(newCenter);
			}
			else{
				PanelCenterMain CenterMain = DIMS.getInstance().getCenterMain();
				PanelMainContent newCenter = new PanelMainContent(DIMS.getInstance().getCurrentOID());
				CenterMain.reloadCenter(newCenter);
			}
		}catch(SQLException e1){
			new FrameNotification("Maybe something went wrong when you were trying to search something<br>" +e1.getMessage());
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
	}

}

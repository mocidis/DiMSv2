package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JPanel;

import utils.ColorBG;
import utils.DIMS;
import utils.IsHost;
import utils.IsService;
import utils.IsServiceGroup;
import utils.ScreenSpecification;
import utils.ShowHostPage;
import utils.ShowServiceInGroup;
import utils.ShowServicePage;

public class PanelMainDetailContent extends JPanel {
	protected PanelMainDetailContent(){}
	public PanelMainDetailContent(int object_id) throws SQLException{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		DIMS.getInstance().setDetailContent(this);
		if(IsHost.check(object_id)){
			ShowHostPage shower = new ShowHostPage();
			this.add(shower.createContent(object_id), BorderLayout.LINE_START);
		}
		else if(IsService.check(object_id)) {
			ShowServicePage shower = new ShowServicePage();
			this.add(shower.createContent(object_id));
		}
		else if(IsServiceGroup.check(String.valueOf(object_id))){
			ShowServiceInGroup shower = new ShowServiceInGroup();
			this.add(shower.createContent(object_id));
		}
		else{
			PanelCenterMain CenterMain = DIMS.getInstance().getCenterMain();
			PanelMainContent newCenter = new PanelMainContent(ScreenSpecification.MATRIX);
			CenterMain.reloadCenter(newCenter);
			DIMS.getInstance().setCurrentObjectID(ScreenSpecification.MATRIX);
		}
	}
	
	protected JPanel createContent(int OID) throws SQLException { 
		return null;
	}
	protected JPanel createContent(String Objectname) throws SQLException {
		return null;
	}
	public void reloadContent(PanelMainDetailContent newContent) throws SQLException{
		this.removeAll();
		this.add(newContent.createContent(DIMS.getInstance().getCurrentOID()), BorderLayout.LINE_START);
		this.revalidate();
	}
	
	
}

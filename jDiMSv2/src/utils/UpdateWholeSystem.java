package utils;

import java.sql.SQLException;

import gui.PanelCenterMain;
import gui.PanelMainContent;
import gui.PanelMenuLeft;

public final class UpdateWholeSystem {
	public void update(DIMS app) throws SQLException{
		app.initModels();
		PanelCenterMain CenterMain = DIMS.getInstance().getCenterMain();
		PanelMainContent newCenter = new PanelMainContent(DIMS.getInstance().getCurrentOID());
		PanelMenuLeft newLeft = new PanelMenuLeft();
		
		CenterMain.reloadCenter(newCenter);
		CenterMain.reloadLeft(newLeft);
	}
}

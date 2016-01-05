package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import utils.ColorBG;
import utils.DIMS;
import utils.ScreenSpecification;

public class PanelCenterMain extends PanelCenter {
	private PanelMenuLeft left;
	private PanelMainContent center;
	private JScrollPane scroll;
	public PanelCenterMain() throws Exception {
		this.setLayout(new BorderLayout());
		left = new PanelMenuLeft();
		center = new PanelMainContent(ScreenSpecification.MATRIX);
		scroll = new JScrollPane(center, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.decode(ColorBG.BACKGROUND)));
		this.add(left, BorderLayout.LINE_START);
		this.add(scroll, BorderLayout.CENTER);
		DIMS.getInstance().setCenterMain(this);
	}

	public void reloadCenter(PanelMainContent newCenter) throws SQLException {
		if(scroll != null) this.remove(scroll);
		scroll = new JScrollPane(newCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.decode(ColorBG.BACKGROUND)));
		this.add(scroll, BorderLayout.CENTER);
		this.revalidate();
	}
	
	public void reloadLeft(PanelMenuLeft newLeft) throws SQLException {
		if(left != null) this.remove(left);
		left = new PanelMenuLeft();
		this.add(left, BorderLayout.LINE_START);
		this.revalidate();
	}
}

package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import utils.DIMS;

public class FrameMain extends JFrame {
	private static final long serialVersionUID = 1L;
	private PanelTop topPanel;
	private PanelCenter centerPanel;
	public FrameMain(PanelTop top, PanelCenter center) throws SQLException{
		this.setLayout(new BorderLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		DIMS.getInstance().setScreenSize(this.getMaximumSize());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		reloadTopPanel(top);
		reloadCenterPanel(center);
		
	}
	public void reloadTopPanel(PanelTop aTopPanel) {
		if (topPanel != null) remove(topPanel);
		topPanel = aTopPanel;
		this.add(topPanel, BorderLayout.NORTH);
	}
	
	public void reloadCenterPanel(PanelCenter aCenterPanel) {
		if (centerPanel != null) remove(centerPanel);
		centerPanel = aCenterPanel;
		this.add(centerPanel);
	}
}

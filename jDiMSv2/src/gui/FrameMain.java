package gui;

import java.awt.BorderLayout;
<<<<<<< HEAD
import java.awt.Dimension;
import java.awt.Toolkit;
=======
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import utils.DIMS;
<<<<<<< HEAD
import utils.ScreenSpecification;
=======
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d

public class FrameMain extends JFrame {
	private static final long serialVersionUID = 1L;
	private PanelTop topPanel;
	private PanelCenter centerPanel;
	public FrameMain(PanelTop top, PanelCenter center) throws SQLException{
		this.setLayout(new BorderLayout());
<<<<<<< HEAD
		this.setTitle("Welcome to DiMS | Login");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("dicom.png")));
//		this.setPreferredSize(new Dimension((int)ScreenSpecification.MONITOR_WIDTH, (int)ScreenSpecification.MONITOR_HEIGHT));
//		this.setMaximumSize(new Dimension((int)ScreenSpecification.MONITOR_WIDTH, (int)ScreenSpecification.MONITOR_HEIGHT));
=======
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
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
<<<<<<< HEAD
	
=======
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
}

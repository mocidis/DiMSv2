package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import events.MouseMenuRight;
import utils.ColorBG;
import utils.DIMS;
import utils.LabelIcon;
import utils.ScreenSpecification;

public class PanelMenuRight extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MouseMenuRight listener = new MouseMenuRight();
	
	private JPanel hostRight, servicesRight, historyRight;
	private JLabel rHost, rService, rHistory;
	private Dimension selectedTab = new Dimension((int)(0.08*ScreenSpecification.MONITOR_WIDTH), (int)(0.09*ScreenSpecification.MONITOR_HEIGHT));
	private Dimension normalTab = new Dimension((int)(0.022*ScreenSpecification.MONITOR_WIDTH), (int)(0.09*ScreenSpecification.MONITOR_HEIGHT));
	public PanelMenuRight() throws SQLException {
		DIMS.getInstance().setMenuRight(this);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize(new Dimension((int)(0.088*ScreenSpecification.MONITOR_WIDTH), (int)(0.846*ScreenSpecification.MONITOR_HEIGHT)));
		this.setMaximumSize(this.getPreferredSize());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));

		//Fix position to right
		JPanel fix = new JPanel();
		fix.setLayout(new BoxLayout(fix, BoxLayout.Y_AXIS));
		fix.setBackground(Color.decode(ColorBG.BACKGROUND));
		//Create menu on right
		hostRight = new JPanel(new BorderLayout());
		hostRight.setName("Device");
		hostRight.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
		hostRight.setPreferredSize(selectedTab);
		hostRight.setMaximumSize(hostRight.getPreferredSize());
		hostRight.setBackground(Color.decode(ColorBG.HOST_OPTION));
		rHost = new JLabel(new ImageIcon(new ImageIcon(this.getClass().getResource("/host.png")).getImage()));
		rHost.setName("Device");
		rHost.setText("Device");
		rHost.setForeground(Color.decode(ColorBG.BACKGROUND));
		hostRight.add(rHost, BorderLayout.CENTER);
		JPanel hostthis = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		hostthis.setBackground(Color.decode(ColorBG.BACKGROUND));
		hostthis.add(hostRight);
		
		//Service
		servicesRight = new JPanel();
		servicesRight.setName("Services");
		servicesRight = new JPanel(new BorderLayout());
		servicesRight.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
		servicesRight.setPreferredSize(normalTab);
		servicesRight.setMaximumSize(servicesRight.getPreferredSize());
		servicesRight.setBackground(Color.decode(ColorBG.SERVICE_OPTION));
		rService = new JLabel(new ImageIcon(new ImageIcon(this.getClass().getResource("/services.png")).getImage()));
		rService.setName("Services");
		servicesRight.add(rService, BorderLayout.CENTER);
		JPanel servicethis = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		servicethis.setBackground(Color.decode(ColorBG.BACKGROUND));
		servicethis.add(servicesRight);
		
		//History
		historyRight = new JPanel();
		historyRight.setName("History");
		historyRight = new JPanel(new BorderLayout());
		historyRight.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
		historyRight.setPreferredSize(normalTab);
		historyRight.setMaximumSize(servicesRight.getPreferredSize());
		historyRight.setBackground(Color.decode(ColorBG.HISTORY_OPTION));
		rHistory = new JLabel(new ImageIcon(new ImageIcon(this.getClass().getResource("/history.png")).getImage()));
		rHistory.setName("History");
		historyRight.add(rHistory, BorderLayout.CENTER);
		JPanel historythis = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		historythis.setBackground(Color.decode(ColorBG.BACKGROUND));
		historythis.add(historyRight);
		
		//Add mouse listener
		JLabel [] tabLabel = {rHost, rService, rHistory};
		JPanel [] tabPanel = {hostRight, servicesRight, historyRight};
		listener.setComponent(tabPanel, tabLabel);
		listener.setSelectingTab(rHost);
		for(int i = 0; i < tabLabel.length; i++){
			tabLabel[i].addMouseListener(listener);
		}
		
		//Add to fixed panel 
		fix.add(hostthis);
		fix.add(servicethis);
		fix.add(historythis);
		//Add to right box
		this.add(fix);
	}
	
	public void focusTo(int tab_id) {
		if(tab_id == 2) {
			hostRight.setPreferredSize(normalTab);
			hostRight.setMaximumSize(hostRight.getPreferredSize());
			rHost.setText("");
			
			servicesRight.setPreferredSize(selectedTab);
			servicesRight.setMaximumSize(servicesRight.getPreferredSize());
			rService.setText("Services");
			rService.setForeground(Color.decode(ColorBG.BACKGROUND));
			
			listener.setSelectingTab(rService);
		}
	}
}

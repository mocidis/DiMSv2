package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
<<<<<<< HEAD
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.SQLException;

import javax.swing.BorderFactory;
=======
import java.awt.Image;
import java.sql.SQLException;

>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

<<<<<<< HEAD
import events.MouseCheckCore;
import utils.ColorBG;
import utils.DIMS;
import utils.LabelIcon;
import utils.ScreenSpecification;
import utils.ShowHostGroups;

public class PanelTopMain extends PanelTop {
	private Dimension logo = new Dimension((int)(0.1435*ScreenSpecification.MONITOR_WIDTH), (int)(0.104*ScreenSpecification.MONITOR_HEIGHT));
	private int height = (int)(0.104*ScreenSpecification.MONITOR_HEIGHT);
	private JPanel coreStatusBorder;
	private MouseCheckCore listener = new MouseCheckCore();
=======
import utils.ColorBG;
import utils.ShowHostGroups;
import utils.SubDimension;

public class PanelTopMain extends PanelTop {
	private Dimension logo = new Dimension(196, 80);
	private int height = 80;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	public PanelTopMain() throws SQLException {
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setPreferredSize(new Dimension(JFrame.MAXIMIZED_HORIZ, height));
		this.setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
<<<<<<< HEAD
		DIMS.getInstance().setTopMain(this);
=======
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		
		JPanel logoPart = new JPanel(new BorderLayout());
		logoPart.setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
		logoPart.setPreferredSize(logo);
		logoPart.setMaximumSize(logoPart.getPreferredSize());
		JLabel logo = new JLabel(new ImageIcon(new ImageIcon(this.getClass().getResource("/logo.png")).getImage().getScaledInstance(110, 45, Image.SCALE_SMOOTH)));
		logoPart.add(logo, BorderLayout.CENTER);
		
		JPanel menuSitePart = new JPanel(new BorderLayout());
		menuSitePart.setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
		ShowHostGroups groups = new ShowHostGroups();
		JPanel menuSite = groups.getMenuSite();
		menuSitePart.add(menuSite, BorderLayout.LINE_START);
		
<<<<<<< HEAD
		coreStatusBorder = this.drawCoreBorder();
		this.add(logoPart);
		this.add(menuSitePart);
		this.add(coreStatusBorder);

	}
	
	public void reloadCoreStatus() {
		if(coreStatusBorder != null) {
			this.remove(coreStatusBorder);
		}
		coreStatusBorder = this.drawCoreBorder();
		this.add(coreStatusBorder);
		this.revalidate();
	}
	private String aa = "OK";
	public JPanel drawCoreBorder() {
		JPanel coreBorder = new JPanel(new BorderLayout());
		coreBorder.setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
		coreBorder.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode(ColorBG.TOP_BACKGROUND)));
		coreBorder.setPreferredSize(new Dimension((int)(3*height), (int)(height)));
		coreBorder.setMaximumSize(coreBorder.getPreferredSize());
		JPanel coreBox = new JPanel(new BorderLayout());
		coreBox.setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
		JPanel coreStatus = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		coreStatus.setBackground(Color.decode(ColorBG.BACKGROUND));
		//############################//
		String image = null;
		if(aa.equals("OK")) {
			aa = "STOPPED";
			image="dot_red.png";
		}
		else {
			aa = "OK";
			image="dot_green.png";
		}
		JPanel statusBorder = new JPanel();
		statusBorder.setBackground(Color.decode(ColorBG.BACKGROUND));
		JLabel led = new LabelIcon(image, (int)(0.25*height), (int)(0.25*height));
		led.addMouseListener(listener);
		
		JLabel status = new JLabel("System core is "+aa);
		status.setForeground(Color.decode(ColorBG.TOP_BACKGROUND));
		statusBorder.add(led);
		statusBorder.add(status);
		
		coreBox.add(statusBorder, BorderLayout.CENTER);
		coreBorder.add(coreBox, BorderLayout.PAGE_END);
		return coreBorder;
=======
		this.add(logoPart);
		this.add(menuSitePart);
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	}
}

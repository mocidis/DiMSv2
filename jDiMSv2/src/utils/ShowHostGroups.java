package utils;

import events.MouseHostGroups;
import events.MouseRefresh;
import gui.PanelMainDetailContent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowHostGroups extends PanelMainDetailContent{
	private JPanel [] listPanel;
	private JLabel [] listLabel;
	private MouseHostGroups hgListener = new MouseHostGroups();
	
	private MouseRefresh rfListener = new MouseRefresh();
	private JPanel [] groups;
	private JPanel matrixTab;
	private JLabel [] titleGroup;
	
	public JPanel getMenuSite() throws SQLException {
		DIMS.getInstance().setPanelGroups(this);
		//Get list of hosts
		String [][] GroupName = DIMS.getInstance().getHostGroups().getHostGroup();
		groups = new JPanel [GroupName.length];
		titleGroup = new JLabel [groups.length];
		//Menu of tabs
		JPanel menuSite = new JPanel();
		menuSite.setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
		menuSite.setLayout(new BorderLayout());
		
		//Set layout to menu
		JPanel border = new JPanel();
//		border.setLayout(new GridLayout(1,10, (int)(0.013*ScreenSpecification.MONITOR_HEIGHT), (int)(0.013*ScreenSpecification.MONITOR_HEIGHT)));
		border.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Create matrixTab of services
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		Font font = new Font("Tahoma", Font.PLAIN, 12);
		
		JLabel titleMatrixTab = new JLabel("Services matrix");
		int matrixWidth = (int)(font.getStringBounds("Services matrix", frc).getWidth());
		titleMatrixTab.addMouseListener(hgListener);
		titleMatrixTab.setName("0");
		matrixTab = new JPanel();
//		matrixTab.setLayout(new BoxLayout(matrixTab, BoxLayout.LINE_AXIS));
		matrixTab.setBackground(Color.decode(ColorBG.BACKGROUND));
		matrixTab.setLayout(new BorderLayout());
		matrixTab.setPreferredSize(new Dimension((int)(1.3*matrixWidth), SizeTab.TAB_HEIGHT));
		matrixTab.add(titleMatrixTab, BorderLayout.CENTER);
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = (int)(0.013*ScreenSpecification.MONITOR_HEIGHT);
		border.add(matrixTab);
		
		
		//Add tabs to menu
		for(int i = 0; i < GroupName.length; i++){
			String text = GroupName[i][0];
			
			int textwidth = (int)(font.getStringBounds(text, frc).getWidth());

//			if(text.equals("Ho-Chi-Minh-City")) text = "HCMC-City";
			
			titleGroup[i] = new JLabel(text);
			titleGroup[i].addMouseListener(hgListener);
			titleGroup[i].setForeground(Color.decode(ColorBG.BACKGROUND));
			titleGroup[i].setName(GroupName[i][1]);
			
			groups[i] = new JPanel(new BorderLayout());
			groups[i].setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
			groups[i].setPreferredSize(new Dimension((int)(1.3*textwidth), SizeTab.TAB_HEIGHT));
			groups[i].setMinimumSize(groups[i].getPreferredSize());
			
			groups[i].add(titleGroup[i], BorderLayout.CENTER);
			c.gridy = 0;
			c.gridx = i+1;
			c.ipadx = (int)(0.013*ScreenSpecification.MONITOR_HEIGHT);
			border.add(groups[i]);
		}
		
		
		//Add refresh
		JPanel refresh = new JPanel();
		refresh.setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
		refresh.setLayout(new BorderLayout());
		refresh.setPreferredSize(new Dimension(SizeTab.REFRESH_WIDTH, SizeTab.TAB_HEIGHT));
		refresh.setMaximumSize(refresh.getPreferredSize());
		JLabel titleRefresh = new LabelIcon("refresh.png", 35, 35);
		titleRefresh.setForeground(Color.decode(ColorBG.BACKGROUND));
		titleRefresh.setName("refresh");
		titleRefresh.addMouseListener(rfListener);
		refresh.add(titleRefresh, BorderLayout.CENTER);
		border.add(refresh);

		
		//List Panel
		listPanel = new JPanel[1+groups.length];
		listLabel = new JLabel [1+groups.length];
		listPanel[0] = matrixTab;
		listLabel[0] = titleMatrixTab;
		for(int i = 1; i < listPanel.length; i++){
			listPanel[i] = groups[i-1];
			listLabel[i] = titleGroup[i-1];
		}
		hgListener.setComponents(listPanel, listLabel);

		//Draw layout
		border.setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
		menuSite.add(border, BorderLayout.PAGE_END);
		return menuSite;
	}
	
	public JPanel[] getHostGroupPanel(){
		return listPanel;
	}
	
	public JLabel [] getHostGroupLabel(){
		return listLabel;
	}
	
	public void focusTo(String tab_id) {
		for(int tab = 0; tab < listPanel.length; tab++) {
			listPanel[tab].setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
			listLabel[tab].setForeground(Color.decode(ColorBG.BACKGROUND));
			if(tab_id.equals(listLabel[tab].getName())) {
				listPanel[tab].setBackground(Color.decode(ColorBG.BACKGROUND));
				listLabel[tab].setForeground(Color.BLACK);
			}
		}
	}
}

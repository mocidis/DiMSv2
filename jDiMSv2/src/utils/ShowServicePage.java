package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import events.MouseServicePage;
import gui.PanelMainDetailContent;
import gui.PanelSetSchedule;

public class ShowServicePage extends PanelMainDetailContent {
	
	private MouseServicePage listener = new MouseServicePage();
	
	public ShowServicePage() throws SQLException{
		DIMS.getInstance().getMenuRight().focusTo(ScreenIndex.SERVICE_TAB);
	}
	
	@Override
	public JPanel createContent(int service_object_id) throws SQLException{
		//Retrieve data
		int position = 0;
		RetrieveData picker = DIMS.getInstance().getPicker();
		String [] servicesId = DIMS.getInstance().getServicePage().getServicesId();
		for(int i = 0; i < servicesId.length; i++){
			if(Integer.parseInt(servicesId[i]) == service_object_id) {
				position = i;
				break;
			}
		}
		
		String [] partOneData = DIMS.getInstance().getServicePage().getPartOne(position);
		String partTwoData = DIMS.getInstance().getServicePage().getPartTwo(position);
		String [] partThreeData = DIMS.getInstance().getServicePage().getPartThree(position);
		String serviceGroupID1 = DIMS.getInstance().getServicePage().getServiceGroupId1(position);
		String serviceGroupID2 = DIMS.getInstance().getServicePage().getServiceGroupId2(position);
		
		//Set current host
		DIMS.getInstance().setCurrentHostID(Integer.parseInt(partOneData[1]));
		DIMS.getInstance().setCurrentHostName(partOneData[7]);
		DIMS.getInstance().setCurrentServiceName(partOneData[5]);
		
		//Change tab
		ShowHostGroups tabs = DIMS.getInstance().getPanelGroups();
		tabs.focusTo(String.valueOf(DIMS.getInstance().getServicePage().getHostGroupId(position)));
		
		//Border Box
		JPanel Bigbox = new JPanel();
		Bigbox.setLayout(new BorderLayout());
		
		
		//Create part 1
		JPanel partOne = new JPanel(new BorderLayout());
		partOne.setPreferredSize(new Dimension(SizeDetail.CONTENT_WIDTH, SizeDetail.PARTONE_HEIGHT));
		//partOne.setMaximumSize(partOne.getPreferredSize());
		partOne.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode(ColorBG.SUBMIT)));
		
		JPanel status_infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		status_infoPanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		statusPanel.setLayout(new BorderLayout());
		statusPanel.setPreferredSize(new Dimension(90, 50));
		JPanel infoPanel = new JPanel(new BorderLayout());
		infoPanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		JLabel status = new JLabel(partOneData[2]);
		status.setForeground(Color.decode(ColorBG.BACKGROUND));
		JLabel since = new JLabel(partOneData[3]);
		since.setForeground(Color.decode(ColorBG.BACKGROUND));
		since.setFont(new Font(since.getFont().getName(), Font.ITALIC, 11));
		JLabel name = new JLabel(partOneData[4]+" ("+partOneData[5]+")");
		JLabel hostName = new JLabel("Device: "+partOneData[6]);
		hostName.setFont(new Font(hostName.getFont().getName(), Font.ITALIC, hostName.getFont().getSize()));
		if(partOneData[2].equals("0")) {
			status.setText(Glossary.OK);
			statusPanel.setBackground(Color.decode(ColorBG.OK));
		}
		else if(partOneData[2].equals("1")) {
			status.setText(Glossary.WARNING);
			statusPanel.setBackground(Color.decode(ColorBG.WARNING));
		}
		else if(partOneData[2].equals("2")) {
			status.setText(Glossary.CRITICAL);
			statusPanel.setBackground(Color.decode(ColorBG.CRITICAL));
			name.setText(name.getText()+" ! ");
		}
		else if(partOneData[2].equals("3")){
			status.setText(Glossary.PENDING);
			statusPanel.setBackground(Color.decode(ColorBG.PENDING));
		}
		else {
			status.setText(Glossary.UNKNOWN);
			statusPanel.setBackground(Color.decode(ColorBG.UNKNOWN));
		}
		statusPanel.add(status, BorderLayout.NORTH);
		statusPanel.add(since);
		infoPanel.add(name, BorderLayout.NORTH);
		infoPanel.add(hostName);
		
		status_infoPanel.add(statusPanel);
		status_infoPanel.add(infoPanel);
		
		JPanel titleOne = new JPanel(new FlowLayout(FlowLayout.LEFT));
		titleOne.setBackground(Color.decode(ColorBG.BACKGROUND));
		titleOne.add(new JLabel("Service detail informations"));
		//add to part 1
		
		partOne.add(status_infoPanel, BorderLayout.NORTH);
		partOne.add(titleOne);
		
		//Create part 2
		JPanel partTwo = new JPanel(new BorderLayout());
		partTwo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		titlePanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		JLabel titleTwo = new JLabel("Plugin output");
		titleTwo.setForeground(Color.decode(ColorBG.SUBMIT));
		titlePanel.add(titleTwo);
		
		JPanel outputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		outputPanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		JLabel outputLabel = new JLabel(partTwoData);
		outputPanel.add(outputLabel);
		
		//Add to part 2
		partTwo.add(titlePanel, BorderLayout.NORTH);
		partTwo.add(outputPanel);
		
		//create part 3
		JPanel partThree = new JPanel();
		partThree.setPreferredSize(new Dimension(SizeDetail.CONTENT_WIDTH, SizeDetail.PARTTHREE_HEIGHT));
		partThree.setLayout(new BorderLayout());
		partThree.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		JPanel perfData = new JPanel();
		JPanel checkSource = new JPanel();
		JPanel isReachable = new JPanel();
		JPanel serviceGroup = new JPanel();
		JPanel lastCheck = new JPanel();
		JPanel nextCheck = new JPanel();
		JPanel checkAttempt = new JPanel();
		JPanel executionTime = new JPanel();
		JPanel history = new JPanel();
		JPanel partThreeBox = new JPanel(new GridBagLayout());
		partThreeBox.setBackground(Color.decode(ColorBG.BACKGROUND));
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel LperfData = new JLabel("Performance Data");
		JLabel LcheckSource = new JLabel("Check source ");
		JLabel LisReachable = new JLabel("Reachable ");
		JLabel LserviceGroup = new JLabel("Service Group ");
		JLabel LlastCheck = new JLabel("Last check ");
		JLabel LnextCheck = new JLabel("Next check ");
		JLabel LcheckAttempt = new JLabel("Check attempts ");
		JLabel LexecutionTime = new JLabel("Check execution time ");
		JLabel Lhistory = new JLabel ("History");
		JLabel checknow = null;
		JLabel reschedule = null;
		
		
		JPanel [] Lpanel = {perfData, checkSource, isReachable, serviceGroup, lastCheck, nextCheck, checkAttempt, executionTime};
		JLabel [] Llabel3 = {LperfData, LcheckSource, LisReachable, LserviceGroup, LlastCheck, LnextCheck, LcheckAttempt, LexecutionTime};
		JPanel [] Rpanel = new JPanel[9];
		JLabel [] Rlabel3 = new JLabel [10];
		int gridRow = 0;
		for(gridRow = 1; gridRow < Llabel3.length; gridRow++){
			//lpanel
			Lpanel[gridRow].setLayout(new FlowLayout(FlowLayout.LEFT));
			Lpanel[gridRow].setPreferredSize(new Dimension(150, 20));
			Lpanel[gridRow].setMaximumSize(Lpanel[gridRow].getPreferredSize());
			Lpanel[gridRow].setBackground(Color.decode(ColorBG.BACKGROUND));
			Lpanel[gridRow].add(Llabel3[gridRow]);
			//Rpanel
			
			if( gridRow < 3){
				Rlabel3[gridRow] = new JLabel(partThreeData[gridRow]);
				Rpanel[gridRow] = new JPanel(new BorderLayout());
				Rpanel[gridRow].setPreferredSize(Rlabel3[gridRow].getPreferredSize());
				Rpanel[gridRow].setMaximumSize(Rpanel[gridRow].getPreferredSize());
				Rpanel[gridRow].setBackground(Color.decode(ColorBG.BACKGROUND));
				Rpanel[gridRow].add(Rlabel3[gridRow]);
			}
			else if( gridRow == 3){
				Rlabel3[gridRow] = new JLabel (partThreeData[gridRow]+", ");
				Rlabel3[gridRow].setIcon(new ImageIcon(this.getClass().getResource("/servicegroup.png")));
				Rlabel3[gridRow].setForeground(Color.decode(ColorBG.LINK));
				Rlabel3[gridRow].addMouseListener(listener);
				Rlabel3[gridRow].setName(serviceGroupID1);
				Rpanel[gridRow] = new JPanel(new BorderLayout());
				Rpanel[gridRow].setBackground(Color.decode(ColorBG.BACKGROUND));
				Rpanel[gridRow].add(Rlabel3[gridRow], BorderLayout.LINE_START);
				if(serviceGroupID2 != null){
					JLabel Rlabel33 = new JLabel(partThreeData[gridRow+1]);
					Rlabel33.addMouseListener(listener);
					Rlabel33.setForeground(Color.decode(ColorBG.LINK));
					Rlabel33.setName(serviceGroupID2);
					Rpanel[gridRow].add(Rlabel33);
				}
			}
			else{
				Rlabel3[gridRow] = new JLabel(partThreeData[gridRow+1]);
				Rpanel[gridRow] = new JPanel(new BorderLayout());
				Rpanel[gridRow].setBackground(Color.decode(ColorBG.BACKGROUND));
				Rpanel[gridRow].add(Rlabel3[gridRow], BorderLayout.WEST);
				if( gridRow == 4){
					checknow = new JLabel(new ImageIcon(new ImageIcon(this.getClass().getResource("/check.png")).getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH)));
					checknow.setText("Check now");
					checknow.setName("checknow");
					checknow.addMouseListener(listener);
					checknow.setForeground(Color.decode(ColorBG.LINK));
					Rpanel[gridRow].add(checknow);
				}
				if(gridRow == 5){
					reschedule = new JLabel(new ImageIcon(new ImageIcon(this.getClass().getResource("/check.png")).getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH)));
					reschedule.setText("Reschedule");
					reschedule.setName("reschedule");
					reschedule.addMouseListener(listener);
					reschedule.setForeground(Color.decode(ColorBG.LINK));
					Rpanel[gridRow].add(reschedule);
				}
			}
			c.gridx = 0;
			c.gridy = gridRow;
			c.ipadx = 15;
			c.ipady = 3;
			partThreeBox.add(Lpanel[gridRow], c);
			c.gridx = 1;
			c.gridy = gridRow;
			c.ipady = 3;
			partThreeBox.add(Rpanel[gridRow], c);
		}
		//Add history
		history.setLayout(new FlowLayout(FlowLayout.LEFT));
		history.setPreferredSize(new Dimension(150, 20));
		history.setMaximumSize(history.getPreferredSize());
		history.setBackground(Color.decode(ColorBG.BACKGROUND));
		history.add(Lhistory);
		c.gridx = 0;
		c.gridy = gridRow+1;
		c.ipadx = 15;
		partThreeBox.add(history, c);
		JPanel Rhistory = new JPanel();
		Rhistory.setBackground(Color.decode(ColorBG.BACKGROUND));
		JLabel RhistoryLabel = new JLabel(new ImageIcon(new ImageIcon("Icon/history_petrol.png").getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH)));
		RhistoryLabel.setText("See history");
		RhistoryLabel.setName("history");
		RhistoryLabel.addMouseListener(listener);
		RhistoryLabel.setForeground(Color.decode(ColorBG.LINK));
		Rhistory.add(RhistoryLabel);
		c.gridx = 1;
		c.gridy = gridRow+1;
		partThreeBox.add(Rhistory, c);
		
		//Set schedule box
		JPanel setSchedule = new PanelSetSchedule();
		setSchedule.setVisible(false);
		//Add event components
		listener.setComponents(setSchedule);
		
		//
		c.gridx = 2;
		c.gridy = 5;
		c.gridheight = 6;
		partThreeBox.add(setSchedule, c);
				
		//Custom position
		JPanel ptBox = new JPanel(new BorderLayout());
		ptBox.setBackground(Color.decode(ColorBG.BACKGROUND));
		ptBox.add(partThreeBox, BorderLayout.NORTH);
		partThree.add(ptBox, BorderLayout.LINE_START);
		//Add parts to box container
		Bigbox.add(partOne, BorderLayout.NORTH);
		Bigbox.add(partTwo);
		Bigbox.add(partThree, BorderLayout.AFTER_LAST_LINE);
		//
		return Bigbox;
	}	
}

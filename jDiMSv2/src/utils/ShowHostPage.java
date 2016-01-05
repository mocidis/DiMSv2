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

import events.MouseHostPage;
import gui.PanelMainDetailContent;
import gui.PanelSetSchedule;

public class ShowHostPage extends PanelMainDetailContent{
	private MouseHostPage listener = new MouseHostPage();
	
	@Override
	public JPanel createContent(int host_object_id) throws SQLException {

		//Retrieve data
<<<<<<< HEAD
		int position = FindPosition.toHost(host_object_id);
=======
		int position = 0;
		RetrieveData picker = DIMS.getInstance().getPicker();
		String [] hosts = DIMS.getInstance().getHostPage().getHostId();
		for(int i = 0; i < hosts.length; i++){
			if(host_object_id == Integer.parseInt(hosts[i])){
				position = i;
				break;
			}
		}
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
		
		String [] partOneData = DIMS.getInstance().getHostPage().getPartOne(position);
		String partTwoData = DIMS.getInstance().getHostPage().getPartTwo(position);
		String [] partThreeData = DIMS.getInstance().getHostPage().getPartThree(position);
		int [] countData = DIMS.getInstance().getHostPage().getNumberOfService(position);
		
		//Change tab
		ShowHostGroups tabs = DIMS.getInstance().getPanelGroups();
		tabs.focusTo(String.valueOf(DIMS.getInstance().getHostPage().getGroupId(position)));
		
		//Set current host name
		DIMS.getInstance().setCurrentHostName(partOneData[4]);
		
		//Border Box
		JPanel Bigbox = new JPanel();
		Bigbox.setLayout(new BorderLayout());
		
		//Create part 1
		JPanel partOne = new JPanel(new BorderLayout());
		partOne.setPreferredSize(new Dimension(SizeDetail.CONTENT_WIDTH, SizeDetail.PARTONE_HEIGHT));
		partOne.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode(ColorBG.SUBMIT)));
		
		JPanel status_infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		status_infoPanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		statusPanel.setLayout(new BorderLayout());
		statusPanel.setPreferredSize(new Dimension(90, 50));
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		JLabel status = new JLabel(partOneData[1]);
		status.setForeground(Color.decode(ColorBG.BACKGROUND));
		JLabel since = new JLabel(partOneData[2]);
		since.setForeground(Color.decode(ColorBG.BACKGROUND));
		since.setFont(new Font(since.getFont().getName(), Font.ITALIC, 11));
		JLabel name = new JLabel(partOneData[3]+" ("+partOneData[4]+")");
		
		if(partOneData[1].equals("0")) {
			status.setText(Glossary.UP);
			statusPanel.setBackground(Color.decode(ColorBG.OK));
		}
		else if(partOneData[1].equals("1")) {
			status.setText(Glossary.DOWN);
			statusPanel.setBackground(Color.decode(ColorBG.CRITICAL));
			name.setText(name.getText()+" ! ");
		}
		else {
			status.setText(Glossary.UNKNOWN);
			statusPanel.setBackground(Color.decode(ColorBG.UNKNOWN));
		}
		
		statusPanel.add(status, BorderLayout.NORTH);
		statusPanel.add(since);
		infoPanel.add(name);
		
		status_infoPanel.add(statusPanel);
		status_infoPanel.add(infoPanel);
		
		//Show number of service on host by status
		JPanel numberService = new ConfiguredServices(countData);
		
		
		//add to part 1
		
		partOne.add(status_infoPanel, BorderLayout.NORTH);
		partOne.add(numberService);
		
		//Create part 2
		JPanel partTwo = new JPanel(new BorderLayout());
		partTwo.setPreferredSize(new Dimension(SizeDetail.CONTENT_WIDTH, SizeDetail.PARTTWO_HEIGHT));
		partTwo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		titlePanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		JLabel title = new JLabel("Plugin output");
		title.setForeground(Color.decode(ColorBG.SUBMIT));
		titlePanel.add(title);
		
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
		JPanel checkSource = new JPanel();
		JPanel isReachable = new JPanel();
		JPanel hostGroup = new JPanel();
		JPanel lastCheck = new JPanel();
		JPanel nextCheck = new JPanel();
		JPanel checkAttempt = new JPanel();
		JPanel executionTime = new JPanel();
		JPanel partThreeBox = new JPanel(new GridBagLayout());
		partThreeBox.setBackground(Color.decode(ColorBG.BACKGROUND));
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel LcheckSource = new JLabel("Check source ");
		JLabel LisReachable = new JLabel("Reachable ");
		JLabel LhostGroup = new JLabel("Host Group ");
		JLabel LlastCheck = new JLabel("Last check ");
		JLabel LnextCheck = new JLabel("Next check ");
		JLabel LcheckAttempt = new JLabel("Check attempts ");
		JLabel LexecutionTime = new JLabel("Check execution time ");
		JLabel checknow = null;
		JLabel reschedule = null;
		
		
		JPanel [] Lpanel = {checkSource, isReachable, hostGroup, lastCheck, nextCheck, checkAttempt, executionTime};
		JLabel [] Llabel3 = {LcheckSource, LisReachable, LhostGroup, LlastCheck, LnextCheck, LcheckAttempt, LexecutionTime};
		JPanel [] Rpanel = new JPanel[7];
		JLabel [] Rlabel3 = new JLabel [7];
		for(int i = 0; i < Llabel3.length; i++){
			int j = 0;
			//lpanel
			Lpanel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			Lpanel[i].setPreferredSize(new Dimension(150, 20));
			Lpanel[i].setMaximumSize(Lpanel[i].getPreferredSize());
			Lpanel[i].setBackground(Color.decode(ColorBG.BACKGROUND));
			Lpanel[i].add(Llabel3[i]);
			//Rpanel
			
			Rlabel3[i] = new JLabel(partThreeData[i]);
			if(i == 2) {
				Rlabel3[i].setIcon(new ImageIcon(this.getClass().getResource("/hostgroup.png")));
				Rlabel3[i].setName(DIMS.getInstance().getHostPage().getGroupId(position));
				Rlabel3[i].addMouseListener(listener);
				Rlabel3[i].setForeground(Color.decode(ColorBG.LINK));
			}
			Rpanel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
			Rpanel[i].setPreferredSize(new Dimension(200, 20));
			Rpanel[i].setMaximumSize(Rpanel[i].getPreferredSize());
			Rpanel[i].setBackground(Color.decode(ColorBG.BACKGROUND));
			Rpanel[i].add(Rlabel3[i]);
			
			if( i == 3){
				checknow = new LabelIcon("check.png", 14, 14);
				checknow.setText("Check now");
				checknow.setName("checknow");
				checknow.addMouseListener(listener);
				checknow.setForeground(Color.decode(ColorBG.LINK));
				Rpanel[i].add(checknow);
			}
			if(i == 4){
				reschedule = new LabelIcon("check.png", 14, 14);
				reschedule.setText("Reschedule");
				reschedule.setName("reschedule");
				reschedule.addMouseListener(listener);
				reschedule.setForeground(Color.decode(ColorBG.LINK));
				Rpanel[i].add(reschedule);
			}
			c.gridx = j;
			c.gridy = i;
			c.ipadx = 15;
			partThreeBox.add(Lpanel[i], c);
			c.gridx = j+1;
			c.gridy = i;
			partThreeBox.add(Rpanel[i], c);
		}
		
		//Add schedule box
		JPanel setSchedule = new PanelSetSchedule();
		setSchedule.setVisible(false);
		//Add to event components
		listener.setComponents(setSchedule);
		
		//
		c.gridx = 2;
		c.gridy = 4;
		c.gridheight = 6;
		partThreeBox.add(setSchedule, c);
		
		//Add to layout
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

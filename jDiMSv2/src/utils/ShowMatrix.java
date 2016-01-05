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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import events.MouseMatrixLeftAndCenter;
import events.MouseServiceTitle;

public class ShowMatrix {
	private MouseMatrixLeftAndCenter listener = new MouseMatrixLeftAndCenter();
	private MouseServiceTitle titleListener = new MouseServiceTitle();
	private int rotateDegree = 300;
<<<<<<< HEAD
	private int rotateWidth = (int)(0.011*ScreenSpecification.MONITOR_WIDTH);
	private int iconSize = (int)(0.0146*ScreenSpecification.MONITOR_WIDTH);
=======
	private int rotateWidth = 15;
	private int iconSize = 20;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	private double rotateHeight;
	
	public JPanel Draw() throws SQLException{
		//change tab
		ShowHostGroups tabs = DIMS.getInstance().getPanelGroups();
<<<<<<< HEAD
		tabs.focusTo(String.valueOf(ScreenSpecification.MATRIX));
		//Set current OID
		DIMS.getInstance().setCurrentObjectID(ScreenSpecification.MATRIX);
		//Data
		String [] serviceTitle = DIMS.getInstance().getServicesMatrix().getServiceTitle();
		String [][] hostTitle = DIMS.getInstance().getServicesMatrix().getHostTitle();
		String [][] serviceState = DIMS.getInstance().getServicesMatrix().getState();
		if(serviceTitle.length > 0 && hostTitle.length > 0 && serviceState.length > 0) {
			//Draw
			JPanel bottom = new JPanel();
			bottom.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.decode(ColorBG.BACKGROUND)));
			bottom.setLayout(new GridBagLayout());
			bottom.setBackground(Color.decode(ColorBG.BACKGROUND));
			GridBagConstraints c = new GridBagConstraints();
			
			//Retrieve data
			JPanel hostTitleBox = new JPanel();
			hostTitleBox.setLayout(new BoxLayout(hostTitleBox, BoxLayout.Y_AXIS));
			
			JLabel [] hostRow = new JLabel [hostTitle.length];
			for(int i = 0; i < hostTitle.length; i++){
				hostRow[i] = new JLabel(hostTitle[i][0]);
				hostRow[i].addMouseListener(listener);
				hostRow[i].setName(hostTitle[i][1]);
				if(!hostTitle[i][2].equals("0")) hostRow[i].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/caution.png")).getImage().getScaledInstance(17, 17, Image.SCALE_SMOOTH)));
				hostTitleBox.add(hostRow[i]);
			}
			JPanel services_border = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
			services_border.setBackground(Color.decode(ColorBG.BACKGROUND));
			
			JLabel [] serviceColumn = new JLabel [serviceTitle.length];
			for(int i = 0; i < serviceTitle.length; i++){
				serviceColumn[i] = new RotateLabel(rotateDegree, rotateWidth);
				serviceColumn[i].setText(serviceTitle[i]);
				serviceColumn[i].addMouseListener(titleListener);
				serviceColumn[i].setName(serviceTitle[i]);
				serviceColumn[i].setFont(new Font(serviceColumn[i].getFont().getName(), serviceColumn[i].getFont().getStyle(), serviceColumn[i].getFont().getSize() - 1));
				services_border.add(serviceColumn[i]);
			}
			JLabel spaceWhenRotate = new JLabel(serviceTitle[0]);
			rotateHeight = spaceWhenRotate.getPreferredSize().getWidth();
			
			//Make a particular panel for service title columns
			JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
	//		JPanel top = new JPanel(new BorderLayout());
			top.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.decode(ColorBG.BACKGROUND)));
			top.setBackground(Color.decode(ColorBG.BACKGROUND));
			JPanel topSpace = new JPanel();
			topSpace.setBackground(Color.decode(ColorBG.BACKGROUND));
			int spaceBorn = (int)countSpaceBornWhenRotate();
			topSpace.setPreferredSize(new Dimension((int)hostTitleBox.getPreferredSize().getWidth() - spaceBorn - (iconSize - rotateWidth) - 10, 100));
			
			top.add(topSpace);
			top.add(services_border);
	
			JLabel [][] serviceStatus = new JLabel [hostRow.length][serviceColumn.length];
			for(int i = 0; i < hostRow.length; i++){
				for(int j = 0; j < serviceColumn.length; j++){
					if(serviceState[i][j].equals(".")) {
						serviceStatus[i][j] = new JLabel(serviceState[i][j]);
						serviceStatus[i][j].setName(serviceState[i][j]);
						continue;
					}
					else{ 
						String icon = "";
						String [] parts = serviceState[i][j].split("-");
						if(parts[0].equals("0")) icon = "OK.png";
						else if(parts[0].equals("1")) icon = "warning.png";
						else if(parts[0].equals("2")) icon = "critical.png";
						else if(parts[0].equals("3")) icon = "pending.png";
						serviceStatus[i][j] = new LabelIcon(icon, iconSize, iconSize);
						serviceStatus[i][j].setName(parts[1]);
					}
				}
			}
	
			for(int i = 0; i < hostRow.length; i++){
				c.gridx = 0;
				c.gridy = i;
				c.ipadx = 10;
				hostRow[i].setFont(new Font(hostRow[i].getFont().getFontName(), hostRow[i].getFont().getStyle(), hostRow[i].getFont().getSize()-1));
				bottom.add(hostRow[i], c);
			}
			
			//Make matrix
			for(int row = 0; row < hostRow.length; row++){
				for(int col = 0; col < serviceColumn.length; col++){
					c.gridx = col+2;
					c.gridy = row;
					c.ipadx = 1;
					c.ipady = 2;
					if(serviceStatus[row][col].getName().compareTo(".") != 0) serviceStatus[row][col].addMouseListener(listener);
					bottom.add(serviceStatus[row][col], c);
				}
			}
			services_border.setPreferredSize(new Dimension((int)(bottom.getPreferredSize().getWidth()), (int)(0.1302*ScreenSpecification.MONITOR_HEIGHT)));
			
			JPanel boxContainer = new JPanel(new BorderLayout());
			boxContainer.setBackground(Color.decode(ColorBG.BACKGROUND));
			boxContainer.add(top, BorderLayout.NORTH);
			boxContainer.add(bottom, BorderLayout.LINE_START);
			
			JPanel marginLeft = new JPanel (new BorderLayout());
			marginLeft.setBackground(Color.decode(ColorBG.BACKGROUND));
			marginLeft.add(boxContainer, BorderLayout.LINE_START);
			
			return marginLeft;
		}
		return new PageFail();
=======
		tabs.focusTo(String.valueOf(ScreenIndex.MATRIX));
		//Set current OID
		DIMS.getInstance().setCurrentObjectID(ScreenIndex.MATRIX);
		
		//Draw
		JPanel bottom = new JPanel();
		bottom.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.decode(ColorBG.BACKGROUND)));
		bottom.setLayout(new GridBagLayout());
		bottom.setBackground(Color.decode(ColorBG.BACKGROUND));
		GridBagConstraints c = new GridBagConstraints();
		
		//Retrieve data
		JPanel hostTitleBox = new JPanel();
		hostTitleBox.setLayout(new BoxLayout(hostTitleBox, BoxLayout.Y_AXIS));
		String [][] hostTitle = DIMS.getInstance().getServicesMatrix().hostTitle;
		JLabel [] hostRow = new JLabel [hostTitle.length];
		for(int i = 0; i < hostTitle.length; i++){
			hostRow[i] = new JLabel(hostTitle[i][0]);
			hostRow[i].addMouseListener(listener);
			hostRow[i].setName(hostTitle[i][1]);
			if(!hostTitle[i][2].equals("0")) hostRow[i].setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/caution.png")).getImage().getScaledInstance(17, 17, Image.SCALE_SMOOTH)));
			hostTitleBox.add(hostRow[i]);
		}
		JPanel services_border = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0)); 
		services_border.setBackground(Color.decode(ColorBG.BACKGROUND));
		String [] serviceTitle = DIMS.getInstance().getServicesMatrix().serviceTitle;
		JLabel [] serviceColumn = new JLabel [serviceTitle.length];
		for(int i = 0; i < serviceTitle.length; i++){
			serviceColumn[i] = new RotateLabel(rotateDegree, rotateWidth);
			serviceColumn[i].setText(serviceTitle[i]);
			serviceColumn[i].addMouseListener(titleListener);
			serviceColumn[i].setName(serviceTitle[i]);
			serviceColumn[i].setFont(new Font(serviceColumn[i].getFont().getName(), serviceColumn[i].getFont().getStyle(), serviceColumn[i].getFont().getSize() - 1));
			services_border.add(serviceColumn[i]);
		}
		JLabel spaceWhenRotate = new JLabel(serviceTitle[0]);
		rotateHeight = spaceWhenRotate.getPreferredSize().getWidth();
		
		//Make a particular panel for service title columns
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		JPanel top = new JPanel(new BorderLayout());
		top.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.decode(ColorBG.BACKGROUND)));
		top.setBackground(Color.decode(ColorBG.BACKGROUND));
		JPanel topSpace = new JPanel();
		topSpace.setBackground(Color.decode(ColorBG.BACKGROUND));
		int spaceBorn = (int)countSpaceBornWhenRotate();
		topSpace.setPreferredSize(new Dimension((int)hostTitleBox.getPreferredSize().getWidth() - spaceBorn - (iconSize - rotateWidth), 100));
		
		top.add(topSpace);
		top.add(services_border);

		String [][] serviceState = DIMS.getInstance().getServicesMatrix().serviceState;
		JLabel [][] serviceStatus = new JLabel [hostRow.length][serviceColumn.length];
		for(int i = 0; i < hostRow.length; i++){
			for(int j = 0; j < serviceColumn.length; j++){
				if(serviceState[i][j].equals(".")) {
					serviceStatus[i][j] = new JLabel(serviceState[i][j]);
					serviceStatus[i][j].setName(serviceState[i][j]);
					continue;
				}
				else{ 
					String icon = "";
					String [] parts = serviceState[i][j].split("-");
					if(parts[0].equals("0")) icon = "OK.png";
					else if(parts[0].equals("1")) icon = "warning.png";
					else if(parts[0].equals("2")) icon = "critical.png";
					else if(parts[0].equals("3")) icon = "pending.png";
					serviceStatus[i][j] = new LabelIcon(icon, iconSize, iconSize);
					serviceStatus[i][j].setName(parts[1]);
				}
			}
		}

		for(int i = 0; i < hostRow.length; i++){
			c.gridx = 0;
			c.gridy = i;
			c.ipadx = 10;
			hostRow[i].setFont(new Font(hostRow[i].getFont().getFontName(), hostRow[i].getFont().getStyle(), hostRow[i].getFont().getSize()-1));
			bottom.add(hostRow[i], c);
		}
		
		//Make matrix
		for(int row = 0; row < hostRow.length; row++){
			for(int col = 0; col < serviceColumn.length; col++){
				c.gridx = col+2;
				c.gridy = row;
				c.ipadx = 1;
				c.ipady = 2;
				if(serviceStatus[row][col].getName().compareTo(".") != 0) serviceStatus[row][col].addMouseListener(listener);
				bottom.add(serviceStatus[row][col], c);
			}
		}
		services_border.setPreferredSize(new Dimension((int)(bottom.getPreferredSize().getWidth()), 100));
		
		JPanel boxContainer = new JPanel(new BorderLayout());
		boxContainer.setBackground(Color.decode(ColorBG.BACKGROUND));
		boxContainer.add(top, BorderLayout.NORTH);
		boxContainer.add(bottom, BorderLayout.LINE_START);
		
		JPanel marginLeft = new JPanel (new BorderLayout());
		marginLeft.setBackground(Color.decode(ColorBG.BACKGROUND));
		marginLeft.add(boxContainer, BorderLayout.LINE_START);
		
		return marginLeft;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
	}
	
	public double countSpaceBornWhenRotate() {
		double space;
		int halfLength = (int)rotateHeight/2;
		space = halfLength - (Math.cos(Math.toRadians(360 - rotateDegree)) * halfLength);
		return space;
	}
}

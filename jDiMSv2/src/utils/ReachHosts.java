package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import events.MouseHostInGroup;

public class ReachHosts extends JPanel{
	private MouseHostInGroup listener = new MouseHostInGroup();
	
	public ReachHosts(String [][] data){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		
		int size = data.length;
		JPanel [] hosts = new JPanel [size];
		JPanel [] statusPanel = new JPanel [size];
		JPanel [] infoPanel = new JPanel[size];
		JLabel [] status = new JLabel [size];
		JLabel [] since = new JLabel [size];
		JLabel [] info = new JLabel [size];
		JLabel [] output = new JLabel [size];
		

		
		//Add a space
		JPanel space = new JPanel();
		space.setPreferredSize(new Dimension(100,10));
		space.setMaximumSize(space.getPreferredSize());
		space.setBackground(Color.decode(ColorBG.BACKGROUND));
		JLabel spaces = new JLabel(" ");
		space.add(spaces);
		this.add(space);
		
		for (int i = 0; i < size; i++){
			//
			hosts[i] = new JPanel();
			hosts[i].setLayout(new FlowLayout(FlowLayout.LEFT,2,2));
			hosts[i].setBackground(Color.decode(ColorBG.BACKGROUND));
			//Declaration
			statusPanel[i] = new JPanel();
			statusPanel[i].setBackground(Color.decode(ColorBG.BACKGROUND));
			statusPanel[i].setLayout(new BorderLayout());
			statusPanel[i].setPreferredSize(SubDimension.STATUS);
			statusPanel[i].setMaximumSize(statusPanel[i].getPreferredSize());
			status[i] = new JLabel(data[i][1]);
			since[i] = new JLabel(data[i][2]);
			since[i].setFont(new Font("Arial", Font.ITALIC, 11));
			
			infoPanel[i] = new JPanel(new BorderLayout());
			infoPanel[i].setPreferredSize(SubDimension.INFO);
			infoPanel[i].setMaximumSize(infoPanel[i].getPreferredSize());
			infoPanel[i].setBackground(Color.decode(ColorBG.BACKGROUND));
			info[i] = new JLabel(data[i][3] + " ("+data[i][4]+")");
			info[i].setName(data[i][0]);
			info[i].addMouseListener(listener);
			output[i] = new JLabel(data[i][5]);
			output[i].addMouseListener(listener);
			output[i].setName(data[i][0]);
			output[i].setFont(new Font("Arial", Font.ITALIC, 11));
			
			if(data[i][1].equals("0")) {
				statusPanel[i].setBackground(Color.decode(ColorBG.OK));
				status[i].setForeground(Color.decode(ColorBG.BACKGROUND));
				status[i].setText(Glossary.OK);
				since[i].setForeground(Color.decode(ColorBG.BACKGROUND));
			}
			else if(data[i][1].equals("1")){
				statusPanel[i].setBackground(Color.decode(ColorBG.CRITICAL));
				status[i].setForeground(Color.decode(ColorBG.BACKGROUND));
				status[i].setText(Glossary.CRITICAL);
				since[i].setForeground(Color.decode(ColorBG.BACKGROUND));
				info[i].setText(" ! "+info[i].getText());
			}
			else {
				statusPanel[i].setBackground(Color.decode(ColorBG.UNKNOWN));
				status[i].setForeground(Color.decode(ColorBG.BACKGROUND));
				status[i].setText(Glossary.UNKNOWN);
				since[i].setForeground(Color.decode(ColorBG.BACKGROUND));
			}
			
			statusPanel[i].add(status[i], BorderLayout.NORTH);
			statusPanel[i].add(since[i]);
			infoPanel[i].add(info[i], BorderLayout.NORTH);
			infoPanel[i].add(output[i]);
			
			hosts[i].add(statusPanel[i]);
			hosts[i].add(infoPanel[i]);
			
			this.add(hosts[i]);
			//Set component of event
			listener.setComponents(statusPanel, infoPanel, info);
		}
	}
	
	
	public ReachHosts(String [][] data, int [] countData, String objectType){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		
		int size = data.length;
		JPanel [] hosts = new JPanel [size];
		JPanel [] statusPanel = new JPanel [size];
		JPanel [] infoPanel = new JPanel[size];
		JLabel [] status = new JLabel [size];
		JLabel [] since = new JLabel [size];
		JLabel [] info = new JLabel [size];
		JLabel [] output = new JLabel [size];
		
		//Number of services with their status
		JPanel numberService = new ConfiguredHosts(countData);
		//End
		
		//Title box
		JPanel titleContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		titleContainer.setBackground(Color.decode(ColorBG.BACKGROUND));
		JPanel titleBox = new PageTitle(objectType, numberService);
		titleContainer.add(titleBox);
		//End

		
		//Add a space
		JPanel space = new JPanel();
		space.setPreferredSize(new Dimension(100,10));
		space.setMaximumSize(space.getPreferredSize());
		space.setBackground(Color.decode(ColorBG.BACKGROUND));
		JLabel spaces = new JLabel(" ");
		space.add(spaces);
		this.add(space);
		this.add(titleContainer);
		
		for (int i = 0; i < size; i++){
			//
			hosts[i] = new JPanel();
			hosts[i].setLayout(new FlowLayout(FlowLayout.LEFT,2,2));
			hosts[i].setBackground(Color.decode(ColorBG.BACKGROUND));
			//Declaration
			statusPanel[i] = new JPanel();
			statusPanel[i].setBackground(Color.decode(ColorBG.BACKGROUND));
			statusPanel[i].setLayout(new BorderLayout());
			statusPanel[i].setPreferredSize(SubDimension.STATUS);
			statusPanel[i].setMaximumSize(statusPanel[i].getPreferredSize());
			status[i] = new JLabel(data[i][1]);
			since[i] = new JLabel(data[i][2]);
			since[i].setFont(new Font("Arial", Font.ITALIC, 11));
			
			infoPanel[i] = new JPanel(new BorderLayout());
			infoPanel[i].setPreferredSize(SubDimension.INFO);
			infoPanel[i].setMaximumSize(infoPanel[i].getPreferredSize());
			infoPanel[i].setBackground(Color.decode(ColorBG.BACKGROUND));
			info[i] = new JLabel(data[i][3] + " ("+data[i][4]+")");
			info[i].setName(data[i][0]);
			info[i].addMouseListener(listener);
			output[i] = new JLabel(data[i][5]);
			output[i].addMouseListener(listener);
			output[i].setName(data[i][0]);
			output[i].setFont(new Font("Arial", Font.ITALIC, 11));
			
			if(data[i][1].equals("0")) {
				statusPanel[i].setBackground(Color.decode(ColorBG.OK));
				status[i].setForeground(Color.decode(ColorBG.BACKGROUND));
				status[i].setText(Glossary.UP);
				since[i].setForeground(Color.decode(ColorBG.BACKGROUND));
			}
			else if(data[i][1].equals("1")){
				statusPanel[i].setBackground(Color.decode(ColorBG.CRITICAL));
				status[i].setForeground(Color.decode(ColorBG.BACKGROUND));
				status[i].setText(Glossary.DOWN);
				since[i].setForeground(Color.decode(ColorBG.BACKGROUND));
				info[i].setText(" ! "+info[i].getText());
			}
			else {
				statusPanel[i].setBackground(Color.decode(ColorBG.UNKNOWN));
				status[i].setForeground(Color.decode(ColorBG.BACKGROUND));
				status[i].setText(Glossary.UNKNOWN);
				since[i].setForeground(Color.decode(ColorBG.BACKGROUND));
			}
			
			statusPanel[i].add(status[i], BorderLayout.NORTH);
			statusPanel[i].add(since[i]);
			infoPanel[i].add(info[i], BorderLayout.NORTH);
			infoPanel[i].add(output[i]);
			
			hosts[i].add(statusPanel[i]);
			hosts[i].add(infoPanel[i]);
			
			this.add(hosts[i]);
			//Set component of event
			listener.setComponents(statusPanel, infoPanel, info);
		}
	}
}

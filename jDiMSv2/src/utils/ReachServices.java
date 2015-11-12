package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import events.MouseReachServices;

public class ReachServices extends JPanel{
	private MouseReachServices listener = new MouseReachServices();
	
	public ReachServices(String [][] data){
		//Box contains panels
				this.setLayout(new BorderLayout());
				this.setBackground(Color.decode(ColorBG.BACKGROUND));
				JPanel hostsBox = new JPanel();
				hostsBox.setLayout(new BoxLayout(hostsBox, BoxLayout.Y_AXIS));
				hostsBox.setBackground(Color.decode(ColorBG.BACKGROUND));
				
				
				int length = data.length;
				JPanel [] hosts = new JPanel [length];
				JPanel [] statusPanel = new JPanel [length];
				JPanel [] infoPanel = new JPanel[length];
				JLabel [] status = new JLabel [length];
				JLabel [] since = new JLabel [length];
				JLabel [] info = new JLabel [length];
				JLabel [] output = new JLabel [length];
				
				//Add a space
				JPanel space = new JPanel();
				space.setPreferredSize(new Dimension(100,10));
				space.setMaximumSize(space.getPreferredSize());
				space.setBackground(Color.decode(ColorBG.BACKGROUND));
				JLabel spaces = new JLabel(" ");
				space.add(spaces);
				hostsBox.add(space);
						
				for (int i = 0; i < length; i++){
					//
					hosts[i] = new JPanel();
					hosts[i].setPreferredSize(SubDimension.LINEBOX);
					hosts[i].setMaximumSize(hosts[i].getPreferredSize());
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
					info[i] = new JLabel(data[i][3]);
					info[i].addMouseListener(listener);
					info[i].setName(data[i][0]);
					output[i] = new JLabel(data[i][4]);
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
						statusPanel[i].setBackground(Color.decode(ColorBG.WARNING));
						status[i].setForeground(Color.decode(ColorBG.BACKGROUND));
						status[i].setText(Glossary.WARNING);
						since[i].setForeground(Color.decode(ColorBG.BACKGROUND));
					}
					else if(data[i][1].equals("2")){
						statusPanel[i].setBackground(Color.decode(ColorBG.CRITICAL));
						status[i].setForeground(Color.decode(ColorBG.BACKGROUND));
						status[i].setText(Glossary.CRITICAL);
						since[i].setForeground(Color.decode(ColorBG.BACKGROUND));
					}
					else if(data[i][1].equals("3")){
						statusPanel[i].setBackground(Color.decode(ColorBG.PENDING));
						status[i].setForeground(Color.decode(ColorBG.BACKGROUND));
						status[i].setText(Glossary.PENDING);
						since[i].setForeground(Color.decode(ColorBG.BACKGROUND));
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
						
					hostsBox.add(hosts[i]);
				}
				listener.setComponents(statusPanel, infoPanel, info);
				this.add(hostsBox, BorderLayout.NORTH);
	}
	
	public ReachServices(String [][] data, int [] countData, String objectType) throws SQLException{
		//Box contains panels
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		JPanel hostsBox = new JPanel();
		hostsBox.setLayout(new BoxLayout(hostsBox, BoxLayout.Y_AXIS));
		hostsBox.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		
		//Number of services with their status
		JPanel numberService = new ConfiguredServices(countData);
		//End
		
		//Title box
		JPanel titleBox = new PageTitle(objectType, numberService);
		//End
		
		int length = data.length;
		JPanel [] hosts = new JPanel [length];
		JPanel [] statusPanel = new JPanel [length];
		JPanel [] infoPanel = new JPanel[length];
		JLabel [] status = new JLabel [length];
		JLabel [] since = new JLabel [length];
		JLabel [] info = new JLabel [length];
		JLabel [] output = new JLabel [length];
		
		//Add a space
		JPanel space = new JPanel();
		space.setPreferredSize(new Dimension(100,10));
		space.setMaximumSize(space.getPreferredSize());
		space.setBackground(Color.decode(ColorBG.BACKGROUND));
		JLabel spaces = new JLabel(" ");
		space.add(spaces);
		hostsBox.add(space);
				
		for (int i = 0; i < length; i++){
			//
			hosts[i] = new JPanel();
			hosts[i].setPreferredSize(SubDimension.LINEBOX);
			hosts[i].setMaximumSize(hosts[i].getPreferredSize());
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
			info[i] = new JLabel(data[i][3]);
			info[i].addMouseListener(listener);
			info[i].setName(data[i][0]);
			output[i] = new JLabel(data[i][4]);
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
				statusPanel[i].setBackground(Color.decode(ColorBG.WARNING));
				status[i].setForeground(Color.decode(ColorBG.BACKGROUND));
				status[i].setText(Glossary.WARNING);
				since[i].setForeground(Color.decode(ColorBG.BACKGROUND));
			}
			else if(data[i][1].equals("2")){
				statusPanel[i].setBackground(Color.decode(ColorBG.CRITICAL));
				status[i].setForeground(Color.decode(ColorBG.BACKGROUND));
				status[i].setText(Glossary.CRITICAL);
				since[i].setForeground(Color.decode(ColorBG.BACKGROUND));
			}
			else if(data[i][1].equals("3")){
				statusPanel[i].setBackground(Color.decode(ColorBG.PENDING));
				status[i].setForeground(Color.decode(ColorBG.BACKGROUND));
				status[i].setText(Glossary.PENDING);
				since[i].setForeground(Color.decode(ColorBG.BACKGROUND));
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
				
			hostsBox.add(hosts[i]);
		}
		listener.setComponents(statusPanel, infoPanel, info);
		this.add(titleBox, BorderLayout.NORTH);
		this.add(hostsBox);
	}
}

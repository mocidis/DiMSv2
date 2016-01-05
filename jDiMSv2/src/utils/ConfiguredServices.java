package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import events.MouseConfiguredServices;

public class ConfiguredServices extends JPanel{
	
	private MouseConfiguredServices listener = new MouseConfiguredServices();
	
	public ConfiguredServices(int [] data){
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		int sum = 0;
		for(int i = 0; i < data.length-1; i++){
			sum += data[i];
		}
		
		JPanel totalPanel = new JPanel(new BorderLayout());
		totalPanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		JPanel okPanel = new JPanel(new BorderLayout());
		okPanel.setPreferredSize(SubDimension.SQUARED);
		okPanel.setMaximumSize(okPanel.getPreferredSize());
		okPanel.setBackground(Color.decode(ColorBG.OK));
		
		JPanel warningPanel = new JPanel(new BorderLayout());
		warningPanel.setPreferredSize(SubDimension.SQUARED);
		warningPanel.setMaximumSize(okPanel.getPreferredSize());
		warningPanel.setBackground(Color.decode(ColorBG.WARNING));
		
		JPanel criticalPanel = new JPanel(new BorderLayout());
		criticalPanel.setPreferredSize(SubDimension.SQUARED);
		criticalPanel.setMaximumSize(okPanel.getPreferredSize());
		criticalPanel.setBackground(Color.decode(ColorBG.CRITICAL));
		
		JPanel pendingPanel = new JPanel(new BorderLayout());
		pendingPanel.setPreferredSize(SubDimension.SQUARED);
		pendingPanel.setMaximumSize(okPanel.getPreferredSize());
		pendingPanel.setBackground(Color.decode(ColorBG.PENDING));
		
		JPanel unknownPanel = new JPanel(new BorderLayout());
		unknownPanel.setPreferredSize(SubDimension.SQUARED);
		unknownPanel.setMaximumSize(okPanel.getPreferredSize());
		unknownPanel.setBackground(Color.decode(ColorBG.UNKNOWN));
		
		JLabel total = new JLabel(sum+ " configured services: ");
		JLabel ok = new JLabel(String.valueOf(data[0]));
		ok.addMouseListener(listener);
		ok.setName("0");
		ok.setForeground(Color.decode(ColorBG.BACKGROUND));
		JLabel warning = new JLabel(String.valueOf(data[1]));
		warning.setName("1");
		warning.setForeground(Color.decode(ColorBG.BACKGROUND));
		warning.addMouseListener(listener);
		JLabel critical = new JLabel(String.valueOf(data[2]));
		critical.setName("2");
		critical.addMouseListener(listener);
		critical.setForeground(Color.decode(ColorBG.BACKGROUND));
		JLabel pending = new JLabel(String.valueOf(data[3]));
		pending.setName("3");
		pending.addMouseListener(listener);
		pending.setForeground(Color.decode(ColorBG.BACKGROUND));
		JLabel unknown = new JLabel(String.valueOf(data[4]));
		unknown.addMouseListener(listener);
		unknown.setName("4");
		unknown.setForeground(Color.decode(ColorBG.BACKGROUND));
		
		totalPanel.add(total);
		okPanel.add(ok);
		warningPanel.add(warning);
		criticalPanel.add(critical);
		pendingPanel.add(pending);
		unknownPanel.add(unknown);
		
		this.add(totalPanel);
		this.add(okPanel);
		this.add(warningPanel);
		this.add(criticalPanel);
		this.add(pendingPanel);
		this.add(unknownPanel);
	}
}

package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import events.MouseConfiguredHosts;

public class ConfiguredHosts extends JPanel{
	private MouseConfiguredHosts listener = new MouseConfiguredHosts();
	
	public ConfiguredHosts(int [] data){
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		int sum = 0;
		for(int i = 0; i < data.length; i++){
			sum += data[i];
		}
		
		JPanel totalPanel = new JPanel(new BorderLayout());
		totalPanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		JPanel okPanel = new JPanel(new BorderLayout());
		okPanel.setPreferredSize(SubDimension.SQUARED);
		okPanel.setMaximumSize(okPanel.getPreferredSize());
		okPanel.setBackground(Color.decode(ColorBG.OK));
		
		
		JPanel criticalPanel = new JPanel(new BorderLayout());
		criticalPanel.setPreferredSize(SubDimension.SQUARED);
		criticalPanel.setMaximumSize(okPanel.getPreferredSize());
		criticalPanel.setBackground(Color.decode(ColorBG.CRITICAL));
		
		
		
		JLabel total = new JLabel(sum+ " configured hosts: ");
		JLabel ok = new JLabel(String.valueOf(data[0]));
		ok.addMouseListener(listener);
		ok.setName("0");
		ok.setForeground(Color.decode(ColorBG.BACKGROUND));
		
		JLabel critical = new JLabel(String.valueOf(data[1]));
		critical.setName("1");
		critical.addMouseListener(listener);
		critical.setForeground(Color.decode(ColorBG.BACKGROUND));
		
		
		totalPanel.add(total);
		okPanel.add(ok);
		criticalPanel.add(critical);
		
		this.add(totalPanel);
		this.add(okPanel);
		this.add(criticalPanel);

	}
}

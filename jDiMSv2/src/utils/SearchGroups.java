package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import events.MouseSearchGroups;

public class SearchGroups extends JPanel{
	private MouseSearchGroups listener = new MouseSearchGroups();
	
	public SearchGroups(String [][] data, String title, String IDIcon, String keyword, String group){
		int size = data.length;
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setPreferredSize(SubDimension.LINEBOX);
		titlePanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		JLabel titleLabel = new JLabel(title);
		titlePanel.add(titleLabel, BorderLayout.LINE_START);
		titleLabel.setForeground(Color.decode(ColorBG.TOP_BACKGROUND));
		this.add(titlePanel);
		
		if(size > 0){	
			//Declare panel
			JPanel [] Groups = new JPanel [size];
			JPanel [] leftComponentPanel = new JPanel [size];
			JPanel [] rightComponentPanel = new JPanel [size];
			JPanel [] IDPanel = new JPanel [size];
			
			//Declare Label
			JLabel [] name = new JLabel [size];
			JLabel [] ID = new JLabel [size];
			
			for(int row = 0; row < size; row++){
				Groups[row] = new JPanel(new FlowLayout(FlowLayout.LEFT));
				Groups[row].setBackground(Color.decode(ColorBG.BACKGROUND));
				Groups[row].setPreferredSize(SubDimension.LINEBOX);
				Groups[row].setMaximumSize(Groups[row].getPreferredSize());
				leftComponentPanel[row] = new JPanel(new BorderLayout());
				leftComponentPanel[row].setPreferredSize(SubDimension.IDENTIFIED_OBJECT_TYPE);
				leftComponentPanel[row].setBackground(Color.decode(ColorBG.BACKGROUND));
				leftComponentPanel[row].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(ColorBG.LINK)));
				IDPanel[row] = new JPanel();
				IDPanel[row].setBackground(Color.decode(ColorBG.BACKGROUND));
				ID[row] = new JLabel(IDIcon);
				ID[row].setForeground(Color.decode(ColorBG.TOP_BACKGROUND));
				ID[row].setFont(new Font(ID[row].getFont().getName(), ID[row].getFont().getStyle(), ID[row].getFont().getSize()+10));
				IDPanel[row].add(ID[row]);
				leftComponentPanel[row].add(IDPanel[row], BorderLayout.CENTER);
				
				rightComponentPanel[row] = new JPanel(new BorderLayout());
				rightComponentPanel[row].setBackground(Color.decode(ColorBG.BACKGROUND));
				name[row] = new JLabel(data[row][1]);
				name[row].addMouseListener(listener);
				name[row].setName(data[row][0]);
				rightComponentPanel[row].add(name[row]);
				
				Groups[row].add(leftComponentPanel[row]);
				Groups[row].add(rightComponentPanel[row]);
				
				this.add(Groups[row]);
			}
			
			
		}
		else{
			JPanel notification = new JPanel(new FlowLayout(FlowLayout.LEFT));
			notification.setBackground(Color.decode(ColorBG.BACKGROUND));
			notification.add(new JLabel("No "+group+" matching the filter: \""+keyword+"\""));
			this.add(notification, BorderLayout.LINE_START);
		}
	}
}

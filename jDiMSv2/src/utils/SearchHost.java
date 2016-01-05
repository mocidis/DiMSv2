package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchHost extends JPanel{
	private String [][] data;
	private int [] countData;
	public SearchHost(String keyword) throws SQLException{
		data = DIMS.getInstance().getPicker().searchHostByName(keyword);
		
		int size = data.length;
		
		//Layout
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		JPanel boxContent = new JPanel();
		boxContent.setLayout(new BoxLayout(boxContent, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setPreferredSize(SubDimension.LINEBOX);
		titlePanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		JLabel titleLabel = new JLabel("Hosts");
		titlePanel.add(titleLabel, BorderLayout.LINE_START);
		titleLabel.setForeground(Color.decode(ColorBG.TOP_BACKGROUND));
		boxContent.add(titlePanel);
		
		if(size > 0){
			JPanel servicesContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
			servicesContainer.setBackground(Color.decode(ColorBG.BACKGROUND));
			JPanel services = new ReachHosts(data);
			servicesContainer.add(services);
			boxContent.add(servicesContainer);
		}
		else{
			JPanel notification = new JPanel(new FlowLayout(FlowLayout.LEFT));
			notification.setBackground(Color.decode(ColorBG.BACKGROUND));
			notification.add(new JLabel("No host matching the filter: \""+keyword+"\""));
			boxContent.add(notification, BorderLayout.LINE_START);
		}
		this.add(boxContent, BorderLayout.LINE_START);
	}
}

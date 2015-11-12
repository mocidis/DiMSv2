package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchService extends JPanel{
	private String [][] data;
	public SearchService(String keyword) throws SQLException{
		data = DIMS.getInstance().getPicker().searchServiceByName(keyword);
		int size = data.length;
		
		//Layout
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		
		JPanel boxContent = new JPanel();
		boxContent.setLayout(new BoxLayout(boxContent, BoxLayout.Y_AXIS));
		boxContent.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setPreferredSize(SubDimension.LINEBOX);
		titlePanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		JLabel titleLabel = new JLabel("Services");
		titlePanel.add(titleLabel, BorderLayout.LINE_START);
		titleLabel.setForeground(Color.decode(ColorBG.TOP_BACKGROUND));
		boxContent.add(titlePanel);
		
		if(size > 0){
			//JPanel servicesContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
			//servicesContainer.setBackground(Color.decode(ColorBG.BACKGROUND));
			JPanel services = new ReachServices(data);
			//servicesContainer.add(services);
			boxContent.add(services);
		}
		else{
			JPanel notification = new JPanel(new FlowLayout(FlowLayout.LEFT));
			notification.setBackground(Color.decode(ColorBG.BACKGROUND));
			notification.add(new JLabel("No service matching the filter: \""+keyword+"\""));
			boxContent.add(notification);
		}
		this.add(boxContent, BorderLayout.LINE_START);
	}
}

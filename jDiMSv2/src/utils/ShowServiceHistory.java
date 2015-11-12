package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.PanelMainDetailContent;

public class ShowServiceHistory extends PanelMainDetailContent{
	private int position = 0;
	private int setPosition(int service_object_id) throws SQLException{
		for(int i = 0; i < DIMS.getInstance().getServiceHistory().service_object_id.length; i++){
			if(service_object_id == Integer.parseInt(DIMS.getInstance().getServiceHistory().service_object_id[i][0])) {
				position = i;
				break;
			}
		}
		return position;
	}
	
	@Override
	public JPanel createContent(int service_object_id) throws SQLException{
		setPosition(service_object_id);
		if(DIMS.getInstance().getServiceHistory().has_history[position]){
			int RowSize = DIMS.getInstance().getServiceHistory().service_object_id[position].length;
			String [][] data = new String [RowSize][6];
			for(int col = 0; col < RowSize; col++){
				data[col][0] = DIMS.getInstance().getServiceHistory().service_object_id[position][col];
				data[col][1] = DIMS.getInstance().getServiceHistory().state[position][col];
				data[col][2] = DIMS.getInstance().getServiceHistory().state_time[position][col];
				data[col][3] = DIMS.getInstance().getServiceHistory().display_name[position][col];
				data[col][4] = DIMS.getInstance().getServiceHistory().check_attempt[position][col];
				data[col][5] = DIMS.getInstance().getServiceHistory().output[position][col];
			}
			
			JPanel boxbox = History.Draw(data);
			return boxbox;
		}
		else{
			
			JPanel boxbox = new JPanel(new BorderLayout());
			boxbox.setPreferredSize(SubDimension.MAIN_SCREEN);
			boxbox.setBackground(Color.decode(ColorBG.BACKGROUND));
			
			//Add title page
			JPanel titleContainer = new JPanel(new BorderLayout());
			titleContainer.setPreferredSize(SubDimension.LINEBOX);
			titleContainer.setMaximumSize(titleContainer.getPreferredSize());
			JPanel title = new JPanel(new FlowLayout(FlowLayout.LEFT));
			title.setBackground(Color.decode(ColorBG.BACKGROUND));
			JLabel titleLabel = new JLabel("History");
			title.add(titleLabel);
			
			//Where are u?
			int where = 0;
			for(int i = 0; i < DIMS.getInstance().getServicePage().getServicesId().length; i++){
				if(service_object_id == Integer.parseInt(DIMS.getInstance().getServicePage().getServicesId()[i])){
					where = i;
					break;
				}
					
			}
			JPanel place = new PageTitle(DIMS.getInstance().getServicePage().getPartOne(where)[4], title);
			
			titleContainer.add(place);
			
			JPanel messageContainer = new JPanel(new BorderLayout());
			messageContainer.setBackground(Color.decode(ColorBG.BACKGROUND));
			String message = "No history";
			JLabel messageBox = new JLabel(message);
			messageBox.setFont(new Font(messageBox.getFont().getName(), Font.ITALIC, messageBox.getFont().getSize() + 10));
			messageContainer.add(messageBox, BorderLayout.PAGE_START);
			
			boxbox.add(titleContainer, BorderLayout.NORTH);
			boxbox.add(messageContainer);
			return boxbox;
		}
	}

}

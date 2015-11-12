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

import gui.PanelMainDetailContent;

public class ShowHostHistory extends PanelMainDetailContent{
	private int position = 0;
	private void setPosition(int host_object_id) throws SQLException{
		for(int i = 0; i < DIMS.getInstance().getHostHistory().host_object_id.length; i++){
			if(host_object_id == Integer.parseInt(DIMS.getInstance().getHostHistory().host_object_id[i][0])) {
				position = i;
				break;
			}
		}
	}
	
	@Override
	public JPanel createContent(int host_object_id) throws SQLException{
		setPosition(host_object_id);
		if(DIMS.getInstance().getHostHistory().has_history[position]){
			int RowSize = DIMS.getInstance().getHostHistory().host_object_id[position].length;
			String [][] data = new String [RowSize][6];
			for(int col = 0; col < RowSize; col++){
				data[col][0] = DIMS.getInstance().getHostHistory().host_object_id[position][col];
				data[col][1] = DIMS.getInstance().getHostHistory().state[position][col];
				data[col][2] = DIMS.getInstance().getHostHistory().state_time[position][col];
				data[col][3] = DIMS.getInstance().getHostHistory().display_name[position][col];
				data[col][4] = DIMS.getInstance().getHostHistory().check_attempt[position][col];
				data[col][5] = DIMS.getInstance().getHostHistory().output[position][col];
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
			for(int i = 0; i < DIMS.getInstance().getHostPage().getHostId().length; i++){
				if(host_object_id == Integer.parseInt(DIMS.getInstance().getServicePage().getServicesId()[i])){
					where = i;
					break;
				}
					
			}
			JPanel place = new PageTitle(DIMS.getInstance().getServicePage().getPartOne(where)[3], title);
			
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

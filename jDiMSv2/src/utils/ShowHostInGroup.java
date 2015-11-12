package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import events.MouseHostInGroup;
import gui.PanelMainDetailContent;

public class ShowHostInGroup {
	private int position = 0;
	
	private void setPosition(int group_object_id) throws SQLException{
		for(int i = 0; i < DIMS.getInstance().getHostGroups().HostGroups.length; i++){
			if(group_object_id == Integer.parseInt(DIMS.getInstance().getHostGroups().HostGroups[i][1])) {
				position = i;
				break;
			}
		}
	}

	public JPanel createContent(int group_object_id) throws SQLException{
		setPosition(group_object_id);
		int RowSize = DIMS.getInstance().getHostInGroup().alias[position].length;
		String [][] data = new String [RowSize][6];
		for(int i = 0; i < RowSize; i++){
			data[i][0] = DIMS.getInstance().getHostInGroup().host_object_id[position][i];
			data[i][1] = DIMS.getInstance().getHostInGroup().state[position][i];
			data[i][2] = DIMS.getInstance().getHostInGroup().since[position][i];
			data[i][3] = DIMS.getInstance().getHostInGroup().hostName[position][i];
			data[i][4] = DIMS.getInstance().getHostInGroup().alias[position][i];
			data[i][5] = DIMS.getInstance().getHostInGroup().output[position][i];
		}
		
		int [] countData = DIMS.getInstance().getPicker().countConfiguredHostInGroup(group_object_id);
		//Box contains panels
		JPanel boxbox = new JPanel(new BorderLayout());
		boxbox.setBackground(Color.decode(ColorBG.BACKGROUND));
		JPanel hostsBox = new ReachHosts(data, countData, DIMS.getInstance().getHostGroups().getHostGroup()[position][0]);
		
		boxbox.add(hostsBox, BorderLayout.LINE_START);
		return boxbox;
	}
}

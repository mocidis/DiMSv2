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

	public JPanel createContent(int group_object_id) throws SQLException{
		int position = FindPosition.toHostInGroup(group_object_id);
		int RowSize = DIMS.getInstance().getHostInGroup().getAlias()[position].length;
		String [][] data = new String [RowSize][7];
		for(int i = 0; i < RowSize; i++){
			data[i][0] = DIMS.getInstance().getHostInGroup().getHostObjectId()[position][i];
			data[i][1] = DIMS.getInstance().getHostInGroup().getState()[position][i];
			data[i][2] = DIMS.getInstance().getHostInGroup().getTime()[position][i];
			data[i][3] = DIMS.getInstance().getHostInGroup().getName()[position][i];
			data[i][4] = DIMS.getInstance().getHostInGroup().getAlias()[position][i];
			data[i][5] = DIMS.getInstance().getHostInGroup().getMessage()[position][i];
			data[i][6] = DIMS.getInstance().getHostInGroup().getGroup()[position][i];
		}
		DIMS.getInstance().setCurrentData(data);
		int [] countData = Classification.stateToHost(data, 1);
		//Box contains panels
		JPanel boxbox = new JPanel(new BorderLayout());
		boxbox.setBackground(Color.decode(ColorBG.BACKGROUND));
		JPanel hostsBox = new ReachHosts(data, countData, DIMS.getInstance().getHostGroups().getHostGroup()[position][0]);
		
		boxbox.add(hostsBox, BorderLayout.LINE_START);
		return boxbox;
	}
}

package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JPanel;

public class ShowServiceByName {

	public JPanel createContent(String serviceName) throws SQLException {
		int position = FindPosition.toServiceInName(serviceName);
		int rowSize = DIMS.getInstance().getServiceInName().getName()[position].length;
		String [][] data = new String [rowSize][6];
		for(int i = 0; i < rowSize; i++) {
			data[i][0] = DIMS.getInstance().getServiceInName().getServiceObjectId()[position][i];
			data[i][1] = DIMS.getInstance().getServiceInName().getState()[position][i];
			data[i][2] = DIMS.getInstance().getServiceInName().getTime()[position][i];
			data[i][3] = DIMS.getInstance().getServiceInName().getName()[position][i];
			data[i][4] = DIMS.getInstance().getServiceInName().getAlias()[position][i];
			data[i][5] = DIMS.getInstance().getServiceInName().getMessage()[position][i];
		}
		//Box contains panels
		JPanel boxContainer = new JPanel(new BorderLayout());
		boxContainer.setBackground(Color.decode(ColorBG.BACKGROUND));
		JPanel box = new ReachServices(data);
		boxContainer.add(box, BorderLayout.LINE_START);
		return boxContainer;
	}
}

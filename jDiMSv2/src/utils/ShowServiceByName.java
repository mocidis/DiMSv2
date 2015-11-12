package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JPanel;

public class ShowServiceByName {

	public JPanel createContent(String serviceName) throws SQLException {
		String [][] data = DIMS.getInstance().getPicker().getServiceByName(serviceName);
		//Box contains panels
		JPanel boxContainer = new JPanel(new BorderLayout());
		boxContainer.setBackground(Color.decode(ColorBG.BACKGROUND));
		JPanel box = new ReachServices(data);
		boxContainer.add(box, BorderLayout.LINE_START);
		return boxContainer;
	}
}

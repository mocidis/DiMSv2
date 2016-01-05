package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import utils.ColorBG;
import utils.IsHostGroup;
<<<<<<< HEAD
import utils.ScreenSpecification;
=======
import utils.ScreenIndex;
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
import utils.ShowHostInGroup;
import utils.ShowMatrix;
import utils.ShowServiceByName;

public class PanelMainContent extends JPanel {
	public PanelMainContent(){}
	public PanelMainContent (int object_id) throws SQLException{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.decode(ColorBG.BACKGROUND)));
<<<<<<< HEAD
		if(object_id == ScreenSpecification.CHANGEPASSWORD){
			PanelChangePass changepass = new PanelChangePass();
			this.add(changepass);
		}
		else if(object_id == ScreenSpecification.MATRIX){
=======
		if(object_id == ScreenIndex.CHANGEPASSWORD){
			PanelChangePass changepass = new PanelChangePass();
			this.add(changepass);
		}
		else if(object_id == ScreenIndex.MATRIX){
>>>>>>> 59b111e4ac1414d99c263946f0e194a1f2a8593d
			ShowMatrix pencil = new ShowMatrix();
			JPanel picture = pencil.Draw();
			this.add(picture, BorderLayout.PAGE_START);
		}
		
		else if(IsHostGroup.check(object_id)){
			ShowHostInGroup shower = new ShowHostInGroup();
			JPanel box = shower.createContent(object_id);
			this.add(box, BorderLayout.PAGE_START);
		}
		
		else{
			JPanel menu = new PanelMenuRight();
			JPanel content = new PanelMainDetailContent(object_id);
			this.add(menu, BorderLayout.EAST);
			this.add(content);
		}
	}
	
	public PanelMainContent (String serviceName) throws SQLException{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.decode(ColorBG.BACKGROUND)));
		ShowServiceByName shower = new ShowServiceByName();
		JPanel list = shower.createContent(serviceName);
		this.add(list, BorderLayout.PAGE_START);
	}
}

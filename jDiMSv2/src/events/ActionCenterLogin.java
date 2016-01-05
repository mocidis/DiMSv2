package events;

import gui.FrameMain;
import gui.FrameNotification;
import gui.PanelCenterMain;
import gui.PanelTopMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import models.Account;
import utils.DIMS;
import utils.HashingPassword;

public class ActionCenterLogin implements ActionListener{
		//LoginGUI
		private JLabel error;
		private JTextField username;
		private JPasswordField password;
		private JButton submit;
		private FrameMain window;
		
		public void setComponent(JTextField username, JPasswordField password, JLabel error, JButton submit){
			this.username = username;
			this.password = password;
			this.submit = submit;
			this.error = error;
		}
		
		
		@Override
		public void actionPerformed(ActionEvent arg0){
			// TODO Auto-generated method stub
			try{
				Account logger = new Account();
				String pass = "";
				boolean OK = false;
				for(int i = 0; i < password.getPassword().length; i++) pass += password.getPassword()[i];
				String hashPass = HashingPassword.hash(pass);
				for(int i = 0; i < logger.getUsername().length; i++){
					if(username.getText().equals(logger.getUsername()[i]) && hashPass.equals(logger.getPassword()[i])) {
//					if(username.getText().equals(logger.getUsername()[i]) && pass.equals("admin")) {
						DIMS.getInstance().setUser(username.getText());
						DIMS.getInstance().setPassword(pass);
						window = DIMS.getInstance().getWindow();
						window.pack();						
						window.reloadTopPanel(new PanelTopMain());
						window.reloadCenterPanel(new PanelCenterMain());
						window.setTitle("DiMS | Monitoring Dashboard");
//						SocketListener.checkSystemCore();
						break;
					}
				}
				error.setText("Login failed");
			}
			catch(Exception e){
				new FrameNotification("Maybe something went wrong when you were trying to login<br>" +e.getMessage());
				e.printStackTrace();
			}
		}
}

package events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import gui.FrameNotification;
import utils.CheckChangePass;

public class ActionChangePass implements ActionListener{
	private JPasswordField oldPass;
	private JPasswordField newPass;
	private JPasswordField rePass;
	private JLabel notification;
	public void setComponents(JPasswordField oldPass, JPasswordField newPass, JPasswordField rePass, JLabel notification) {
		this.oldPass = oldPass;
		this.newPass = newPass;
		this.rePass = rePass;
		this.notification = notification;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String oldPassword = "";
		String newPassword = "";
		String rePassword = "";
		
		for(int i = 0; i < oldPass.getPassword().length; i++) oldPassword += oldPass.getPassword()[i];
		for(int i = 0; i < newPass.getPassword().length; i++) newPassword += newPass.getPassword()[i];
		for(int i = 0; i < rePass.getPassword().length; i++) rePassword += rePass.getPassword()[i];
		
		try {
			int result = CheckChangePass.check(oldPassword, newPassword, rePassword);
			if(result == 0) notification.setText("Password has changed successfully");
			else if(result == 1) notification.setText("Current password is not correct");
			else if(result == 2) notification.setText("Re-password is not match new password");
			else notification.setText("Nothing is correct");
		} catch (Exception e1) {
			new FrameNotification(e1.getMessage());
		}
	}

}

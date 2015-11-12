package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jdesktop.swingx.prompt.PromptSupport;

import events.ActionCenterLogin;
import utils.ColorBG;

public class PanelCenterLogin extends PanelCenter {
	private ActionCenterLogin SubmitListener = new ActionCenterLogin();
	private int titleSize = 15; 
	private String titleStyle = "Arial";
	private String titleText = "DiCOM Monitoring System";
	private Dimension form = new Dimension(400, 200);
	private int field_width = 20;
	private Dimension submit = new Dimension(223, 20);
	private double height = 0.5;
	private String defaultAccount = "admin";
	public PanelCenterLogin() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(JFrame.MAXIMIZED_HORIZ, (int) ((int)JFrame.MAXIMIZED_VERT*height)));
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		JLabel error = new JLabel(" "); //Bao loi dang nhap
		error.setForeground(Color.decode(ColorBG.CRITICAL));
		
		JLabel title = new JLabel(titleText, JLabel.CENTER);
		title.setFont(new Font(titleStyle, Font.BOLD, titleSize));
		JPanel form = new JPanel();
		form.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		form.setPreferredSize(this.form);
		form.setBackground(Color.decode(ColorBG.BACKGROUND));
		
		JPanel namePanel = new JPanel(new BorderLayout());
		JPanel passPanel = new JPanel(new BorderLayout());
		
		JTextField username = new JTextField(field_width);
		username.setText(defaultAccount);
		JLabel nameLabel = new JLabel("Username ");
		nameLabel.setBackground(Color.decode(ColorBG.BACKGROUND));
		nameLabel.setOpaque(true);
		
		JPasswordField password = new JPasswordField(field_width);
		password.setText(defaultAccount);
//		password.setActionCommand("login");
		JLabel passLabel = new JLabel("Password ");
		passLabel.setBackground(Color.decode(ColorBG.BACKGROUND));
		passLabel.setOpaque(true);
		
		JButton submit = new JButton("Login");
		submit.setBackground(Color.decode(ColorBG.TOP_BACKGROUND));
		submit.setForeground(Color.decode(ColorBG.BACKGROUND));
		submit.setPreferredSize(this.submit);
		//Set place holder
		PromptSupport.setPrompt("Username", username);
		PromptSupport.setPrompt("Password", password);
		
		//Add action listener
		SubmitListener.setComponent(username, password, error, submit);
		username.addActionListener(SubmitListener);
		password.addActionListener(SubmitListener);
		submit.addActionListener(SubmitListener);
		//Add components
		
		c.gridx=1;
		c.gridy=0;
		form.add(title,c);
		
		c.gridx=1;
		c.gridy=1;
		form.add(error,c);
		
		c.gridx=0;
		c.gridy=2;		
		form.add(nameLabel,c);
		
		c.gridx=1;
		c.gridy=2;
		form.add(username, c);
		
		c.gridx=0;
		c.gridy=3;
		form.add(passLabel,c);
		
		c.gridx=1;
		c.gridy=3;
		form.add(password,c);
		
		c.gridx=1;
		c.gridy=4;
		form.add(submit,c);
		
		this.add(form, BorderLayout.NORTH);
	}

}

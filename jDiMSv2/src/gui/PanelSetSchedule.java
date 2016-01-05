package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import events.ActionSubmitSchedule;
import utils.ColorBG;
import utils.ScreenSpecification;

public class PanelSetSchedule extends JPanel{
	private GridBagConstraints constraint;
	private ActionSubmitSchedule listener = new ActionSubmitSchedule();
	private Dimension box = new Dimension((int)(0.22*ScreenSpecification.MONITOR_WIDTH), (int)(0.195*ScreenSpecification.MONITOR_HEIGHT));
	private Dimension field = new Dimension((int)(0.033*ScreenSpecification.MONITOR_WIDTH), (int)(0.0325*ScreenSpecification.MONITOR_HEIGHT));
	private Dimension button = new Dimension((int)(0.095*ScreenSpecification.MONITOR_WIDTH), (int)(0.04*ScreenSpecification.MONITOR_HEIGHT));
	private int field_width = (int)(0.00878*ScreenSpecification.MONITOR_WIDTH);
	public PanelSetSchedule(){
		constraint = new GridBagConstraints();
		
		this.setBackground(Color.decode(ColorBG.BACKGROUND));
		this.setPreferredSize(box);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(1), "Reschedule box"));
		this.setMaximumSize(this.getPreferredSize());
		
		JPanel errorPanel = new JPanel(new BorderLayout());
		JPanel yearTopPanel = new JPanel(new BorderLayout());
		JPanel monthTopPanel = new JPanel(new BorderLayout());
		JPanel dayTopPanel = new JPanel(new BorderLayout());
		JPanel hourTopPanel = new JPanel(new BorderLayout());
		JPanel minuteTopPanel = new JPanel(new BorderLayout());
		JPanel dayBotPanel = new JPanel(new BorderLayout());
		JPanel yearBotPanel = new JPanel(new BorderLayout());
		JPanel monthBotPanel = new JPanel(new BorderLayout());
		JPanel hourBotPanel = new JPanel(new BorderLayout());
		JPanel minuteBotPanel = new JPanel(new BorderLayout());
		
		//Error
		JLabel errorLabel = new JLabel("");
		errorLabel.setForeground(Color.decode(ColorBG.CRITICAL));
		errorPanel.setBackground(Color.decode(ColorBG.BACKGROUND));
		errorPanel.add(errorLabel);
		
		//Date
		JLabel yearLabel = new JLabel("year");
		yearTopPanel.add(yearLabel, BorderLayout.CENTER);
		JLabel monthLabel = new JLabel("month");
		monthTopPanel.add(monthLabel, BorderLayout.CENTER);
		JLabel dayLabel = new JLabel("day");
		dayTopPanel.add(dayLabel, BorderLayout.CENTER);
		JTextField day = new JTextField(field_width);
		day.setName("day");
		dayBotPanel.add(day, BorderLayout.CENTER);
		JTextField month = new JTextField(field_width);
		month.setName("month");
		monthBotPanel.add(month, BorderLayout.CENTER);
		JTextField year = new JTextField(field_width);
		year.setName("year");
		yearBotPanel.add(year, BorderLayout.CENTER);
		
		
		//Time
		JLabel hourLabel = new JLabel("hour");
		hourTopPanel.add(hourLabel, BorderLayout.CENTER);
		JLabel minuteLabel = new JLabel("minute");
		minuteTopPanel.add(minuteLabel, BorderLayout.CENTER);
		JTextField hour = new JTextField(field_width);
		hour.setName("hour");
		hourBotPanel.add(hour, BorderLayout.CENTER);
		JTextField minute = new JTextField(field_width);
		minute.setName("minute");
		minuteBotPanel.add(minute, BorderLayout.CENTER);
		
		

		
		JPanel datePanel = new JPanel(new GridBagLayout());
		JPanel timePanel = new JPanel(new GridBagLayout());
		
		JPanel [] dates = {dayTopPanel, monthTopPanel, yearTopPanel,  dayBotPanel, monthBotPanel, yearBotPanel};
		JPanel [] times = {hourTopPanel, minuteTopPanel, hourBotPanel, minuteBotPanel};
		for(int i = 0; i < 2; i++){
			if( i == 0) {
				for(int j = 0; j < 3; j++){
					dates[j].setBackground(Color.decode(ColorBG.BACKGROUND));
					dates[j].setPreferredSize(field);
					dates[j].setMaximumSize(dates[j].getPreferredSize());
					constraint.gridy = i;
					constraint.gridx = j;
					constraint.ipadx = 10;
					datePanel.add(dates[j], constraint);
				}
			}
			if (i == 1) {
				for (int j = 3; j < dates.length; j++){
					dates[j].setBackground(Color.decode(ColorBG.BACKGROUND));
					dates[j].setPreferredSize(field);
					dates[j].setMaximumSize(dates[j].getPreferredSize());
					constraint.gridy = i+2;
					constraint.gridx = j-3;
					constraint.ipadx = 10;
					datePanel.add(dates[j], constraint);
				}
			}
			
		}
		for(int i = 0; i < 2; i++){
			if( i == 0){
				for(int j = 0; j < 2; j++){
					times[j].setBackground(Color.decode(ColorBG.BACKGROUND));
					times[j].setPreferredSize(field);
					times[j].setMaximumSize(times[j].getPreferredSize());
					constraint.gridy = i;
					constraint.gridx = j;
					constraint.ipadx = 10;
					timePanel.add(times[j], constraint);
				}
			}
			if (i == 1){
				for (int j = 2; j < times.length; j++){
					times[j].setBackground(Color.decode(ColorBG.BACKGROUND));
					times[j].setPreferredSize(field);
					times[j].setMaximumSize(times[j].getPreferredSize());
					constraint.gridy = i+2;
					constraint.gridx = j-2;
					constraint.ipadx = 10;
					timePanel.add(times[j], constraint);
				}
			}
			
		}
		
		JPanel panelButton = new JPanel();
		panelButton.setBackground(Color.decode(ColorBG.BACKGROUND));
		panelButton.setMaximumSize(panelButton.getPreferredSize());
		JButton reSchedule = new JButton("Reschedule");
		reSchedule.addActionListener(listener);
		reSchedule.setPreferredSize(this.button);
		reSchedule.setBackground(Color.decode(ColorBG.SUBMIT));
		reSchedule.setForeground(Color.decode(ColorBG.BACKGROUND));
		panelButton.add(reSchedule);
		
		//Set to event component
		listener.setComponents(this, year, month, day, hour, minute, errorLabel);
		
		//Add to box
		constraint.gridy = 0;
		constraint.gridx = 2;
		this.add(errorPanel, constraint);
		
		constraint.gridx = 0;
		constraint.gridy = 1;
		constraint.ipadx = 20;
		this.add(datePanel, constraint);
		constraint.gridx = 1;
		constraint.gridy = 2;
		this.add(timePanel, constraint);
		constraint.gridx = 2;
		constraint.gridy = 4;
		this.add(panelButton, constraint);
	}
}

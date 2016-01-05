package events;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.FrameNotification;
import gui.PanelSetSchedule;
import utils.CheckValidDate;
import utils.DIMS;
import utils.ExternalCommandPipe;
import utils.IsHost;
import utils.UpdateData;
import utils.UpdateWholeSystem;

public class ActionSubmitSchedule implements ActionListener{
	private PanelSetSchedule box;
	private JTextField year, month, day, hour, minute;
	private JLabel error;

	public void setComponents(PanelSetSchedule box, JTextField year, JTextField month, JTextField day, JTextField hour, JTextField minute, JLabel error){
		this.box = box;
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.error = error;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton now = (JButton)e.getSource();
		try{
			if(CheckValidDate.check(year, month, day, hour, minute)){
				now.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				int yr = Integer.parseInt(year.getText());
				int mth = Integer.parseInt(month.getText());
				int days = Integer.parseInt(day.getText());
				int hrs = Integer.parseInt(hour.getText());
				int min = Integer.parseInt(minute.getText());
				Date myTime = new Date(yr-1900, mth-1, days, hrs, min, 00);
				String strTime = String.valueOf(myTime.getTime());
				strTime = strTime.substring(0, 10);
				
				String WantedDate = year.getText()+"-"+month.getText()+"-"+day.getText()+" "+hour.getText()+":"+minute.getText()+":00";
				if(IsHost.check(DIMS.getInstance().getCurrentOID())){
					ExternalCommandPipe.rescheduleHost(DIMS.getInstance().getCurrentHostName(), strTime);
					
					UpdateData.updateHostSchedule(DIMS.getInstance().getCurrentOID(), WantedDate);
					DIMS myApp = DIMS.getInstance();
					UpdateWholeSystem updater = new UpdateWholeSystem();
					updater.update(myApp);
				}
				else{
					ExternalCommandPipe.rescheduleService(DIMS.getInstance().getCurrentHostName(), DIMS.getInstance().getCurrentServiceName(), strTime);
					
					UpdateData.updateServiceSchedule(DIMS.getInstance().getCurrentOID(), WantedDate);
					DIMS myApp = DIMS.getInstance();
					UpdateWholeSystem updater = new UpdateWholeSystem();
					updater.update(myApp);
				}
			}
			else error.setText("Failed! Please check the typed infomations");
		}catch(SQLException e1){
			new FrameNotification("Maybe something went wrong when you were trying to reschedule<br>" +e1.getMessage());
		}
	}
	
}

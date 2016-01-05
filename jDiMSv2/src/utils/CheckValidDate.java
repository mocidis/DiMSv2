package utils;

import javax.swing.JTextField;

public final class CheckValidDate {
	public static boolean check(JTextField year, JTextField month, JTextField day, JTextField hour, JTextField minute) {
		
		if(!year.getText().equals("") && !month.getText().equals("") && !minute.getText().equals("") && !hour.getText().equals("") && !minute.getText().equals("")){
			
			int yearTF = Integer.parseInt(year.getText());
			int monthTF = Integer.parseInt(month.getText());
			int dayTF = Integer.parseInt(day.getText());
			int hourTF = Integer.parseInt(hour.getText());
			int minuteTF = Integer.parseInt(minute.getText());
			
			if	(		(yearTF >= 2015) && 
						(monthTF <= 12 && monthTF >= 1) && 
						(dayTF <= 31 && dayTF >= 1) && 
						(hourTF <= 23 && hourTF >= 0) &&
						(minuteTF <= 59 && minuteTF >= 0)
				) return true;
		}
		return false;
	}
	
	
}

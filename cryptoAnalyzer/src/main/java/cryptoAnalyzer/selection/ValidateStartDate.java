package cryptoAnalyzer.selection;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * This class represents to check whether the start date selected by user 
 * is the date before current date or not, if a future date chosen,
 * then the data would be considered as invalidation and a warning 
 * message should be returned to allow user to select date again.
 * 
 * @author Ruyi Jin
 * @version 1.0
 * @since 2021-12-03
 *
 */
public class ValidateStartDate {
	private String date;
	
	/**
	 * this is the constructor for the class
	 * @param date assigned value in date
	 */
	public ValidateStartDate(String date) {
		setDate(date);		
	}

	/**
	 * setter method: assigned value to date
	 * method will be used when the data of the selected date is called
	 * @param date the value of date will be stored. 
	 */
	private void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * This method is to check whether the chosen start date is
	 * valid (previous date) or invalid (future date)
	 * @return false if the start date is invalid
	 */
	public boolean validSDate() {
		DateFormat df = null;
		if(date.contains("/"))
			 df = new SimpleDateFormat("dd/MM/yyyy");
		else if(date.contains("-"))
			 df = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Calendar contCalendar = Calendar.getInstance();
			Calendar tagCalendar = (Calendar) contCalendar.clone();
			tagCalendar.setTime(df.parse(date));
	        return tagCalendar.before(contCalendar);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;				
	}
	
	/**
	 * getter method: get the value of the start date
	 * @return the value of date
	 */
	public String getDate() {
		return this.date;
	}
}

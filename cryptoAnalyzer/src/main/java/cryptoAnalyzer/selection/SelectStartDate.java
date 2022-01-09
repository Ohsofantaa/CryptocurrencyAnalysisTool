package cryptoAnalyzer.selection;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import cryptoAnalyzer.selection.ValidateStartDate;
import cryptoAnalyzer.analysis.getDataFromSelectionList;
import cryptoAnalyzer.gui.ExceptionDialog;
import cryptoAnalyzer.analysis.*;

/**
 * Inherit SelectionObserver class, use Observer Design Pattern
 * This method implements the function that user select a start date
 * after they choose the type of cryptocurrency to analyze.
 * @author Ruyi Jin
 * @version 1.0
 * @since 2021-12-03
 *
 */
public class SelectStartDate extends SelectionObserver { 
	private ArrayList<String> dateList;
	private String[] dateStringList;
	private String date;
	private boolean isPastDate;
	
	/**
	 * this is the constructor for the class
	 * @param subject update the chosen start date
	 * @param selectionList stores the list of date
	 */
	public SelectStartDate(String subject, SelectionList selectionList) {
		this.selectionList = selectionList;
		this.selectionList.attach(this);
		dateList = new ArrayList<String>();
		update(subject);
	}
	

	
	/**
	 * Override the update method inherited from SelectionObserver class
	 */
	@Override
	public void update(String subject) {
		 boolean isPastDate = new ValidateStartDate(subject).validSDate();
         if(isPastDate) {
         	this.date = subject;
         	this.isPastDate = true;
         }
         else {
         	System.out.println("Invalid startDate");
         	this.isPastDate = false;
         }
	}
	
	/**
	 * get and format the chosen date, and get date when the interval
	 * frequency of data fetching was selected (daily, weekly, monthly, yearly)
	 */
	public void getMergeDate(){
//		dateList.clear();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if(date.contains("-"))
			df = new SimpleDateFormat("dd-MM-yyyy");
		String currentDay = date;
		dateList.add(currentDay);
		Calendar calendar = new GregorianCalendar();
		try {
			calendar.setTime(df.parse(currentDay));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Interval = new getDataFromSelectionList(selectionList).getInterval();
		String addDate;
		// 4 times 
		if (Interval == "Daily") {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			addDate = df.format(calendar.getTime());
			
			while(new ValidateStartDate(addDate).validSDate()) {
				dateList.add(addDate);
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				addDate = df.format(calendar.getTime());				
			}
		}
		else if(Interval == "Weekly") {
			calendar.add(Calendar.WEEK_OF_MONTH, 1);
			addDate = df.format(calendar.getTime());
			
			while(new ValidateStartDate(addDate).validSDate()) {
				dateList.add(addDate);
				calendar.add(Calendar.WEEK_OF_MONTH, 1);
				addDate = df.format(calendar.getTime());				
			}
		}
		else if(Interval == "Monthly") {
			calendar.add(Calendar.MONTH, 1);
			addDate = df.format(calendar.getTime());
			
			while(new ValidateStartDate(addDate).validSDate()) {
				dateList.add(addDate);
				calendar.add(Calendar.MONTH, 1);
				addDate = df.format(calendar.getTime());
			}
		}
		else if(Interval == "Yearly") {
			calendar.add(Calendar.YEAR, 1);
			addDate = df.format(calendar.getTime());
			
			while(new ValidateStartDate(addDate).validSDate()) {
				dateList.add(addDate);
				calendar.add(Calendar.YEAR, 1);
				addDate = df.format(calendar.getTime());
			}
		}
	}
	
	/**
	 * convert the value of dates into a list
	 */
	public void convertToList() {
		getMergeDate();
		dateStringList = new String[dateList.size()];
		int count = 0;
		for(String date : dateList) {
			dateStringList[count] = date;
			count++;
		}
	}
	
	/**
	 * getter method: convert the date to list
	 * @return the list of past date
	 */
	public String[] getDateList() {
		if(isPastDate) {
			if(dateStringList == null)
				convertToList();
			return this.dateStringList;
		}
		else {
			return null;
		}
	}
	
	
	/**
	 * getter method: get the value of the start date
	 * @return the value of date
	 */
	public String getDate() {
		return this.date;
	}
	
	
	/**
	 * getter method: get the value the whether the start date is past date or not
	 * @return true if the date is valid
	 */
	public boolean getIsPastDay() {
		return this.isPastDate;
	}
	
	/**
	 * set method: reset dateStringList as null
	 */
	public void resetDateList() {
		this.dateStringList = null;
		this.dateList.clear();
	}
}

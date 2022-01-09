package cryptoAnalyzer.selection;
import java.util.ArrayList;

import cryptoAnalyzer.selection.*;
/**
 * This class convert result get from four select classes into a whole list
 * @author Ruyi Jin
 * @version 1.0
 * @since 2021-12-03
 *
 */
public class ConvertToList extends SelectionObserver {
	private SelectStartDate selectStartDate;
	private ArrayList<String> dateList;
	private String[] dateStringList;
	private String date;
	private boolean isPastDate;
	private String interval;
	
	/**
	 * this is the constructor for the class
	 * @param subject update the subject(date, interval, metrics, crypto) to be performed
	 * @param selectionList stores as a list 
	 */
	public ConvertToList(String subject, SelectionList selectionList) {
		this.selectionList = selectionList;
		this.selectionList.attach(this);
		selectStartDate = new SelectStartDate(subject, selectionList);
		date = selectStartDate.getDate();
		isPastDate = selectStartDate.getIsPastDay();
		if(isPastDate) {
			selectStartDate.getMergeDate();
			selectStartDate.convertToList();
		}
	}

	@Override
	public void update(String subject) {
		// TODO Auto-generated method stub
	}

	/**
	 * getter method: get the algorithm which can perform the analysis by selected type
	 * @return the string list storing date
	 */
	public String[] getDateList() {
		return this.dateStringList;
	}
	
	
}

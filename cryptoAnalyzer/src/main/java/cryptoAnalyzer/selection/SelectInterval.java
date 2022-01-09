package cryptoAnalyzer.selection;

import cryptoAnalyzer.analysis.*;

/**
 * This class represents selecting the interval frequency of data fecthing 
 * inherit SelectionObserver class, use Observer design pattern
 * @author Ruyi JIn
 * @version 1.0
 * @since 2021-12-03
 *
 */
public class SelectInterval extends SelectionObserver {
	private String interval;
	
	/**
	 * this is the constructor for the class
	 * @param subject update the interval frequency of data fetching
	 * @param selectionList stores the interval frequency of data fetching
	 */
	public SelectInterval(String subject, SelectionList selectionList) {
		this.selectionList = selectionList;
		for(SelectionObserver curObserver : selectionList.getSelectionObservers()) {
			if(curObserver.getClass() == SelectInterval.class) {
				selectionList.remove(curObserver);
				System.out.println("duplicate");
				break;		
			}
		}
		this.selectionList.attach(this);
		update(subject);
	}
	
	/**
	 * update the interval frequency of data fetching
	 */
	public void update(String subject) {
		this.interval = subject;
	}
	
	/**
	 * getter method: get the interval how often the data should be fetched
	 * @return the interval frequency of data fetching
	 */
	public String getInterval() {
		return interval;
	}
}
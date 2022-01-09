package cryptoAnalyzer.selection;

import cryptoAnalyzer.analysis.*;

/**
 * This class represents that user selects the analysis type to be performed
 * @author Ruyi Jin
 * @version 1.0
 * @since 2021-12-03
 *
 */
public class SelectAnalysisType extends SelectionObserver {
	private String algorithm;
	
	/**
	 * this is the constructor for the class
	 * @param subject update the analysis type to be performed
	 * @param selectionList stores a list of analysis type
	 */
	public SelectAnalysisType(String subject, SelectionList selectionList) {
		this.selectionList = selectionList;
		for(SelectionObserver curObserver : selectionList.getSelectionObservers()) {
			if(curObserver.getClass() == SelectAnalysisType.class) {
				selectionList.remove(curObserver);
				System.out.println("duplicate");
				break;
						
			}
		}
		this.selectionList.attach(this);
		update(subject);
	}
	
	/**
	 * update the type of analysis
	 * Algorithm contain 8 different types of available analysis:
	 * Showing Price, Market Capitalization, Volume, Coins in Circulation,
	 * and the changes in interval frequency of the analysis types above
	 */
	public void update(String subject) {
		this.algorithm = subject;
	}
	
	
	/**
	 * getter method: get the algorithm which can perform the analysis by selected type
	 * @return the algorithm for the 8 analysis types above
	 */
	public String getAlgorithm(){
		return this.algorithm;
	}
}

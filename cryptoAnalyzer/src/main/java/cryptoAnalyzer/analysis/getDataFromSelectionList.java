package cryptoAnalyzer.analysis;

import java.util.List;
import cryptoAnalyzer.selection.*;
import cryptoAnalyzer.selection.SelectionList;

public class getDataFromSelectionList {
	/**
	 * The getDataFromSelectionList program is used to obtain data from the 
	 * selection list
	 * @author Yanwen
	 * @since 2021-11-29
	 */

	private SelectionList selectionList;
	private List<SelectionObserver> selectionObserver;
	public getDataFromSelectionList(SelectionList selectionList) {
		this.selectionList = selectionList;
		this.selectionObserver = selectionList.getSelectionObservers();
	}
	
	public String getAnalysis() {
		/**
		 * This method is used to obtain analysis type from the 
		 * selection list
		 * @param nothing
		 * @return String result obtained analysis type
		 */
		String result = null;
		for(SelectionObserver curObserver : selectionObserver) {
			if (curObserver instanceof SelectAnalysisType) {
				result = ((SelectAnalysisType) curObserver).getAlgorithm();
				break;
			}
			
		}
		return result;
	}
	
	public String getStartDate() {
		/**
		 * This method is used to obtain start date from the 
		 * selection list
		 * @param nothing
		 * @return String result obtained start date
		 */
		String result = "";
		for(SelectionObserver curObserver : selectionObserver) {
			if (curObserver instanceof SelectStartDate) {
				result = ((SelectStartDate) curObserver).getDate();
				break;
			}
		}
		return result;
	}
	
	public String[] getDateList() {
		/**
		 * This method is used to obtain cryptocurrency data from the 
		 * selection list
		 * @param nothing
		 * @return String array result obtained a list of data
		 */
		String[] result = {};
		for(SelectionObserver curObserver : selectionObserver) {
			if (curObserver instanceof SelectStartDate) {
				result = ((SelectStartDate) curObserver).getDateList();
				break;
			}
		}
		return result;
	}
	
	public String[] getCrypto() {
		/**
		 * This method is used to obtain the type of cryptocurrency that used for analysis
		 * @param nothing
		 * @return String array result obtained a list of data
		 */
		String[] result = {};
		for(SelectionObserver curObserver : selectionObserver) {
			if (curObserver instanceof SelectCryptocurrency) {
				result = ((SelectCryptocurrency) curObserver).getCryptoStringList();
				break;
			}
		}
		return result;
	}
	
	public String getInterval() {
		/**
		 * This method is used to obtain time interval from the 
		 * selection list
		 * @param nothing
		 * @return String result 
		 */
		String result = "";
		for(SelectionObserver curObserver : selectionObserver) {
			if (curObserver instanceof SelectInterval) {
				result = ((SelectInterval) curObserver).getInterval();
				break;
			}
		}
		return result;
	}
}

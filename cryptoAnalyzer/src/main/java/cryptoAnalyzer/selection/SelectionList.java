package cryptoAnalyzer.selection;

import java.util.List;
import java.util.ArrayList; 
import cryptoAnalyzer.selection.*;

/**
 * This class represents create a new list as selection list to store all result we get from four select classes
 * @author Ruyi Jin
 * @version 1.0
 * @since 2021-12-03
 *
 */
public class SelectionList {
	
	private List<SelectionObserver> selectionList;
	

	public SelectionList() {
		selectionList = new ArrayList<SelectionObserver>();
	}
	
	/**
	 * This method connect to observer class
	 * @param observer using observer design pattern and inherit viewer from observer class
	 */
	public void attach(SelectionObserver observer) {
		if (selectionList.contains(observer)){
			selectionList.remove(observer);
		}
		selectionList.add(observer);
		
	}
	
	/**
	 * This method get selection list from selection observer class
	 * @return selection List attached
	 */
	public List<SelectionObserver> getSelectionObservers(){
		return selectionList;
	}
	
	/**
	 * This method remove any unused observer in selection list
	 * @param observer using observer design pattern and inherit viewer from observer class
	 */
	public void remove(SelectionObserver observer) {
		selectionList.remove(observer);
	}
	
	
}

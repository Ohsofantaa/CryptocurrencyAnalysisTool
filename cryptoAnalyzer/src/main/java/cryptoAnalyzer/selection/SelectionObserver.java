package cryptoAnalyzer.selection;

/**
 * This class represents observer in observer design pattern
 * @author Ruyi Jin
 * @version 1.0
 * @since 2021-12-03
 *
 */
public abstract class SelectionObserver {
	
	protected SelectionList selectionList;
	
	public abstract void update(String subject);
	
	/**
	 * This method get selection list
	 * @return selection list call from class selection list
	 */
	public SelectionList getSelectionList() {
		return this.selectionList;
	}
}

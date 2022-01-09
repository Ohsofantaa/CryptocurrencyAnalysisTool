package cryptoAnalyzer.gui;


import javax.swing.JOptionPane;
/**
 * The class is working on prompt an error window with a given message
 * @author Xiaoke
 *
 */
public class ExceptionDialog {

	/**
	 * The constructor to init the class
	 */
	public ExceptionDialog() {}

	/**
	 * To prompt the error window with the specific message
	 * @param message the specific message
	 */
	public void presentError(String message) {
		JOptionPane.showConfirmDialog(null,message,"INVALID SELECTION",JOptionPane.CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);	
	}
	
	
}

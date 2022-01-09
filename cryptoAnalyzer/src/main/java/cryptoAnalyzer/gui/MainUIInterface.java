package cryptoAnalyzer.gui;


import java.awt.event.ActionEvent;

import javax.swing.JComponent;

/**
 * A collection of methods used in MainUI
 * @author xiaoke
 *
 */

public interface MainUIInterface{
	public static final long serialVersionUID = 1L;
//	public MainUIInterface getInstance();
	public void updateStats(JComponent component);
	public void actionPerformed(ActionEvent e);
}

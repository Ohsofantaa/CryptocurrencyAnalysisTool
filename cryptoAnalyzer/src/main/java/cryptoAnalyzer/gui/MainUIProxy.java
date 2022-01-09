package cryptoAnalyzer.gui;

import cryptoAnalyzer.gui.RealMainUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;


/**
 * The Class represent a proxy for the mainUi, all the methods are revoked form RealMainUI object
 * @author Xiaoke
 *
 */
public class MainUIProxy extends JFrame implements MainUIInterface, ActionListener{

	private static final long serialVersionUID = 1L;

	private static RealMainUI instance;
	private JPanel stats, chartPanel, tablePanel;
	
	// Should be a reference to a separate object in actual implementation
	private List<String> selectedList;
	
	private JTextArea selectedCryptoList;
	private JComboBox<String> cryptoList;


	/**
	 * The constructor to init the class
	 */
	MainUIProxy() {
		if (instance == null)
			instance = RealMainUI.getInstance();
	}
	
	/**
	 * the method update the view component to attribute
	 */
	public void updateStats(JComponent component) {
		instance.updateStats(component);
	}

	/**
	 * The method listen the action from user
	 */
	public void actionPerformed(ActionEvent e) {
		instance.actionPerformed(e);
	}
	
	/**
	 * The method acts validation server to validate the user credentials
	 * @param ae the user action
	 * @param userName_text the user name
	 * @param password_text the user password
	 * @param message the message box
	 * @param credentials the user credential server list
	 * @return return true if the credentials are correct, elseother wise
	 */
	public boolean loginValidate(ActionEvent ae, String userName_text, String password_text, JLabel message, Map<String, String> credentials){
	      String userName = userName_text;
	      String password = password_text;
	      
          //Read JSON file
	      // init invalidate status
	      
          int flag = 0;
          
          for (Entry<String, String> entry : credentials.entrySet()) {
        	    String key = entry.getKey();
        	    String value = entry.getValue();
        	    if(key.equals(userName) && value.equals(password)) {
        	    	flag = 1;
        	    	break;
        	    } 	 
          }
          
          if (flag == 1) {  
        	  message.setText(" Validation: Valid user.. ");
        	  return true;
        	 
          }
          else {
        	  message.setText(" Validation: Invalid user.. ");
        	  return false;
          }
	          
	  

	}
	
	public static RealMainUI getInstance() {
		return instance;
	}

}

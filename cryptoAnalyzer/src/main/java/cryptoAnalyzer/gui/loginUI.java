package cryptoAnalyzer.gui;


/**
 * The loginUI class display the UI of login page and validatrs credentials
 * of user inputs
 * @author Xiaoke
 * @since 2021-11-30
 */
import cryptoAnalyzer.gui.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

/**
 * The login UI that represent a login window and it using validation server
 * @author Xiaoke
 *
 */
public class loginUI extends JFrame implements ActionListener {
	JPanel panel;
	JLabel user_label, password_label, message;
	JTextField userName_text;
	JPasswordField password_text;
	JButton submit, cancel;
	MainUIProxy mainUIProxy;
	private Map<String, String> credentials;

	// login 2.1
	/**
	 * The constructor to init the class
	 */
	public loginUI() {

		System.out.println("Login Window is init...\n");
		CredentialsList credentialsList = new CredentialsList();
		credentials = credentialsList.getCredentials();
		// Username Label
		mainUIProxy = new MainUIProxy();
		user_label = new JLabel();
		user_label.setText("User Name :");
		user_label.setHorizontalAlignment(JLabel.CENTER);
		userName_text = new JTextField();
		// Password Label
		password_label = new JLabel();
		password_label.setText("Password :");
		password_label.setHorizontalAlignment(JLabel.CENTER);
		password_text = new JPasswordField();
		// Submit
		submit = new JButton("SUBMIT");
		panel = new JPanel(new GridLayout(3, 1));
		panel.add(user_label);
		panel.add(userName_text);
		panel.add(password_label);
		panel.add(password_text);
		message = new JLabel();
		panel.add(message);
		panel.add(submit);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Adding the listeners to components..
		submit.addActionListener(this);
		add(panel, BorderLayout.CENTER);
		setTitle("Please Login Here !");
		setSize(450, 350);
		setVisible(true);
	}


	public void actionPerformed(ActionEvent ae) {
		/**
		 * The method obtains user credentials and validate the credentials of the user
		 * input. If the input is valid, direct the to the mainpage terminate the
		 * program otherwise
		 * 
		 * @param subject type of analysis
		 * @return nothing
		 */
		String userName = userName_text.getText();
		String password = password_text.getText();

		// validate the uesr username and password
		boolean flag = mainUIProxy.loginValidate(ae, userName, password, message, credentials);
		if (flag == true) {

			// get into main UI
			System.out.println("\nValidation Passed\n");
			JFrame frame = mainUIProxy.getInstance();
			frame.setSize(900, 600);
			frame.pack();
			frame.setVisible(true);
		} else {
			System.out.println("\nValidation Failed\n");
		}

	}

}

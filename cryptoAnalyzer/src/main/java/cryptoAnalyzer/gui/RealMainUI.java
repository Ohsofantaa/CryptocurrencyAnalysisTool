package cryptoAnalyzer.gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import cryptoAnalyzer.selection.*;
import cryptoAnalyzer.analysis.*;
import cryptoAnalyzer.render.ViewCreator;



public class RealMainUI extends JFrame implements ActionListener{
	/**
	 * The MainUIProxy program keeps a reference to the RealMainUI object, 
	 * controls access to RealMainUI, and provides and interface that is 
	 * similar to ReaMainUI acting as a substitute
	 * @author Yanwen
	 * @since 2021-11-29
	 */
	private static final long serialVersionUID = 1L;

	private static RealMainUI instance;
	private JPanel stats, chartPanel, tablePanel;
	
	// Should be a reference to a separate object in actual implementation
	private List<String> selectedList;
	
	private JTextArea selectedCryptoList;
	private JComboBox<String> cryptoList;

	private SelectionList selectionList;
	private SelectAnalysisType selectAnalysisType;
	private SelectInterval selectInterval;
	private SelectStartDate selectStartDate;
	private SelectCryptocurrency selectCryptocurrency;
	private List<SelectionObserver> selectionObservers;
	private JComponent bar, line, table, scatter;
	private ExceptionDialog exceptionDialog;
	private boolean startDateFlag; // make sure a valid startdate is present


	/**
	 * Singleton design pattern
	 * @return the static instance for use
	 */
	public static RealMainUI getInstance() {
		if (instance == null)
			instance = new RealMainUI();

		return instance;
	}

	/**
	 * The construct to init the class
	 */
	private RealMainUI() {
		
		// Set window title
		super("Crypto Analysis Tool");
		selectionList = new SelectionList();
		selectCryptocurrency = new SelectCryptocurrency(selectionList);
		selectStartDate = new SelectStartDate("1-12-2021", selectionList);
		selectAnalysisType = new SelectAnalysisType("Price", selectionList);
		selectInterval = new SelectInterval("Yearly", selectionList);
		startDateFlag = false;
		exceptionDialog = new ExceptionDialog();
		
		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a cryptocurrency: ");

		String[] cryptoNames = CryptocurrencyList.shownList();
//		String[] cryptoNames = AvailableCryptoList.getInstance().getAvailableCryptos();

		cryptoList = new JComboBox<String>(cryptoNames);
		
		selectedList = new ArrayList<String>();
		
		JButton addCrypto = new JButton("+");
		addCrypto.setActionCommand("add");
		addCrypto.addActionListener(this);
		
		JButton removeCrypto = new JButton("-");
		removeCrypto.setActionCommand("remove");
		removeCrypto.addActionListener(this);
		
		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(cryptoList);
		north.add(addCrypto);
		north.add(removeCrypto);
		

		// Set bottom bar
		JLabel from = new JLabel("From");
		UtilDateModel dateModel = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
		@SuppressWarnings("serial")
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new AbstractFormatter() {
			private String datePatern = "dd/MM/yyyy";

		    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePatern);

		    @Override
		    public Object stringToValue(String text) throws ParseException {
		    	/**
				 * This method is used to obtain the string and convert it to an object
				 * @param value 
				 * return dateFormatter this return formatted date
				 * exception ParseException on parse error
				 * see ParseException
				 */
		        return dateFormatter.parseObject(text);
		    }

		    @Override
		    public String valueToString(Object value) throws ParseException {
		        if (value != null) {
		            Calendar cal = (Calendar) value;
		            /**
					 * This method is used to obtain the start date and convert it string
					 * @param value 
					 * return dateFormatter this return formatted date
					 * exception ParseException on parse error
					 * see ParseException
					 */
		            
		            // To get the value from the start date

		            String startDate = dateFormatter.format(cal.getTime());
		            selectStartDate.update(startDate);
		            boolean isPastDate = new ValidateStartDate(startDate).validSDate();
		            if(isPastDate) {
		            	startDateFlag = true;
		            }
		            else {
		            	startDateFlag = false;
		            }
		           
		            return dateFormatter.format(cal.getTime());
		        }

		        return "";
		    }
		});
		
		JButton refresh = new JButton("Refresh");
		refresh.setActionCommand("refresh");
		refresh.addActionListener(this);
		
		
		JButton reset = new JButton("Reset");
		reset.setActionCommand("reset");
		reset.addActionListener(this);

		JLabel metricsLabel = new JLabel("        Metrics: ");

		Vector<String> metricsNames = new Vector<String>();
		metricsNames.add("Price");
		metricsNames.add("Market Capitalization");
		metricsNames.add("Transaction Volume");
		metricsNames.add("Coins in Circulation");
		metricsNames.add("Percent Change of Unit Price");
		metricsNames.add("Percent Change of Market Capitalization");
		metricsNames.add("Percent Change of Transaction Volume");
		metricsNames.add("Percent Change of Coins in Circulation");
		final JComboBox<String> metricsList = new JComboBox<String>(metricsNames);
		metricsList.addActionListener(new ActionListener() {
			
			// get metric info
			public void actionPerformed(ActionEvent e) {
				/**
				 * This method is used to obtain metric
				 * @param e
				 * return nothing 
				 */
				String metrics = (String) metricsList.getSelectedItem();
				selectAnalysisType.update(metrics);
				System.out.println(metrics);
			}
		});
		

		JLabel intervalLabel = new JLabel("        Choose interval: ");

		Vector<String> intervalNames = new Vector<String>();
		intervalNames.add("Daily");
		intervalNames.add("Weekly");
		intervalNames.add("Monthly");
		intervalNames.add("Yearly");

		final JComboBox<String> intervalList = new JComboBox<String>(intervalNames);
		intervalList.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				/**
				 * This method is used to obtain intervals
				 * @param e
				 * return nothing 
				 */
				// Interval info getting
				String interval = (String)intervalList.getSelectedItem();
				selectInterval = new SelectInterval(interval, selectionList);
				System.out.println(interval);
			}
		});

		JPanel south = new JPanel();
		south.add(from);
		south.add(datePicker);
		
		south.add(metricsLabel);
		south.add(metricsList);
		
		south.add(intervalLabel);
		south.add(intervalList);
		south.add(refresh);
		south.add(reset);
		
		JLabel selectedCryptoListLabel = new JLabel("List of selected cryptocurrencies: ");
		selectedCryptoList = new JTextArea(16, 10);
		JScrollPane selectedCryptoScrollPane = new JScrollPane(selectedCryptoList);
		JPanel east = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(selectedCryptoListLabel);
		east.add(selectedCryptoScrollPane);

		// Set charts region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(1250,650));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));
		
		west.add(stats);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
	}
	
	public void updateStats(JComponent component) {
		/**
		 * This method updates the user selected elements and validate if the inputs are valid
		 * @param component this is the element selected by an user
		 * @ return nothing
		 */
		stats.add(component);
		stats.revalidate();
	}


	public void actionPerformed(ActionEvent e) {
		/**
		 * This method is used to initiate analysis by pressing Refresh button
		 * after the coin, the type of analysis, the start date and the data fetch interval
		 * are selected
		 * @param e
		 * return nothing 
		 */
		selectionObservers = this.selectionList.getSelectionObservers();
		
		boolean flag = true;
		String command = e.getActionCommand();
		if ("refresh".equals(command)) {	
			stats.removeAll();
			if(checkEmptyList())
				flag = false;
			else 
				flag = true;
			
			if(!startDateFlag) {
				String errorMessage = "Invalid StartDate, cant not render the views!";
				exceptionDialog.presentError(errorMessage);
				System.out.println(errorMessage);
			}
			
			else if(flag && startDateFlag) {
				ViewCreator viewCreator = new ViewCreator(selectionList);		
				viewCreator.createrView();
			}
			
			else {
				String errorMessage = "Missing cryptocurrency selecitons, cant not render the views!";
				exceptionDialog.presentError(errorMessage);
				System.out.println(errorMessage);
			}

		} else if ("add".equals(command)) {
			// lower case items
			String curCryptocurrency = cryptoList.getSelectedItem().toString();
			if (validAllowList(curCryptocurrency)){
					if(!hasInList(curCryptocurrency)) {
					flag = true;
					System.out.println("Valid Cryptocurrency: "+ curCryptocurrency);
					selectedList.add(curCryptocurrency);
					selectCryptocurrency.add(curCryptocurrency.toLowerCase());
					String text = "";
					for (String crypto: selectedList)
						text += crypto + "\n";
					
					selectedCryptoList.setText(text);
					}
					else
					{
						String errorMessage = "Duplicate Selection: " + curCryptocurrency;
						exceptionDialog.presentError(errorMessage);
						System.out.println(errorMessage);
					}
	
			}
			
			else {
				String errorMessage = "Invalid Cryptocurrency: " + curCryptocurrency;
				exceptionDialog.presentError(errorMessage);
				System.out.println(errorMessage);
			}
		} else if ("remove".equals(command)) {
			selectedList.remove(cryptoList.getSelectedItem());
			selectCryptocurrency.remove(cryptoList.getSelectedItem().toString().toLowerCase());
			String text = "";
			for (String crypto: selectedList)
				text += crypto + "\n";
			
			selectedCryptoList.setText(text);
			if(selectedList.size() == 0)
				flag = false;
			
		}
		else if ("reset".equals(command)) {
			System.out.println("\ncleaning view...\n");

			stats.removeAll();
//			statrevalidate();
			stats.repaint();
			AnalysisResult.reset();
			selectStartDate.resetDateList();
//			startDateFlag = false;
			selectedList.clear();
			selectCryptocurrency.clear();
			String text = "";
			for (String crypto: selectedList)
				text += crypto + "\n";
			
			selectedCryptoList.setText(text);
		
		}
	}

	private boolean validAllowList(String cryproCurrency) {
		/**
		 * This method validate if a cryptocurrency to be added is in the list 
		 * of allowable cryptocurrency or not. 
		 * @param cryproCurrency
		 * @return boolean return the result if the cryptocurrency is in the list or not/
		 */
		boolean result = false;
		
		for(String cur : CryptocurrencyList.getAllowList()) {
			if (cur.equals(cryproCurrency)) {
				result = true;
				break;
			}
		}
			
		return result;
	}
	
	private boolean hasInList(String cryptocurrency) {
		/**
		 * This method validate if a cryptocurrency is already in the selected list. 
		 * @param cryproCurrency
		 * @return boolean return the result if the cryptocurrency is already 
		 * in the selected list or not
		 */
		boolean result = false;
		
		for(String cur : selectedList) {
			if(cur.equals(cryptocurrency)) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	public void setComponent(String type, JComponent component) {
		
		if("line".equals(type))
			this.line = component;
	
		else if("bar".equals(type))
			this.bar = component;
			
		else if("table".equals(type))
			this.table = component;
		
		else if("scatter".equals(type))
			this.scatter = component;
		
		this.updateStats(component);
	}
	
	/**
	 * To method to check if the selected cryptocurrency has add into the selection list
	 * @return return true if the cryptolist is not null else otherwise
	 */
	private boolean checkEmptyList() {
		if(selectCryptocurrency.getCryptoStringList().length == 0)
			return true;
		return false;
		
	} 
}

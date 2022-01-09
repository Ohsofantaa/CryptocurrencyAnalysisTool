package cryptoAnalyzer.selection;

import java.awt.List;
import java.util.ArrayList;
import cryptoAnalyzer.selection.ValidateCryptocurrency;

/**
 * inherit SelectObserver class, use Observer design pattern
 * This class represents the function that user 
 * selects cryptocurrencies to fetch and visualize 
 * data for
 * @author Ruyi Jin
 * @version 1.0
 * @since 2021-12-03
 *
 */
public class SelectCryptocurrency extends SelectionObserver { 
	private ArrayList<String> cryptocurrencyStrings;
	private String cryproString;
	/**
	 * this is the constructor for the class
	 * @param selectionList stores the cryptocurrencies that user selected to analyze
	 */
	public SelectCryptocurrency(SelectionList selectionList) {
		this.selectionList = selectionList;
		this.selectionList.attach(this);
		cryptocurrencyStrings = new ArrayList<String>();
	}

	public void clear() {
		this.cryptocurrencyStrings.clear();
	}
	
	
	/**
	 * when a cryptocurrency is selected from the drop down menu,
	 * add successfully if it is in the available kist
	 * @param subject is the cryptocurrency which is chosen from the 
	 * drop down menu and will be check if it is valid to fetch data
	 * if not available, display a warning message and fail to add 
	 * that crypto into the list which contains the cryptos to be fetched
	 */
	public void add(String subject) {	
		if (check(subject))
		cryptocurrencyStrings.add(subject);
		else
			System.out.println("Invalid");
	}
	
	
	
	
	/**
	 * remove cryptos from the list
	 * @param subject the cryptocurrency which is chosen from the 
	 * drop down menu to be removed
	 */
	public void remove(String subject) {
		cryptocurrencyStrings.remove(subject);
		
	}

	/**
	 * override the update method from SelectionObsever class
	 */
	@Override
	public void update(String subject) {
	}
	
	/**
	 * getter method: get the list of cryptocurrencies that are chosen to be analyze
	 * @return the list of cryptocurrencies that are chosen to be fetched
	 * and visualize data for
	 */
	public String[] getCryptoStringList() {
		String[] result = new String[this.cryptocurrencyStrings.size()];
		int count = 0;
		for(String curStr : cryptocurrencyStrings) {
			result[count] = curStr;
			count++;
		}
		return result;
	}
	
	/**
	 * check if the cryptocurrency selected from the drop down menu is
	 * in the available crypto list or not
	 * @param subject the latest cryptocurrency selected from the drop down menu
	 * @return true if the crypto is in the available list, 
	 * return false otherwise.
	 */
	public boolean check(String subject) {
		// TODO Auto-generated method stub	
		ValidateCryptocurrency testCrypto = new ValidateCryptocurrency();
		boolean isAvailableCrypto = testCrypto.validCrypto(subject);
		return isAvailableCrypto;
	}
	
}

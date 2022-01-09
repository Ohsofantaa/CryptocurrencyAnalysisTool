package cryptoAnalyzer.selection;

import java.util.Arrays;
import java.util.List;
import cryptoAnalyzer.gui.CryptocurrencyList;

/**
 * This class represents to check whether the selected cryptocurrency 
 * is in the available crypto list or not.
 * @author Ruyi Jin
 * @version 1.0
 * @since 2021-12-03
 *
 */
public class ValidateCryptocurrency {
	
	/**
	 * This method is to check whether the cryptocurrency which user selected 
	 * is valid (in the available crypto list) or not.
	 * @param cryptocurrency which the user chooses to analyze the data
	 * @return true if the chosen cryptocurrency is in the available list, 
	 * return false when it is invalid.
	 */
	public boolean validCrypto(String cryptocurrency) {
		boolean result = false;		
		for(String cur : CryptocurrencyList.getAllowList()) {
			if (cur.toLowerCase().equals(cryptocurrency)) {
				result = true;
				break;
			}
		}			
		return result;
	}
}

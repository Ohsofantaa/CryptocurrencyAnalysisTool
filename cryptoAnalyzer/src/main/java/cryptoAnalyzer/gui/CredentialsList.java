package cryptoAnalyzer.gui;

import java.util.HashMap;
import java.util.Map;
/**
 * The CredentialsList contains the userName and userPassword 
 * @author Xiaoke
 * @since 2021-11-30
 */
public class CredentialsList {
	
	private HashMap<String, String> credentials;
	/**
	 * The Constructor to init the class, and set userName and userPassword
	 */
	public CredentialsList() {
		
	 	credentials = new HashMap<String, String>();
	    credentials.put("user1", "123");
	    credentials.put("user2", "1234");
	    credentials.put("user3", "12345");
	    credentials.put("1", "1");
	}
	
	/**
	 * Get method to get the validate credentials list
	 * @return  return a hashMap object
	 */
	public HashMap<String, String> getCredentials(){
		return credentials;
	}
 
}
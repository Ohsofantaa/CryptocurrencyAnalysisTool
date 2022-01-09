package cryptoAnalyzer.analysis;
/**
 * The AlgorithmResult gets and sets value and date of a cryptocurrency 
 * and convert the date and value to string
 * @author Yanwen
 * @since 2021-11-30
 */
public class AlgorithmResult {
	private double value;
	private String date;
	/**
	 * 
	 */
	public AlgorithmResult() {
		
	}
	
	public AlgorithmResult(String date, Double value) {
		/**
		 * This method is used to set date and value of cryptocurrency on that date
		 * @param date, value
		 * @return nothing
		 */
		setDate(date);
		setValue(value);
	}
	
	
	
	public String getValue() {
		/**
		 * This method is used to get the value of cryptocurrency on that date
		 * @param nothing
		 * @return Double value
		 */
		return value+"";
	}
	public void setValue(double value) {
		this.value = value;
		/**
		 * This method is used to set the value of cryptocurrency on that date
		 * @param nothing
		 * @return Double value
		 */
	}
	public String getDate() {
		/**
		 * This method is used to get the date of cryptocurrency
		 * @param nothing
		 * @return String date
		 */
		return date;
	}
	public void setDate(String date) {
		/**
		 * This method is used to set the date of cryptocurrency
		 * @param nothing
		 * @return String date
		 */
		this.date = date;
	}
	
	public String toString() {
		/**
		 * This method is convert date and value to string
		 * @param nothing
		 * @return String resultString
		 */
		String resultString = " [algoRes: " + this.date + " - " + this.value + " ]";
		return resultString;
	}
	
	
}

package cryptoAnalyzer.analysis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

public class PriceAnalysis extends Algorithm {
	/**
	 * This PriceAnalysis class perform analysis 
	 * algorithm in price
	 * @author Yanwen
	 * @since 2021-11-28
	 */
	public PriceAnalysis() {}
	/**
	 * This PriceAnalysis gets cryptocurrency information 
	 * from Gecko's API and performs analysis on price
	 * @param datalist the map contains cryptocurrency and its datalist
	 * @return return map object contains all the results
	 */
	public Map<String, AlgorithmResult[]> getAnalysisResult(Map<String, String[]> datalist) {
		Map<String, AlgorithmResult[]> resultMap = new HashMap<String, AlgorithmResult[]>();
		
		AlgorithmResult[] algorithmResult; 
		AlgorithmResult curAlgorithmResult;
		String cryptocurrency;
	    String[] date;
	    DataReader currentDataReader;
	    int count;

	    // loop dataList
		for (Entry<String, String[]> entry : datalist.entrySet()) {
		    cryptocurrency = entry.getKey();
		    date = entry.getValue();
		    algorithmResult = new AlgorithmResult[date.length];
		    // loop date
		    count = 0;
		    for (String currentDate: date) {
		    	currentDate = currentDate.replace("/", "-");
		    	cryptocurrency = cryptocurrency.toLowerCase();
		    	currentDataReader = new DataReader(cryptocurrency, currentDate);
		    	// current algorithmResult object
		    	curAlgorithmResult = new AlgorithmResult(currentDate, currentDataReader.getPrice());
		    	
		    	algorithmResult[count] = curAlgorithmResult;
		    	System.out.println(cryptocurrency+" - "+curAlgorithmResult.toString());
		    	count++;	    	
		    }
		    
		    resultMap.put(cryptocurrency, algorithmResult);
		}
		return resultMap;
	}

}

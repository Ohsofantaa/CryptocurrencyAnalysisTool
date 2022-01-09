package cryptoAnalyzer.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
/**
 * This MarketCapAnalysis class perform analysis 
 * algorithm on market capitalization
 * @author Yanwen
 * @since 2021-11-28
 */

public class MarketCapAnalysis extends Algorithm {
	
	public MarketCapAnalysis() {}
	/**
	 * This MarketCapAnalysis gets cryptocurrency information 
	 * from Gecko's API and performs market capitalization analysis 
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
		    	currentDataReader = new DataReader(cryptocurrency, currentDate);
		    	// current algorithmResult object
		    	curAlgorithmResult = new AlgorithmResult(currentDate, currentDataReader.getMarketCap());
		    	algorithmResult[count] = curAlgorithmResult;
		    	System.out.println(cryptocurrency+" - "+curAlgorithmResult.toString());
		    	count++;	    	
		    }
		    resultMap.put(cryptocurrency, algorithmResult);
		}
		return resultMap;
	}

}

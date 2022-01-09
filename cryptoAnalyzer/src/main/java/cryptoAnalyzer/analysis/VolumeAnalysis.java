package cryptoAnalyzer.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

public class VolumeAnalysis extends Algorithm {
	/**
	 * This PriceAnalysis class perform analysis 
	 * algorithm on volume
	 * @author Yanwen
	 * @since 2021-11-28
	 */
	public VolumeAnalysis() {}
	
	/**
	 * This VolumeAnalysis gets cryptocurrency information 
	 * from Gecko's API and performs analysis on volume
	 * @return return a resultMap object
	 * @param datalist the map contains cryptocurrency and its date list
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
		    	curAlgorithmResult = new AlgorithmResult(currentDate, currentDataReader.getVolume());
		    	algorithmResult[count] = curAlgorithmResult;
		    	System.out.println(cryptocurrency+" - "+curAlgorithmResult.toString());
		    	count++;	    	
		    }
		    resultMap.put(cryptocurrency, algorithmResult);
		}
		return resultMap;
	}

}

package cryptoAnalyzer.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

public class perChangePriceAnalysis extends Algorithm {
	/**
	 * This perChangePriceAnalysis class perform analysis 
	 * algorithm on percent of change in price
	 * @author Yanwen
	 * @since 2021-11-28
	 */
	public perChangePriceAnalysis() {}
	/**
	 * This perChangeCICAnalysis gets cryptocurrency information 
	 * from Gecko's API and performs analysis on percent of change in price
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
	    double oldPrice;
	    double newPrice = 0;
	    double perChangePrice;
	    int count;
	    // loop dataList

		for (Entry<String, String[]> entry : datalist.entrySet()) {
		    cryptocurrency = entry.getKey();
		    date = entry.getValue();
		    algorithmResult = new AlgorithmResult[date.length-1];
		    // loop date
		    if (date.length == 1) {
		    	System.out.println("There is only one date in the selection, the percentage change is 100%");
		    	String curDate = date[0];
		    	AlgorithmResult curAlgo = new AlgorithmResult(curDate, 1.0);
		    	System.out.println(cryptocurrency+" - "+curAlgo.toString());
		    	algorithmResult = new AlgorithmResult[date.length];
		    	algorithmResult[0] = curAlgo;
		    	resultMap.put(cryptocurrency, algorithmResult);
		    	continue;
		    }
		    count = -1;
		    for (String currentDate: date) {
		    	currentDataReader = new DataReader(cryptocurrency, currentDate);
		    	// current algorithmResult object
		    	if(count == -1) {
		    		oldPrice = newPrice;
			    	newPrice = currentDataReader.getPrice();
		    		count++;
		    		continue;
		    	}
		    	else {
		    		oldPrice = newPrice;
			    	newPrice = currentDataReader.getPrice();
			    	perChangePrice = (newPrice-oldPrice)/oldPrice * 100;
			    	curAlgorithmResult = new AlgorithmResult(currentDate, perChangePrice);
			    	algorithmResult[count] = curAlgorithmResult;
			    	System.out.println(cryptocurrency+" - "+curAlgorithmResult.toString());
			    	count++;	    	
		    	}
		    }
		    resultMap.put(cryptocurrency, algorithmResult);
		}
		return resultMap;
	}

}

package cryptoAnalyzer.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cryptoAnalyzer.selection.SelectionList;

import java.sql.Date;
import java.time.LocalDate;
/**
 * The AnalysisResult gets and sets value and date of a cryptocurrency 
 * and convert the date and value to string
 * @author Yanwen
 * @since 2021-11-30
 */
public class AnalysisResult{
	
	private Algorithm algorithm;
	AnalysisResultCreator analysisResultCreator;

	private Map<String, String[]> dataList = new HashMap<String, String[]>();
	
	private static Map<String, AlgorithmResult[]> analysisResObject;
	
	public AnalysisResult(SelectionList selectionList) {
		/**
		 * The method performs analysis on selected cryptocurrencies
		 * @param selectionList selected list of cryptocurrencies
		 * @return nothing
		 */
		
		System.out.println("View is presenting...");
		getDataFromSelectionList getData = new getDataFromSelectionList(selectionList);
		String algorithmType = getData.getAnalysis();
		String[] id = getData.getCrypto();
	

		String[] dateList = getData.getDateList();
	
		
    	if (analysisResObject == null) {
    		setDataList(id, dateList);
			setAlgorithm(algorithmType);
			setResult();
		}
	}
	
	public static void reset() {
		analysisResObject = null;
	}
		
	private void setAlgorithm(String subject) {
		/**
		 * The method set different types of algorithms
		 * @param subject type of analysis
		 * @return nothing
		 */
		
		if(subject.equals("Price")) {
			analysisResultCreator = new PriceAnalysisCreator();		
		}
		else if(subject.equals("Market Capitalization")) {
			analysisResultCreator = new MarketCapAnalysisCreator();
		}
		else if(subject.equals("Transaction Volume")) {
			analysisResultCreator = new VolumeAnalysisCreator();
		}
		else if(subject.equals("Coins in Circulation")) {
			analysisResultCreator = new CoinInCirculationAnalysisCreator();
		}
		else if(subject.equals("Percent Change of Unit Price")) {
			analysisResultCreator = new perChangePriceAnalysisCreator();
		}
		else if(subject.equals("Percent Change of Market Capitalization")) {
			analysisResultCreator = new perChangeMarketCapAnalysisCreator();
		}
		else if(subject.equals("Percent Change of Transaction Volume")) {
			analysisResultCreator = new perChangeVolumeAnalysisCreator();
		}
		else if(subject.equals("Percent Change of Coins in Circulation")) {
			analysisResultCreator = new perChangeCICAnalysisCreator();
		}
		algorithm = analysisResultCreator.factorymethod();
	}
	
	private void setDataList(String[] id, String[] dateList) {
		/**
		 * The method set a list of dates
		 * @param id, datelist
		 * @return nothing
		 */
		for(String crypto : id) {
			dataList.put(crypto, dateList);
		}	
	}
	
	private void setResult() {
		/**
		 * The method set analysis results
		 * @param nothing
		 * @return nothing
		 */
		analysisResObject =  algorithm.getAnalysisResult(dataList);
	}
	
	public Map<String, AlgorithmResult[]> getResult() {
		return analysisResObject;
	}
	
	public Map<String, String[]> getDataList(){
		return dataList;
	}
	
	public String updateDate(String date) {
		/**
		 * The method updates the date to an empty list
		 * @param date
		 * @return String
		 */
		return "";
	}
}

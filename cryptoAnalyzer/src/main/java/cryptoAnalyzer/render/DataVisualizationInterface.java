package cryptoAnalyzer.render;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Collections;
import org.jfree.chart.ui.Align;

import cryptoAnalyzer.selection.SelectionList;
import cryptoAnalyzer.analysis.*;

/**
 * The class abstract the render view
 * @author Yanwen
 *
 */
public abstract class DataVisualizationInterface {

	protected AnalysisResult analysisResult;
	protected static Map<String, AlgorithmResult[]> resultMap;
	protected static String[] columnNames;
	protected static String[][] data;
	protected String[] dataLine;
	protected String[] DateList;
	
	/**
	 * The constructot to init the class base on the selections
	 * @param selectionList the SelectionList object with contains all the selections
	 */
	public DataVisualizationInterface(SelectionList selectionList) {
	
		analysisResult = new AnalysisResult(selectionList);
		
		resultMap = analysisResult.getResult();
		data = new String[resultMap.size()][getRowSize()];
		setColumnName();
		setData();
		
 	}
	
	/**
	 * Abstract the method use to build the views based on data
	 */
	public abstract void createCharts();
	
	/**
	 * The method to print at end of work
	 */
	public abstract void endPrompt();
	
	/**
	 * The get the table row size base on data
	 * @return return the integer object as result
	 */
	protected int getRowSize() {
		int result = 0;
		for (Entry<String, AlgorithmResult[]> entry : resultMap.entrySet()) {
			result = entry.getValue().length+1;
			break;
		}
		return result;
	}
	
	/**
	 * The setter method to set columns names based on selections
	 */
	protected void setColumnName() {
		int count;
		
		for (Entry<String, AlgorithmResult[]> entry : resultMap.entrySet()) {
			columnNames = new String[entry.getValue().length+1];
			count = 0;
			columnNames[0] = "cryptocurrency";
			count++;
		    AlgorithmResult[] val = entry.getValue();
		    for (AlgorithmResult dataAlgo: val) {    	
		    	columnNames[count] = dataAlgo.getDate();
		    	count++;
		    }
		    break;
		    
		}
		
	}
	
	/**
	 * The setter method set the table value based on selections
	 */
	protected void setData() {
		int count;
		int rows = 0;
		for (Entry<String, AlgorithmResult[]> entry : resultMap.entrySet()) {
			dataLine = new String[entry.getValue().length+1];
			count = 0;
		    String key = entry.getKey();
		    AlgorithmResult[] val = entry.getValue();
		    dataLine[count] = key;
		    count++;
		    for (AlgorithmResult dataAlgo: val) {    	
		    	dataLine[count] = dataAlgo.getValue();
		    	count++;
		    }
		    data[rows] = dataLine;
		    rows++;
		}

	}
	
	
	protected double[] getRange() {
		double[] resultRange = new double[2];
		ArrayList<Double> overall = new ArrayList<Double>();
		AlgorithmResult[] algo;
		for (Entry<String, AlgorithmResult[]> entry : resultMap.entrySet()) {
			algo = entry.getValue();
			
			for(AlgorithmResult value : algo) {
				overall.add(Double.parseDouble(value.getValue()));
			}
		}
		
		Double max = Collections.max(overall);
		Double min = Collections.min(overall);
		resultRange[0] = min;
		resultRange[1] = max;
		return resultRange;
	}
}

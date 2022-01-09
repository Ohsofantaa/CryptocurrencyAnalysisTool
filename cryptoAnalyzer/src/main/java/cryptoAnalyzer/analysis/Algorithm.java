package cryptoAnalyzer.analysis;
/**
 /* This Algorithm program implements an abstract class of Algorithm
 * @author Yanwen
 * @since 2021-11-30
 */
import java.util.ArrayList;

import java.util.Map;

public abstract class Algorithm {
	
	private AlgorithmResult algorithmResult;
	public abstract Map<String, AlgorithmResult[]> getAnalysisResult(Map<String, String[]> datalist);
	
}

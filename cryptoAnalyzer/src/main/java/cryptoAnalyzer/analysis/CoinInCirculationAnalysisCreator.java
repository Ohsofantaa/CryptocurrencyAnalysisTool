package cryptoAnalyzer.analysis;
/**
 * The CoinInCirculationAnalysisCreator is a sub-class of the AnalysisResultCreator
 * class and overrides the factorymethod method in order for factorymethod to 
 * construct and return a CoinInCirculationAnalysis object
 * @author Xiaoke
 * @since 2021-12-3
 */
// Creator
public class CoinInCirculationAnalysisCreator extends AnalysisResultCreator {
@Override
public Algorithm factorymethod() {
	return new CoinInCirculationAnalysis();
	}	
}

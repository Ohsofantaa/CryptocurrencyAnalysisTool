package cryptoAnalyzer.analysis;
/**
 * The AnalysisResultCreator is a sub-class of the AnalysisResultCreator
 * class and overrides the factorymethod method in order for factorymethod to 
 * construct and return a MarketCapAnalysis object
 * @author Xiaoke
 * @since 2021-12-3
 */
public class MarketCapAnalysisCreator extends AnalysisResultCreator{
@Override
public Algorithm factorymethod() {
	return new MarketCapAnalysis();
	}	
}

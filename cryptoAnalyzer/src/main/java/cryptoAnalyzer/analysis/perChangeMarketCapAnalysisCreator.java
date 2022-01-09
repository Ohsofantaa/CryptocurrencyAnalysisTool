package cryptoAnalyzer.analysis;
/**
 * The perChangeMarketCapAnalysisCreator is a sub-class of the AnalysisResultCreator
 * class and overrides the factorymethod method in order for factorymethod to 
 * construct and return a perChangeMarketCapAnalysis object
 * @author Xiaoke
 * @since 2021-12-3
 */
public class perChangeMarketCapAnalysisCreator extends AnalysisResultCreator {
	@Override
	public Algorithm factorymethod() {
		return new perChangeMarketCapAnalysis();
		}	

}

package cryptoAnalyzer.analysis;
/**
 * The perChangePriceAnalysisCreator is a sub-class of the AnalysisResultCreator
 * class and overrides the factorymethod method in order for factorymethod to 
 * construct and return a perChangePriceAnalysis object
 * @author Xiaoke
 * @since 2021-12-3
 */
public class perChangePriceAnalysisCreator extends AnalysisResultCreator {
	@Override
	public Algorithm factorymethod() {
		return new perChangePriceAnalysis();
		}		

}

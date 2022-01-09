package cryptoAnalyzer.analysis;
/**
 * The PriceAnalysisCreator is a sub-class of the AnalysisResultCreator
 * class and overrides the factorymethod method in order for factorymethod to 
 * construct and return a PriceAnalysiss object
 * @author Xiaoke
 * @since 2021-12-3
 */
public class PriceAnalysisCreator extends AnalysisResultCreator{
	@Override
	public Algorithm factorymethod() {
		return new PriceAnalysis();
		}	

}

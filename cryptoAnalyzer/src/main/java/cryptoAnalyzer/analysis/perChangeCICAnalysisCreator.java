package cryptoAnalyzer.analysis;
/**
 * The perChangeCICAnalysisCreator is a sub-class of the AnalysisResultCreator
 * class and overrides the factorymethod method in order for factorymethod to 
 * construct and return a perChangeCICAnalysis object
 * @author Xiaoke
 * @since 2021-12-3
 */
public class perChangeCICAnalysisCreator extends AnalysisResultCreator{
	@Override
	public Algorithm factorymethod() {
		return new perChangeCICAnalysis();
	}	

}

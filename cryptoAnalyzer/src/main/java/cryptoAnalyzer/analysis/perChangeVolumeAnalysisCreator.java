package cryptoAnalyzer.analysis;
/**
 * The perChangeVolumeAnalysisCreator is a sub-class of the AnalysisResultCreator
 * class and overrides the factorymethod method in order for factorymethod to 
 * construct and return a perChangeVolumeAnalysis object
 * @author Xiaoke
 * @since 2021-12-3
 */
public class perChangeVolumeAnalysisCreator extends AnalysisResultCreator{
	@Override
	public Algorithm factorymethod() {
		return new perChangeVolumeAnalysis();
		}	
}

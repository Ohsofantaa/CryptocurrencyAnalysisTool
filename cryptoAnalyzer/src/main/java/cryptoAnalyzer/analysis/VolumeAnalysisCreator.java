package cryptoAnalyzer.analysis;
/**
 * The VolumeAnalysisCreator is a sub-class of the AnalysisResultCreator
 * class and overrides the factorymethod method in order for factorymethod to 
 * construct and return a VolumeAnalysis object
 * @author Xiaoke
 * @since 2021-12-3
 */
public class VolumeAnalysisCreator extends AnalysisResultCreator {
	@Override
	public Algorithm factorymethod() {
		return new VolumeAnalysis();
		}

}

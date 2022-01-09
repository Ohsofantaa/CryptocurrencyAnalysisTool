package cryptoAnalyzer.render;

import java.util.List;
import cryptoAnalyzer.analysis.*;

import cryptoAnalyzer.selection.SelectionList;
import cryptoAnalyzer.selection.SelectionObserver;

public class ViewCreator {
	/**
	 * ThisViewCreator class generates view and concentrate all the view drawer
	 * class
	 * 
	 * @author Yanwen
	 * @since 2021-11-28
	 */
	SelectionList selectionList;
	DataVisualizationInterface tableDrawer;
	DataVisualizationInterface lineDrawer;
	DataVisualizationInterface barDrawer;
	DataVisualizationInterface scatterDrawer;

	DataVisualizationInterface dataVisualizationInterface;
	List<SelectionObserver> selectionObservers;

	public ViewCreator(SelectionList selectionList) {
		/**
		 * this method is used to generate view
		 * 
		 * @param slectionList
		 * @return nothing
		 */
		this.selectionList = selectionList;
		selectionObservers = selectionList.getSelectionObservers();
		tableDrawer = new TableDrawer(selectionList);
		lineDrawer = new LineDrawer(selectionList);
		barDrawer = new BarDrawer(selectionList);
		scatterDrawer = new ScatterDrawer(selectionList);

		getDataFromSelectionList getData = new getDataFromSelectionList(selectionList);
		String[] dateList = getData.getDateList();
		String[] cryptoStrings = getData.getCrypto();
		String intv = getData.getInterval();
		String analysisType = getData.getAnalysis();
		System.out.println("\nHere is the summary of your selection:");
		System.out.println("Analysis Type: " + analysisType);
		System.out.println("Interval Frequence: " + intv);
		System.out.println("StartDate: " + getData.getStartDate());
		System.out.println("\nDates are Selected: ");
		for (String xString : dateList) {
			System.out.println(xString);
		}
		System.out.println("\nCryptocurrencies are Selected: ");
		for (String yString : cryptoStrings) {
			System.out.println(yString);
		}
		System.out.println("\n");
	}

	/** 
	 * The method to present infomation table view 
	 */
	private void tableCreator() {
		tableDrawer.createCharts();
	}

	/** 
	 * The method to present line table view 
	 */
	private void lineCreator() {
		lineDrawer.createCharts();
	}

	/** 
	 * The method to present bar table view 
	 */
	private void barCreator() {
		barDrawer.createCharts();
	}

	/** 
	 * The method to present scatter table view 
	 */
	private void scatterCreator() {
		scatterDrawer.createCharts();
	}

	/**
	 * The concentrate class that revoked all the draw to create charts
	 */
	public void createrView() {

		tableCreator();
		lineCreator();
		scatterCreator();
		barCreator();

	}

}

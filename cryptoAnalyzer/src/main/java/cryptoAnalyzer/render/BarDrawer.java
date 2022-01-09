package cryptoAnalyzer.render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import cryptoAnalyzer.analysis.*;
import cryptoAnalyzer.selection.SelectionList;
import cryptoAnalyzer.gui.MainUIProxy;

public class BarDrawer extends DataVisualizationInterface{
	/**
 * This BarDrawer class draw bar chart based on the available data
 * @author Yanwe
 * @since 2021-11-28
 */
	
	/**
	 * The contructor the init the class
	 * @param selectionList contains all the selections
	 */
	public BarDrawer(SelectionList selectionList) {
		super(selectionList);	
	}
	
	public void createCharts() {
		/**
		 * This BarDrawer draw bar chart based on the available data
		 * @param noting
		 * @return nothing
		 */
		

		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String crypto;
		AlgorithmResult[] algorithmResults;
		Double value;
		String date;
		for (Entry<String, AlgorithmResult[]> entry : resultMap.entrySet()) {
			crypto = entry.getKey();
			algorithmResults = entry.getValue();
			for(AlgorithmResult curAlgo : algorithmResults) {
				value = Double.parseDouble(curAlgo.getValue());
				date = curAlgo.getDate();
				dataset.setValue(value, crypto, date);
			}
		}
		

		
		
		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();
		
		
		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Date");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Price(USD)");
		rangeAxis.setRange(new Range(1.0, 70000.0));
		plot.setRangeAxis(rangeAxis);
	

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart barChart = new JFreeChart("Bar Chart", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUIProxy.getInstance().setComponent("bar",chartPanel);
		endPrompt();

	}

	public void endPrompt() {
		/**
		 * This method print the display message for completion of chart generation 
		 * @param noting
		 * @return nothing
		 */
		System.out.println("Bar Drawer has finished it's job.");
	}

}

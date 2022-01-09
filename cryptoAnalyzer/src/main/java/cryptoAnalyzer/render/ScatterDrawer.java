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

public class ScatterDrawer extends DataVisualizationInterface{
	/**
	 * This scatterDrawer class draw scatter chart based on the available data
	 * @author Yanwe
	 * @since 2021-11-28
	 */
	
	TimeSeries[] timeSeries;
	public ScatterDrawer(SelectionList selectionList) {
		super(selectionList);
		timeSeries = new TimeSeries[super.getRowSize()];
	}
	
	private Day convertDate(String date) {
		/**
		 * This method convert a type of list of string date 
		 * to a list of integer
		 * @param String date
		 * @return nothing 
		 */
		
		String[] dateList = {};
		if(date.contains("-")) {
			dateList = date.split("-");
		}
		else if(date.contains("/")) {
			dateList = date.split("/");
		}
		int[] result = new int[dateList.length];
		
		int count = 0;
		for(String dates : dateList) {
			result[count] = Integer.parseInt(dates);
			count++;
		}
		return new Day(result[0], result[1], result[2]);
	}
	
	
	public void createCharts() {
		/**
		 * This createCharts draw scatter chart based on the available data
		 * @param noting
		 * @since 2021-11-28
		 */
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		String crypto;
		AlgorithmResult[] algorithmResults;
		Double value;
		String date;
		Day curDay;
		int serNum = 0;
		for (Entry<String, AlgorithmResult[]> entry : resultMap.entrySet()) {
			crypto = entry.getKey();
			algorithmResults = entry.getValue();
			// loop series		
			String tempString = crypto;

			timeSeries[serNum]= new TimeSeries(tempString);
			dataset.addSeries(timeSeries[serNum]);
			for(AlgorithmResult curAlgo : algorithmResults) {
				value = Double.parseDouble(curAlgo.getValue());
				date = curAlgo.getDate();
				curDay = convertDate(date);
				timeSeries[serNum].add(curDay, value);
			}
			
			serNum++;
		}


		XYPlot plot = new XYPlot();
		XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);

		plot.setDataset(0, dataset);
		plot.setRenderer(0, itemrenderer1);
		DateAxis domainAxis = new DateAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new LogAxis("Price(USD)"));

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
		
		JFreeChart scatterChart = new JFreeChart("Scatter Chart",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUIProxy.getInstance().setComponent("scatter",chartPanel);
		endPrompt();
	}

	public void endPrompt() {
		/**
		 * This method print the display message for completion of chart generation 
		 * @param noting
		 * @return nothing
		 */
		System.out.println("Scatter Drawer has finished it's job.");
	}

}

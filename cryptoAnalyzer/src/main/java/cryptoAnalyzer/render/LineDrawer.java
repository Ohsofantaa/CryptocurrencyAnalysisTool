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

public class LineDrawer extends DataVisualizationInterface{
	/**
	 * This LineDrawer class draw line chart based on the available data
	 * @author Yanwe
	 * @since 2021-11-28
	 */
		
	TimeSeries[] timeSeries;
	public LineDrawer(SelectionList selectionList) {
		super(selectionList);
		timeSeries = new TimeSeries[super.getRowSize()];
	}
	
	private Day convertDate(String date) {
		/**
		 * This method convert a type of list of string date 
		 * to a list of integer
		 * @param String date
		 * @since 2021-11-28
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
		 * This BarDrawer draw bar chart based on the available data
		 * @param noting
		 * @return nothing
		 */
		String crypto;
		AlgorithmResult[] alogList;
		AlgorithmResult curAlgorithmResult;
		int count;
		int serNum;
		Day curDay;
		// loop resultMap
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		for (Entry<String, AlgorithmResult[]> entry : resultMap.entrySet()) {
			crypto = entry.getKey();
			alogList = entry.getValue();
			count = 0;	
			serNum = 0;
			
			// loop series		
			String tempString = crypto;
//			System.out.println(tempString);
			timeSeries[serNum]= new TimeSeries(tempString);
			dataset.addSeries(timeSeries[serNum]);
			while(count < alogList.length) {			
				curAlgorithmResult = alogList[count];
				curDay = convertDate(curAlgorithmResult.getDate());
				timeSeries[serNum].add(curDay, Double.parseDouble(curAlgorithmResult.getValue()));
//				System.out.println(count +" : "+ curDay.toString() + " - " +curAlgorithmResult.getValue() + "");
				count++;				
			}
			serNum++;
		}

		XYPlot plot = new XYPlot();
		XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
		
		plot.setDataset(0, dataset);
		plot.setRenderer(0, splinerenderer1);
		DateAxis domainAxis = new DateAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new LogAxis("Price(USD)"));

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
		//plot.mapDatasetToRangeAxis(2, 2);// 3rd dataset to 3rd y-axis
		
		JFreeChart chart = new JFreeChart("Line Chart", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		endPrompt();		
		MainUIProxy.getInstance().setComponent("line",chartPanel);
	}

	public void endPrompt() {
		/**
		 * This method print the display message for completion of chart generation 
		 * @param noting
		 * @return nothing
		 */
		System.out.println("Line Drawer has finished it's job.");
	}

}

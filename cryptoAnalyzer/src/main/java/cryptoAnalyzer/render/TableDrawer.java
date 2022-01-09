package cryptoAnalyzer.render;

import cryptoAnalyzer.gui.*;
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

/**
 * This TableDrawer class draws table based on the available data
 * @author Yanwe
 * @since 2021-11-28
 */
public class TableDrawer extends DataVisualizationInterface{

	public TableDrawer(SelectionList selectionList) {
		super(selectionList);
	}
	

	public void endPrompt() {
		System.out.println("Table Drawer has finished it's job.");
	}
	
	public void createCharts() {
		/**
		 * This BarDrawer draw table based on the available data
		 * @param noting
		 * @since 2021-11-28
		 */
//		for(String xString : columnNames) {
//			System.out.println(xString);
//		}
//		
//		for(String[] xString : data) {
//			for(String yString : xString) {
//				System.out.println(yString);
//			}
//		}
		
		JTable table = new JTable(data, columnNames);
		//table.setPreferredSize(new Dimension(600, 300));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Summary Table",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
	
		
		scrollPane.setPreferredSize(new Dimension(600, 300));
		table.setFillsViewportHeight(true);;
		endPrompt();
		MainUIProxy.getInstance().setComponent("table",scrollPane);
	}

}

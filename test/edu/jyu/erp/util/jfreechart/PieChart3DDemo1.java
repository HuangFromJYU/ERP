package edu.jyu.erp.util.jfreechart;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

public class PieChart3DDemo1 extends ApplicationFrame {
	public PieChart3DDemo1(String paramString) {
		super(paramString);
		JPanel localJPanel = createDemoPanel();
		localJPanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(localJPanel);
	}

	private static PieDataset createDataset() {
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		localDefaultPieDataset
				.setValue("Java", new Double(43.200000000000003D));
		localDefaultPieDataset.setValue("Visual Basic", new Double(10.0D));
		localDefaultPieDataset.setValue("C/C++", new Double(17.5D));
		localDefaultPieDataset.setValue("PHP", new Double(32.5D));
		localDefaultPieDataset.setValue("Perl", null);
		return localDefaultPieDataset;
	}

	private static JFreeChart createChart(PieDataset paramPieDataset) {
		JFreeChart localJFreeChart = ChartFactory.createPieChart3D(
				"Pie Chart 3D Demo 1", paramPieDataset, true, true, false);
		PiePlot3D localPiePlot3D = (PiePlot3D) localJFreeChart.getPlot();
		localPiePlot3D.setDarkerSides(true);
		localPiePlot3D.setStartAngle(290.0D);
		localPiePlot3D.setDirection(Rotation.CLOCKWISE);
		localPiePlot3D.setForegroundAlpha(0.5F);
		localPiePlot3D.setNoDataMessage("No data to display");
		return localJFreeChart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart localJFreeChart = createChart(createDataset());
		return new ChartPanel(localJFreeChart);
	}

	public static void main(String[] paramArrayOfString) {
		PieChart3DDemo1 localPieChart3DDemo1 = new PieChart3DDemo1(
				"JFreeChart: PieChart3DDemo1.java");
		localPieChart3DDemo1.pack();
		RefineryUtilities.centerFrameOnScreen(localPieChart3DDemo1);
		localPieChart3DDemo1.setVisible(true);
	}
}
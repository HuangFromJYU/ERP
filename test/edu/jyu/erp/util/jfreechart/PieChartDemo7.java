package edu.jyu.erp.util.jfreechart;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieChartDemo7 extends ApplicationFrame {
	public PieChartDemo7(String paramString) {
		super(paramString);
		setContentPane(createDemoPanel());
	}

	private static PieDataset createDataset(int paramInt) {
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		for (int i = 0; i < paramInt; ++i) {
			double d = 100.0D * Math.random();
			localDefaultPieDataset.setValue("Section " + i, d);
		}
		return localDefaultPieDataset;
	}

	public static JPanel createDemoPanel() {
		PieDataset localPieDataset = createDataset(14);
		JFreeChart localJFreeChart = ChartFactory.createPieChart(
				"Pie Chart Demo 7", localPieDataset, false, true, false);
		PiePlot localPiePlot = (PiePlot) localJFreeChart.getPlot();
		localPiePlot.setCircular(true);
		localPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0} = {2}", NumberFormat.getNumberInstance(), NumberFormat
						.getPercentInstance()));
		localPiePlot.setNoDataMessage("No data available");
		ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
		localChartPanel.setPreferredSize(new Dimension(1000, 740));
		Rotator localRotator = new Rotator(localPiePlot);
		localRotator.start();
		return localChartPanel;
	}

	public static void main(String[] paramArrayOfString) {
		PieChartDemo7 localPieChartDemo7 = new PieChartDemo7(
				"JFreeChart: PieChartDemo7.java");
		localPieChartDemo7.pack();
		RefineryUtilities.centerFrameOnScreen(localPieChartDemo7);
		localPieChartDemo7.setVisible(true);
	}

	static class Rotator extends Timer implements ActionListener {
		private PiePlot plot;
		private int angle = 270;

		Rotator(PiePlot paramPiePlot) {
			super(50, null);
			this.plot = paramPiePlot;
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent paramActionEvent) {
			this.plot.setStartAngle(this.angle);
			this.angle += 90;
			if (this.angle != 360)
				return;
			this.angle = 0;
		}
	}
}
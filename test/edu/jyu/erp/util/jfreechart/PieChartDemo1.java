package edu.jyu.erp.util.jfreechart;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartDemo1{
	public static void main(String[] paramArrayOfString) throws IOException {
		JFreeChart jfc = createChart();
		//创建JFreeChart目标是为了得到图形报表，最终格式应该是一张图片才能显示到网页上
		BufferedImage bi = jfc.createBufferedImage(500, 370);
		//Java如何创建图片
		ImageIO.write(bi, "PNG", new File("1.PNG"));
	}
	
	private static JFreeChart createChart() {
		//数据初始化
		DefaultPieDataset dataSet = new DefaultPieDataset();
		dataSet.setValue("臭豆腐", new Double(15D));
		dataSet.setValue("辣条", new Double(24D));
		dataSet.setValue("键盘", new Double(34D));
		//由数据生成图形
		JFreeChart localJFreeChart = ChartFactory.createPieChart("采购报表", dataSet, true, false, false);
		
		PiePlot plot = (PiePlot) localJFreeChart.getPlot();
		plot.setLabelFont(new Font("SansSerif", 0, 12));
		plot.setNoDataMessage("无查询结果报表信息！");
		plot.setCircular(true);
		plot.setLabelGap(0.02D);
		return localJFreeChart;
	}

}
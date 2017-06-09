package edu.jyu.erp.invoice.bill.business.ebo;

import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import edu.jyu.erp.invoice.bill.business.ebi.BillEbi;
import edu.jyu.erp.invoice.bill.dao.dao.BillDao;
import edu.jyu.erp.invoice.bill.vo.BillQueryModel;
import edu.jyu.erp.invoice.goods.vo.GoodsModel;
import edu.jyu.erp.invoice.orderdetail.vo.OrderDetailModel;
import edu.jyu.erp.util.jxl.JxlUtil;

public class BillEbo implements BillEbi{
	static {
		StandardChartTheme theme = new StandardChartTheme("unicode") {
			public void apply(JFreeChart chart) {
				chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				super.apply(chart);
			}
		};
		theme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 20));
		theme.setLargeFont(new Font("宋体", Font.PLAIN, 14));
		theme.setRegularFont(new Font("宋体", Font.PLAIN, 12));
		theme.setSmallFont(new Font("宋体", Font.PLAIN, 10));
		ChartFactory.setChartTheme(theme);
	}
	
	private BillDao billDao;
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	public List<Object[]> getBuyBill(BillQueryModel bqm) {
		return billDao.getBuyBill(bqm);
	}

	public List<OrderDetailModel> getBuyBillDetail(BillQueryModel bqm) {
		return billDao.getBuyBillDetail(bqm);
	}

	public void writeJFreeChartToOs(OutputStream os,BillQueryModel bqm) throws Exception{
		List<Object[]> temp = billDao.getBuyBill(bqm);
		
		//jfc->os
		DefaultPieDataset dataSet = new DefaultPieDataset();
		for(Object[] objs:temp){
			GoodsModel gm = (GoodsModel)objs[0];
			Long sum = (Long) objs[1];
			dataSet.setValue(gm.getName(), new Double(sum));
		}
		//由数据生成图形
		JFreeChart jfc = ChartFactory.createPieChart("采购报表", dataSet, true, false, false);
		
		PiePlot plot = (PiePlot) jfc.getPlot();
		plot.setLabelFont(new Font("SansSerif", 0, 12));
		plot.setNoDataMessage("无查询结果报表信息！");
		plot.setCircular(true);
		plot.setLabelGap(0.02D);
		
		//jfc对象已经存在
		BufferedImage bi = jfc.createBufferedImage(500, 370);
		ImageIO.write(bi, "PNG", os);
	}

	public InputStream getWriteExcelStream(BillQueryModel bqm) throws Exception {
		List<Object[]> temp = billDao.getBuyBill(bqm);
		
		//将excel文件写入流中
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook w = Workbook.createWorkbook(bos); 
		
		WritableSheet s = w.createSheet("总括", 0); 

		//设置单元格宽度
		JxlUtil.sColumnSize(s, 1, 8);
		JxlUtil.sColumnSize(s, 2, 8);
		JxlUtil.sColumnSize(s, 3, 25);
		JxlUtil.sColumnSize(s, 4, 25);
		JxlUtil.sColumnSize(s, 5, 25);
		//设置单元格高度
		JxlUtil.sRowSize(s, 1, 15);
		JxlUtil.sRowSize(s, 2, 37);
		JxlUtil.sRowSize(s, 3, 6);
		JxlUtil.sRowSize(s, 4, 23);
		
		//设置合并单元格
		JxlUtil.sMerge(s, 2,2,2,4);
		JxlUtil.sMerge(s, 3,2,3,5);
		
		Label lab22 = JxlUtil.cLabel(2, 2, "进货统计报表");
		JxlUtil.sLabelStyle(lab22, "黑体", 24, Colour.BLACK, Colour.LIGHT_BLUE, 1, "2020");
		JxlUtil.aLabelToSheet(lab22, s);
		
		Label lab25 = JxlUtil.cLabel(2, 5, "不限");
		JxlUtil.sLabelStyle(lab25, "黑体", 12, Colour.BLACK, Colour.LIGHT_BLUE, 1, "2002");
		JxlUtil.aLabelToSheet(lab25, s);
		
		Label lab32 = JxlUtil.cLabel(3, 2, "");
		JxlUtil.sLabelStyle(lab32, "黑体", 1, Colour.BLACK, Colour.GRAY_25, 1, "2022");
		JxlUtil.aLabelToSheet(lab32, s);
		
		Label lab42 = JxlUtil.cLabel(4, 2, "编号");
		JxlUtil.sLabelStyle(lab42, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
		JxlUtil.aLabelToSheet(lab42, s);
		
		Label lab43 = JxlUtil.cLabel(4, 3, "厂商");
		JxlUtil.sLabelStyle(lab43, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
		JxlUtil.aLabelToSheet(lab43, s);
		
		Label lab44 = JxlUtil.cLabel(4, 4, "商品名");
		JxlUtil.sLabelStyle(lab44, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
		JxlUtil.aLabelToSheet(lab44, s);
		
		Label lab45 = JxlUtil.cLabel(4, 5, "数量");
		JxlUtil.sLabelStyle(lab45, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2222");
		JxlUtil.aLabelToSheet(lab45, s);
		
		int row  = 5;
		int i = 0;
		Long sumAll = 0L;
		for(Object[] objs:temp){
			
			GoodsModel gm = (GoodsModel)objs[0];
			Long sum = (Long)objs[1];
			
			//设置行高
			JxlUtil.sRowSize(s, row+i, 19);
			//创建所有单元格
			
			Label lab_data_1 = JxlUtil.cLabel(row+i, 2, i+1+"");
			JxlUtil.sLabelStyle(lab_data_1, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0120");
			JxlUtil.aLabelToSheet(lab_data_1, s);
			
			Label lab_data_2 = JxlUtil.cLabel(row+i, 3, gm.getGtm().getSm().getName());
			JxlUtil.sLabelStyle(lab_data_2, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0110");
			JxlUtil.aLabelToSheet(lab_data_2, s);
			
			Label lab_data_3 = JxlUtil.cLabel(row+i, 4, gm.getName());
			JxlUtil.sLabelStyle(lab_data_3, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0110");
			JxlUtil.aLabelToSheet(lab_data_3, s);
			
			Label lab_data_4 = JxlUtil.cLabel(row+i, 5, sum.toString());
			JxlUtil.sLabelStyle(lab_data_4, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0112");
			JxlUtil.aLabelToSheet(lab_data_4, s);
			
			i++;
			sumAll += sum;
		}
		
		//设置最后一行高度
		JxlUtil.sRowSize(s, row+i , 25);
		//设置最后一行的合并
		JxlUtil.sMerge(s, row+i, 2, row+i, 4);
		
		Label lab_tail_1 = JxlUtil.cLabel(row+i, 2, "总计：");
		JxlUtil.sLabelStyle(lab_tail_1, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
		JxlUtil.aLabelToSheet(lab_tail_1, s);
		
		Label lab_tail_2 = JxlUtil.cLabel(row+i, 5, sumAll.toString());
		JxlUtil.sLabelStyle(lab_tail_2, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2222");
		JxlUtil.aLabelToSheet(lab_tail_2, s);
		
		w.write();
		w.close(); 
		
		//当前excel文件的内容已经写入到流os对象中，该流是一个输出流
		//表现层需要的是输入流
		//输出流转输入流
		//ByteArrayOutputStream->inputStream
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		return bis;
	}


}

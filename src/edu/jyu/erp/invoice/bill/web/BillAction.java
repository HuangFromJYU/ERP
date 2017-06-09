package edu.jyu.erp.invoice.bill.web;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import edu.jyu.erp.invoice.bill.business.ebi.BillEbi;
import edu.jyu.erp.invoice.bill.vo.BillQueryModel;
import edu.jyu.erp.invoice.orderdetail.vo.OrderDetailModel;
import edu.jyu.erp.invoice.supplier.business.ebi.SupplierEbi;
import edu.jyu.erp.invoice.supplier.vo.SupplierModel;
import edu.jyu.erp.util.base.BaseAction;

public class BillAction extends BaseAction{
	public BillQueryModel bqm = new BillQueryModel();

	private BillEbi billEbi;
	private SupplierEbi supplierEbi;
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}
	public void setBillEbi(BillEbi billEbi) {
		this.billEbi = billEbi;
	}

	public String buyBillList(){
		//加载报表数据
		/*
		select 
			od.goodsUuid, 
			g.name, 
			sum(od.num)
		from
			tbl_orderdetail od,
			tbl_goods g
		where
			g.uuid = od.goodsUuid
		group by
			od.goodsUuid
		*/
		
		//加载所有供应商信息
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList",supplierList);
		
		List<Object[]>  billList = billEbi.getBuyBill(bqm);
		/*
		for(Object[] objs:billList){
			GoodsModel gm = (GoodsModel)objs[0];
			System.out.println(gm.getName());
			System.out.println(objs[1]);
			System.out.println("-------------------");
		}
		*/
		put("billList",billList);
		return "buyBillList";
	}

	public void pieBill() throws Exception{
		HttpServletResponse response  = getResponse();
		OutputStream os = response.getOutputStream();
		//将jfreechart生成的图片信息写入到os中就行了
		billEbi.writeJFreeChartToOs(os,bqm);
	}
	
	/*
	public void pieBill() throws Exception{
		//只需要保障该请求可以将一个图片信息的内容通过IO的形式写入到响应流中即可
		HttpServletResponse response  = getResponse();
		OutputStream os = response.getOutputStream();
		File f = new File("1.png");
		System.out.println(f.getAbsolutePath());
		InputStream is = new FileInputStream(f);
		byte[] buf = new byte[1024];
		int len = -1;
		do{
			len = is.read(buf);
			if(len != -1){
				//写出去
				os.write(buf, 0, len);
			}
		}while(len != -1);
		is.close();
		
	}
	*/
	
	
	private InputStream downloadExcelStreamn;
	public InputStream getDownloadExcelStreamn() {
		return downloadExcelStreamn;
	}
	private String xlsName;
	public String getXlsName() throws UnsupportedEncodingException {
		//字符级要进行过滤
		//xlsName->byte[]->string
		return new String(xlsName.getBytes("utf-8"),"iso8859-1");
	}
	//下载excel报表
	public String downloadExcelBill() throws Exception{
		//将要下载的内容写入downloadExcelStreamn中
		xlsName = "采购报表.xls";
		downloadExcelStreamn = billEbi.getWriteExcelStream(bqm);
		return "downloadExcelBill";
	}
	
	
	//----AJAX----------------------------------
	private List<OrderDetailModel> odmList;
	public List<OrderDetailModel> getOdmList() {
		return odmList;
	}
	public String ajaxGetBuyBillDetail(){
		//根据条件获取明细信息，传递到页面，通过json格式，页面获取后展示
		//所有的条件都封装在了bqm对象中
		odmList = billEbi.getBuyBillDetail(bqm);
		return "ajaxGetBuyBillDetail";
	}
	
}

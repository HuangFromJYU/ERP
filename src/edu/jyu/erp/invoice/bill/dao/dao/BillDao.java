package edu.jyu.erp.invoice.bill.dao.dao;

import java.util.List;

import edu.jyu.erp.invoice.bill.vo.BillQueryModel;
import edu.jyu.erp.invoice.orderdetail.vo.OrderDetailModel;

public interface BillDao{

	public List<Object[]> getBuyBill(BillQueryModel bqm);

	public List<OrderDetailModel> getBuyBillDetail(BillQueryModel bqm);

}

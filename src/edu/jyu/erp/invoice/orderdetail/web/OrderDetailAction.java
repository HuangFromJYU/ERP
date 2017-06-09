package edu.jyu.erp.invoice.orderdetail.web;

import java.util.List;

import edu.jyu.erp.invoice.orderdetail.business.ebi.OrderDetailEbi;
import edu.jyu.erp.invoice.orderdetail.vo.OrderDetailModel;
import edu.jyu.erp.invoice.orderdetail.vo.OrderDetailQueryModel;
import edu.jyu.erp.util.base.BaseAction;

public class OrderDetailAction extends BaseAction{
	public OrderDetailModel om = new OrderDetailModel();
	public OrderDetailQueryModel oqm = new OrderDetailQueryModel();

	private OrderDetailEbi orderDetailEbi;
	public void setOrderDetailEbi(OrderDetailEbi orderDetailEbi) {
		this.orderDetailEbi = orderDetailEbi;
	}

	//列表
	public String list(){
		setDataTotal(orderDetailEbi.getCount(oqm));
		List<OrderDetailModel> orderDetailList = orderDetailEbi.getAll(oqm,pageNum,pageCount);
		put("orderDetailList", orderDetailList);
		return LIST;
	}

	//到添加
	public String input(){
		if(om.getUuid()!=null){
			om = orderDetailEbi.get(om.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(om.getUuid() == null){
			orderDetailEbi.save(om);
		}else{
			orderDetailEbi.update(om);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		orderDetailEbi.delete(om);
		return TO_LIST;
	}

	
	//----AJAX---------------
	//----AJAX---------------
	//----AJAX---------------
	//----AJAX---------------
	public OrderDetailModel getOm() {
		return om;
	}
	public String ajaxGetSurplus(){
		om = orderDetailEbi.get(om.getUuid());
		return "ajaxGetSurplus";
	}

	
}

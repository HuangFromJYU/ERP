package edu.jyu.erp.invoice.operdetail.web;

import java.util.List;

import edu.jyu.erp.invoice.operdetail.business.ebi.OperDetailEbi;
import edu.jyu.erp.invoice.operdetail.vo.OperDetailModel;
import edu.jyu.erp.invoice.operdetail.vo.OperDetailQueryModel;
import edu.jyu.erp.util.base.BaseAction;

public class OperDetailAction extends BaseAction {
	public OperDetailModel om = new OperDetailModel();
	public OperDetailQueryModel oqm = new OperDetailQueryModel();

	private OperDetailEbi operDetailEbi;

	public void setOperDetailEbi(OperDetailEbi operDetailEbi) {
		this.operDetailEbi = operDetailEbi;
	}

	// 列表
	public String list() {
		setDataTotal(operDetailEbi.getCount(oqm));
		List<OperDetailModel> operDetailList = operDetailEbi.getAll(oqm, pageNum, pageCount);
		put("operDetailList", operDetailList);
		return LIST;
	}

	// 操作明细只需要查询列表功能，并不用添加修改删除等功能

}

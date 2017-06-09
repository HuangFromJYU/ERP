package edu.jyu.erp.invoice.storedetail.web;

import java.util.List;

import edu.jyu.erp.invoice.storedetail.business.ebi.StoreDetailEbi;
import edu.jyu.erp.invoice.storedetail.vo.StoreDetailModel;
import edu.jyu.erp.invoice.storedetail.vo.StoreDetailQueryModel;
import edu.jyu.erp.util.base.BaseAction;

public class StoreDetailAction extends BaseAction{
	public StoreDetailModel sm = new StoreDetailModel();
	public StoreDetailQueryModel sqm = new StoreDetailQueryModel();

	private StoreDetailEbi storeDetailEbi;
	public void setStoreDetailEbi(StoreDetailEbi storeDetailEbi) {
		this.storeDetailEbi = storeDetailEbi;
	}

	//列表
	public String list(){
		setDataTotal(storeDetailEbi.getCount(sqm));
		List<StoreDetailModel> storeDetailList = storeDetailEbi.getAll(sqm,pageNum,pageCount);
		put("storeDetailList", storeDetailList);
		return LIST;
	}


}

package edu.jyu.erp.invoice.store.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import edu.jyu.erp.invoice.store.dao.dao.StoreDao;
import edu.jyu.erp.invoice.store.vo.StoreModel;
import edu.jyu.erp.invoice.store.vo.StoreQueryModel;
import edu.jyu.erp.util.base.BaseImpl;
import edu.jyu.erp.util.base.BaseQueryModel;

public class StoreImpl extends BaseImpl<StoreModel> implements StoreDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		StoreQueryModel sqm = (StoreQueryModel)qm;
		// TODO 添加自定义查询条件
	}

}

package edu.jyu.erp.invoice.operdetail.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import edu.jyu.erp.invoice.operdetail.dao.dao.OperDetailDao;
import edu.jyu.erp.invoice.operdetail.vo.OperDetailModel;
import edu.jyu.erp.invoice.operdetail.vo.OperDetailQueryModel;
import edu.jyu.erp.util.base.BaseImpl;
import edu.jyu.erp.util.base.BaseQueryModel;

public class OperDetailImpl extends BaseImpl<OperDetailModel> implements OperDetailDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		OperDetailQueryModel oqm = (OperDetailQueryModel)qm;
		// TODO 添加自定义查询条件
	}

}

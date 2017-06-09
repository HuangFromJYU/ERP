package edu.jyu.erp.invoice.goodstype.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import edu.jyu.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import edu.jyu.erp.invoice.goodstype.vo.GoodsTypeModel;
import edu.jyu.erp.invoice.goodstype.vo.GoodsTypeQueryModel;
import edu.jyu.erp.util.base.BaseImpl;
import edu.jyu.erp.util.base.BaseQueryModel;

public class GoodsTypeImpl extends BaseImpl<GoodsTypeModel> implements GoodsTypeDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		GoodsTypeQueryModel gqm = (GoodsTypeQueryModel)qm;
		// TODO 添加自定义查询条件
	}

	public List<GoodsTypeModel> getAllBySmUuid(Long uuid) {
		String hql = "from GoodsTypeModel where sm.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

	public List<GoodsTypeModel> getAllUnionBySmUuid(Long uuid) {
		//gtm->gm(没有关系)
		//gm->gtm
		String hql = "select distinct gt from GoodsModel gm join gm.gtm gt where gt.sm.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

}

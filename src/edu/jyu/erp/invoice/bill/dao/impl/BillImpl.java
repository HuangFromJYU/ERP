package edu.jyu.erp.invoice.bill.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.jyu.erp.invoice.bill.dao.dao.BillDao;
import edu.jyu.erp.invoice.bill.vo.BillQueryModel;
import edu.jyu.erp.invoice.orderdetail.vo.OrderDetailModel;

public class BillImpl extends HibernateDaoSupport implements BillDao{

	public List<Object[]> getBuyBill(BillQueryModel bqm) {
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
		//String hql = "select gm,sum(num) from OrderDetailModel group by gm.uuid";
		DetachedCriteria dc = DetachedCriteria.forClass(OrderDetailModel.class);
		
		//设置一条查询的查询结果内容为多个内容
		ProjectionList plist = Projections.projectionList();
		//分组(HQL,SQL中使用分组采用group by来完成，QBC中分组是使用投影完成的)
		plist.add(Projections.groupProperty("gm"));
		//select的内容
		plist.add(Projections.sum("num"));
		// select gm,sum(num)    from ......    group by gm
		dc.setProjection(plist);
		
		//条件
		dc.createAlias("om", "o");
		if(bqm.getType()!=null && bqm.getType()!=-1){
			dc.add(Restrictions.eq("o.type", bqm.getType()));
		}
		if(bqm.getSupplierUuid()!=null && bqm.getSupplierUuid()!= -1){
			dc.createAlias("o.sm", "s");
			dc.add(Restrictions.eq("s.uuid", bqm.getSupplierUuid()));
		}
			
		return this.getHibernateTemplate().findByCriteria(dc);
	}
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-bill.xml");
		BillDao dao = (BillDao) ctx.getBean("billDao");
		List<Object[]> list = dao.getBuyBill(new BillQueryModel());
		for(Object[] objs:list){
			for(Object obj:objs){
				System.out.println(obj);
			}
			System.out.println("-------------------");
		}
	}
	public List<OrderDetailModel> getBuyBillDetail(BillQueryModel bqm) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderDetailModel.class);
		//条件
		dc.add(Restrictions.eq("gm.uuid", bqm.getGoodsUuid()));
		
		dc.createAlias("om", "o");
		if(bqm.getType()!=null && bqm.getType()!=-1){
			dc.add(Restrictions.eq("o.type", bqm.getType()));
		}
		if(bqm.getSupplierUuid()!=null && bqm.getSupplierUuid()!= -1){
			dc.createAlias("o.sm", "s");
			dc.add(Restrictions.eq("s.uuid", bqm.getSupplierUuid()));
		}
		return this.getHibernateTemplate().findByCriteria(dc);
	}
}

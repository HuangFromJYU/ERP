package edu.jyu.erp.auth.res.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import edu.jyu.erp.auth.res.dao.dao.ResDao;
import edu.jyu.erp.auth.res.vo.ResModel;
import edu.jyu.erp.auth.res.vo.ResQueryModel;
import edu.jyu.erp.util.base.BaseImpl;
import edu.jyu.erp.util.base.BaseQueryModel;

public class ResImpl extends BaseImpl<ResModel> implements ResDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		ResQueryModel rqm = (ResQueryModel)qm;
		// TODO 添加自定义查询条件
	}

	public List<ResModel> getAllByEmpUuid(Long uuid) {
		//资源与员工没有直接关系
		//资源与角色有关系，角色与员工有关系
		//关系：资源->角色->员工
		
		//员工->角色->资源
		/*
		员工找角色
		员工1		角色1,2
		员工2		角色3,4
					角色5,6,7
		员工3			
		from EmpModel em join em.roles		//获取所有具有角色的员工信息 
		from RoleModel rm join rm.reses		//获取所有具有资源的角色信息
		from EmpModel em join em.roles rm join rm.reses res	//获取具有资源的角色的员工信息
		
		//默认不写select查询的是所有被关联的信息总和
		select em,rm,res from EmpModel em join em.roles rm join rm.reses res
		//仅仅需要资源信息
		select res from EmpModel em join em.roles rm join rm.reses res 
		*/
		//此处包含一个小BUG，第五天解决  distinct
		String hql ="select distinct res from EmpModel em join em.roles rm join rm.reses res where em.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

}

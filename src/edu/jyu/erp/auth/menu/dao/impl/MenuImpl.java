package edu.jyu.erp.auth.menu.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import edu.jyu.erp.auth.menu.dao.dao.MenuDao;
import edu.jyu.erp.auth.menu.vo.MenuModel;
import edu.jyu.erp.auth.menu.vo.MenuQueryModel;
import edu.jyu.erp.util.base.BaseImpl;
import edu.jyu.erp.util.base.BaseQueryModel;

public class MenuImpl extends BaseImpl<MenuModel> implements MenuDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		MenuQueryModel mqm = (MenuQueryModel)qm;
		//设置过滤掉系统菜单的条件
		//uuid不等于1
		dc.add(Restrictions.not(Restrictions.eq("uuid", MenuModel.MENU_SYSTEM_MENU_UUID)));
		if(mqm.getName()!=null && mqm.getName().trim().length()>0){
			dc.add(Restrictions.like("name", "%"+mqm.getName().trim()+"%"));
		}
		//mqm.parent.uuid
		if(mqm.getParent()!=null && mqm.getParent().getUuid()!=null && mqm.getParent().getUuid()!=-1){
			dc.add(Restrictions.eq("parent", mqm.getParent()));
		}
	}

	public List<MenuModel> getByPuuidIsOneOrZero() {
		String hql = "from MenuModel where parent.uuid = ? or uuid = ?";
		return this.getHibernateTemplate().find(hql,MenuModel.MENU_SYSTEM_MENU_UUID,MenuModel.MENU_SYSTEM_MENU_UUID);
	}
	
	/*
	public List<MenuModel> getAllOneLevel2() {
		String hql = "from MenuModel where parent.uuid = ?";
		return this.getHibernateTemplate().find(hql,MenuModel.MENU_SYSTEM_MENU_UUID);
	}
	*/
	
	public List<MenuModel> getAllOneLevelByEmpUuid(Long uuid) {
		//menu->role->emp
		String hql ="select distinct menu from MenuModel menu join menu.roles role join role.emps emp where emp.uuid = ? and menu.parent.uuid = ? order by menu.uuid";
		return this.getHibernateTemplate().find(hql,uuid,MenuModel.MENU_SYSTEM_MENU_UUID);
	}

	public List<MenuModel> getByEmpUuidAndPuuid(Long uuid, Long puuid) {
		String hql ="select distinct menu from MenuModel menu join menu.roles role join role.emps emp where emp.uuid = ? and menu.parent.uuid = ? order by menu.uuid";
		return this.getHibernateTemplate().find(hql,uuid,puuid);
	}
}









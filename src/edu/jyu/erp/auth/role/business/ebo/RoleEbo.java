package edu.jyu.erp.auth.role.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.jyu.erp.auth.menu.vo.MenuModel;
import edu.jyu.erp.auth.res.vo.ResModel;
import edu.jyu.erp.auth.role.business.ebi.RoleEbi;
import edu.jyu.erp.auth.role.dao.dao.RoleDao;
import edu.jyu.erp.auth.role.vo.RoleModel;
import edu.jyu.erp.util.base.BaseQueryModel;

public class RoleEbo implements RoleEbi{
	private RoleDao roleDao;
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	//废弃
	public void save(RoleModel rm) {
	}
	//废弃
	public void update(RoleModel rm) {
	}

	public void delete(RoleModel rm) {
		roleDao.delete(rm);
	}

	public RoleModel get(Serializable uuid) {
		return roleDao.get(uuid);
	}

	public List<RoleModel> getAll() {
		return roleDao.getAll();
	}

	public List<RoleModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return roleDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return roleDao.getCount(qm);
	}

//	public void save(RoleModel rm, Long[] resUuids) {
//		//array->set
//		Set<ResModel> reses = new HashSet<ResModel>();
//		for(Long uuid:resUuids){
//			ResModel temp = new ResModel();
//			temp.setUuid(uuid);
//			reses.add(temp);
//		}
//		rm.setReses(reses);
//		roleDao.save(rm);
//	}
//
//	public void update(RoleModel rm, Long[] resUuids) {
//		Set<ResModel> reses = new HashSet<ResModel>();
//		for(Long uuid:resUuids){
//			ResModel temp = new ResModel();
//			temp.setUuid(uuid);
//			reses.add(temp);
//		}
//		rm.setReses(reses);
//		roleDao.update(rm);
//	}
	
	public void save(RoleModel rm, Long[] resUuids, Long[] menuUuids) {
		//array->set
		Set<ResModel> reses = new HashSet<ResModel>();
		for(Long uuid:resUuids){
			ResModel temp = new ResModel();
			temp.setUuid(uuid);
			reses.add(temp);
		}
		rm.setReses(reses);
		
		Set<MenuModel> menus = new HashSet<MenuModel>();
		for(Long uuid:menuUuids){
			MenuModel temp = new MenuModel();
			temp.setUuid(uuid);
			menus.add(temp);
		}
		rm.setMenus(menus);
		
		roleDao.save(rm);
	}
	public void update(RoleModel rm, Long[] resUuids, Long[] menuUuids) {
		Set<ResModel> reses = new HashSet<ResModel>();
		for(Long uuid:resUuids){
			ResModel temp = new ResModel();
			temp.setUuid(uuid);
			reses.add(temp);
		}
		rm.setReses(reses);
		
		Set<MenuModel> menus = new HashSet<MenuModel>();
		for(Long uuid:menuUuids){
			MenuModel temp = new MenuModel();
			temp.setUuid(uuid);
			menus.add(temp);
		}
		rm.setMenus(menus);
		
		roleDao.update(rm);
	}

}

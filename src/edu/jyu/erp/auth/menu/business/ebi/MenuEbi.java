package edu.jyu.erp.auth.menu.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import edu.jyu.erp.auth.menu.vo.MenuModel;
import edu.jyu.erp.util.base.BaseEbi;

@Transactional
public interface MenuEbi extends BaseEbi<MenuModel>{
	/**
	 * 获取系统菜单和所有一级菜单
	 * @return
	 */
	public List<MenuModel> getAllOneLevel();

	public void save(MenuModel mm, Long[] roleUuids);

	public void update(MenuModel mm, Long[] roleUuids);
	/**
	 * 获取指定员工对应的所有可操作的一级菜单
	 * @param uuid 员工uuid
	 * @return
	 */
	public List<MenuModel> getAllOneLevelByEmp(Long uuid);
	/**
	 * 获取指定员工对应的指定一级菜单可操作的二级菜单
	 * @param uuid 员工uuid
	 * @param puuid 一级菜单uuid
	 * @return
	 */
	public List<MenuModel> getByEmpAndPuuid(Long uuid, Long puuid);

}

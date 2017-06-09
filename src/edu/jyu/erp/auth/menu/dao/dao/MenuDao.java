package edu.jyu.erp.auth.menu.dao.dao;

import java.util.List;

import edu.jyu.erp.auth.menu.vo.MenuModel;
import edu.jyu.erp.util.base.BaseDao;

public interface MenuDao extends BaseDao<MenuModel> {

	public List<MenuModel> getByPuuidIsOneOrZero();

	public List<MenuModel> getAllOneLevelByEmpUuid(Long uuid);

	public List<MenuModel> getByEmpUuidAndPuuid(Long uuid, Long puuid);

}

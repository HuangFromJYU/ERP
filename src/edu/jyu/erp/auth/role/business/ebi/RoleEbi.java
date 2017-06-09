package edu.jyu.erp.auth.role.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import edu.jyu.erp.auth.role.vo.RoleModel;
import edu.jyu.erp.util.base.BaseEbi;

@Transactional
public interface RoleEbi extends BaseEbi<RoleModel>{

	/**
	 * 保存角色信息
	 * @param rm 角色信息数据模型
	 * @param resUuids 角色关联资源uuid数组
	 * @param menuUuids 角色关联菜单uuid数组
	 */
	public void save(RoleModel rm, Long[] resUuids, Long[] menuUuids);

	/**
	 * 修改角色信息
	 * @param rm 角色信息数据模型
	 * @param resUuids 角色关联资源uuid数组
	 * @param menuUuids 角色关联菜单uuid数组
	 */
	public void update(RoleModel rm, Long[] resUuids, Long[] menuUuids);

}

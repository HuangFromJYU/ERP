package edu.jyu.erp.auth.menu.vo;

import java.util.Set;

import edu.jyu.erp.auth.role.vo.RoleModel;

/**
 * 菜单
 * 
 * @author Jason
 *
 */
public class MenuModel {
	public static final Long MENU_SYSTEM_MENU_UUID = 1L;

	private Long uuid;
	/** 菜单名称 */
	private String name;
	/** 菜单URL */
	private String url;

	// 对菜单多对一
	/** 父级菜单 */
	private MenuModel parent;
	// 对菜单一对多
	/** 子菜单 */
	private Set<MenuModel> children;
	// 对角色多对多
	private Set<RoleModel> roles;

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	public Set<MenuModel> getChildren() {
		return children;
	}

	public void setChildren(Set<MenuModel> children) {
		this.children = children;
	}

	public Long getUuid() {
		return uuid;
	}

	public MenuModel getParent() {
		return parent;
	}

	public void setParent(MenuModel parent) {
		this.parent = parent;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

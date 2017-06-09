package edu.jyu.erp.auth.dep.vo;

/**
 * 部门
 * 
 * @author Jason
 *
 */
public class DepModel {
	private Long uuid;

	/** 部门名称 */
	private String name;
	/** 电话 */
	private String tele;

	public Long getUuid() {
		return uuid;
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

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

}

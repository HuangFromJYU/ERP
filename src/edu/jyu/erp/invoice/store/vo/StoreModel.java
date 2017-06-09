package edu.jyu.erp.invoice.store.vo;

import edu.jyu.erp.auth.emp.vo.EmpModel;

/**
 * 仓库
 * 
 * @author Jason
 *
 */
public class StoreModel {
	private Long uuid;
	/** 仓库名称 */
	private String name;
	/** 仓库地址 */
	private String address;
	/** 仓库管理员 */
	private EmpModel em;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public EmpModel getEm() {
		return em;
	}

	public void setEm(EmpModel em) {
		this.em = em;
	}
}

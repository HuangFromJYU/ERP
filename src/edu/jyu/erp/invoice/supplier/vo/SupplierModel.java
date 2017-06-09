package edu.jyu.erp.invoice.supplier.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 供应商
 * 
 * @author Jason
 *
 */
public class SupplierModel {
	public static final Integer SUPPLIER_NEEDS_IS_YES = 1;
	public static final Integer SUPPLIER_NEEDS_IS_NO = 0;

	public static final String SUPPLIER_NEEDS_IS_YES_VIEW = "送货";
	public static final String SUPPLIER_NEEDS_IS_NO_VIEW = "自提";

	public static final Map<Integer, String> needsMap = new HashMap<Integer, String>();
	static {
		needsMap.put(SUPPLIER_NEEDS_IS_YES, SUPPLIER_NEEDS_IS_YES_VIEW);
		needsMap.put(SUPPLIER_NEEDS_IS_NO, SUPPLIER_NEEDS_IS_NO_VIEW);
	}

	private Long uuid;
	/** 供应商名称 */
	private String name;
	/** 地址 */
	private String address;
	/** 联系人 */
	private String contact;
	/** 电话 */
	private String tele;

	/** 送货方式 */
	private Integer needs;

	private String needsView;

	public String getNeedsView() {
		return needsView;
	}

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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public Integer getNeeds() {
		return needs;
	}

	public void setNeeds(Integer needs) {
		this.needs = needs;
		this.needsView = needsMap.get(needs);
	}

}

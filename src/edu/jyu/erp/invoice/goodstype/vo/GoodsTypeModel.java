package edu.jyu.erp.invoice.goodstype.vo;

import edu.jyu.erp.invoice.supplier.vo.SupplierModel;

/**
 * 商品类别
 * 
 * @author Jason
 *
 */
public class GoodsTypeModel {
	private Long uuid;
	/** 类别名称 */
	private String name;
	/** 供应商 */
	private SupplierModel sm;

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

	public SupplierModel getSm() {
		return sm;
	}

	public void setSm(SupplierModel sm) {
		this.sm = sm;
	}

}

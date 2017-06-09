package edu.jyu.erp.invoice.bill.vo;

import edu.jyu.erp.util.base.BaseQueryModel;

/**
 * 采购订单查询
 * 
 * @author Jason
 *
 */
public class BillQueryModel implements BaseQueryModel {
	/** 订单类别 */
	private Integer type;
	/** 供应商ID */
	private Long supplierUuid;
	/** 开始日期 */
	private Long start;
	/** 结束日期 */
	private Long end;
	/** 商品ID */
	private Long goodsUuid;

	public Long getGoodsUuid() {
		return goodsUuid;
	}

	public void setGoodsUuid(Long goodsUuid) {
		this.goodsUuid = goodsUuid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getSupplierUuid() {
		return supplierUuid;
	}

	public void setSupplierUuid(Long supplierUuid) {
		this.supplierUuid = supplierUuid;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

}

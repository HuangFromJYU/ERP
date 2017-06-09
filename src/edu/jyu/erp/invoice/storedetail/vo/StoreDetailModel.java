package edu.jyu.erp.invoice.storedetail.vo;

import edu.jyu.erp.invoice.goods.vo.GoodsModel;
import edu.jyu.erp.invoice.store.vo.StoreModel;

/**
 * 库存
 * 
 * @author Jason
 *
 */
public class StoreDetailModel {

	private Long uuid;
	/** 库存量 */
	private Integer num;
	/** 仓库 */
	private StoreModel sm;
	/** 商品 */
	private GoodsModel gm;

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public StoreModel getSm() {
		return sm;
	}

	public void setSm(StoreModel sm) {
		this.sm = sm;
	}

	public GoodsModel getGm() {
		return gm;
	}

	public void setGm(GoodsModel gm) {
		this.gm = gm;
	}

}

package edu.jyu.erp.invoice.goods.vo;

import edu.jyu.erp.invoice.goodstype.vo.GoodsTypeModel;
import edu.jyu.erp.util.format.FormatUtil;

/**
 * 商品
 * 
 * @author Jason
 *
 */
public class GoodsModel {
	private Long uuid;
	/** 商品名称 */
	private String name;
	/** 产地 */
	private String origin;
	/** 生产厂家 */
	private String producer;
	/** 单位 */
	private String unit;
	/** 进货价格 */
	private Double inPrice;
	/** 销售价格 */
	private Double outPrice;

	/** 进货价格视图值 */
	private String inPriceView;
	/** 销售价格视图值 */
	private String outPriceView;
	/** 商品类别 */
	private GoodsTypeModel gtm;

	public String getInPriceView() {
		return inPriceView;
	}

	public String getOutPriceView() {
		return outPriceView;
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

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getInPrice() {
		return inPrice;
	}

	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
		this.inPriceView = FormatUtil.formatMoney(inPrice);
	}

	public Double getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
		this.outPriceView = FormatUtil.formatMoney(outPrice);
	}

	public GoodsTypeModel getGtm() {
		return gtm;
	}

	public void setGtm(GoodsTypeModel gtm) {
		this.gtm = gtm;
	}

}

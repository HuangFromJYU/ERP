package edu.jyu.erp.invoice.orderdetail.vo;

import edu.jyu.erp.invoice.goods.vo.GoodsModel;
import edu.jyu.erp.invoice.order.vo.OrderModel;
import edu.jyu.erp.util.format.FormatUtil;

public class OrderDetailModel {
	private Long uuid;
	/** 购买数量 */
	private Integer num;
	/** 剩余数量 */
	private Integer surplus;
	/** 进货单价 */
	private Double price;

	private String priceView;
	private String totalPriceView;
	/** 商品 */
	private GoodsModel gm;
	/** 所属订单 */
	private OrderModel om;

	public Integer getSurplus() {
		return surplus;
	}

	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}

	public String getTotalPriceView() {
		return totalPriceView;
	}

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
		this.priceView = FormatUtil.formatMoney(price);
		this.totalPriceView = FormatUtil.formatMoney(num * price);
	}

	public GoodsModel getGm() {
		return gm;
	}

	public void setGm(GoodsModel gm) {
		this.gm = gm;
	}

	public OrderModel getOm() {
		return om;
	}

	public void setOm(OrderModel om) {
		this.om = om;
	}

	public String getPriceView() {
		return priceView;
	}

}

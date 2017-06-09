package edu.jyu.erp.invoice.operdetail.vo;

import java.util.HashMap;
import java.util.Map;

import edu.jyu.erp.auth.emp.vo.EmpModel;
import edu.jyu.erp.invoice.goods.vo.GoodsModel;
import edu.jyu.erp.invoice.store.vo.StoreModel;
import edu.jyu.erp.util.format.FormatUtil;

/**
 * 仓库操作明细
 * 
 * @author Jason
 *
 */
public class OperDetailModel {

	public static final Integer OPER_TYPE_OF_IN = 1;
	public static final Integer OPER_TYPE_OF_OUT = 2;

	public static final String OPER_TYPE_OF_IN_VIEW = "入库";
	public static final String OPER_TYPE_OF_OUT_VIEW = "出库";

	public static final Map<Integer, String> typeMap = new HashMap<Integer, String>();
	static {
		typeMap.put(OPER_TYPE_OF_IN, OPER_TYPE_OF_IN_VIEW);
		typeMap.put(OPER_TYPE_OF_OUT, OPER_TYPE_OF_OUT_VIEW);
	}

	private Long uuid;

	/** 商品的数量 */
	private Integer num;
	/** 操作时间 */
	private Long operTime;
	/** 操作类别 */
	private Integer type;
	/** 操作时间视图值 */
	private String operTimeView;
	/** 操作类别视图值 */
	private String typeView;
	/** 操作人 */
	private EmpModel em;
	/** 操作的商品 */
	private GoodsModel gm;
	/** 操作的仓库 */
	private StoreModel sm;

	public String getOperTimeView() {
		return operTimeView;
	}

	public String getTypeView() {
		return typeView;
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

	public Long getOperTime() {
		return operTime;
	}

	public void setOperTime(Long operTime) {
		this.operTime = operTime;
		this.operTimeView = FormatUtil.formatDate(operTime);
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
		this.typeView = typeMap.get(type);
	}

	public EmpModel getEm() {
		return em;
	}

	public void setEm(EmpModel em) {
		this.em = em;
	}

	public GoodsModel getGm() {
		return gm;
	}

	public void setGm(GoodsModel gm) {
		this.gm = gm;
	}

	public StoreModel getSm() {
		return sm;
	}

	public void setSm(StoreModel sm) {
		this.sm = sm;
	}

}

package edu.jyu.erp.invoice.goodstype.dao.dao;

import java.util.List;

import edu.jyu.erp.invoice.goodstype.vo.GoodsTypeModel;
import edu.jyu.erp.util.base.BaseDao;

public interface GoodsTypeDao extends BaseDao<GoodsTypeModel> {

	public List<GoodsTypeModel> getAllBySmUuid(Long uuid);

	public List<GoodsTypeModel> getAllUnionBySmUuid(Long uuid);

}

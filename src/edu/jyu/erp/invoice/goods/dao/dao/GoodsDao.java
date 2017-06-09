package edu.jyu.erp.invoice.goods.dao.dao;

import java.util.List;

import edu.jyu.erp.invoice.goods.vo.GoodsModel;
import edu.jyu.erp.util.base.BaseDao;

public interface GoodsDao extends BaseDao<GoodsModel> {

	public List<GoodsModel> getAllByGtmUuid(Long uuid);

}

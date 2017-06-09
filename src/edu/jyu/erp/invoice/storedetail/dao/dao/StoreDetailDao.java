package edu.jyu.erp.invoice.storedetail.dao.dao;

import edu.jyu.erp.invoice.storedetail.vo.StoreDetailModel;
import edu.jyu.erp.util.base.BaseDao;

public interface StoreDetailDao extends BaseDao<StoreDetailModel> {

	public StoreDetailModel getBySmAndGm(Long storeUuid, Long uuid);

}

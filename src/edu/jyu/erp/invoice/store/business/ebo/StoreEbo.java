package edu.jyu.erp.invoice.store.business.ebo;

import java.io.Serializable;
import java.util.List;

import edu.jyu.erp.invoice.store.business.ebi.StoreEbi;
import edu.jyu.erp.invoice.store.dao.dao.StoreDao;
import edu.jyu.erp.invoice.store.vo.StoreModel;
import edu.jyu.erp.util.base.BaseQueryModel;

public class StoreEbo implements StoreEbi{
	private StoreDao storeDao;
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	public void save(StoreModel sm) {
		storeDao.save(sm);
	}

	public void update(StoreModel sm) {
		storeDao.update(sm);
	}

	public void delete(StoreModel sm) {
		storeDao.delete(sm);
	}

	public StoreModel get(Serializable uuid) {
		return storeDao.get(uuid);
	}

	public List<StoreModel> getAll() {
		return storeDao.getAll();
	}

	public List<StoreModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return storeDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return storeDao.getCount(qm);
	}

}

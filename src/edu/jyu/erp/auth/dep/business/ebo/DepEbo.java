package edu.jyu.erp.auth.dep.business.ebo;

import java.io.Serializable;
import java.util.List;

import edu.jyu.erp.auth.dep.business.ebi.DepEbi;
import edu.jyu.erp.auth.dep.dao.dao.DepDao;
import edu.jyu.erp.auth.dep.vo.DepModel;
import edu.jyu.erp.util.base.BaseQueryModel;

public class DepEbo implements DepEbi{
	private DepDao depDao;
	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
	}

	public void save(DepModel dm) {
		depDao.save(dm);
	}
	
	public void update(DepModel dm) {
		depDao.update(dm);
	}

	public void delete(DepModel dm) {
		depDao.delete(dm);
	}

	public DepModel get(Serializable uuid) {
		return depDao.get(uuid);
	}

	public List<DepModel> getAll() {
		return depDao.getAll();
	}

	public List<DepModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return depDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return depDao.getCount(qm);
	}

}

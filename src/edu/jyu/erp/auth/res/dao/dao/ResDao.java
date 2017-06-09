package edu.jyu.erp.auth.res.dao.dao;

import java.util.List;

import edu.jyu.erp.auth.res.vo.ResModel;
import edu.jyu.erp.util.base.BaseDao;

public interface ResDao extends BaseDao<ResModel> {

	public List<ResModel> getAllByEmpUuid(Long uuid);

}

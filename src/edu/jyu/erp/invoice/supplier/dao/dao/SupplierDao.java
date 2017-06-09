package edu.jyu.erp.invoice.supplier.dao.dao;

import java.util.List;

import edu.jyu.erp.invoice.supplier.vo.SupplierModel;
import edu.jyu.erp.util.base.BaseDao;

public interface SupplierDao extends BaseDao<SupplierModel> {

	public List<SupplierModel> getAllUnion();

	public List<SupplierModel> getAllUnionTwo();

}

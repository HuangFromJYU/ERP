package edu.jyu.erp.invoice.supplier.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import edu.jyu.erp.invoice.supplier.vo.SupplierModel;
import edu.jyu.erp.util.base.BaseEbi;

@Transactional
public interface SupplierEbi extends BaseEbi<SupplierModel>{
	/**
	 * 获取具有商品类别信息的供应商信息
	 * @return
	 */
	public List<SupplierModel> getAllUnion();
	/**
	 * .....
	 * @return
	 */
	public List<SupplierModel> getAllUnionTwo();

}

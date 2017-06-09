package edu.jyu.erp.invoice.storedetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import edu.jyu.erp.invoice.storedetail.vo.StoreDetailModel;
import edu.jyu.erp.util.base.BaseEbi;

@Transactional
public interface StoreDetailEbi extends BaseEbi<StoreDetailModel>{

}

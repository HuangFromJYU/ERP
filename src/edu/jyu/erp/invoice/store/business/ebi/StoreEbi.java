package edu.jyu.erp.invoice.store.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import edu.jyu.erp.invoice.store.vo.StoreModel;
import edu.jyu.erp.util.base.BaseEbi;

@Transactional
public interface StoreEbi extends BaseEbi<StoreModel>{

}

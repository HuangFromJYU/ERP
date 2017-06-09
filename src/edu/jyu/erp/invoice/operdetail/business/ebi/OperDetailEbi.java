package edu.jyu.erp.invoice.operdetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import edu.jyu.erp.invoice.operdetail.vo.OperDetailModel;
import edu.jyu.erp.util.base.BaseEbi;

@Transactional
public interface OperDetailEbi extends BaseEbi<OperDetailModel>{

}

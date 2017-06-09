package edu.jyu.erp.invoice.orderdetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import edu.jyu.erp.invoice.orderdetail.vo.OrderDetailModel;
import edu.jyu.erp.util.base.BaseEbi;

@Transactional
public interface OrderDetailEbi extends BaseEbi<OrderDetailModel>{

}

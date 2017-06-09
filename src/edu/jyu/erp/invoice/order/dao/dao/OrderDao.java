package edu.jyu.erp.invoice.order.dao.dao;

import java.util.List;

import edu.jyu.erp.invoice.order.vo.OrderModel;
import edu.jyu.erp.invoice.order.vo.OrderQueryModel;
import edu.jyu.erp.util.base.BaseDao;

public interface OrderDao extends BaseDao<OrderModel> {

	public List<OrderModel> getAllOrderTypes(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount, Integer[] orderTypes);

	public Integer getCountOrderTypes(OrderQueryModel oqm,
			Integer[] buyCheckOrderTypes);

	public List<OrderModel> getAllTypes(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount, Integer[] types);

	public Integer getAllTypes(OrderQueryModel oqm, Integer[] types);

}

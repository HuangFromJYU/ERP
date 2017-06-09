package edu.jyu.erp.invoice.order.business.ebo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.jyu.erp.auth.emp.vo.EmpModel;
import edu.jyu.erp.invoice.goods.vo.GoodsModel;
import edu.jyu.erp.invoice.operdetail.dao.dao.OperDetailDao;
import edu.jyu.erp.invoice.operdetail.vo.OperDetailModel;
import edu.jyu.erp.invoice.order.business.ebi.OrderEbi;
import edu.jyu.erp.invoice.order.dao.dao.OrderDao;
import edu.jyu.erp.invoice.order.vo.OrderModel;
import edu.jyu.erp.invoice.order.vo.OrderQueryModel;
import edu.jyu.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import edu.jyu.erp.invoice.orderdetail.vo.OrderDetailModel;
import edu.jyu.erp.invoice.store.vo.StoreModel;
import edu.jyu.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import edu.jyu.erp.invoice.storedetail.vo.StoreDetailModel;
import edu.jyu.erp.util.base.BaseQueryModel;
import edu.jyu.erp.util.exception.AppException;
import edu.jyu.erp.util.num.NumUtil;

public class OrderEbo implements OrderEbi{
	private OrderDao orderDao;
	private OrderDetailDao orderDetailDao;
	private StoreDetailDao storeDetailDao;
	private OperDetailDao operDetailDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}
	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}
	public void setOperDetailDao(OperDetailDao operDetailDao) {
		this.operDetailDao = operDetailDao;
	}

	public void save(OrderModel om) {
		orderDao.save(om);
	}

	public void update(OrderModel om) {
		orderDao.update(om);
	}

	public void delete(OrderModel om) {
		orderDao.delete(om);
	}

	public OrderModel get(Serializable uuid) {
		return orderDao.get(uuid);
	}

	public List<OrderModel> getAll() {
		return orderDao.getAll();
	}

	public List<OrderModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return orderDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return orderDao.getCount(qm);
	}
	/*
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		//1422780670262.11
		//14227806702621.1	Wed Nov 11 00:31:42 CST 2420
		System.out.println(new Date(14227806702621L));
	}
	*/
	//在企业级应用中，业务层方法难度高到低，从1到5，该方法为4
	public void saveBuyOrder(OrderModel om, Long[] goodsUuids, Integer[] nums,Double[] prices, EmpModel creater) {
		//保存订单
		//设置订单号:订单号唯一
		String orderNum = NumUtil.generatorOrderNum();
		om.setOrderNum(orderNum);
		//订单创建时间是当前系统时间
		om.setCreateTime(System.currentTimeMillis());
		//当前保存的是采购订单
		om.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		//新保存的订单的状态是未审核
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK);
		//制单人
		om.setCreater(creater);
		//对应的供应商（已经封装在了om）
		
		Integer totalNum = 0;
		Double totalPrice = 0.0d;
		Set<OrderDetailModel> odms = new HashSet<OrderDetailModel>();
		for(int i = 0;i<goodsUuids.length;i++){
			//创建订单明细的对象并添加到集合中
			OrderDetailModel odm = new OrderDetailModel();
			//设置订单明细数量
			odm.setNum(nums[i]);
			//设置订单剩余未入库数量值
			odm.setSurplus(nums[i]);
			//设置订单明细单价
			odm.setPrice(prices[i]);
			//设置订单明细的商品
			GoodsModel gm = new GoodsModel();
			gm.setUuid(goodsUuids[i]);
			odm.setGm(gm);
			//设置所属的订单
			odm.setOm(om);
			//将明细对象加入集合
			odms.add(odm);
			
			totalNum += nums[i];
			totalPrice += nums[i]*prices[i];
		}
		//设置订单中对应的所有明细数据
		om.setOdms(odms);
		//设置订单总数量 
		om.setTotalNum(totalNum);
		//设置订单总价值
		om.setTotalPrice(totalPrice);
		
		orderDao.save(om);
	}

	public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum,	Integer pageCount) {
		//设置一个固定的条件，订单类别为采购
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}
	
	private Integer[] buyCheckOrderTypes = new Integer[]{
			OrderModel.ORDER_ORDERTYPE_OF_BUY,
			OrderModel.ORDER_ORDERTYPE_OF_RETURN_BUY
		};
	public int getCountBuyCheck(OrderQueryModel oqm) {
		
		return orderDao.getCountOrderTypes(oqm,buyCheckOrderTypes);
	}

	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount) {
		//条件中应该具有一组订单类别为采购或采购退货
		//1.  and (orderType = ???? || orderType = ????)
		//2.  and orderType in (????,????)
		return orderDao.getAllOrderTypes(oqm, pageNum, pageCount,buyCheckOrderTypes);
	}

	public void buyCheckPass(Long uuid, EmpModel checker) {
		//所谓审核实际上是修改业务
		//快照更新
		OrderModel temp = orderDao.get(uuid);
		
		//逻辑校验：比对的数据必须是从数据库中取出的数据，而不能使用页面传递的数据
		if(!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK)){
			throw new AppException("对不起，请不要进行非法操作！");
		}
		
		//type
		temp.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS);
		//审核时间
		temp.setCheckTime(System.currentTimeMillis());
		//审核人
		temp.setChecker(checker);
	}

	public void buyCheckNoPass(Long uuid, EmpModel checker) {
		OrderModel temp = orderDao.get(uuid);
		//逻辑校验
		if(!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK)){
			throw new AppException("对不起，请不要进行非法操作！");
		}
		temp.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_NO_PASS);
		temp.setCheckTime(System.currentTimeMillis());
		temp.setChecker(checker);
	}

	private Integer[] taskTypes = new Integer[]{
			OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS,
			OrderModel.ORDER_TYPE_OF_BUY_BUYING,
			OrderModel.ORDER_TYPE_OF_BUY_IN_STORE,
			OrderModel.ORDER_TYPE_OF_BUY_COMPLETE
		};
	public Integer getCountTask(OrderQueryModel oqm) {
		return orderDao.getAllTypes(oqm,taskTypes);
	}
	
	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		//运输任务必须是已经审核通过的
		return orderDao.getAllTypes(oqm, pageNum, pageCount, taskTypes);
	}

	public void assignTask(Long uuid, EmpModel completer) {
		OrderModel temp = orderDao.get(uuid);
		
		//逻辑校验(集合包含性判定)
		if(!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS)){
			throw new AppException("对不起，请不要进行非法操作！");
		}
		
		//设置状态
		temp.setType(OrderModel.ORDER_TYPE_OF_BUY_BUYING);
		//设置跟单人
		temp.setCompleter(completer);
	}

	public Integer getCountTask(OrderQueryModel oqm, EmpModel login) {
		//设置当前登录人为跟单人
		oqm.setCompleter(login);
		//设置type加强程序的健壮性
		return orderDao.getCount(oqm);
	}

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount, EmpModel login) {
		//设置当前登录人为跟单人
		oqm.setCompleter(login);
		//设置type加强程序的健壮性
		return orderDao.getAll(oqm, pageNum, pageCount);
	}

	public void endTask(Long uuid) {
		//快照
		OrderModel temp = orderDao.get(uuid);

		//逻辑校验(集合包含性判定)
		if(!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_BUYING)){
			throw new AppException("对不起，请不要进行非法操作！");
		}
		//设置状态为入库中
		temp.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
	}

	public List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		//入库的数据状态必然时正在入库中（采购入库中，销售退货入库中）简单制作一个状态
		oqm.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}

	public Integer getCountInStore(OrderQueryModel oqm) {
		oqm.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
		return orderDao.getCount(oqm);
	}

	
	
	
	public OrderDetailModel inGoods(Long storeUuid, Long odmUuid, Integer num,EmpModel login) {
		//入库
		//1.订单明细中的剩余数量要更新
		//快照
		OrderDetailModel odm = orderDetailDao.get(odmUuid);
		OrderModel om = odm.getOm();
		
		if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE)){
			throw new AppException("悟空，不要调皮！");
		}
		if(odm.getSurplus()<num){
			throw new AppException("悟空，不要调皮！");
		}
		
		//更新订单明细的剩余数量
		odm.setSurplus(odm.getSurplus()-num);
		
		//货物信息需要使用
		GoodsModel gm = odm.getGm();
		StoreModel sm = new StoreModel();
		sm.setUuid(storeUuid);
		
		//2.库存数量要发生变化
		//使用快照更新数量
		//查询按照仓库与货物查询
		StoreDetailModel sdm = storeDetailDao.getBySmAndGm(storeUuid,gm.getUuid());
		//判断该货物在指定仓库中有没有存储过
		if(sdm != null){
			//如果存储过，快照更新
			//修改当前库存数量
			sdm.setNum(sdm.getNum()+num);
		}else{
			//如果没有存储过，新增数据
			sdm = new StoreDetailModel();
			sdm.setNum(num);
			sdm.setGm(gm);
			sdm.setSm(sm);
			storeDetailDao.save(sdm);
		}
		
		//3.数据要求可追踪，记录操作日志
		OperDetailModel opdm = new OperDetailModel();
		opdm.setNum(num);
		opdm.setOperTime(System.currentTimeMillis());
		opdm.setType(OperDetailModel.OPER_TYPE_OF_IN);
		opdm.setGm(gm);
		opdm.setSm(sm);
		opdm.setEm(login);
		operDetailDao.save(opdm);
		
		//4.设置订单的状态为入库完毕
		Integer sum = 0;
		for(OrderDetailModel temp : om.getOdms()){
			sum += temp.getSurplus();
		}
		if(sum == 0 ){
			//全部入库完毕
			om.setType(OrderModel.ORDER_TYPE_OF_BUY_COMPLETE);
			om.setEndTime(System.currentTimeMillis());
		}
		return odm;
	}

}








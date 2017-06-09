package edu.jyu.erp.invoice.order.web;

import java.util.List;

import edu.jyu.erp.auth.emp.business.ebi.EmpEbi;
import edu.jyu.erp.auth.emp.vo.EmpModel;
import edu.jyu.erp.invoice.goods.business.ebi.GoodsEbi;
import edu.jyu.erp.invoice.goods.vo.GoodsModel;
import edu.jyu.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import edu.jyu.erp.invoice.goodstype.vo.GoodsTypeModel;
import edu.jyu.erp.invoice.order.business.ebi.OrderEbi;
import edu.jyu.erp.invoice.order.vo.OrderModel;
import edu.jyu.erp.invoice.order.vo.OrderQueryModel;
import edu.jyu.erp.invoice.orderdetail.vo.OrderDetailModel;
import edu.jyu.erp.invoice.store.business.ebi.StoreEbi;
import edu.jyu.erp.invoice.store.vo.StoreModel;
import edu.jyu.erp.invoice.supplier.business.ebi.SupplierEbi;
import edu.jyu.erp.invoice.supplier.vo.SupplierModel;
import edu.jyu.erp.util.base.BaseAction;

public class OrderAction extends BaseAction{
	public OrderModel om = new OrderModel();
	public OrderQueryModel oqm = new OrderQueryModel();

	private OrderEbi orderEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	private GoodsEbi goodsEbi;
	private EmpEbi empEbi;
	private StoreEbi storeEbi;
	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}
	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}
	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}
	public void setOrderEbi(OrderEbi orderEbi) {
		this.orderEbi = orderEbi;
	}

	//列表
	public String list(){
		setDataTotal(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return LIST;
	}

	//到添加
	public String input(){
		if(om.getUuid()!=null){
			om = orderEbi.get(om.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(om.getUuid() == null){
			orderEbi.save(om);
		}else{
			orderEbi.update(om);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		orderEbi.delete(om);
		return TO_LIST;
	}

	//-----------------采购相关-------------------------
	public String buyList(){
		setDataTotal(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAllBuy(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return "buyList";
	}

	//进入采购订单
	public String buyInput(){
		//加载具有类别信息的供应商信息，类别必须具有商品信息
		List<SupplierModel> supplierList = supplierEbi.getAllUnionTwo();
		//第一个供应商的具有商品的类别数据
		List<GoodsTypeModel> gtmList = goodsTypeEbi.getAllUnionBySm(supplierList.get(0).getUuid());
		//第一个类别的商品
		List<GoodsModel> gmList = goodsEbi.getAllByGtm(gtmList.get(0).getUuid());
		//第一个商品的价格
		put("supplierList",supplierList);
		put("gtmList",gtmList);
		put("gmList",gmList);
		return "buyInput";
	}
	
	public Long[] goodsUuids;
	public Integer[] nums;
	public Double[] prices;
	//保存采购订单
	public String buySave(){
		//om.sm.uuid->om
		//三个数组
		orderEbi.saveBuyOrder(om,goodsUuids,nums,prices,getLogin());
		return "toBuyList";
	}
	
	//查看采购订单明细
	public String buyDetail(){
		//根据om.uuid获取对应信息，加载到详情页
		om = orderEbi.get(om.getUuid());
		return "buyDetail";
	}
	
	//-----------------------------------------------------
	//-----------------------------------------------------
	//----------------采购审核相关开始-------------------------
	//-----------------------------------------------------
	//-----------------------------------------------------
	
	public String buyCheckList(){
		//要加载订单数据列表
		setDataTotal(orderEbi.getCountBuyCheck(oqm));
		List<OrderModel> orderList = orderEbi.getAllBuyCheck(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return "buyCheckList";
	}
	
	public String buyCheckDetail(){
		//根据om.uuid查询om，页面显示
		om = orderEbi.get(om.getUuid());
		return "buyCheckDetail";
	}
	
	//采购审核通过
	public String buyCheckPass(){
		//业务层
		orderEbi.buyCheckPass(om.getUuid(),getLogin());
		//跳页面
		return "toBuyCheckList";
	}
	//采购审核驳回
	public String buyCheckNoPass(){
		//业务层
		orderEbi.buyCheckNoPass(om.getUuid(),getLogin());
		//跳页面
		return "toBuyCheckList";
	}
	
	
	//----采购审核相关结束-------------------------------
	
	//----运输任务相关------------------------------------
	
	public String taskList(){
		setDataTotal(orderEbi.getCountTask(oqm));
		List<OrderModel> orderList = orderEbi.getAllTask(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return "taskList";
	}
	
	public String taskDetail(){
		//加载运输部门的所有员工信息
		List<EmpModel> empList = empEbi.getByDep(getLogin().getDm().getUuid());
		put("empList",empList);
		om = orderEbi.get(om.getUuid());
		return "taskDetail";
	}
	
	//指派任务
	public String assignTask(){
		//om中包含了两个数据:om.uuid		om.completer.uuid
		orderEbi.assignTask(om.getUuid(),om.getCompleter());
		return "toTaskList";
	}
	
	//查询当前登录人运输任务
	public String tasks(){
		//当前登录人查询的任务只能查询自己的任务，所以查询条件中要设置条件，跟单人为登录人
		//状态值是否要求设置？企业开发中要进行设置，目的是为了安全性，即便不设置也可以（数据间进行了安全保障）
		setDataTotal(orderEbi.getCountTask(oqm,getLogin()));
		List<OrderModel> orderList = orderEbi.getAllTask(oqm,pageNum,pageCount,getLogin());
		put("orderList", orderList);
		return "tasks";
	}
	
	public String task(){
		om = orderEbi.get(om.getUuid());
		return "task";
	}
	
	//运输任务完成
	public String endTask(){
		orderEbi.endTask(om.getUuid());
		return "toTasks";
	}
	
	//----入库相关---------------------------------
	public String inStoreList(){
		setDataTotal(orderEbi.getCountInStore(oqm));
		List<OrderModel> orderList = orderEbi.getAllInStore(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return "inStoreList";
	}
	
	//进入入库操作明细页
	public String inStoreDetail(){
		//加载所有仓库信息
		List<StoreModel> storeList = storeEbi.getAll();
		put("storeList",storeList);
		om = orderEbi.get(om.getUuid());
		return "inStoreDetail";
	}
	
	
	//----AJAX---------------------------------
	//----AJAX---------------------------------
	//----AJAX---------------------------------
	//----AJAX---------------------------------
	
	public Long supplierUuid;
	public Long gtmUuid;
	public Long gmUuid;
	
	private List<GoodsTypeModel> gtmList;
	private List<GoodsModel> gmList;
	private GoodsModel gm;
	public GoodsModel getGm() {
		return gm;
	}
	public List<GoodsTypeModel> getGtmList() {
		return gtmList;
	}
	public List<GoodsModel> getGmList() {
		return gmList;
	}
	//ajax根据供应商的uuid获取类别和商品信息
	public String ajaxGetGtmAndGm(){
		gtmList = goodsTypeEbi.getAllUnionBySm(supplierUuid);
		gmList = goodsEbi.getAllByGtm(gtmList.get(0).getUuid());
		gm = gmList.get(0);
		return "ajaxGetGtmAndGm";
	}
	
	//已经使用过的，需要过滤的商品uuid
	public String used;			//'11','22','33','44',
	//ajax根据供应商的uuid获取类别和商品信息(具有数据过滤功能)
	public String ajaxGetGtmAndGm2(){
		gtmList = goodsTypeEbi.getAllUnionBySm(supplierUuid);
		
		//由于类别数据中第一个类别的所有商品已经使用完毕，而没有将其删除，导致该商品类别对应的商品集合在下面的迭代过程中没有商品，抛出索引越界异常
		//解决方案：删除掉对应的商品类别即可
		//过滤掉所有商品已经使用完毕的商品类别
		Goods:
		for(int i = gtmList.size()-1 ;i>=0;i--){
			List<GoodsModel> gmListTemp = goodsEbi.getAllByGtm(gtmList.get(i).getUuid());
			for(GoodsModel gmTemp : gmListTemp){
				if(!used.contains("'"+gmTemp.getUuid()+"'")){
					//保留该类别，直接判断下一个类别
					continue Goods;
				}
			}
			//循环执行完毕，执行到的此处，上述循环没有进入if语句，里面的商品都使用过
			gtmList.remove(i);
		}
		//上述集合中的类别一定都存在没有使用过的商品
		
		gmList = goodsEbi.getAllByGtm(gtmList.get(0).getUuid());
		//当前获取的商品信息的uuid具有重复的，要对其进行过滤
		//对集合进行迭代删除，怎么做？必须逆向进行
		//从gmList中取出所有的元素，挨个迭代，与本次传递过来的used进行比对，比对完发现重复的，删除掉（逆序进行）
		for(int i = gmList.size()-1 ;i >=0 ;i--){
			Long uuid = gmList.get(i).getUuid();
			//判断该uuid是否出现在used中
			if(used.contains("'"+uuid+"'")){
				gmList.remove(i);
			}
		}
		gm = gmList.get(0);
		return "ajaxGetGtmAndGm";
	}
	
	//ajax根据商品类别uuid获取商品信息
	public String ajaxGetGm(){
		gmList = goodsEbi.getAllByGtm(gtmUuid);
		//过滤数据
		for(int i = gmList.size()-1 ;i >=0 ;i--){
			Long uuid = gmList.get(i).getUuid();
			//判断该uuid是否出现在used中
			if(used.contains("'"+uuid+"'")){
				gmList.remove(i);
			}
		}
		gm = gmList.get(0);
		return "ajaxGetGm";
	}
	
	//ajax根据商品uuid获取商品价格信息
	public String ajaxGetPrice(){
		gm = goodsEbi.get(gmUuid);
		return "ajaxGetPrice";
	}
	
	public Integer num;
	public Long storeUuid;
	public Long odmUuid;
	private OrderDetailModel odm;
	
	//入库操作(重要)
	public OrderDetailModel getOdm() {
		return odm;
	}
	public String ajaxInGoods(){
		odm = orderEbi.inGoods(storeUuid,odmUuid,num,getLogin());
		return "ajaxInGoods";
	}
}

















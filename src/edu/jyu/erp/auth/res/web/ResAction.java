package edu.jyu.erp.auth.res.web;

import java.util.List;

import edu.jyu.erp.auth.res.business.ebi.ResEbi;
import edu.jyu.erp.auth.res.vo.ResModel;
import edu.jyu.erp.auth.res.vo.ResQueryModel;
import edu.jyu.erp.util.base.BaseAction;

public class ResAction extends BaseAction{
	public ResModel rm = new ResModel();
	public ResQueryModel rqm = new ResQueryModel();

	private ResEbi resEbi;
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	//列表
	public String list(){
		setDataTotal(resEbi.getCount(rqm));
		List<ResModel> resList = resEbi.getAll(rqm,pageNum,pageCount);
		put("resList", resList);
		return LIST;
	}

	//到添加
	public String input(){
		if(rm.getUuid()!=null){
			rm = resEbi.get(rm.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(rm.getUuid() == null){
			resEbi.save(rm);
		}else{
			resEbi.update(rm);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		resEbi.delete(rm);
		return TO_LIST;
	}

}

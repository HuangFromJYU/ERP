package edu.jyu.erp.auth.dep.web;

import java.util.List;

import edu.jyu.erp.auth.dep.business.ebi.DepEbi;
import edu.jyu.erp.auth.dep.vo.DepModel;
import edu.jyu.erp.auth.dep.vo.DepQueryModel;
import edu.jyu.erp.util.base.BaseAction;

public class DepAction extends BaseAction {
	public DepModel dm = new DepModel();
	// 接收查询条件
	public DepQueryModel dqm = new DepQueryModel();

	private DepEbi depEbi;

	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}

	// 跳转到列表页面
	public String list() {
		setDataTotal(depEbi.getCount(dqm));
		List<DepModel> depList = depEbi.getAll(dqm, pageNum, pageCount);
		put("depList", depList);
		return LIST;
	}

	// 跳转到添加和修改页面
	public String input() {
		// 根据是否有传入uuid来判断是要添加部门还是修改部门
		if (dm != null && dm.getUuid() != null) {
			// 有传入uuid，说明是要修改，则查出对应的部门信息进行回显
			dm = depEbi.get(dm.getUuid());
		}
		return INPUT;
	}

	// 添加和修改功能
	public String save() {
		// 根据是否有传入uuid来判断是要添加部门还是修改部门
		if (dm.getUuid() == null) {
			// 没有传入uuid，添加部门
			depEbi.save(dm);
			System.out.println("------------------");
		} else {
			// 修改部门
			depEbi.update(dm);
		}
		return TO_LIST;
	}

	// 删除部门
	public String delete() {
		depEbi.delete(dm);
		return TO_LIST;
	}

}

package edu.jyu.erp.auth.emp.vo;

import edu.jyu.erp.util.base.BaseQueryModel;
import edu.jyu.erp.util.format.FormatUtil;

public class EmpQueryModel extends EmpModel implements BaseQueryModel{
	//追加生日作为查询条件的最大值字段
	private Long birthday2;
	private String birthday2View;
	
	public String getBirthday2View() {
		return birthday2View;
	}
	public Long getBirthday2() {
		return birthday2;
	}
	public void setBirthday2(Long birthday2) {
		this.birthday2 = birthday2;
		this.birthday2View = FormatUtil.formatDate(birthday2);
	}
	
}

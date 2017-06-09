package edu.jyu.erp.auth.res.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import edu.jyu.erp.auth.res.vo.ResModel;
import edu.jyu.erp.util.base.BaseEbi;

@Transactional
public interface ResEbi extends BaseEbi<ResModel>{
	/**
	 * 获取指定员工所有可操作资源信息
	 * @param uuid 员工uuid
	 * @return
	 */
	public List<ResModel> getAllByEmp(Long uuid);

}

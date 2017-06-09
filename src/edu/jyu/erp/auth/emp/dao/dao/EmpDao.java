package edu.jyu.erp.auth.emp.dao.dao;

import java.util.List;

import edu.jyu.erp.auth.emp.vo.EmpModel;
import edu.jyu.erp.util.base.BaseDao;

public interface EmpDao extends BaseDao<EmpModel> {

	/**
	 * 根据用户名和MD5加密后的密码查询用户
	 * 
	 * @param userName
	 *            用户名
	 * @param pwd
	 *            加密后的密码
	 * @return
	 */
	public EmpModel getByUserNameAndPwd(String userName, String pwd);

	public boolean updatePwdByUserNameAndPwd(String userName, String pwd, String newPwd);

	public List<EmpModel> getAllByDepUuid(Long uuid);
}

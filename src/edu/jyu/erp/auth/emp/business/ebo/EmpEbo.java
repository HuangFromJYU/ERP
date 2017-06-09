package edu.jyu.erp.auth.emp.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.jyu.erp.auth.emp.business.ebi.EmpEbi;
import edu.jyu.erp.auth.emp.dao.dao.EmpDao;
import edu.jyu.erp.auth.emp.vo.EmpModel;
import edu.jyu.erp.auth.role.vo.RoleModel;
import edu.jyu.erp.util.base.BaseQueryModel;
import edu.jyu.erp.util.exception.AppException;
import edu.jyu.erp.util.format.MD5Utils;

public class EmpEbo implements EmpEbi{
	private EmpDao empDao;
	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}
	
	//废弃
	public void save(EmpModel em) {
		//如果用户名没有输入，那么认定为特殊错误现象
		if(em.getUserName()==null || em.getUserName().trim().length() == 0){
			//认定为错误现象
			throw new AppException("INFO_EMP_USERNAME_IS_EMPTY");
		}
		
		//对密码加密
		em.setPwd(MD5Utils.md5(em.getPwd()));
		//设置默认值
		em.setLastLoginTime(System.currentTimeMillis());
		em.setLastLoginIp("-");
		em.setLoginTimes(0);
		empDao.save(em);
	}
	//废弃
	public void update(EmpModel em) {
		//em->update tbl_...  set fn=?, fn2=? ,fn3=? where uuid = ?
		//h3修改方案
		//1.调用update方法，完成修改————物理更新：硬更新
		//2.使用快照思想，完成修改————逻辑更新：软更新
		
		//快照更新
		//1.查询出数据
		EmpModel temp = empDao.get(em.getUuid());
		//2.将传递过来的需要修改的数据，覆盖temp中的相应的字段
		//传递过来了userName值，但是不将userName设置到temp，此值不参加更新
		temp.setName(em.getName());
		temp.setEmail(em.getEmail());
		temp.setTele(em.getTele());
		temp.setGender(em.getGender());
		temp.setAddress(em.getAddress());
		temp.setDm(em.getDm());
		//temp.getDm().setUuid(em.getDm().getUuid());
		
		/*
		//做修改功能外界传递了一个em对象，有些东西不让修改
		//不允许修改，将你修改的值恢复
		EmpModel temp = empDao.get(em.getUuid());
		
		em.setUserName(temp.getUserName());
		em.setBirthday(temp.getBirthday());
		empDao.update(em);
		*/
	}

	public void delete(EmpModel em) {
		empDao.delete(em);
	}

	public EmpModel get(Serializable uuid) {
		return empDao.get(uuid);
	}

	public List<EmpModel> getAll() {
		return empDao.getAll();
	}

	public List<EmpModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return empDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return empDao.getCount(qm);
	}

	public EmpModel login(String userName, String pwd,String lastLoginIp) {
		//MD5加密
		pwd = MD5Utils.md5(pwd);
		//调用数据层
		EmpModel loginEm = empDao.getByUserNameAndPwd(userName,pwd);
		if(loginEm != null){
			//登录成功
			//添加登录信息
			//登录次数+1
			loginEm.setLoginTimes(loginEm.getLoginTimes()+1);
			//最后登录时间
			loginEm.setLastLoginTime(System.currentTimeMillis());
			//最后登录IP
			loginEm.setLastLoginIp(lastLoginIp);
			//快照更新
		}
		return loginEm;
	}

	public boolean changePwd(String userName, String pwd, String newPwd) {
		//对密码加密 
		pwd = MD5Utils.md5(pwd);
		newPwd = MD5Utils.md5(newPwd);
		return empDao.updatePwdByUserNameAndPwd(userName,pwd,newPwd);
	}

	
	//原始的save方法已经不再使用，将原始方法废弃
	public void save(EmpModel em, Long[] roleUuids) {
		//此时em对象是否具有与角色对象的关系，没有
		//创建em对象与角色的关系（多对多）
		//em对象中添加role的集合关系
		
		Set<RoleModel> roles = new HashSet<RoleModel>();
		//array->set
		for(Long uuid:roleUuids){
			//创建RoleModel对象，将uuid设置到对象中，将对象添加到集合中
			RoleModel temp = new RoleModel();
			temp.setUuid(uuid);
			roles.add(temp);
		}
		em.setRoles(roles);
		
		em.setPwd(MD5Utils.md5(em.getPwd()));
		em.setLastLoginTime(System.currentTimeMillis());
		em.setLastLoginIp("-");
		em.setLoginTimes(0);
		empDao.save(em);
	}

	public void update(EmpModel em, Long[] roleUuids) {
		EmpModel temp = empDao.get(em.getUuid());
		
		temp.setName(em.getName());
		temp.setEmail(em.getEmail());
		temp.setTele(em.getTele());
		temp.setGender(em.getGender());
		temp.setAddress(em.getAddress());
		temp.setDm(em.getDm());
		
		//array->set->对象（temp）
		Set<RoleModel> roles = new HashSet<RoleModel>();
		//array->set
		for(Long uuid:roleUuids){
			//创建RoleModel对象，将uuid设置到对象中，将对象添加到集合中
			RoleModel temp2 = new RoleModel();
			temp2.setUuid(uuid);
			roles.add(temp2);
		}
		temp.setRoles(roles);
		
		//该操作引发的SQL语句
		//删除原始的所有关系
		//重新建立所有关系
		
	}

	public List<EmpModel> getByDep(Long uuid) {
		return empDao.getAllByDepUuid(uuid);
	}
}













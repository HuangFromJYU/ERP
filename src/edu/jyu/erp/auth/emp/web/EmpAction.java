package edu.jyu.erp.auth.emp.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.jyu.erp.auth.dep.business.ebi.DepEbi;
import edu.jyu.erp.auth.dep.vo.DepModel;
import edu.jyu.erp.auth.emp.business.ebi.EmpEbi;
import edu.jyu.erp.auth.emp.vo.EmpModel;
import edu.jyu.erp.auth.emp.vo.EmpQueryModel;
import edu.jyu.erp.auth.res.business.ebi.ResEbi;
import edu.jyu.erp.auth.res.vo.ResModel;
import edu.jyu.erp.auth.role.business.ebi.RoleEbi;
import edu.jyu.erp.auth.role.vo.RoleModel;
import edu.jyu.erp.util.base.BaseAction;

public class EmpAction extends BaseAction{
	public EmpModel em = new EmpModel();
	public EmpQueryModel eqm = new EmpQueryModel();

	private EmpEbi empEbi;
	private DepEbi depEbi;
	private RoleEbi roleEbi;
	private ResEbi resEbi;
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}

	//列表
	public String list(){
		//加载所有部门的信息数据
		List<DepModel> depList = depEbi.getAll();
		put("depList",depList);
		setDataTotal(empEbi.getCount(eqm));
		List<EmpModel> empList = empEbi.getAll(eqm,pageNum,pageCount);
		put("empList", empList);
		return LIST;
	}

	//到添加
	public String input(){
		//加载所有角色信息数据
		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList",roleList);
		//加载所有部门信息数据
		List<DepModel> depList = depEbi.getAll();
		put("depList",depList);
		if(em.getUuid()!=null){
			em = empEbi.get(em.getUuid());
			//此时roleUuids中无数据，必须对其进行初始化才可以进行数据回显
			roleUuids = new Long[em.getRoles().size()];
			//set->array
			int i = 0;
			for(RoleModel temp : em.getRoles()){
				roleUuids[i++] = temp.getUuid();
			}
		}
		return INPUT;
	}
	
	//如何接收页面传递的多个参数使用相同的name值
	//public String[] aa;
	//public List<String> aa;
	//页面的数据如果存在多个值具有相同的name使用数组或者集合均可接收
	//如果传递的数据不能直接使用，推荐数组，否则推荐集合
	public Long[] roleUuids;
	//添加
	public String save(){
		if(em.getUuid() == null){
			empEbi.save(em,roleUuids);
		}else{
			empEbi.update(em,roleUuids);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		empEbi.delete(em);
		return TO_LIST;
	}

	//登录
	public String login(){
		HttpServletRequest request = getRequest();
		String loginIp = request.getHeader("x-forwarded-for"); 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("WL-Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getRemoteAddr(); 
		}
		EmpModel loginEm = empEbi.login(em.getUserName(),em.getPwd(),loginIp);
		if(loginEm == null){
			this.addActionError("对不起，用户名密码错误！");
			return "loginFail";
		}else{
			//加载当前登录人对应的所有可操作资源信息
			List<ResModel> resList = resEbi.getAllByEmp(loginEm.getUuid());
			StringBuilder sbf = new StringBuilder();
			for(ResModel rm : resList){
				sbf.append(rm.getUrl());
				sbf.append(",");
			}
			loginEm.setResAll(sbf.toString());
			putSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, loginEm);
			return "loginSuccess";
		}
	}
	
	//登出/注销
	public String logout(){
		//1.获得session.removeAtrribute("name");
		//2.所谓登录失败指loginEm == null,setAttribute("name",null)
		putSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, null);
		return "noLogin";
	}
	
	//跳转到修改密码
	public String toChangePwd(){
		return "toChangePwd";
	}
	
	public String newPwd;
	//修改密码
	public String changePwd(){
		//原始密码:em.pwd
		//新密码:newPwd
		//修改密码功能如何实现？
		//1.从session中获取登录人信息，比对原始密码是否相同，如果相同，使用新密码更新原始密码(数据不同步)
		//2.使用原始密码与用户名查找数据，得到数据后，使用快照更新新密码(数据并发不同步)
		//A,13:01时间修改密码功能
		//B,13:01时间修改密码功能
		//3.执行update ...  set pwd = newPwd where userName = session[userName] and pwd = em.pwd
		//调用业务层完成修改密码
		boolean flag = empEbi.changePwd(getLogin().getUserName(),em.getPwd(),newPwd);
		if(flag){
			//修改成功
			//重新登录
			putSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, null);
			return "noLogin";
		}else{
			//提示用户当前信息输入有误
			//信息：自己处理
			return "toChangePwd";
		}
	}
	
	
	
	
}

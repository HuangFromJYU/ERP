package edu.jyu.erp.util.interceptor;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import edu.jyu.erp.auth.emp.vo.EmpModel;
import edu.jyu.erp.auth.res.business.ebi.ResEbi;
import edu.jyu.erp.auth.res.vo.ResModel;
import edu.jyu.erp.util.exception.AppException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

//权限校验
public class AuthInterceptor extends AbstractInterceptor {

	// 当前拦截器对象由struts创建，因此具有自动装配功能（struts-spring-plugin.jar）
	private ResEbi resEbi;

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	// 改良2:改良1方案中每次操作都要重新加载当前登录人的可操作资源全信息，每次查询，该操作将成为系统瓶颈
	// 改良当前登录人资源加载方式（登录时加载，并将其放入登录人信息中）
	public String intercept(ActionInvocation invocation) throws Exception {

		// 1.获取本次操作
		String actionName = invocation.getProxy().getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName + "." + methodName;

		// 获取所有资源
		String allRes = ServletActionContext.getServletContext().getAttribute("allRes").toString();
		// 2.判断本次操作是否是被拦截操作，即没有定义这个资源控制的话那就不对此操作进行拦截，比如说登录等操作
		if (!allRes.contains(allName)) {
			return invocation.invoke();
		}
		// 3.从session中获取当前登录人信息
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		// 对于登录用户对应的可操作资源在每次登录过程中，均保持不变
		// 可以考虑进行一次性加载工作，放入指定范围后，每次使用直接获取
		// 查询时机：登录时查询
		// 范围：session
		// loginEm.getResAll即为当前登录人可进行的所有资源数据字符串
		// 4.获取当前登录人可执行的所有操作（资源-角色-员工）
		// 5.判断当前登录人对应的所有可操作资源中是否包含有本次操作
		if (loginEm.getResAll().contains(allName)) {
			return invocation.invoke();
		}

		throw new AppException("对不起，请不要进行非法操作，权限不足！");
	}

	// 改良1：原始方案中每次都要获取资源数据的全数据，每次进行查询，该操作将成为系统瓶颈
	// 改良全资源加载方式
	public String intercept2(ActionInvocation invocation) throws Exception {
		// 1.获取本次操作
		// 2.判断本次操作是否是被拦截操作
		// 3.从session中获取当前登录人信息
		// 4.获取当前登录人可执行的所有操作（资源-角色-员工）
		// 5.判断当前登录人对应的所有可操作资源中是否包含有本次操作

		String actionName = invocation.getProxy().getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName + "." + methodName;

		// 全资源加载是不区分登录用户都要进行的操作
		// 所有用户使用的该数据都相同
		// 改良方案：将该数据提交获取完毕，放入指定范围，使用时直接获取
		// 查询时机：服务器启动时完成————监听器
		// 范围：ServletContext
		String allRes = ServletActionContext.getServletContext().getAttribute("allRes").toString();
		if (!allRes.contains(allName)) {
			return invocation.invoke();
		}

		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		List<ResModel> resList = resEbi.getAllByEmp(loginEm.getUuid());
		for (ResModel rm : resList) {
			if (rm.getUrl().equals(allName)) {
				return invocation.invoke();
			}
		}

		throw new AppException("对不起，请不要进行非法操作，权限不足！");
	}

	public String intercept1(ActionInvocation invocation) throws Exception {

		// 思想：
		// 获取本次操作
		// 必须保障当前操作是已经登录的
		// 获取当前登录人信息
		// 当前登录人可执行的操作中是否包含本次操作对应的内容
		// 判断当前操作人是否可以执行
		// 对原始操作的调用

		// 1.获取本次操作
		// 2.从session中获取当前登录人信息
		// 2.1如果当前登录人信息为null，跳转到登录页
		// 3.获取当前登录人可执行的所有操作（资源-角色-员工）
		// 4.判断当前登录人对应的所有可操作资源中是否包含有本次操作
		// 4.1如果不包含，拦截（抛出异常）

		// 1.获取本次操作
		// 2.判断本次操作是否是被拦截操作
		// 3.从session中获取当前登录人信息
		// 4.获取当前登录人可执行的所有操作（资源-角色-员工）
		// 5.判断当前登录人对应的所有可操作资源中是否包含有本次操作

		// 1.获取本次操作
		String actionName = invocation.getProxy().getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName + "." + methodName;

		// 1.1获取所有的资源信息，比对本次操作是否在资源全列表中，如果出现了，需要拦截，否则直接放行
		List<ResModel> resAll = resEbi.getAll();
		// list->string(stringbuffer,stringbuilder)
		StringBuilder sbf = new StringBuilder();
		for (ResModel temp : resAll) {
			sbf.append(temp.getUrl());
			sbf.append(",");
		}
		// sbf中保存有所有需要校验的资源
		if (sbf.indexOf(allName) < 0) {
			return invocation.invoke();
		}
		// 到达此处，说明该操作需要被校验

		// 2.从session中获取当前登录人信息
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		// 上述对象无法完成OpenSessionInView,因此最初关联的session对象已经消失
		// 2.1如果当前登录人信息为null，跳转到登录页
		/*
		 * if(loginEm == null){ return "noLogin"; }
		 */
		// 3.获取当前登录人可执行的所有操作（资源-角色-员工）
		// 获取指定员工对应的所有可操作资源

		List<ResModel> resList = resEbi.getAllByEmp(loginEm.getUuid());
		// 4.判断当前登录人对应的所有可操作资源中是否包含有本次操作
		// 4.1如果不包含，拦截（抛出异常）
		for (ResModel rm : resList) {
			if (rm.getUrl().equals(allName)) {
				return invocation.invoke();
			}
		}

		// 不包含可执行的操作
		throw new AppException("对不起，请不要进行非法操作，权限不足！");

	}

}

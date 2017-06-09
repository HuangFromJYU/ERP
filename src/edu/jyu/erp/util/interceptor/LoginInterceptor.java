package edu.jyu.erp.util.interceptor;

import edu.jyu.erp.auth.emp.vo.EmpModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		// 执行除了登录操作之前的所有操作做登录校验
		// 获取本次操作的信息
		/*
		 * System.out.println(invocation.getProxy().getAction());
		 * System.out.println(invocation.getProxy().getActionName()); emp_login
		 * System.out.println(invocation.getProxy().getMethod());
		 */
		// 获取action类的全限定类名，如edu.jyu.erp.auth.emp.web.EmpAction
		String actionName = invocation.getProxy().getAction().getClass().getName();
		// 获取要调用的action中的方法名，如EmpAction的login方法
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName + "." + methodName;

		// 获取操作名称，就是配置好的action的name+占位符（方法名），如emp_login、page_login
		String operName = invocation.getProxy().getActionName(); // page_login
		// 如果是要进入登录页面，则放行
		if ("page_login".equals(operName)) {
			return invocation.invoke();
		}
		// 如果是要做登录操作，则放行
		if ("edu.jyu.erp.auth.emp.web.EmpAction.login".equals(allName)) {
			return invocation.invoke();
		}

		// 获取当前登录人信息
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		// 如果当前没有登录，跳转到登录页面
		if (loginEm == null) {
			// 跳转到登录
			return "noLogin";
		}

		// 执行原始操作
		return invocation.invoke();
	}

}

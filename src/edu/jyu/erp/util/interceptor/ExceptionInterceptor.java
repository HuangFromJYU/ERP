package edu.jyu.erp.util.interceptor;

import edu.jyu.erp.util.exception.AppException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (AppException e) {
			// 记录日志
			// 发送日志到程序员邮箱
			// 报警
			ActionSupport as = (ActionSupport) invocation.getAction();
			// 获取国际化消息
			as.addActionError(as.getText(e.getMessage()));
			return "error";
		} catch (Exception e) {
			// ActionSupport as = (ActionSupport) invocation.getAction();
			// as.addActionError("对不起，服务器已关闭，请联系管理员！");
			// return "error";
			// 记录日志
			// 发送日志到程序员邮箱
			// 报警
			e.printStackTrace();
			return invocation.invoke();
		}
	}

}

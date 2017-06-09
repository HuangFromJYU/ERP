package edu.jyu.erp.util.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import edu.jyu.erp.auth.emp.vo.EmpModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	public static final String LIST = "list";
	public static final String TO_LIST = "toList";
	public static final String INPUT = "input";

	public Integer pageNum = 1;
	public Integer pageCount = 10;
	public Integer maxPageNum;
	public Integer dataTotal;

	/**
	 * 这个方法是为全局结果集配置服务的，各个Action的result有很多类似的地方，不同的只是文件夹的名称，
	 * 而这个不同的文件夹名称就是Action前面的几个字母，第一个字母小写，比如说DepAction对应的就是dep，
	 * 所以可以根据这个特性来把各个Action类似的结果集抽取成全局结果集，具体看struts.xml中的<global-results>
	 * 
	 * @return
	 */
	public String getActionName() {
		// 动态
		// DepAction ->dep
		// EmpAction ->emp
		String actionName = this.getClass().getSimpleName();
		// DepAction ->Dep
		String temp = actionName.substring(0, actionName.length() - 6);
		// Dep ->dep OrderDetailAction ->orderDetail orderdetail
		return temp.substring(0, 1).toLowerCase() + temp.substring(1);
	}

	protected void setDataTotal(int dataTotal) {
		this.dataTotal = dataTotal;
		maxPageNum = (dataTotal + pageCount - 1) / pageCount;
	}

	protected void put(String name, Object obj) {
		ActionContext.getContext().put(name, obj);
	}

	protected Object get(String name) {
		return ActionContext.getContext().get(name);
	}

	protected void putSession(String name, Object obj) {
		ActionContext.getContext().getSession().put(name, obj);
	}

	protected Object getSession(String name) {
		return ActionContext.getContext().getSession().get(name);
	}

	protected EmpModel getLogin() {
		return (EmpModel) getSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

}

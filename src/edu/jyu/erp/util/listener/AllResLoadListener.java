package edu.jyu.erp.util.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.jyu.erp.auth.res.business.ebi.ResEbi;
import edu.jyu.erp.auth.res.vo.ResModel;

public class AllResLoadListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent event) {
		//读取所有资源信息，放入SerlvetContext范围
		//使用spring的上下文对象
		ServletContext sc = event.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc); 
		ResEbi resEbi = (ResEbi) ctx.getBean("resEbi");
		List<ResModel> resList = resEbi.getAll();
		StringBuilder sbf = new StringBuilder();
		for(ResModel temp :resList){
			sbf.append(temp.getUrl());
			sbf.append(",");
		}
		//放入sc中
		sc.setAttribute("allRes", sbf.toString());
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
	}
}

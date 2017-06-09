package edu.jyu.erp.auth.menu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import edu.jyu.erp.auth.menu.business.ebi.MenuEbi;
import edu.jyu.erp.auth.menu.vo.MenuModel;
import edu.jyu.erp.auth.menu.vo.MenuQueryModel;
import edu.jyu.erp.auth.role.business.ebi.RoleEbi;
import edu.jyu.erp.auth.role.vo.RoleModel;
import edu.jyu.erp.util.base.BaseAction;

public class MenuAction extends BaseAction{
	public MenuModel mm = new MenuModel();
	public MenuQueryModel mqm = new MenuQueryModel();

	private MenuEbi menuEbi;
	private RoleEbi roleEbi;
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}
	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	//列表
	public String list(){
		List<MenuModel> parentList = menuEbi.getAllOneLevel();
		put("parentList",parentList);
		setDataTotal(menuEbi.getCount(mqm));
		List<MenuModel> menuList = menuEbi.getAll(mqm,pageNum,pageCount);
		put("menuList", menuList);
		return LIST;
	}

	//到添加
	public String input(){
		//加载所有角色数据信息
		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList",roleList);
		//加载所有的一级菜单
		List<MenuModel> menuList = menuEbi.getAllOneLevel();
		put("menuList",menuList);
		if(mm.getUuid()!=null){
			mm = menuEbi.get(mm.getUuid());
			//set->array
			roleUuids = new Long[mm.getRoles().size()];
			int i = 0;
			for(RoleModel rm : mm.getRoles()){
				roleUuids[i++] = rm.getUuid();
			}
		}
		return INPUT;
	}

	
	public Long[] roleUuids;
	//添加
	public String save(){
		if(mm.getUuid() == null){
			menuEbi.save(mm,roleUuids);
		}else{
			menuEbi.update(mm,roleUuids);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		menuEbi.delete(mm);
		return TO_LIST;
	}

	//显示菜单
	public void showMenu() throws IOException{
		//1.首先获取root参数
		String root = getRequest().getParameter("root");
		//2.判断参数值   source   id
		HttpServletResponse response = getResponse();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		StringBuilder json = new StringBuilder();
		json.append("[");
		
		if("source".equals(root)){
			//生成一级菜单
			List<MenuModel> menuList = menuEbi.getAllOneLevelByEmp(getLogin().getUuid());
			for(MenuModel temp :menuList){
				json.append("{\"text\":\"");
				json.append(temp.getName());
				json.append("\",\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"");
				json.append(temp.getUuid());
				json.append("\"},");
			}
		}else{
			//生成二级菜单项
			//获取指定一级菜单的二级菜单项
			Long puuid = new Long(root);
			List<MenuModel> menuList = menuEbi.getByEmpAndPuuid(getLogin().getUuid(),puuid);
			for(MenuModel temp :menuList){
				json.append("{\"text\":\"<a class='hei' target='main' href='");
				json.append(temp.getUrl());
				json.append("'>");
				json.append(temp.getName());
				json.append("</a>\",\"hasChildren\":false,\"classes\":\"file\"},");
			}
		}
		
		json.deleteCharAt(json.length()-1);
		json.append("]");
		
		pw.write(json.toString());
		pw.flush();
	}
	
	/*
	//显示菜单
	public void showMenu() throws IOException{
		HttpServletResponse response = getResponse();
		//手动设置字符集
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		StringBuilder json = new StringBuilder();
		json.append("[");
		
		//获取当前登录人所能进行操作的所有一级菜单
		List<MenuModel> menuList = menuEbi.getAllOneLevelByEmp(getLogin().getUuid());
		for(MenuModel temp :menuList){
			json.append("{\"text\":\"");
			json.append(temp.getName());
			json.append("\",\"hasChildren\":true,\"classes\":\"folder\",\"id\":\"");
			json.append(temp.getUuid());
			json.append("\"},");
		}
		json.deleteCharAt(json.length()-1);
		json.append("]");
		
		pw.write(json.toString());
		pw.flush();
	}
	*/
	
	
	/*
	//显示菜单
	public void showMenu() throws IOException{
		HttpServletResponse response = getResponse();
		//手动设置字符集
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		StringBuilder json = new StringBuilder();
		json.append("[");
		//一级菜单
		json.append("{\"text\":\"aaa\",\"hasChildren\":true,\"classes\":\"folder\"},");
		//二级菜单项
		json.append("{\"text\":\"bbb\",\"hasChildren\":false,\"classes\":\"file\"},");
		json.append("{\"text\":\"ccc\",\"hasChildren\":true}");
		json.append("]");
		
		pw.write(json.toString());
		pw.flush();
	}
	*/
	
	/*
	//显示菜单
	public String showMenu() throws IOException{
		//返回一个JSON数据即可
		//从request中获取参数root的值
		//判断其是不是souce
		//如果是source,返回json数组1
		//否则，返回json数组2
		
		//在strus2的响应方法中如何返回一个json数据
		//1.写出json
		String json = "[{\"text\":\"aaaa\"},{\"text\":\"bbbb\"},{\"text\":\"cccc\"}]";
		//2.使用原始SerlvetAPI完成将自定义的JSON内容写入响应
		HttpServletResponse response = getResponse();
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		//3.如果设置当前方法返回json，通过手工形式将json写入请求，返回NONE,null
		return null;
	}
	*/
	
}

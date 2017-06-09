<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/Calendar.js"></script>
<script type="text/javascript">
/*
	$(function() {
		$("#all").click(function() {
			$("[name=resources]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function() {
			$("[name=resources]:checkbox").each(function () {
                $(this).attr("checked", !$(this).attr("checked"));
            });

		});
	});
*/
	$(function(){
		//全选
		$("#all").click(function(){
			//将下面所有组件全部选中
			//$("[name=resUuids]")	是多个组件，整体是个对象数组
			//$("[name=resUuids]").attr("checked","checked");
			
			//先获取当前组件的状态
			//$(this).attr("checked")
			//将所有组件设置为对应状态
			//$("[name=resUuids]").attr("checked",$(this).attr("checked"));
			
			//$(this).attr("checked")获取的值究竟是什么
			//alert($(this).attr("checked"));		//undefined
			//$("[name=resUuids]").attr("checked","undefined");
			
			//js语法规则，除了false,FALSE,"false","FALSE",0五个值之外的所有值，认定为true
			//$("[name=resUuids]").attr("checked",false);
			
			var flag = $(this).attr("checked");
			$("[name=resUuids]").attr("checked",flag == "checked");
		});
		
		//反选
		$("#reverse").click(function(){
			//将所有组件的状态切换成原始状态的反状态
			
			//$("[name=resUuids]").attr("checked",!($("[name=resUuids]").attr("checked")=="checked"));
			
			//当选择器选中的组件是多个时，获取组件的任何数据都是对第一个组件进行操作
			//alert(!($("[name=resUuids]").attr("checked")=="checked"));
			
			//对每个组件进行迭代，让其操作状态为对应组件的原始状态的反状态
			$("[name=resUuids]").each(function(){
				//使用each操作实现对每个组件的操作
				var flag = $(this).attr("checked"); 
				$(this).attr("checked", !(flag =="checked"));
			});
			checkSelect();
		});
		
		//绑定组件
		$("[name=resUuids]").click(function(){
			//将全选的状态设置为基于所有组件的综合状态值
			checkSelect();
		});
		
		function checkSelect(){
			var allFlag = true;
			$("[name=resUuids]").each(function(){
				var flag = $(this).attr("checked") == "checked";
				//&:位运算与	 &&:逻辑与
				allFlag = allFlag && flag; 
			});
			$("#all").attr("checked",allFlag);
		}
		
	});


</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">角色管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<s:form action="role_save" method="post">
			<s:hidden name="rm.uuid"/>
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">角色名称</td>
				      <td width="32%">
				      	<s:textfield name="rm.name" size="25"/>
				      </td>
				      <td width="18%" align="center">角色编码</td>
				      <td width="32%">
				      	<s:textfield name="rm.code" size="25"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">资源名称</td>
				      <td width="82%" colspan="3">
				      	<input type="checkbox" id="all">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	<input type="checkbox" id="reverse">反选
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">&nbsp;</td>
				      <td width="82%" colspan="3">
				      	<s:checkboxlist name="resUuids" list="resList" listKey="uuid" listValue="name"></s:checkboxlist>
				      </td>
				    </tr>
				     <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">菜单名称</td>
				      <td width="82%" colspan="3">
				      	<input type="checkbox" id="all2">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	<input type="checkbox" id="reverse2">反选
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">&nbsp;</td>
				      <td width="82%" colspan="3">
				      	<s:checkboxlist name="menuUuids" list="menuList" listKey="uuid" listValue="name"></s:checkboxlist>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
			</div>
			<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:document.forms[0].submit()"><img src="images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</s:form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="images/content_bbg.jpg" /></div>
</div>

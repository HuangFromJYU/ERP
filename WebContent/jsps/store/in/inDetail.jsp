<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="../../../css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../../js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	

	$(function() {
		//初始化仓库数据
		var storeUuidArr = new Array();
		var storeNameArr = new Array();
			
			storeUuidArr[0] = 11;
			storeNameArr[0] = "1号仓库";
			storeUuidArr[1] = 22;
			storeNameArr[1] = "2号仓库";
			storeUuidArr[2] = 33;
			storeNameArr[2] = "3号仓库";
		var omUuid = 123;
		
		$(".oper").click(function() {
			var $myTr = $(this).parent().parent();
			var $nextTr = $myTr.next();
			if($nextTr.attr("class") == "in"){
				return;
			}
			if($(".in").length>0){
				$(".in").remove();
			}
			var $newTr = $("<tr class='in'></tr>");
			var $td1 = $("<td align='right'>仓库：</td>");
			$newTr.append($td1);	
				var storeSelectStr = "<select style='width:200px'>";
				for(var i = 0;i<storeUuidArr.length;i++){
					storeSelectStr+="<option value='";
					storeSelectStr+=storeUuidArr[i];
					storeSelectStr+="'>";
					storeSelectStr+=storeNameArr[i];
					storeSelectStr+="</option>";
				}
				storeSelectStr += "</select>";
			var $td2 = $("<td height='30'>"+storeSelectStr+"</td>");
			$newTr.append($td2);	
			//2.3入库多少
			var $td3 = $("<td align='right'>入库量：</td>");
			$newTr.append($td3);	
			//获取当前入库数据总量
			var totalNum = $(this).parent().prev().text();
			var $td4 = $("<td><input id='inNum' type='text' value='"+totalNum+"'/></td>");
			$newTr.append($td4);	
			var $td5 = $("<td align='center'><a href='javascript:void(0)' class='ajaxIn xiu'><img src='../../../images/icon_3.gif' />确定</a></td>");
			$newTr.append($td5);
			//3.将新的行对象添加到当前按钮所在的行对象后面
			$myTr.after($newTr);
		});
		$(".ajaxIn").live("click",function(){
			//0.页面校验输入是否合法（省略）
			//1.组织ajax提交的数据
			jsonParam ={};
			//主单编号
			jsonParam["odm.order.uuid"] = omUuid;
			//获取当前链接所在行的上一行中隐藏的第一个子节点和第二个子节点的值
			//子单编号
			jsonParam["odm.uuid"] = $(this).parent().parent().prev().children("input:eq(0)").val();
			//货物编号
			jsonParam["odm.goods.uuid"] = $(this).parent().parent().prev().children("input:eq(1)").val();
			//入库数量
			jsonParam["som.num"] = $(this).parent().prev().children("input:eq(0)").val();
			//仓库编号
			jsonParam["som.sdm.sm.uuid"] = $(this).parent().parent().children("td:eq(1)").children("select").val();
			
			//为ajax提交操作后的操作对象进行初始化
			var $upTr = $(this).parent().parent().prev();
			var $upCenter =  $upTr.children("td:eq(2)");
			var $upRight = $upTr.children("td:eq(3)");
			var $myTr = $(this).parent().parent();
				if(false){
					//显示返回按钮
					$("#inOrderTitle").remove();
					$("#inOrder").remove();
					$("#allInTitle").css("display","block");
					$("#return").css("display","block");
					return;
				}
				
				if(0 == 0){
					$upTr.remove();
					$myTr.remove();
				}else{
					$upCenter.text(20);
					$upRight.text(10);	
				}
			});
			
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">入库</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>订 单 号:</td>
						<td class="order_show_msg">283478f0sdljkh3</td>
						<td>商品总量:</td>
						<td class="order_show_msg">150</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<center id="inOrderTitle" style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;据&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table id="inOrder" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(../../../images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品名称</td>
						<td width="30%">总数量</td>
						<td width="10%">已入库数量</td>
						<td width="30%">剩余数量</td>
						<td width="10%">入库</td>
					</tr>
							<tr aa="bb" align="center" bgcolor="#FFFFFF">
								<input type="hidden" value=1/>
								<input type="hidden" value=2/>
								<td height="30">狼皮毛背心</td>
								<td>100</td>
								<td>20</td>
								<td>80</td>
								<td><a href="javascript:void(0)" class="oper xiu"><img src="../../../images/icon_3.gif" />入库</a></td>
							</tr>
							<tr aa="bb" align="center" bgcolor="#FFFFFF">
								<input type="hidden" value=1/>
								<input type="hidden" value=2/>
								<td height="30">狼皮翻毛大衣</td>
								<td>50</td>
								<td>0</td>
								<td>50</td>
								<td><a href="javascript:void(0)" class="oper xiu"><img src="../../../images/icon_3.gif" />入库</a></td>
							</tr>
				</table>
				
				<center id="allInTitle" style="display:none;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;全&nbsp;&nbsp;部&nbsp;&nbsp;入&nbsp;&nbsp;库&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<table id="return" style="display:none" >
					<tr>
						<td>&nbsp;</td>
						<td width="100%" align="center">
							<a action="list.jsp" style="color:#f00;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(../../../images/btn_bg.jpg)">
								返回
							</a>
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>

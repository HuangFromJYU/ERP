<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货订单明细</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="30">企业名称:</td>
						<td class="order_show_msg">${om.sm.name}</td>
						<td>下单时间:</td>
						<td class="order_show_msg">${om.createTimeView}</td>
						<td>下 单 人:</td>
						<td class="order_show_msg">${om.creater.name }</td>
						<td>订 单 号:</td>
						<td class="order_show_msg" colspan="2">${om.orderNum }</td>
					</tr>
					<tr>
						<td height="30">订单类别:</td>
						<td class="order_show_msg">${om.orderTypeView }</td>
						<td>订单状态:</td>
						<td class="order_show_msg">${om.typeView }</td>
						<td>商品总量:</td>
						<td class="order_show_msg">${om.totalNum }</td>
						<td>订单总额:</td>
						<td class="order_show_msg">${om.totalPriceView } 元</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<center style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;订&nbsp;&nbsp;单&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品类别</td>
						<td width="20%">商品名称</td>
						<td width="20%">购买数量</td>
						<td width="20%">进货单价</td>
						<td width="20%">合计</td>
					</tr>
					<s:iterator value="om.odms">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">${gm.gtm.name}</td>
							<td>${gm.name}</td>
							<td>${num}</td>
							<td align="right">${priceView} 元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td align="right">${totalPriceView} 元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						</tr>
					</s:iterator>
					<tr align="right">
						<td height="30" width="80%" colspan="4">总计&nbsp;&nbsp;</td>
						<td width="20%">${om.totalPriceView } 元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
				</table>
				<br/>
				<table width="100%">
					<tr align="center">
						<td width="50%" algin="right">
							<a href="javascript:history.back()" style="color:#0f0;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(images/btn_bg.jpg)">
								返&nbsp;&nbsp;回
							</a>
						</td>
					</tr>
				</table>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>

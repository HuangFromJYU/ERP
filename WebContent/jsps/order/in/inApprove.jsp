<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="../../../css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../../js/jquery-1.8.3.js"></script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货审核</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="30">企业名称:</td>
						<td class="order_show_msg">七匹狼</td>
						<td>下单时间:</td>
						<td class="order_show_msg">2014-2-23</td>
						<td>下 单 人:</td>
						<td class="order_show_msg">张三</td>
						<td>订 单 号:</td>
						<td class="order_show_msg" colspan="2">dsafklj123808adsj27dj3</td>
					</tr>
					<tr>
						<td height="30">订单类别:</td>
						<td class="order_show_msg">采购</td>
						<td>订单状态:</td>
						<td class="order_show_msg">未审核</td>
						<td>商品总量:</td>
						<td class="order_show_msg">101</td>
						<td>订单总额:</td>
						<td class="order_show_msg">78824.00 元</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<center style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;订&nbsp;&nbsp;单&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(../../../images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品类别</td>
						<td width="20%">商品名称</td>
						<td width="20%">购买数量</td>
						<td width="20%">进货单价</td>
						<td width="20%">合计</td>
					<tr align="center" bgcolor="#FFFFFF">
						<td height="30">上衣</td>
						<td>雪酷狼皮价格</td>
						<td>100</td>
						<td align="right">788.23 元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="right">78823.00 元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr align="center" bgcolor="#FFFFFF">
						<td height="30">裤子</td>
						<td>狼皮秋裤</td>
						<td>1</td>
						<td align="right">1.00 元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="right">1.00 元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr align="right">
						<td height="30" width="80%" colspan="4">总计&nbsp;&nbsp;</td>
						<td width="20%">78824.00  元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
				</table>
				<br/>
				<table width="100%">
					<tr align="center">
						<td width="50%">
							<a href="inApproveList.jsp" style="color:#0f0;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(../../../images/btn_bg.jpg)">
								通&nbsp;&nbsp;过
							</a>
						</td>
						<td width="50%">
							<a href="inApproveList.jsp" style="color:#f00;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(../../../images/btn_bg.jpg)">
								驳&nbsp;&nbsp;回
							</a>
						</td>
					</tr>
				</table>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>

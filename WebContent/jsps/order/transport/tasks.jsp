<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="../../../css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../../js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">任务查询</span>
		</div>
	</div>
	<div class="content-text">
		<form action="tasks.jsp" method="post"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="8%">供&nbsp;应&nbsp;商:</td>
						<td width="29%">
							<select style="width:137px">
								<option value="-1">----请-选-择----</option>
								<option value="1">七匹狼</option>
								<option value="0">康师傅</option>
							</select>
						</td>
						<td width="8%">发货方式:</td>
						<td width="45%">
							<select style="width:137px">
								<option value="-1">----请-选-择----</option>
								<option value="1">送货</option>
								<option value="0">自提</option>
							</select>
						</td>
						<td width=""><a id="query"> 
							<img src="../../../images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(../../../images/table_bg.gif) repeat-x;">
						<td width="8%" height="30">订单类别</td>
						<td width="11%">供应商</td>
						<td width="7%">发货方式</td>
						<td width="6%">联系人</td>
						<td width="12%">联系方式</td>
						<td width="40%">地址</td>
						<td width="8%">地址</td>
						<td width="6%">详情</td>
					</tr>
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">采购</td>
							<td>七匹狼</td>
							<td>送货</td>
							<td>狼外婆</td>
							<td>119</td>
							<td align="left">&nbsp;狼堡</td>
							<td>正在采购</td>
							<td>
								<a href="./taskDetail.jsp">
									<img src="../../../images/icon_3.gif" />详情
								</a>
							</td>
						</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>

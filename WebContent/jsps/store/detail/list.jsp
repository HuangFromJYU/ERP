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
			<span class="page_title">仓库货物明细</span>
		</div>
	</div>
	<div class="content-text">
		<form action="list.jsp" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="10%">仓库名称:</td>
						<td width="20%"><input type="text" size="20" /></td>
						<td>管理员:</td>
						<td><input type="text" size="20" /></td>
						<td>货物名称:</td>
						<td><input type="text" size="20" /></td>
						<td width="">
							<a id="query"><img src="../../../images/can_b_01.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(../../../images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">仓库名称</td>
						<td width="25%">仓库管理员</td>
						<td width="25%">货物名称</td>
						<td width="25%">当前库存量</td>
					</tr>
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">一号仓库</td>
							<td>王仓库</td>
							<td>狼皮背心</td>
							<td>6000&nbsp;件</td>
						</tr>
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">一号仓库</td>
							<td>王仓库</td>
							<td>梦龙雪糕</td>
							<td>500000&nbsp;箱</td>
						</tr>
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">一号仓库</td>
							<td>王仓库</td>
							<td>小肥羊涮料</td>
							<td>4&nbsp;箱</td>
						</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>

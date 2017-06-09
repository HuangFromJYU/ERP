<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
<title>蓝源进销存(教学版)-系统登录页</title>
<script>
	$(function() {
		$("#login_ok").click(function() {
			$("form:first").submit();
		});
	});
	function MM_swapImage(srcObj,image_src){
		srcObj.src=image_src;
	}
</script>
</head>
<body>
	<div class="container-login">
		<div class="login-pic">
			<div class="login-text">
				<form action="main.jsp" method="post">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="19%" height="28">用户名：</td>
							<td colspan="2">
								<input value="admin" type="text" size="18" />
							</td>
						</tr>
						<tr>
							<td height="31">密&nbsp;&nbsp;码：</td>
							<td colspan="2">
								<input value="1" type="test" size="18" />
							</td>
						</tr>
						<tr>
							<td height="30">验证码：</td>
							<td width="43%">
								<input type="text" size="9" />
							</td>
							<td width="32%"><img src="../images/test.gif" />
							</td>
						</tr>
						<tr>
							<td height="30">&nbsp;</td>
							<td colspan="2">
								<a href="javascript:void(0)" id="login_ok">
									<img src="../images/denglu_bg_03.gif" 
										 name="Image1" width="40"	
										 height="22" border="0"  
										 onmouseover="MM_swapImage(this,'../images/denglu_h_03.gif')" 
										 onmouseout="MM_swapImage(this,'../images/denglu_bg_03.gif')" /></a>
								<a href="#">
									<img src="../images/giveup_bg_03.gif" 
										 name="Image2" width="42" 
										 height="22" border="0"  
										 onmouseover="MM_swapImage(this,'../images/giveup_h_03.gif')" 
										 onmouseout="MM_swapImage(this,'../images/giveup_bg_03.gif')" /></a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

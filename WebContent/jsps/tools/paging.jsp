<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	$(function(){
		var pageNum = ${pageNum};
		var pageCount = ${pageCount};
		if(pageCount == 1){
			$("#fir").css("display","none");
			$("#pre").css("display","none");
			$("#next").css("display","none");
			$("#last").css("display","none");
		}else if(pageNum == 1){
			$("#fir").css("display","none");
			$("#pre").css("display","none");
			$("#next").css("display","inline");
			$("#last").css("display","inline");
		}else if(pageNum == pageCount){
			$("#fir").css("display","inline");
			$("#pre").css("display","inline");
			$("#next").css("display","none");
			$("#last").css("display","none");
		}else{
			$("#fir").css("display","inline");
			$("#pre").css("display","inline");
			$("#next").css("display","inline");
			$("#last").css("display","inline");
		}
		$("#fir").click(function(){
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
		$("#pre").click(function(){
			$("[name='pageNum']").val($("[name='pageNum']").val()-1);
			$("form:first").submit();
		});
		$("#next").click(function(){
			$("[name='pageNum']").val($("[name='pageNum']").val()*1+1);
			$("form:first").submit();
		});
		$("#last").click(function(){
			$("[name='pageNum']").val(pageCount);
			$("form:first").submit();
		});

	});
</script>
<br/>
<s:hidden name="pageNum"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="51%">&nbsp;</td>
		<td width="13%">共 ${dataTotal}条记录
		<td width="6%">
			<a id="fir" class="sye">首&nbsp;&nbsp;页</a>
		</td>
		<td width="6%">
			<a id="pre" class="sye">上一页</a>
		</td>
		<td width="6%">
			<a id="next" class="sye">下一页</a>
		</td>
		<td width="6%">
			<a id="last" class="sye">末&nbsp;&nbsp;页</a>
		</td>
		<td width="12%">当前第<span style="color:red;">${pageNum}</span>/${pageCount}页</td>
	</tr>
</table>

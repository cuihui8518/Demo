<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tools</title>
</head>
<body>

	<form action="genarate" method="post">

		<div  class="dialog-left"
			style="width: 49%; display: inline-block; border: 1px solid #ddd;height:800px; padding: 2px;background: rgb(205, 206, 207)">
			<div>
				<p>生成set get方法，多个以回车分开，格式如：</p>
				<p>private Integer tenantId;//租户ID<br>private String wayBillNo;//运单号</p>
				静态实体<input type="radio" value="1" name="entityType" checked="checked"> 动态实体<input type="radio" value="2" name="entityType">
				<input type="submit" value="生成代码">
			</div>
			<textarea rows="40" cols="60" id="fields" name="fields"></textarea>
			
		</div>
		<div class="dialog-right"
			style="width: 49%;  display: inline-block; border: 1px solid #ddd; overflow:auto;height:800px; padding: 2px; vertical-align: top;
			background: rgb(197, 197, 197)">
			<div>
				<%
					if (session.getAttribute("result") != null) {
						String result = (String) session.getAttribute("result");
				%>
				<p><%=result%></p>
				<%
					}
				%>
			</div>
		</div>
		
				
		<%
			if (session.getAttribute("errorInfo") != null) {
				String errorInfo = (String) session.getAttribute("errorInfo");
		%>
		<script type="text/javascript">
			alert('<%=errorInfo%>');
		</script>
		<%
			session.setAttribute("errorInfo",null);
			}
		%>
				
	</form>
</body>
</html>
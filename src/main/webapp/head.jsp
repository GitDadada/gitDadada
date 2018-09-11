<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
 	<head>
 	 <meta charset="utf-8">
  	 <link href="/news/css/1.css" rel="stylesheet" type="text/css">
  	 <script type="text/javascript"> 
  	 	function search(){
  	 		document.getElementById('searchForm').submit();
  	 	}	
  	 </script>
  	</head>
  <body>
	  <form id="searchForm" action="/news/servlet/NewsServlet?type1=search">
		 <div class="div-out">
		 	<div class="logleft">
		 		<img src="/news/images/log.png">
		 	</div>
		 	<div class="logMiddle">
				<div class="logMiddleInner">			
					
				</div> 	
		 	</div>
			<div class="logRight">
				<div class="logRightInner">	
					<!-- 当已经登录了，就可以点击 -->
					<c:if test="${!(empty sessionScope.user) }">
						<a href="/news/user/manageUIMain/manageMain.jsp">管理</a>&nbsp;
					</c:if>
				
					<a href="/news/index.jsp">首页</a>&nbsp;
					<!-- 当没有登录，就可以点击 注册登录，否则显示注销-->
					<c:choose>
						<c:when test="${empty sessionScope.user}">
							<a href="/news/user/free/login.jsp">登录</a>
							&nbsp;<a href="/news/user/free/register.jsp">注册</a>
					    </c:when>
					    <c:otherwise>
					    	${sessionScope.user.name}&nbsp;
					    	<a href="/news/servlet/UserServlet?type1=exit">注销</a>
					    </c:otherwise>
					</c:choose>		
				</div> 	
		 	</div>
		</div>
		<div class="clear"></div>
	</form>	   
  </body> 
</html>

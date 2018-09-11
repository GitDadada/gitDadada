<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/myTagLib" prefix="myTag"%>

<!doctype html>
<html>
 <head><link href="/news/css/1.css" rel="stylesheet" type="text/css"></head>
   <body>
	<div class="news">
		<!-- 遍历新闻类型 -->
	   <c:forEach items="${requestScope.newsTypes}"  var="newsType" varStatus="newsTypeStatus">	
			<div class="newsleft" name="news1">
				<table class="invisibleTable">
					<tbody>
						<tr class="newsColumn">
							<td>
								<!-- 选择标签，当类型为all时显示"最新",否则显示各自的类型名称 -->
								<c:choose>
								    <c:when test="${newsType == 'all'}">
										最新
									</c:when>
								    <c:otherwise>
								        ${newsType}
								    </c:otherwise>
								</c:choose>
								
							</td>
							<td align="right">
								<!-- 点击更多时，根据特定新闻类型找到更多的新闻-->
								<!-- 
								servlet:NewsServlet 
								传递参数:
								type1=showNewsByNewsType, 
								newsType=${newsType},
								page=1,
								pageSize=5
								-->
								<a href="/news/servlet/NewsServlet?type1=showNewsByNewsType&newsType=${newsType}&page=1&pageSize=5">更多</a>
							</td>
						</tr>
						
								<!-- 
									精简标题 
									超链接到详细内容
								-->
						<c:forEach items="${requestScope.newsesList[newsTypeStatus.index]}"  var="news" varStatus="status">
							<tr>
								<td class="mainPageUl">
									<a href="/news/servlet/NewsServlet?type1=showANews&newsId=${news.newsId}&page=1&pageSize=2"
											title="${news.caption}">
										${requestScope.newsCaptionsList[newsTypeStatus.index].get(status.index)}
									</a>
								</td>
								<td align="right" width="130">
									<myTag:LocalDateTimeTag dateTime="${news.newsTime}" type="YMD"/>
								</td>
							</tr>
						</c:forEach>
						
					</tbody>
				
				</table>
			</div>
       </c:forEach>
	</div> 
	 
	<form>
		<input type="hidden" id="newsTypeNumber" value="${requestScope.newsTypesNumber}">
	</form>	
	
  </body>
  	<script type="text/javascript">
  		//获取新闻分区数目，对半分为左右两部分，左右两部分用不同的css样式newsLeft和newsRight
 		a=document.getElementById('newsTypeNumber');
 		var newsTypeNumber=parseInt(a.value);
 		var divs=document.getElementsByName("news1");
 		for(var i=0;i<divs.length;i++){
 			if(i%2==1)
 				divs[i].setAttribute("class", "newsRight");
 		} 		
 	</script>    
   
 </html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!doctype html>
<html>
 <head>
 	<meta charset="utf-8">
 	<link href="/news/css/1.css" rel="stylesheet" type="text/css">
 	<script type="text/javascript">
 		//输入框失去焦点时判断
		function valName(){
			//正则表达式
			var pattern = new RegExp("^[a-z]([a-z0-9])*[-_]?([a-z0-9]+)$","i");//创建模式对象
			var str1=document.getElementById("username").value;//获取文本框的内容
			//若输入为空,则在提示框中加入“*不能为空”提示
			if(document.getElementById("username").value==null || document.getElementById("username").value==""){
				document.getElementById("namespan").innerHTML="*不能为空";
				return false;
			}else
				return true;
		}
		//输入框失去焦点时判断
		function valPassword(){
			var str = document.getElementById("password").value;
			//若输入为空,则在提示框中加入“*不能为空”提示
			if(document.getElementById("password").value==null || document.getElementById("password").value==""){
				document.getElementById("passwordspan").innerHTML="*不能为空";
				return false;
			}else
				return true;
		}
			
		function submit1(){
			//若用户名和密码都输入正确则允许提交
			result1=valName();
			result1=valPassword() && result1;		
			if( result1)
				return true;//提交
			else 
				return false;//阻止提交
		}
		
	</script>
</head>

<body>

<form action="/news/servlet/UserServlet?type1=login" method="post" onsubmit="return submit1()">
	<div class="center" style="width:400px;margin-top:40px">
		<table  height="121" border="0" align="center">
			<tbody>
				<tr height="30">
					<td></td><td>登录</td>
				</tr>			
				<tr height="30">
					<td align="right">用户名：</td>
					<td align="left"><input type="text" name="name" id="name" onBlur="valName()"><span id="namespan"></span></td>
				</tr>
				<tr height="30">
					<td align="right">密码：</td>
					<td align="left"><input type="password" name="password" id="password" onBlur="valPassword()"><span id="passwordspan"></span></td>
				</tr>
				<tr height="30">
					<td></td><td><input type="submit" value="    登  录    "/></td>
				</tr> 
			</tbody>
		</table>
	</div>
</form>

  </body>
</html>

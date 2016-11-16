<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天翼智慧员工管理中心</title>
<link href="<%=request.getContextPath() %>/common/css/style.css" rel="stylesheet" type="text/css" />
<script  src="<%=request.getContextPath() %>/common/js/jquery.js"></script>
<script src="<%=request.getContextPath() %>/common/js/cloud.js" type="text/javascript"></script>
<script >
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })
});  
</script>
</head>
<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
	<div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  
<div class="logintop">    
    <span>欢迎员工信息管理系统</span>    
    <ul>
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/main">回首页</a></li>
    </ul>    
    </div>
    <div class="loginbody">
    <span class="systemlogo"></span> 
    <div class="loginbox">
   
    
    <sf:form method="post" modelAttribute="basicUser">
    <ul>
    <li><sf:input path="name" type="text" class="loginuser" value="用户名" onclick="JavaScript:this.value=''"/></li>
    <li><sf:input path="password" type="text" class="loginpwd" value="密码" onclick="JavaScript:this.value=''"/></li>
    <li><input type="submit" class="loginbtn" value="登录" />
<!--     <label><input name="" type="checkbox" value="" checked="checked" />记住密码</label> -->
    <label><a href="javascript:void(0)" onclick="javascript:alert('请联系公司网站管理员修改密码哦！')">忘记密码？</a></label>
    <label><i   style="color:red" class="tips">${error}</i></label></li>
    </ul>
    </sf:form>
    
    </div>
    </div>
    <div class="loginbm">版权所有  2015  <a href="<%=request.getContextPath() %>/leave/basicuser/login">杭州天翼智慧城市科技有限公司</a></div>  
</body>
</html>

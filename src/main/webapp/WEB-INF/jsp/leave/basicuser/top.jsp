<%@page import="com.hesc.leave.pojo.BasicUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天翼智慧员工管理系统</title>
<link href="<%=request.getContextPath() %>/common/css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath() %>/common/js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	});
});
</script>
</head>
<body style="background:url(<%=request.getContextPath() %>/common/images/topbg.gif) repeat-x;">
	<div class="topleft">
    <a href="<%=request.getContextPath() %>/leave/basicuser/main" target="_parent"><img src="<%=request.getContextPath() %>/common/images/logo.png" title="天翼智慧" /></a>
    </div>
    <ul class="nav">
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/index" target="rightFrame" class="selected"><img src="<%=request.getContextPath() %>/common/images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
    <li><a href="<%=request.getContextPath() %>/leave/basicleave/list" target="rightFrame"><img src="<%=request.getContextPath() %>/common/images/icon02.png" title="请假" /><h2>请假</h2></a></li>
    <li><a href="<%=request.getContextPath() %>/leave/basicovertime/list"  target="rightFrame"><img src="<%=request.getContextPath() %>/common/images/icon03.png" title="加班" /><h2>加班</h2></a></li>
    <li><a href="<%=request.getContextPath() %>/leave/basicoffduty/list"  target="rightFrame"><img src="<%=request.getContextPath() %>/common/images/icon04.png" title="调休" /><h2>调休</h2></a></li>
<%--     <li><a href="<%=request.getContextPath() %>/leave/basicmonthly/list" target="rightFrame"><img src="<%=request.getContextPath() %>/common/images/icon05.png" title="月报" /><h2>月报</h2></a></li> --%>
    <%
    BasicUser basicUser=(BasicUser)request.getAttribute("basicUser");
    if(basicUser.getPosition().equals("组员")){
    }else{	%>
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/${basicUser.id }/caudit" target="rightFrame"><img src="<%=request.getContextPath() %>/common/images/icon01.png" title="请假" /><h2>审核</h2></a></li>
    	<%
    } %>
    
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/${basicUser.id }/update"  target="rightFrame"><img src="<%=request.getContextPath() %>/common/images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/index" target="rightFrame" >系统时间:${now}</a></li>
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/login" target="_parent">退出</a></li>
    </ul>
    <div class="user">
    <span>${basicUser.name }</span>
<!--     <i>消息</i> -->
<!--     <b>5</b> -->
    </div>
    </div>
</body>
</html>

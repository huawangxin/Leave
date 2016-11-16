<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page import="com.hesc.leave.pojo.BasicUser"%>
<!DOCTYPE>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天翼智慧员工管理系统</title>
<link href="<%=request.getContextPath() %>/common/css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath() %>/common/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
});
</script>
</head>
<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>快速入口</div>
    <dl class="leftmenu">
    <dd>
    <div class="title"><a href="<%=request.getContextPath() %>/leave/basicuser/list" target="rightFrame">
    <span><img src="<%=request.getContextPath() %>/common/images/leftico01.png" /></span>成员列表
    </a></div>
    	<ul class="menuson">
        <li class="active"><cite></cite><a href="<%=request.getContextPath() %>/leave/basicuser/list" target="rightFrame">查看全部员工</a><i></i></li>
        <li><cite></cite><a href="<%=request.getContextPath() %>/leave/basicuser/list_only" target="rightFrame">查看小组员工</a><i></i></li>
        <%
    BasicUser basicUser=(BasicUser)request.getAttribute("basicUser");
    if(basicUser.getPosition().equals("组长")||basicUser.getPosition().equals("部门经理")){
    	%>
       <li><cite></cite><a href="<%=request.getContextPath() %>/leave/basicuser/add" target="rightFrame">添加成员</a><i></i></li>
        <%
    }else{	
    } %>
        
        </ul>
    </dd>
    <dd>
    <div class="title"><a href="<%=request.getContextPath() %>/leave/basicleave/list" target="rightFrame">
    <span><img src="<%=request.getContextPath() %>/common/images/leftico02.png" /></span>请假管理
    </a></div>
    <ul class="menuson">
        <li><cite></cite><a href="<%=request.getContextPath() %>/leave/basicleave/add" target="rightFrame">添加请假</a><i></i></li>
        <li><cite></cite><a href="<%=request.getContextPath() %>/leave/basicleave/list" target="rightFrame">请假列表</a><i></i></li>
        </ul>     
    </dd>
    <dd><div class="title"><a href="<%=request.getContextPath() %>/leave/basicovertime/list"  target="rightFrame">
    <span><img src="<%=request.getContextPath() %>/common/images/leftico03.png" /></span>加班管理</a></div>
    <ul class="menuson">
        <li><cite></cite><a href="<%=request.getContextPath() %>/leave/basicovertime/add" target="rightFrame">申请加班</a><i></i></li>
        <li><cite></cite><a href="<%=request.getContextPath() %>/leave/basicovertime/list" target="rightFrame">加班列表</a><i></i></li>
    </ul>    
    </dd>
    <dd><div class="title"><a href="<%=request.getContextPath() %>/leave/basicoffduty/list"  target="rightFrame">
    <span><img src="<%=request.getContextPath() %>/common/images/leftico04.png" /></span>调休管理</a></div>
    <ul class="menuson">
        <li><cite></cite><a href="<%=request.getContextPath() %>/leave/basicoffduty/add"  target="rightFrame">申请调休</a><i></i></li>
        <li><cite></cite><a href="<%=request.getContextPath() %>/leave/basicoffduty/list"  target="rightFrame">调休列表</a><i></i></li>
    </ul>
    </dd>
     <%
    if(basicUser.getPosition().equals("组长")||basicUser.getPosition().equals("部门经理")){
    	%>
    <dd><div class="title"><a href="<%=request.getContextPath() %>/leave/basicuser/${basicUser.id }/caudit"  target="rightFrame">
    <span><img src="<%=request.getContextPath() %>/common/images/leftico03.png" /></span>审核管理</a></div>
    <ul class="menuson">
        <li><cite></cite><a href="<%=request.getContextPath() %>/leave/basicleave/${basicUser.id }/caudit" target="rightFrame">请假审核</a><i></i></li>
        <li><cite></cite><a href="<%=request.getContextPath() %>/leave/basicovertime/${basicUser.id }/caudit" target="rightFrame">加班审核</a><i></i></li>
    	<li><cite></cite><a href="<%=request.getContextPath() %>/leave/basicoffduty/${basicUser.id }/caudit" target="rightFrame">调休审核</a><i></i></li>
    </ul>    
    </dd>
    
    <dd><div class="title"><a href="<%=request.getContextPath() %>/leave/basicmonthly/${basicUser.id }/caudit"  target="rightFrame">
    <span><img src="<%=request.getContextPath() %>/common/images/leftico03.png" /></span>月报管理</a></div>
    </dd>
    <%
    }else{	
    } %>
    </dl>
</body>
</html>

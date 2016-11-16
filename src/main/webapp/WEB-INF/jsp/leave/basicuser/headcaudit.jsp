<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天翼智慧员工管理系统</title>
<link href="<%=request.getContextPath() %>/common/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery.js"></script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/${basicUser.id }/caudit">审核</a></li>
    </ul>
    </div>
    <div class="mainindex">
    <div class="xline"></div>
    <ul class="iconlist">
    <li><img src="<%=request.getContextPath() %>/common/images/ico01.png" /><p><a href="<%=request.getContextPath() %>/leave/basicleave/${basicUser.id }/caudit">请假审核</a></p></li>
    <li><img src="<%=request.getContextPath() %>/common/images/ico02.png" /><p><a href="<%=request.getContextPath() %>/leave/basicovertime/${basicUser.id }/caudit">加班审核</a></p></li>
    <li><img src="<%=request.getContextPath() %>/common/images/ico03.png" /><p><a href="<%=request.getContextPath() %>/leave/basicoffduty/${basicUser.id }/caudit">调休审核</a></p></li>
    <li><img src="<%=request.getContextPath() %>/common/images/ico05.png" /><p><a href="<%=request.getContextPath() %>/leave/basicmonthly/${basicUser.id }/caudit">月报审核</a></p></li>
    </ul>
    <div class="xline"></div>
    <div class="box"></div>
    <div class="welinfo">
    <span><img src="<%=request.getContextPath() %>/common/images/dp.png" alt="提醒" /></span>
    <b>天翼智慧员工管理系统使用指南</b>
    </div>
    <ul class="infolist">
    <li><span>您可以快速进行新员工添加管理操作</span><a class="ibtn" href="<%=request.getContextPath() %>/leave/basicuser/add">添加新员工</a></li>
    </ul>
    </div>
</body>
</html>

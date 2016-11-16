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
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/index">首页</a></li>
    </ul>
    </div>
    <div class="mainindex">
    <div class="welinfo">
    <span><img src="<%=request.getContextPath() %>/common/images/sun.png" alt="天气" /></span>
    <b>${basicUser.name }  早上好，欢迎使用杭州天翼智慧员工管理系统</b>
    <a href="<%=request.getContextPath() %>/leave/basicuser/${basicUser.id }/update">帐号设置</a>
    </div>
    <div class="xline"></div>
    <div class="box"></div>
    <div class="welinfo">
    <span><img src="<%=request.getContextPath() %>/common/images/dp.png" alt="提醒" /></span>
    <b>天翼智慧员工管理系统使用指南</b>
    </div>
    <ul class="infolist">
    <li><span>您可以快速进行申请请假</span><a href="<%=request.getContextPath() %>/leave/basicleave/add" class="ibtn">添加请假</a></li>
    <li><span>您可以快速进行申请加班</span><a href="<%=request.getContextPath() %>/leave/basicovertime/add"  class="ibtn">申请加班</a></li>
    <li><span>您可以快速进行申请调休</span><a href="<%=request.getContextPath() %>/leave/basicoffduty/add"  class="ibtn">申请调休</a></li>
    </ul>
    </div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天翼智慧员工管理系统</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/common/easyui/jquery-1.7.2.min.js"></script>   
<script type="text/javascript" src="<%=request.getContextPath() %>/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/common/easyui/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/common/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/common/easyui/icon.css">      
<link href="<%=request.getContextPath() %>/common/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"  type="text/css"  href="<%=request.getContextPath() %>/common/css/index.css"/>
</head>

<body onload="onload()">

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/index">首页</a></li>
    <li><a href="javascript:history.back(-1);">调休记录列表</a></li>
    <li><a>查看调休</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>调休信息</span></div>
<form>
    <ul class="forminfo">
    <li><label>姓名</label>
    <label class="dfinput" style="width:345px;height:30px">${basicOffduty.name }</label><br/>
     </li>
    <li><label>部门</label>
    <label class="dfinput" style="width:345px;height:30px">${basicOffduty.department }</label><br/>
    </li>
    <li><label>调休事由</label>
    <label class="dfinput" style="width:345px;height:30px">${basicOffduty.reason }</label><br/>
    </li>
    <li><label>调休时间</label>
    <label class="dfinput" style="width:345px;height:30px">${basicOffduty.ovetimeStart } 到  ${basicOffduty.ovetimeOver }    共计  ${basicOffduty.overtime }天</label><br/>
    </li>
    <li><label>审核状态</label>
    	<cite>
    		<input type="radio"checked="checked" />
    		<c:choose>
    			<c:when test="${basicOffduty.state=='1'}">未审核</c:when>
    			<c:when test="${basicOffduty.state=='2'}">审核中</c:when>
    			<c:when test="${basicOffduty.state=='3'}">审核通过</c:when>
    			<c:otherwise>不通过</c:otherwise>
    		</c:choose>
    	</cite>
    </li>
    <li>
    <input type="button" class="btn" value="返回" onclick="javascript:history.back(-1);"/>
    <li><label>&nbsp;</label>调休时间说明:满24小时算一天，精确到0.5天，小时数不超过4的不按天算，不超过8小时的按0.5天算，超过8小时按1天算。</li>
    </ul>
</form>
    </div>
</body>
</html>

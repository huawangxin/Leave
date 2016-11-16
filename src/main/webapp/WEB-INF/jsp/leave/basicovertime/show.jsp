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
    <li><a href="javascript:history.back(-1);">加班记录列表</a></li>
    <li><a>查看加班</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>加班信息</span></div>
<form>
    <ul class="forminfo">
    <li><label>姓名</label>
    <label class="dfinput" style="width:345px;height:30px">${basicOvertime.name }</label><br/>
     </li>
    <li><label>部门</label>
    <label class="dfinput" style="width:345px;height:30px">${basicOvertime.department }</label><br/>
    </li>
    <li><label>加班事由</label>
    	<cite>
    		<input type="radio"checked="checked" />  ${basicOvertime.reason }
    	</cite>
    </li>
    <li><label>加班时间</label>
    <label class="dfinput" style="width:345px;height:30px">${basicOvertime.ovetimeStart } 到  ${basicOvertime.ovetimeOver }    共计  ${basicOvertime.overtime }天</label><br/>
    </li>
    <li><label>加班地点</label>
    <label class="dfinput" style="width:345px;height:30px">${basicOvertime.place }</label><br/>
    </li>
    <li><label>审核状态</label>
    	<cite>
    		<input type="radio"checked="checked" />
    		<c:choose>
    			<c:when test="${basicOvertime.state=='1'}">未审核</c:when>
    			<c:when test="${basicOvertime.state=='2'}">审核中</c:when>
    			<c:when test="${basicOvertime.state=='3'}">审核通过</c:when>
    			<c:otherwise>不通过</c:otherwise>
    		</c:choose>
    	</cite>
    </li>
    <li>
    <input type="button" class="btn" value="返回" onclick="javascript:history.back(-1);"/>
    <li><label>&nbsp;</label>加班时间说明:满8小时算一天，精确到0.5天，小时数不超过4的按 0.5天算，超过4小时的按1天算。</li>
    </ul>
</form>
    </div>
</body>
</html>

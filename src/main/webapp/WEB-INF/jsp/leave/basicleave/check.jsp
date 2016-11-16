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
    <li><a href="<%=request.getContextPath() %>/leave/basicleave/${basicUser.id }/caudit">请假记录列表</a></li>
    <li><a>审核请假</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>请假信息</span></div>
<form id="form2" action="<%=request.getContextPath() %>/leave/basicleave/${basicLeave.id}/check" method="post">
    <ul class="forminfo">
    <li><label>姓名</label>
    <label class="dfinput" style="width:345px;height:30px">${basicLeave.name }</label><br/>
     </li>
    <li><label>部门</label>
    <label class="dfinput" style="width:345px;height:30px">${basicLeave.department }</label><br/>
    </li>
    <li><label>请假类型</label>
    	<cite>
    		<input type="radio"checked="checked" />${basicLeave.type }
    	</cite>
    </li>
    <li><label>请假时间</label>
    <label class="dfinput" style="width:345px;height:30px">${basicLeave.leaveStart } 到  ${basicLeave.leaveOver }    共计  ${basicLeave.leaveTime }天</label><br/>
    </li>
    <li><label>请假说明</label>
    <label class="dfinput" style="width:345px;height:30px">${basicLeave.description }</label><br/>
    </li>
    <li><label>审核操作</label>
    	<cite>
    		<input name="state" type="radio" value="${basicLeave.state }" checked="checked" />同意&nbsp;&nbsp;&nbsp;&nbsp;
    		<input name="state" type="radio" value="0"/>不同意
    	</cite>
    </li>
    <li>
<!--     <input type="button" class="btn" value="返回" onclick="javascript:history.back(-1);"/> -->
    <input type="button" class="btn" value="确认保存" onclick="checkLeave();"/></li>
    <li><label>&nbsp;</label>请假时间说明:精确到0.5天，小时数不超过12的按 0.5天算，超过12小时的按1天算。</li>
    </ul>
<script type="text/javascript">
function checkLeave(){
    document.getElementById('form2').submit();
}
</script>
</form>
    </div>
</body>
</html>

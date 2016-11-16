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
<link rel="stylesheet"  type="text/css"  href="<%=request.getContextPath() %>/common/css/index.css"/>
<link href="<%=request.getContextPath() %>/common/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body onload="onload()">

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/index">首页</a></li>
    <li><a href="<%=request.getContextPath() %>/leave/basicovertime/${basicUser.id }/caudit">请假记录列表</a></li>
    <li><a>审核请假</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>请假信息</span></div>
<form id="form2" action="<%=request.getContextPath() %>/leave/basicovertime/${basicOvertime.id}/check" method="post">
    <ul class="forminfo">
    <li><label>姓名</label>
    <label class="dfinput" style="width:345px;height:30px">${basicOvertime.name }</label><br/>
     </li>
    <li><label>部门</label>
    <label class="dfinput" style="width:345px;height:30px">${basicOvertime.department }</label><br/>
    </li>
     <li><label>加班事由</label>
    <label class="dfinput" style="width:345px;height:30px">${basicOvertime.reason }</label><br/>
    </li>
    <li><label>加班时间</label>
    <input type="hidden" name="name" value="${basicOvertime.name }"/>
    <input type="hidden" name="overtime" value="${basicOvertime.overtime }"/>
    <label class="dfinput" style="width:345px;height:30px">${basicOvertime.ovetimeStart } 到  ${basicOvertime.ovetimeOver }    共计  ${basicOvertime.overtime }天</label><br/>
    </li>
    <li><label>加班地点</label>
    <label class="dfinput" style="width:345px;height:30px">${basicOvertime.place }</label><br/>
    </li>
    <li><label>审核操作</label>
    	<cite>
    		<input name="state" type="radio" value="${basicOvertime.state }" checked="checked" />同意&nbsp;&nbsp;&nbsp;&nbsp;
    		<input name="state" type="radio" value="0"/>不同意
    	</cite>
    </li>
    <li>
    <input type="button" class="btn" value="确认保存" onclick="checkLeave();"/></li>
    <li><label>&nbsp;</label>加班时间说明:满8小时算一天，精确到0.5天，小时数不超过4的按 0.5天算，超过4小时的按1天算。</li>
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

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天翼智慧员工管理系统</title>
<link href="<%=request.getContextPath() %>/common/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/index">首页</a></li>
    <li><a>系统设置</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div class="formtitle"><span>基本信息</span></div>
    <sf:form id="form1" method="post" modelAttribute="basicUser_all">
    <ul class="forminfo">
    <li><label>用户名</label>
    <label class="dfinput" style="width:345px;height:30px">${basicUser_all.name }</label><br/><br/>
    </li>
    <li><label>部    门</label>
    <label class="dfinput" style="width:345px;height:30px">${basicUser_all.department }</label><br/><br/>
    </li>
    <li><label>职    位</label>
    <label class="dfinput" style="width:345px;height:30px">${basicUser_all.position }</label><br/><br/>
    </li>
    <li><label>可调休时间</label>
    <label class="dfinput" style="width:345px;height:30px">${basicUser_all.remainTime } 天</label><br/><br/>
    </li>
    <li><label>密    码</label><sf:input path="password" type="text" class="dfinput"  style="width:345px;height:30px" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"/><i>请输入不少于6位字符的密码</i></li>
    <li><label>联系电话</label><sf:input path="tel" class="dfinput" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"/><i>请输入11位手机号</i><i id="divtel" style="color:red" class="tips"></i></li>
    <li><label>&nbsp;</label>
    <input type="button" class="btn" value="返回" onclick="javascript:history.back(-1);"/>
    <input type="button" class="btn" value="确认保存" onclick="checkUser();"/></li>
    </ul>
    <sf:hidden path="id"/>
    <sf:hidden path="name"/>
    <sf:hidden path="department"/>
    <sf:hidden path="position"/>
    <sf:hidden path="remainTime"/>
    <sf:hidden path="state"/>
    <sf:hidden path="sort"/>
    <sf:hidden path="createTime"/>
    </sf:form>
    </div>
<script type="text/javascript">
function checkUser(){
	   var password = document.getElementById("password").value;
	   if(password.length<6||password.length>30){
	     alert("密码格式不对 ");
	     return false;
	   }
	  document.getElementById("form1").submit();
}
</script>
</body>
</html>

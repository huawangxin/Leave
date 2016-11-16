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
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/${basicUser.id }/update">系统设置</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div class="formtitle"><span>基本信息</span></div>
    <sf:form id="form1" method="post" modelAttribute="basicUser_new"  onsubmit="return checkUser();">
    <ul class="forminfo">
    <li><label>用户名</label><sf:input path="name" class="dfinput" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"/><i>请输入真实姓名</i><i id="divname" style="color:red" class="tips"></i></li>
    <li><label>密    码</label><sf:password path="password" class="dfinput" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"/><i>请输入不少于6位字符的密码</i><i id="divpassword" style="color:red" class="tips"></i></li>
    <li><label>部    门</label><sf:select path="department" class="dfinput">
    					   <option value="研发一组">研发一组</option>
    					   <option value="研发二组">研发二组</option>
    					   <option value="研发三组">研发三组</option>
    					   </sf:select><i id="divdepartment" style="color:red" class="tips"></i></li>
    <li><label>职    位</label><sf:select path="position" class="dfinput">
    					   <option value="组员">组员</option>
    					   <option value="组长">组长</option>
    					   <option value="部门经理">部门经理</option>
    					   </sf:select><i id="divposition" style="color:red" class="tips"></i></li>
    <li><label>联系电话</label><sf:input path="tel" class="dfinput" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"/><i>请输入11位手机号</i><i id="divtel" style="color:red" class="tips"></i></li>
    <li><label>&nbsp;</label>
    <input type="button" class="btn" value="返回" onclick="javascript:history.back(-1);"/>
    <input type="submit" class="btn" value="确认保存" onclick="checkUser();"/></li>
    </ul>
    </sf:form>
    </div>
<script type="text/javascript">
function checkUser(){
	var name = document.getElementById("name").value;
	var password = document.getElementById("password").value;
	var department = document.getElementById("department").value;
	var position = document.getElementById("position").value;
	var tel = document.getElementById("tel").value;
	if(name.length<1||name.length>30){
		divname.innerHTML='<font class="tips_false">请输入正确的用户名 </font>';
		return false;
	}else{
		divname.innerHTML='<font class="tips_true"></font>';
	}
	if(password.length<6||password.length>30){
		divpassword.innerHTML='<font class="tips_false">请输入6~30个字符的密码  </font>';
		return false;
	}else{
		divpassword.innerHTML='<font class="tips_true"></font>';
	}
	if(department.length<0){
		divdepartment.innerHTML='<font class="tips_false">请选择所在部门   </font>';
		return false;
	}else{
		divdepartment.innerHTML='<font class="tips_true"></font>';
	}
	if(position.length<0){
		divposition.innerHTML='<font class="tips_false">请选择职位    </font>';
		return false;
	}else{
		divposition.innerHTML='<font class="tips_true"></font>';
	}
	if(tel.length!=11){
		divtel.innerHTML='<font class="tips_false">请11位手机号码   </font>';
		return false;
	}else{
		divtel.innerHTML='<font class="tips_true"></font>';
	}
	return true;
}
</script>
</body>
</html>
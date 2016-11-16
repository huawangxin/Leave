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
    <li><a href="<%=request.getContextPath() %>/leave/basicleave/list">请假记录列表</a></li>
    <li><a>修改请假</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>请假信息</span></div>
<form id="form2" action="<%=request.getContextPath() %>/leave/basicleave/${basicLeave.id}/update" method="post">
    <ul class="forminfo">
    <li><label>姓名</label>
    <input type="hidden" name="id" value="${basicLeave.id }"/>
    <input type="hidden" name="leaveTime" value="${basicLeave.leaveTime }"/>
    <label class="dfinput" style="width:345px;height:30px">${basicLeave.name }</label><br/>
     </li>
    <li><label>部门</label>
    <select name="department"  class="dfinput">
           <option value="${basicLeave.department }">${basicLeave.department }</option>
           <option value="研发一组">研发一组</option>
           <option value="研发二组">研发二组</option>
           <option value="研发三组">研发三组</option>
    </select>
    <i class="tips" id="divdepartment"></i>
    </li>
    <li><label>请假类型</label>
    	<cite>
    		<input name="type" type="radio" value="事假" checked="checked" />事假&nbsp;&nbsp;&nbsp;&nbsp;
    		<input name="type" type="radio" value="公假"/>公假&nbsp;&nbsp;&nbsp;&nbsp;
    		<input name="type" type="radio" value="病假"/>病假&nbsp;&nbsp;&nbsp;&nbsp;
    		<input name="type" type="radio" value="其他(请说明)"/>其他(请说明)
    	</cite>
    </li>
    <li><label>请假时间</label>
    <input name="leaveStart"  value="${basicLeave.leaveStart }"  id="dt"  class="easyui-datetimebox"  editable="false" 
    data-options="formatter:ww4,parser:w4" style="width:200px;"/>
   到<input name="leaveOver"  value="${basicLeave.leaveOver }"  id="dt1"   class="easyui-datetimebox"  editable="false" 
   data-options="formatter:ww4,parser:w4" style="width:200px;"/>
<!--     一共<input name="leaveTime" value="" type="text"/>天 -->
	<i class="tips" id="leaveTime"></i>
    </li>
    <li><label>请假说明</label><textarea name="description" id="description" cols="" rows="" class="textinput"></textarea></li>
    <li>
    <input type="button" class="btn" value="返回" onclick="javascript:history.back(-1);"/>
    <input type="button" class="btn" value="确认保存" onclick="checkLeave();"/></li>
    <li><label>&nbsp;</label>请假时间说明:精确到0.5天，小时数不超过12的按 0.5天算，超过12小时的按1天算。</li>
    </ul>
<script>
//将textarea中的值显示出来
var description=document.getElementById('description');
description.innerHTML="${basicLeave.description }";
</script>
<script type="text/javascript">
//页面加载 
function onload(){
	/**请假类型单选框  **/
	var type="${basicLeave.type}";
	$("input[name='type'][value="+type+"]").attr("checked",true);
}
//时间 
function ww4(date){
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    var h = date.getHours();  
    return  y+'年'+(m<10?('0'+m):m)+'月'+(d<10?('0'+d):d)+'日'+(h<10?('0'+h):h)+'点';  
      
}  
function w4(s){
    var reg=/[\u4e00-\u9fa5]/;  //利用正则表达式分隔  
    var ss = (s.split(reg));  
    var y = parseInt(ss[0],10);  
    var m = parseInt(ss[1],10);  
    var d = parseInt(ss[2],10);  
    var h = parseInt(ss[3],10);  
    if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h)){  
        return new Date(y,m-1,d,h);  
    } else {  
        return new Date();  
    }  
}
function checkLeave(){
	//验证部门
	var department=form2.department.value;
	if(name.length<1||name.length>30){  	
		divdepartment.innerHTML='<font class="tips_false">请重新选择部门  </font>';
		return false;
	}else{
		divdepartment.innerHTML='<font class="tips_true"></font>';
	}
	//验证请假开始时间  
	var leaveStart=form2.leaveStart.value;
	var leaveOver=form2.leaveOver.value;
	if(leaveStart.length<1||leaveStart.length>200){  	
		leaveTime.innerHTML='<font class="tips_false">请选择请假开始时间  </font>';
		return false;
	}else if(leaveOver.length<1||leaveOver.length>200){  	
		leaveTime.innerHTML='<font class="tips_false">请选择请假结束时间  </font>';
		return false;
	}else if(leaveStart==leaveOver){  	
		leaveTime.innerHTML='<font class="tips_false">请选择请假结束时间  </font>';
		return false;
	}else{
		leaveTime.innerHTML='<font class="tips_true"></font>';
	}
	//验证请假说明 
	var description=form2.description.value;
	if(description.length>200){  	
		description.innerHTML='<font class="tips_false">请重新选择部门  </font>';
		return false;
	}else{
		description.innerHTML='<font class="tips_true"></font>';
	}
    document.getElementById('form2').submit();
}

</script>
</form>
    </div>
</body>
</html>

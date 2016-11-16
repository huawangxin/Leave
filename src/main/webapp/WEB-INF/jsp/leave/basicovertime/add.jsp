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

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/index">首页</a></li>
    <li><a href="<%=request.getContextPath() %>/leave/basicovertime/list">加班记录列表</a></li>
    <li><a href="<%=request.getContextPath() %>/leave/basicovertime/add">添加加班</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>加班信息</span></div>
<form id="form2" action="<%=request.getContextPath() %>/leave/basicovertime/add" method="post">
    <ul class="forminfo">
    <li><label>姓名</label>
    <input type="hidden" name="name" value="${basicUser.name }"/>
    <label class="dfinput" style="width:345px;height:30px">${basicUser.name }</label>
     <i class="tips" id="divname"></i></li>
    <li><label>部门</label>
    <select name="department"  class="dfinput">
           <option value="${basicUser.department }">${basicUser.department }</option>
           <option value="研发一组">研发一组</option>
           <option value="研发二组">研发二组</option>
           <option value="研发三组">研发三组</option>
    </select>
    <i class="tips" id="divdepartment"></i>
    </li>
    <li><label>加班地点</label><input name="place" type="text" 
     onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" class="dfinput" />
     <i class="tips" id="divplace"></i>
     </li>
    <li><label>加班时间</label>
    <input name="ovetimeStart"  id="dt"  class="easyui-datetimebox"  editable="false" 
    data-options="formatter:ww4,parser:w4" style="width:200px;"/>
   到<input name="ovetimeOver"  id="dt1"   class="easyui-datetimebox"  editable="false" 
   data-options="formatter:ww4,parser:w4" style="width:200px;"/>
<!--     一共<input name="leaveTime" value="" type="text"/>天 -->
	<i class="tips" id="divoverTime"></i>
    </li>
    <li><label>加班事由</label><textarea name="reason" cols="" rows="" class="textinput"></textarea>
    <i class="tips" id="divreason"></i></li>
    <li>
    <input type="button" class="btn" value="返回" onclick="javascript:history.back(-1);"/>
    <input type="button" class="btn" value="确认保存" onclick="checkOverTime();"/></li>
    <li><label>&nbsp;</label>加班时间说明:满8小时算一天，精确到0.5天，小时数不超过4的按 0.5天算，超过4小时的按1天算。</li>
    </ul>
<script type="text/javascript">
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
function checkOverTime(){
	//验证部门
	var department=form2.department.value;
	if(department.length<1||department.length>30){  	
		divdepartment.innerHTML='<font class="tips_false">请重新选择部门  </font>';
		return false;
	}else if(department.indexOf(",")!=-1){
		divdepartment.innerHTML='<font class="tips_false">只能选择一个部门 </font>';
		return false;
	}else{
		divdepartment.innerHTML='<font class="tips_true"></font>';
	}
	//验证加班时间   
	var oveStart=form2.ovetimeStart.value;
	var oveOver=form2.ovetimeOver.value;
	if(oveStart.length<1||oveStart.length>200){  	
		divoverTime.innerHTML='<font class="tips_false">请选择请假开始时间  </font>';
		return false;
	}else if(oveOver.length<1||oveOver.length>200){  	
		divoverTime.innerHTML='<font class="tips_false">请选择请假结束时间  </font>';
		return false;
	}else if(oveStart==oveOver){  	
		divoverTime.innerHTML='<font class="tips_false">开始时间和结束时间能相同 </font>';
		return false;
	}else{
		divoverTime.innerHTML='<font class="tips_true"></font>';
	}
	//验证加班地点 
	var place=form2.place.value;
	if(place.length>200){  	
		place.innerHTML='<font class="tips_false">输入字符过长  </font>';
		return false;
	}else{
		place.innerHTML='<font class="tips_true"></font>';
	}
	//验证加班事由 
	var reason=form2.reason.value;
	if(reason.length<1||reason.length>200){  	
		divreason.innerHTML='<font class="tips_false">请简要说明加班事由  </font>';
		return false;
	}else{
		divreason.innerHTML='<font class="tips_true"></font>';
	}
    document.getElementById('form2').submit();
}

</script>
</form>
    </div>
</body>
</html>

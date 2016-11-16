<%@page import="javax.mail.Session"%>
<%@page import="com.hesc.leave.pojo.BasicLeave"%>
<%@page import="com.hesc.trundle.db.page.Page"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% Page<BasicLeave> pages=(Page<BasicLeave>)request.getAttribute("page_basicleave"); %>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天翼智慧员工管理系统</title>
<link href="<%=request.getContextPath() %>/common/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/common/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/select-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/common/editor/kindeditor.js"></script>
<link href="<%=request.getContextPath() %>/common/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"  type="text/css"  href="<%=request.getContextPath() %>/common/css/index.css"/>
<script type="text/javascript">
    KE.show({
        id : 'content7',
        cssPath : './index.css'
    });
  </script>
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
</script>
</head>
<body onload="onload()">
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="<%=request.getContextPath() %>/leave/basicleave/listbefore?curPage=1&pageCount=${page_basicleave.pageCount}">请假</a></li>
    <li><a href="<%=request.getContextPath() %>/leave/basicovertime/listbefore?curPage=1&pageCount=${page_basicOvertime.pageCount}">加班</a></li>
    <li><a href="<%=request.getContextPath() %>/leave/basicoffduty/listbefore?curPage=1&pageCount=${page_basicOffduty.pageCount}">调休</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual">
    <div class="itab">
  	<ul> 
    <li><a id="t1" href="#tab1" class="selected">请假月报</a></li>
<!--     <li><a id="t2"  href="#tab2">加班月报</a></li> -->
<!--     <li><a id="t3"  href="#tab3">调休月报</a></li> -->
  	</ul>
    </div> 
    
    <div id="tab1" class="tabson">
 <form id="form2" method="post" action="<%=request.getContextPath() %>/leave/basicleave/search">
    <ul class="seachform">
    <li><label>关键字查询</label><input name="keyword" id="keyword" type="text" class="scinput" /></li>
     <li><label>时间</label>
    <div class="usercity">
    <div class="cityleft">
    <select id="year" name="year" class="select2">
    <option value="">请选择</option>
    <option value="2015">2015年</option>
    <option value="2014">2014年</option>
    <option value="2013">2013年</option>
    <option value="2012">2012年</option>
    </select>
    </div>
    <div class="cityright">
    <select id="month" name="month"class="select2">
    <option value="">请选择</option>
    <option value="1">1月</option>
    <option value="2">2月</option>
    <option value="3">3月</option>
    <option value="4">4月</option>
    <option value="5">5月</option>
    <option value="6">6月</option>
    <option value="7">7月</option>
    <option value="8">8月</option>
    <option value="9">9月</option>
    <option value="10">10月</option>
    <option value="11">11月</option>
    <option value="12">12月</option>
    
    </select>
    </div>
    </div>
    
    </li>
    <li><label>&nbsp;</label><input name="" type="button" class="scbtn" value="查询"
     onclick="checkMonth();"/><br/>
    <i class="tips" id="divtime"></i></li>
    </ul>
 </form>
    <table id="table" class="tablelist">
    	
        
        <c:forEach items="${basicLeaves }" var="basicLeave">
        
        <tr><td>
        <input type="hidden" id="tip" value="${tip }"/>
        ${basicLeave.department}</td>
        <td>${basicLeave.name}</td>
        <td>${basicLeave.leaveStart} 至  ${basicLeave.leaveOver} 共计 ${basicLeave.leaveTime} 天</td>
        <td>${basicLeave.type}</td>
        <td>${basicLeave.description}</td>
        <td><a href="<%=request.getContextPath() %>/leave/basicleave/${basicLeave.id}" class="tablelink">查看</a></td>
        </tr>
        </c:forEach>
        
    </table>
    <%  if(pages.getCount()==0){
   		
   	}else{
   		%>
    <div class="pagin">
    	<div class="message">共<i class="blue">${page_basicleave.count}</i>条记录，当前显示第&nbsp;<i class="blue">${page_basicleave.curPage}&nbsp;</i>页</div>
        <ul class="paginList">
        <li><a href="<%=request.getContextPath() %>/leave/basicleave/listbefore?curPage=1&pageCount=${page_basicleave.pageCount}">首页</a>	<a href="<%=request.getContextPath() %>/leave/basicleave/listbefore?curPage=${page_basicleave.curPage}&pageCount=${page_basicleave.pageCount}">上一页</a>	第 ${page_basicleave.curPage }/${page_basicleave.pageCount==0?1:page_basicleave.pageCount } 页	<a href="<%=request.getContextPath() %>/leave/basicleave/listnext?curPage=${page_basicleave.curPage}&pageCount=${page_basicleave.pageCount}">下一页</a>	<a href="<%=request.getContextPath() %>/leave/basicleave/listnext?curPage=${page_basicleave.pageCount}&pageCount=${page_basicleave.pageCount}">尾页</a></li>
       	<input id="curPage" type="hidden" value="1"/>
       	<input id="totalPage" type="hidden" value="${page_basicleave.pageCount}"/>
       		
            <li> <a  href="javascript:changePage()">上一页</a></li>
        <li> <a  href="javascript:changePage()">下一页</a></li>
        </ul>
        
    </div>
    <%
   	} %>
	<script type="text/javascript">
	$('.imgtable tbody tr:odd').addClass('odd');
	</script>
	</div>
    
    </div>
     
    </div> 
<script type="text/javascript">
function checkMonth(){
	//debugger;
	//判断关键字搜索是否为空 
	var keyword=form2.keyword.value;
	if(keyword.length<1){
		//验证月报的月份 
		var year=form2.year.value;
		var month=form2.month.value;
		if(year.length<1||year.length>30||month.length<1||month.length>30){
			divtime.innerHTML='<font class="tips_false"> 请选择要生成月报的时间 </font>';
			return false;
		}else{
			divtime.innerHTML='<font class="tips_true"></font>';
		}
	}
	document.getElementById('form2').submit();
}
</script>
 	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<script type="text/javascript">
//将图片先上传到服务器 
function changePage()  
    {  
	//debugger;
    	$.ajax({  
                 url:'<%=request.getContextPath() %>/leave/basicleave/listbefore',//需要链接到服务器地址  
                 secureuri:false,  
                 type:"get",
                 data: "curPage=1",//+document.getElementById('curPage').val, 
                
                 success:function (data)              //相当于java中try语句块的用法  
                 {	alert('上传成功 ');
                 	//alert("data:"+data);//console.log(data);
                    if(data!= null){  
                    	//debugger;
                    	//alert( document.getElementById("listSize").val);
                    	 document.getElementById("table").getElementsByTagName("tr")[0].remove();
                    	// alert(document.getElementById("listSize").val);
                    	 document.getElementById("table").getElementsByTagName("tr")[0].remove();
                    	// alert(document.getElementById("listSize").val);
                    	 document.getElementById("table").innerHTML=data;
                    	 //$("#Result tr:gt(0)").remove();
                    	//window.location.href = '<%=request.getContextPath() %>/leave/basicmonthly/caudit_leave';
                    }
                 },
                 error:function()             //相当于java中catch语句块的用法  
                 {  alert('上传图片失败');
                 }
       }); 
}
	
	
	
	</script>
</body>
</html>

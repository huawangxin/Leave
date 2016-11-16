<%@page import="com.hesc.leave.pojo.BasicOvertime"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hesc.trundle.db.page.Page"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% Page<BasicOvertime> pages=(Page<BasicOvertime>)request.getAttribute("page_basicOvertime"); %>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天翼智慧员工管理系统</title>
<link href="<%=request.getContextPath() %>/common/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery.js"></script>
<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})
})
</script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});
});
</script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/index">首页</a></li>
    <li><a href="<%=request.getContextPath() %>/leave/basicovertime/list">加班记录列表</a></li>
    </ul>
    </div>
    <div class="rightinfo">
    <div class="tools">
    	<ul class="toolbar">
        <li class="click"><a href="<%=request.getContextPath() %>/leave/basicovertime/add"><span><img src="<%=request.getContextPath() %>/common/images/t01.png" /></span>添加</a></li>
        </ul>
    </div>
    <table class="imgtable">
    <thead>
    <tr>
    <th width="100px;">缩略图</th>
    <th>加班事由</th>
    <th>加班地点</th>
    <th>加班时长</th>
    <th>申请人</th>
    <th>是否审核</th>
    <th>操作</th>
    </tr>
    </thead>
    
    <tbody>
    <c:forEach items="${basicOvertimes }" var="basicOvertime">
	<tr>
    <td class="imgtd"><img src="<%=request.getContextPath() %>/common/images/img13.png" /></td>
    <td>${basicOvertime.reason}<p>ID:${basicOvertime.id} </p></td>
    <td><a href="${basicOvertime.id}/update">${basicOvertime.place}</a><p>加班时间：${basicOvertime.ovetimeStart} 至 ${basicOvertime.ovetimeOver}</p></td>
    <td>${basicOvertime.overtime} 天</td>
    <td>${basicOvertime.name}</td>
    <td>
    <c:choose>
    <c:when test="${basicOvertime.state=='1' }">
    	<i>未审核</i>
    </c:when>
    <c:when test="${basicOvertime.state=='2' }">
    	<i>审核中</i>
    </c:when>
    <c:when test="${basicOvertime.state=='3' }">
    	审核通过
    </c:when>
    <c:when test="${basicOvertime.state=='0' }">
    	<i>审核不通过</i>
    </c:when>
    <c:otherwise>
    	<i>审核异常</i>
    </c:otherwise>
	</c:choose>
	</td>
    <td>
    <c:choose>
    	<c:when test="${basicOvertime.state=='0' }" ><a href="<%=request.getContextPath() %>/leave/basicovertime/${basicOvertime.id}">查看</a></c:when>
    	<c:when test="${basicOvertime.state=='3' }" ><a href="<%=request.getContextPath() %>/leave/basicovertime/${basicOvertime.id}">查看</a></c:when>
    	<c:otherwise><a href="${basicOvertime.id}/update">修改</a> | <a href="${basicOvertime.id}/delete">删除</a></c:otherwise>
    </c:choose>
    </td>
   </c:forEach> 
   
    </tbody>
    </table>
    <%  if(pages.getCount()==0){
   		
   	}else{
   		%>
    <div class="pagin">
    	<div class="message">共<i class="blue">${page_basicOvertime.count}</i>条记录，当前显示第&nbsp;<i class="blue">${page_basicOvertime.curPage}&nbsp;</i>页</div>
        <ul class="paginList">
        <li><a href="<%=request.getContextPath() %>/leave/basicovertime/listbefore1?curPage=1&pageCount=${page_basicOvertime.pageCount}">首页</a>	<a href="<%=request.getContextPath() %>/leave/basicovertime/listbefore1?curPage=${page_basicOvertime.curPage}&pageCount=${page_basicOvertime.pageCount}">上一页</a>	第 ${page_basicOvertime.curPage }/${page_basicOvertime.pageCount==0?1:page_basicOvertime.pageCount } 页	<a href="<%=request.getContextPath() %>/leave/basicovertime/listnext1?curPage=${page_basicOvertime.curPage}&pageCount=${page_basicOvertime.pageCount}">下一页</a>	<a href="<%=request.getContextPath() %>/leave/basicovertime/listnext1?curPage=${page_basicOvertime.pageCount}&pageCount=${page_basicOvertime.pageCount}">尾页</a></li>
        </ul>
    </div>
     <%
   	} %>
	<script type="text/javascript">
	$('.imgtable tbody tr:odd').addClass('odd');
	</script>
	</div>
</body>
</html>

<%@page import="com.hesc.trundle.db.page.Page"%>
<%@page import="com.hesc.leave.pojo.BasicUser"%>
<%@page import="com.hesc.leave.pojo.BasicLeave"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  List<BasicUser> basicUsers=(List<BasicUser>)request.getAttribute("list");
	Page<BasicUser> pages=(Page<BasicUser>)request.getAttribute("page_basicuser");
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天翼智慧员工管理系统</title>
<link href="<%=request.getContextPath() %>/common/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery.js"></script>
<script>
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})
});
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
    <li><a href="<%=request.getContextPath() %>/leave/basicuser/list_only">小组员工列表</a></li>
    </ul>
    </div>
    <div class="rightinfo">
    <table class="imgtable">
    <thead>
    <tr>
    <th width="100px;">员工编号</th>
    <th>员工姓名</th>
    <th>所属部门</th>
    <th>职位</th>
    <th>电话</th>
    <th>操作</th>
    </tr>
    </thead>
    
    <tbody>
    <c:forEach items="${basicUsers }" var="basicUser_1">
	<tr>
    <td>${basicUser_1.id }</td>
    <td>${basicUser_1.name }</td>
    <td>${basicUser_1.department }</td>
    <td>${basicUser_1.position }</td>
    <td>${basicUser_1.tel }</td>
    <td><a href="<%=request.getContextPath() %>/leave/basicuser/${basicUser_1.id}">查看</a>
    <c:choose>
    	<c:when test="${basicUser.position=='组员' }" ></c:when>
    	<c:otherwise>|<a href="${basicUser_1.id}/update_all">修改</a> | <a href="${basicUser_1.id}/delete">删除</a></c:otherwise>
    </c:choose>
    </td>
    </tr>
   </c:forEach> 
   
    </tbody>
    </table>
   	<%  if(pages.getCount()==0){
   		
   	}else{
   		%>
   		<div class="pagin">
    	<div class="message">共<i class="blue">${page_basicuser.count}</i>条记录，当前显示第&nbsp;<i class="blue">${page_basicuser.curPage}&nbsp;</i>页</div>
        <ul class="paginList">
        <li><a href="<%=request.getContextPath() %>/leave/basicuser/listbefore1?curPage=1&pageCount=${page_basicuser.pageCount}">首页</a>	<a href="<%=request.getContextPath() %>/leave/basicuser/listbefore1?curPage=${page_basicuser.curPage}&pageCount=${page_basicuser.pageCount}">上一页</a>	第 ${page_basicuser.curPage }/${page_basicuser.pageCount==0?1:page_basicuser.pageCount } 页	<a href="<%=request.getContextPath() %>/leave/basicuser/listnext1?curPage=${page_basicuser.curPage}&pageCount=${page_basicuser.pageCount}">下一页</a>	<a href="<%=request.getContextPath() %>/leave/basicuser/listnext1?curPage=${page_basicuser.pageCount}&pageCount=${page_basicuser.pageCount}">尾页</a></li>
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

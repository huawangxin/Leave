<%@page import="com.hesc.leave.pojo.BasicLeave"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hesc.trundle.db.page.Page"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% List<BasicLeave> basicLeaves=(List<BasicLeave>)request.getAttribute("basicLeaves");
Page<BasicLeave> pages=(Page<BasicLeave>)request.getAttribute("page_basicleave"); 
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
	});
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
    <li><a href="<%=request.getContextPath() %>/leave/basicleave/list">请假记录列表</a></li>
    </ul>
    </div>
    <div class="rightinfo">
    <table class="imgtable">
    <thead>
    <tr>
    <th width="100px;">缩略图</th>
    <th>请假类型</th>
    <th>请假说明</th>
    <th>请假时长</th>
    <th>申请人</th>
    <th>是否审核</th>
    <th>操作</th>
    </tr>
    </thead>
    
    <tbody>
    <c:forEach items="${basicLeaves }" var="basicLeave">
	<tr>
    <td class="imgtd"><img src="<%=request.getContextPath() %>/common/images/img12.png" /></td>
    <td>${basicLeave.type}<p>ID:${basicLeave.id} </p></td>
    <td><a href="${basicLeave.id}/update">${basicLeave.description}</a><p>请假时间：${basicLeave.leaveStart} 至 ${basicLeave.leaveOver}</p></td>
    <td>${basicLeave.leaveTime} 天</td>
    <td>${basicLeave.name}</td>
    <td>
    <c:choose>
    <c:when test="${basicLeave.state=='1' }">
    	<i>未审核</i>
    </c:when>
    <c:when test="${basicLeave.state=='2' }">
    	<i>审核中</i>
    </c:when>
    <c:when test="${basicLeave.state=='3' }">
    	审核通过
    </c:when>
    <c:when test="${basicLeave.state=='0' }">
    	<i>审核不通过</i>
    </c:when>
    <c:otherwise>
    	<i>审核异常</i>
    </c:otherwise>
	</c:choose>
	</td>
    <td>
    <c:choose>
    <c:when test="${basicLeave.state=='1'}">
    	<c:if test="${basicUser.position=='组长' }">
			<a href="<%=request.getContextPath() %>/leave/basicleave/${basicLeave.id}/check">审核 |</a>
		</c:if>
    </c:when>
    <c:when test="${basicLeave.state=='2'}">
    	<c:if test="${basicUser.position=='部门经理' }">
			<a href="<%=request.getContextPath() %>/leave/basicleave/${basicLeave.id}/check">审核 |</a>
		</c:if>
    </c:when>
    <c:otherwise>
    </c:otherwise>
    </c:choose><a href="<%=request.getContextPath() %>/leave/basicleave/${basicLeave.id}">查看</a>
    </td>
    </tr>
   </c:forEach> 
   
    </tbody>
    </table>
    <%  if(pages.getCount()==0){
   		
   	}else{
   		%>
    <div class="pagin">
    	<div class="message">共<i class="blue">${page_basicleave.count}</i>条记录，当前显示第&nbsp;<i class="blue">${page_basicleave.curPage}&nbsp;</i>页</div>
        <ul class="paginList">
        <li><a href="<%=request.getContextPath() %>/leave/basicleave/listbefore2?curPage=1&pageCount=${page_basicleave.pageCount}">首页</a>	<a href="<%=request.getContextPath() %>/leave/basicleave/listbefore2?curPage=${page_basicleave.curPage}&pageCount=${page_basicleave.pageCount}">上一页</a>	第 ${page_basicleave.curPage }/${page_basicleave.pageCount==0?1:page_basicleave.pageCount } 页	<a href="<%=request.getContextPath() %>/leave/basicleave/listnext2?curPage=${page_basicleave.curPage}&pageCount=${page_basicleave.pageCount}">下一页</a>	<a href="<%=request.getContextPath() %>/leave/basicleave/listnext2?curPage=${page_basicleave.pageCount}&pageCount=${page_basicleave.pageCount}">尾页</a></li>
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

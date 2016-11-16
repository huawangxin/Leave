<%@page import="com.hesc.leave.pojo.BasicOffduty"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.hesc.trundle.db.page.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% List<BasicOffduty> basicOffdutys=(List<BasicOffduty>)request.getAttribute("basicOffdutys");
%>
<% Page<BasicOffduty> pages=(Page<BasicOffduty>)request.getAttribute("page_basicOffduty"); %>
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
    <li><a href="<%=request.getContextPath() %>/leave/basicoffduty/${basicUser.id }/caudit">调休记录列表</a></li>
    </ul>
    </div>
    <div class="rightinfo">
    <table class="imgtable">
    <thead>
    <tr>
    <th width="100px;">缩略图</th>
    <th>调休事由</th>
    <th>调休时长</th>
    <th>申请人</th>
    <th>是否审核</th>
    <th>操作</th>
    </tr>
    </thead>
    
    <tbody>
    <c:forEach items="${basicOffdutys }" var="basicOffduty">
	<tr>
    <td class="imgtd"><img src="<%=request.getContextPath() %>/common/images/img12.png" /></td>
    <td>${basicOffduty.reason}<p>ID:${basicOffduty.id} </p></td>
    <td>${basicOffduty.overtime} 天<p>请假时间：${basicOffduty.ovetimeStart} 至 ${basicOffduty.ovetimeOver}</p></td>
    <td>${basicOffduty.name}</td>
    <td>
    <c:choose>
    <c:when test="${basicOffduty.state=='1' }">
    	<i>未审核</i>
    </c:when>
    <c:when test="${basicOffduty.state=='2' }">
    	<i>审核中</i>
    </c:when>
    <c:when test="${basicOffduty.state=='3' }">
    	审核通过
    </c:when>
    <c:when test="${basicOffduty.state=='0' }">
    	<i>审核不通过</i>
    </c:when>
    <c:otherwise>
    	<i>审核异常</i>
    </c:otherwise>
	</c:choose>
	</td>
    <td>
    <c:choose>
    <c:when test="${basicOffduty.state=='1'}">
    	<c:if test="${basicUser.position=='组长' }">
			<a href="<%=request.getContextPath() %>/leave/basicoffduty/${basicOffduty.id}/check">审核 |</a>
		</c:if>
    </c:when>
    <c:when test="${basicOffduty.state=='2'}">
    	<c:if test="${basicUser.position=='部门经理' }">
			<a href="<%=request.getContextPath() %>/leave/basicoffduty/${basicOffduty.id}/check">审核 |</a>
		</c:if>
    </c:when>
    <c:otherwise>
    </c:otherwise>
    </c:choose><a href="<%=request.getContextPath() %>/leave/basicoffduty/${basicOffduty.id}">查看</a>
    </td>
    </tr>
   </c:forEach> 
   
    </tbody>
    </table>
   <%  if(pages.getCount()==0){
   		
   	}else{
   		%>
    <div class="pagin">
    	<div class="message">共<i class="blue">${page_basicOffduty.count}</i>条记录，当前显示第&nbsp;<i class="blue">${page_basicOffduty.curPage}&nbsp;</i>页</div>
        <ul class="paginList">
        <li><a href="<%=request.getContextPath() %>/leave/basicoffduty/listbefore2?curPage=1&pageCount=${page_basicOffduty.pageCount}">首页</a>	<a href="<%=request.getContextPath() %>/leave/basicoffduty/listbefore2?curPage=${page_basicOffduty.curPage}&pageCount=${page_basicOffduty.pageCount}">上一页</a>	第 ${page_basicOffduty.curPage }/${page_basicOffduty.pageCount==0?1:page_basicOffduty.pageCount } 页	<a href="<%=request.getContextPath() %>/leave/basicoffduty/listnext2?curPage=${page_basicOffduty.curPage}&pageCount=${page_basicOffduty.pageCount}">下一页</a>	<a href="<%=request.getContextPath() %>/leave/basicoffduty/listnext2?curPage=${page_basicOffduty.pageCount}&pageCount=${page_basicOffduty.pageCount}">尾页</a></li>
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

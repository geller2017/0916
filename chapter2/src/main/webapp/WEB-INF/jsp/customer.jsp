<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>客户信息列表</title>
</head>
<body>
	<h1>客户列表</h1>
	
	<table>
		<tr>
			<th>姓名</th>
			<th>联系方式</th>
			<th>手机号码</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${customerList }" var="cus">
			<tr>
				<td>${cus.name }</td>
				<td>${cus.contact }</td>
				<td>${cus.telephone }</td>
				<td>${cus.remark }</td>
				<td>
					<a href="${ctx }/customer_edit?id=${cus.id}">编辑</a>
					<a href="${ctx }/customer_delete?id=${cus.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			
		</tr>
	</table>
</body>
</html>
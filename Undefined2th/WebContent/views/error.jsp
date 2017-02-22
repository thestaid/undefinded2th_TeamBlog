<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>에러페이지 입니다.</title>
</head>
<body>
<%
	String msg=(String)request.getAttribute("msg");
%>
	<h3>${msg}</h3>
	<a href="${pageContext.request.contextPath }">인덱스로 가기</a>
</body>
</html>
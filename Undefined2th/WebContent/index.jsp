<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- home.do 요청을 통해 home.jsp 페이지로 이동 --%>
<%
	response.sendRedirect(request.getContextPath()+"/home.do");
%>

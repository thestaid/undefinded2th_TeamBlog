<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//로그인 ajax 처리하는 jsp페이지(로그인 유효값, 아이디를 전송한다.)
boolean isValid = (boolean)request.getAttribute("isValid");
String id = (String)request.getSession().getAttribute("id");
%>
{"isValid":"<%=isValid%>", "id":"<%=id%>"}
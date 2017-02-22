<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//회원 가입 성공 값을 전송하을 ajax 처리한다.
boolean isSuccess = (boolean)request.getAttribute("isSuccess");
%>
{"isSuccess":"<%=isSuccess%>"}
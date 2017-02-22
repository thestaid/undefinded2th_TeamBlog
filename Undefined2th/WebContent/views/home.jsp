<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	//session에서 id 받아오기
	String id=(String)session.getAttribute("id");
	//팝업창을 초기에 띄워주기
	boolean canPopup=true;
	//쿠키 객체를 얻어와서 쿠키 배열에 담은후
	Cookie[] cookies=request.getCookies();
	//반복문을 통해 쿠키의 이름이 Nopopup일 경우
	for(Cookie tmp : cookies){
		if(tmp.getName().equals("Nopopup")){
			//팝업창을 띄우지 않는다.
			canPopup=false;
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Caveat" rel="stylesheet">
<style>
	.welcomeblock{
		display: table;
		width: 100% ;
		height: 800px;
		background-color: #28274b;
	}
	.welcometext{
		display: table-cell;
		vertical-align:middle;
		text-align: center;
		color: #e2583e;
		font-size: 100px;
		font-style: bold;
		font-family: 'Abril Fatface', cursive;		
	}
</style>
<title>WELCOME to Undefined</title>
<jsp:include page="ui/myResource.jsp"></jsp:include>
</head>
<body>
<jsp:include page="ui/navbar.jsp">
	<jsp:param value="home" name="active"/>
</jsp:include> 
	<div class="welcomeblock">
	<%-- 배경을 접속하면 Hello 아이디로 지정 --%>
		<c:choose>
			<c:when test="${empty id }">
				<p class="welcometext">Hello, Stranger!</p>
			</c:when>
			<c:otherwise>
				<p class="welcometext">Hello, <strong>${id }</strong> :)</p>
			</c:otherwise>
		</c:choose>	
	</div>
	<script>
	//팝업창을 초기에 띄우기
	<%if(canPopup){%>
		window.open("${pageContext.request.contextPath}/views/popup/popup_page.jsp","팝업","width=460,height=260,top=138,left=600");
	<%}%>	
	</script>	
<jsp:include page="ui/footer.jsp"></jsp:include>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%
	//session에서 id 받아오기
	String id=(String)session.getAttribute("id");
%>
<div class="row nav navbar-inverse" style="margin:0px;">
	<div class="col-xs-3">
		<a href="${pageContext.request.contextPath }/home.do" class="navbar-brand">UNDEFINED</a>
	</div>
	<div class="col-xs-6">
		<ul class="nav navbar-nav">
			<%-- 각 페이지의 파라메터를 통해 navbar에 active 시키는 기능 --%>
			<li <c:if test="${param.active eq 'home' }">class="active"</c:if>><a href="${pageContext.request.contextPath }/home.do">HOME</a></li>
			<li <c:if test="${param.active eq 'board' }">class="active"</c:if>><a href="${pageContext.request.contextPath }/board/list.do">게시판</a></li>
			<li <c:if test="${param.active eq 'archive' }">class="active"</c:if>><a href="${pageContext.request.contextPath }/archive/list.do">사진첩</a></li>
			<li <c:if test="${param.active eq 'vistor' }">class="active"</c:if>><a href="${pageContext.request.contextPath }/visitor/visitors.do">방명록</a></li>
			<%-- 아이디가 admin 여부에 따라 보이는 기능 --%>
			<li <c:if test="${id ne 'admin'}">style="display:none;"</c:if> id="navbarAdmin" <c:if test="${param.active eq 'admin' }">class="active"</c:if>><a href="${pageContext.request.contextPath }/admin/list.do">관리페이지</a></li>
		</ul>
	</div>
	<div class="col-xs-3 pull-right">
		<ul class="nav navbar-nav">
			<%-- 아이디가 null인 경우 info와 로그아웃 탭을 안보이게 하는 기능 --%>
			<li <c:if test="${empty id }">style="display:none;"</c:if>
				id="navbarId"
				<c:if test="${param.active eq 'info' }">class="active"</c:if>><a
				href="${pageContext.request.contextPath }/users/private/info.do">${id }님
					로그인중..</a></li>
			<li <c:if test="${empty id }">style="display:none;"</c:if>
				id="navbarSignout"><a
				href="${pageContext.request.contextPath }/users/signout.do">로그아웃</a></li>
			<%-- 아이디가 null이 아닌경우 로그인과 회원가입이 안보이게 하는 기능 --%>
			<li <c:if test="${not empty id }">style="display:none;"</c:if>
				id="navbarSignin"
				<c:if test="${param.active eq 'signIn' }">class="active"</c:if>>
				<a class="btn" data-toggle="modal" data-target="#signinModal">LOGIN</a>
			</li>
			<li <c:if test="${not empty id }">style="display:none;"</c:if>
				id="navbarSignup"
				<c:if test="${param.active eq 'signUp' }">class="active"</c:if>>
				<a class="btn" data-toggle="modal" data-target="#signupModal">회원가입</a>
			</li>
		</ul>
	</div>
</div>
<!-- 로그인 Modal -->
<div id="signinModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content" style="background-color:black;opacity:0.8;">
			<div class="modal-header">
				<a class="close" data-dismiss="modal">&times;</a>
				<h4>로그인 해주세요!</h4>
			</div>
			<form
				action="${pageContext.request.contextPath }/views/users/signin.jsp"
				method="post" id="signinForm">
				<div class="modal-body">
					<div class="form-group">
						<label for="signinId">아이디</label> <input class="form-control"
							type="text" id="signinId" name="signinId" placeholder="아이디" />
					</div>
					<div class="form-group">
						<label for="signinPwd">비밀번호</label> <input class="form-control"
							type="password" id="signinPwd" name="signinPwd" placeholder="비밀번호" />
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">로그인</button>
					<button class="btn btn-danger" data-dismiss="modal">취소</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 회원가입 Modal -->
<div id="signupModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content" style="background-color:black;opacity:0.8;">
			<div class="modal-header">
				<a class="close" data-dismiss="modal">&times;</a>
				<h4>회원가입 해주세요!</h4>
			</div>
			<form
				action="${pageContext.request.contextPath }/views/users/signup.jsp"
				method="post" id="signupForm">
				<div class="modal-body">
					<div class="form-group has-feedback">
						<label class="control-label" for="signupid">아이디</label> <input
							class="form-control" type="text" id="signupid" name="signupid" />
						<p class="help-block" style="display: none;">사용할 수 없는 아이디 입니다.</p>
						<span class="glyphicon form-control-feedback"></span>
					</div>
					<div class="form-group">
						<label class="control-label" for="signuppwd">비밀번호</label> <input
							class="form-control" type="password" id="signuppwd" name="signuppwd" />
					</div>
					<div class="form-group">
						<label class="control-label" for="signupemail">이메일</label> <input
							class="form-control" type="text" id="signupemail"
							name="signupemail" />
					</div>
				</div>
				<div class="modal-footer">
					<button id="navinsertBtn" type="submit" class="btn btn-primary">회원가입</button>
					<button class="btn btn-danger" data-dismiss="modal">취소</button>
				</div>
			</form>
		</div>
	</div>
</div>
<script>
	//로그인 ajax 처리
	$("#signinForm").on("submit", function() {
		//form 에 입력한 내용을 읽어온다.
		var signinId = $("#signinId").val();
		var signinPwd = $("#signinPwd").val();
		//서버에 ajax 전송한다.
		$.ajax({
			url : "${pageContext.request.contextPath}/users/signin.do",
			method : "post",
			data : {
				"id" : signinId,
				"pwd" : signinPwd
			},
			success : function(data) {
				//로그인 성공과 실패시 navbar에 변화 시킬 로직
				if (data.isValid=="true") {
					alert(data.id + " 님 로그인 되었습니다.");
					$("#signinModal").modal('hide');
					$("#navbarSignin").hide();
					$("#navbarSignup").hide();
					$("#navbarId").show().find("a").text(data.id + "님 로그인중..");
					$("#navbarSignout").show();
					if(data.id=="admin"){
						$("#navbarAdmin").show();
					}
					location.reload(); 
				} else {
					alert("아이디 혹은 비밀번호가 틀려요!");
					$("#signinId").val("");
					$("#signinPwd").val("");
				}
			}
		});
		return false;//폼전송 막기 
	});//signinForm submit 끝
	//회원 가입 ajax 처리
	$("#signupForm").on("submit", function() {
		// 폼의 값을 읽어와서
		var signupid = $("#signupid").val();
		var signuppwd = $("#signuppwd").val();
		var signupemail = $("#signupemail").val();
		// 서버에 ajax 전송한다.
		var url = $(this).attr("action");
		$.ajax({
			url : "${pageContext.request.contextPath}/users/signup.do",
			method : "post",
			data : {
				"signupid" : signupid,
				"signuppwd" : signuppwd,
				"signupemail" : signupemail
			},
			success : function(data) {
				//data object의 isValid가 true와 false일때를 결정
				if (data.isSuccess) {
					//data => {isValis:true, id:"xxx"}형식의 object
					alert("회원가입 완료");
					$("#signupModal").modal('hide');
				} else {
					alert("회원가입실패!!!!");
				}
			}
		});
		return false;//폼전송 막기 
	});//signupForm submit 끝

	//아이디 입력란에 keyup 이벤트가 발생했을때 실행할 함수 등록 
	$("#signupid").on(
			"keyup",
			function() {
				//입력한 아이디 읽어오기
				var inputId = $("#signupid").val();
				//ajax 요청을 이용해서 서버에 전송
				$.ajax({
					url : "${pageContext.request.contextPath}/users/checkid.do",
					method : "get",
					data : {
						inputId : inputId
					},
					//아이디 중복시 버튼을 막고 display를 통해 가입 가능여부를 유저에게 알려준다.
					success : function(data) {
						$("#signupid").parent().removeClass(
								"has-success has-error");
						if (data.canUse) {
							$("#signupid").parent().addClass("has-success")
									.find(".help-block").hide().parent().find(
											".glyphicon").removeClass(
											"glyphicon-remove").addClass(
											"glyphicon-ok");
							$("#navinsertBtn").attr("disabled", null);
						} else {
							$("#signupid").parent().addClass("has-error").find(
									".help-block").show().parent().find(
									".glyphicon").removeClass("glyphicon-ok")
									.addClass("glyphicon-remove");
							$("#navinsertBtn").attr("disabled", "disabled");
						}
					}
				});
			});
</script>

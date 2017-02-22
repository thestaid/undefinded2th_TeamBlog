<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>회원정보 수정 페이지 입니다.</title>
<jsp:include page="../../ui/myResource.jsp" />
</head>
<style>
	.division{
		text-align: center;
	}
	.updateForm{
		margin:100px 20px 100px 20px;
	}
</style>
<body>
<jsp:include page="../../ui/navbar.jsp">
	<jsp:param value="info" name="active" />
</jsp:include>
	<div class="container">
		<div class="row updateForm">
			<div class="col-xs-12 col-sm-6 col-sm-offset-3">
				<h3 style="margin-bottom:30px">회원정보 수정 페이지</h3>
				<form class="form-horizontal" action=" update.do" method="post"
					id="updateForm">
					<div class="form-group">
						<input type="hidden" name="id" value="${dto.id }" /> 
						<label class="col-sm-3 control-label" for="id">아이디</label>
						<div class="col-sm-9">
							<input class="form-control" type="text" id="id"
								value="${dto.id }" disabled="disabled" /><br />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="pwd">비밀번호</label>
						<div class="col-sm-9">
							<input class="form-control" type="password" name="pwd" id="pwd"
								value="${dto.pwd }" /><br />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="pwd2">비밀번호 확인</label>
						<div class="col-sm-9">
							<input class="form-control" type="password" id="pwd2"
								value="${dto.pwd }" /><br />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="email">이메일 주소</label>
						<div class="col-sm-9">
							<input class="form-control" type="text" name="email" id="email"
								value="${dto.email }" /><br />
						</div>
					</div>
					<button class="btn btn-warning pull-right" type="reset" style="margin-left:5px;">
						<span class="glyphicon glyphicon-remove" style="white"></span> 취소
					</button>
					<button class="btn btn-primary pull-right" type="submit">
						<span class="glyphicon glyphicon-ok" style="white"></span> 수정
					</button>
				</form>
			</div>
		</div>
	</div>
	<script>
		//회원 정보의 비밀번호의 확인이 안될 경우 폼전송을 막는다.
		document.querySelector("#updateForm").addEventListener("submit",
				function(event) {
					var inputPwd = document.querySelector("#pwd").value;
					var inputPwd2 = document.querySelector("#pwd2").value;
					if (inputPwd != inputPwd2) {
						alert("비밀번호를 확인 하세요");
						event.preventDefault();//폼전송 막기 
				}
		});
	</script>
</body>
</html>










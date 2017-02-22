package test.users.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.users.dao.UsersDao;
import test.users.dto.UsersDto;

public class SignupAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//1. 요청 파라미터로 전달되는 아이디 비밀번호 이메일 을 읽어온다.
		String id=request.getParameter("signupid");
		String pwd=request.getParameter("signuppwd");
		String email=request.getParameter("signupemail");
		UsersDto dto=new UsersDto();
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setEmail(email);
		//2. DB 에 저장한다
		boolean isSuccess=UsersDao.getInstance().insert(dto);
		request.setAttribute("isSuccess", isSuccess);
		//3. 응답한다.
		return new ActionForward("/views/users/signup.jsp");
	}

}











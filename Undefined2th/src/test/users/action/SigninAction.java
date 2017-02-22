package test.users.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.users.dao.UsersDao;
import test.users.dto.UsersDto;

public class SigninAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		//아이디 비밀번호를 UserDto 에 담는다. 
		UsersDto dto=new UsersDto();
		dto.setId(id);
		dto.setPwd(pwd);
		
		//1. 유효한 정보인지 확인
		boolean isValid=UsersDao.getInstance().isValid(dto);
		if(isValid){
			//로그인 처리를 해준다.
			request.getSession().setAttribute("id", id);
		}
		request.setAttribute("isValid", isValid);
		//알림을 출력해주는 페이지로 forward 이동 
		return new ActionForward("/views/users/signin.jsp");
	}

}




















package test.users.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.users.dao.UsersDao;
import test.users.dto.UsersDto;

public class UsersUpdateAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//폼 전송된 수정할 회원정보를 읽어온다. 
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		//DB 에 수정 반영한다. 
		UsersDto dto=new UsersDto();
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setEmail(email);
		UsersDao.getInstance().update(dto);
		//응답
		request.setAttribute("msg", id+", 수정되었어요:)");
		request.setAttribute("redirectUri", request.getContextPath()+"/users/private/info.do");
		return new ActionForward("/views/users/alert.jsp");
	}
}





















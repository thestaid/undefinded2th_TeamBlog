package test.users.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.users.dao.UsersDao;
import test.users.dto.UsersDto;

public class UsersInfoAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//1. 세션에 저장된 id 정보를 읽어온다.
		String id = (String)request.getSession().getAttribute("id");
		//2. DB 에서 정보를 읽어온다.
		UsersDto dto=UsersDao.getInstance().getData(id);
		//3. request 에 담는다.
		request.setAttribute("dto", dto);
		//4. 뷰 페이지로 forward 이동해서 정보를 출력해준다.
		return new ActionForward("/views/users/private/info.jsp");
	}

}












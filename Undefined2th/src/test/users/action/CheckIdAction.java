package test.users.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.users.dao.UsersDao;

public class CheckIdAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 1. 요청 파라미터로 전달되는 아이디를 읽어온다.
		String id = request.getParameter("inputId");
		// 2. DB 를 확인해서 해당 아이디로 가입할수 있는지 여부를 얻어온다.
		boolean canUse=UsersDao.getInstance().canUseId(id);
		// 3. 결과값을 request 에 담는다.
		request.setAttribute("canUse", canUse);
		// 4. 뷰페이지로 forward 이동해서 json 문자열을 응답한다.
		return new ActionForward("/views/users/checkid.jsp");
	}

}












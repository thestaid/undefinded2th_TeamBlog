package test.users.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;

public class SignoutAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//세션에 담긴 모든 정보 삭제
		request.getSession().invalidate();
		//응답하기 
		request.setAttribute("alertMess", "다음에 또 만나요 :)");
		request.setAttribute("redirectUri", request.getContextPath());
		return new ActionForward("/views/alert.jsp");
	}

}












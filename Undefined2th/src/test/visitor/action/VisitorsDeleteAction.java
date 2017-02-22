package test.visitor.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.visitor.dao.VisitorDao;

public class VisitorsDeleteAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int num=Integer.parseInt(request.getParameter("num"));
		VisitorDao.getInstance().delete(num);
		return new ActionForward("/visitor/visitors.do", true);
	}

}

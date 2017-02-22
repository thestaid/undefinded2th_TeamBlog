package test.visitor.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.visitor.dao.VisitorDao;
import test.visitor.dto.VisitorDto;


public class VisitorsUpdateformAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int num=Integer.parseInt(request.getParameter("num"));
		VisitorDto dto=VisitorDao.getInstance().getDataform(num);
		String content=dto.getContent();
		request.setAttribute("content", content);
	return new ActionForward("/views/visitor/visitorGetdata.jsp");
	}

}

package test.visitor.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.visitor.dao.VisitorDao;
import test.visitor.dto.VisitorDto;

public class VisitorsUpdateAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int num=Integer.parseInt(request.getParameter("num"));
		String content=request.getParameter("content2");
		VisitorDto dto=new VisitorDto();
		dto.setNum(num);
		dto.setContent(content);
		
		VisitorDao.getInstance().update(dto);
		return new ActionForward("/visitor/visitors.do", true);
	}

}

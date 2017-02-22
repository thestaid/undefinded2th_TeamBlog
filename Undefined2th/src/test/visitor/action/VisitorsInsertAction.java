package test.visitor.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.visitor.dao.VisitorDao;
import test.visitor.dto.VisitorDto;

public class VisitorsInsertAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String writer=request.getParameter("writer");
		String content=request.getParameter("ir1");
		
		VisitorDto dto=new VisitorDto();
		dto.setWriter(writer);
		dto.setContent(content);
		dto.setTarget_id("");
		
		VisitorDao.getInstance().insert(dto);
		
		return new ActionForward("/visitor/visitors.do", true);
	}

}

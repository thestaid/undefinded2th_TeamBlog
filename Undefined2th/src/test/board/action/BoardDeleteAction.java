package test.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.board.dao.BoardDao;
import test.controller.Action;
import test.controller.ActionForward;

public class BoardDeleteAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		boolean isSuccess=BoardDao.getInstence().delete(num);
		if(isSuccess){
			request.setAttribute("alertMess", "글 삭제 성공!");
			request.setAttribute("redirectUri", request.getContextPath()+"/board/list.do");			
		}else{
			request.setAttribute("alertMess", "글 삭제 실패!");
			request.setAttribute("redirectUri", request.getContextPath()+"/board/list.do");				
		}
		return new ActionForward("/views/alert.jsp");
	}
	
}

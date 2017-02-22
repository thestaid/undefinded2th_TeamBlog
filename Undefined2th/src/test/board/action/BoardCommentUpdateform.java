package test.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.boardComment.dao.BoardCommentDao;
import test.boardComment.dto.BoardCommentDto;
import test.controller.Action;
import test.controller.ActionForward;


public class BoardCommentUpdateform extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int num=Integer.parseInt(request.getParameter("num"));
		BoardCommentDto dto=BoardCommentDao.getInstance().getData(num);
		String content=dto.getContent();
		request.setAttribute("content", content);
		return new ActionForward("/views/board/private/BoardCommentGetdata.jsp");
	}

}

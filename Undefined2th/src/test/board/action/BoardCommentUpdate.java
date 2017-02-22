package test.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.boardComment.dao.BoardCommentDao;
import test.boardComment.dto.BoardCommentDto;
import test.controller.Action;
import test.controller.ActionForward;

public class BoardCommentUpdate extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int num=Integer.parseInt(request.getParameter("num"));
		int textNum=Integer.parseInt(request.getParameter("textnum"));
		String content=request.getParameter("content");
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		BoardCommentDto dto=new BoardCommentDto();
		dto.setNum(num);
		dto.setContent(content);
		BoardCommentDao.getInstance().update(dto);
		
		return new ActionForward("/board/detail.do?num="+textNum+"&condition="+condition+"&keyword="+keyword,true);
	}

}

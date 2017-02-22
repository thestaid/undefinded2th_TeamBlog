package test.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import test.board.dao.BoardDao;
import test.board.dto.BoardDto;
import test.controller.Action;
import test.controller.ActionForward;

public class BoardInsertAction extends Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 파라미터로 전송되는 글 제목, 글 내용 읽어오기
		String title = request.getParameter("title");
		String content = request.getParameter("ir1");
		// 세션에 저장된 id 정보 읽어오기
		String id = (String)request.getSession().getAttribute("id");
		// BoardDto 에 정보 담기
		BoardDto dto = new BoardDto();
		dto.setWriter(id);
		dto.setTitle(title);
		dto.setContent(content);
		// BoardDao 객체를 이용해서 DB 에 저장하기
		boolean isSuccess = BoardDao.getInstence().insert(dto);
		// redirect 이동 하라고 응답한다.
		if(isSuccess){
			request.setAttribute("alertMess", "새글을 저장하였습니다");
			request.setAttribute("redirectUri", request.getContextPath()+"/board/list.do");
		}else{
			request.setAttribute("alertMess", "새글 저장 실패!");
			request.setAttribute("redirectUri", request.getContextPath()+"/board/list.do");			
		}
		return new ActionForward("/views/alert.jsp");

	}
	
}

package test.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.board.dao.BoardDao;
import test.board.dto.BoardDto;
import test.controller.Action;
import test.controller.ActionForward;

public class BoardUpdateformAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 1. 파라미터로 전달되는 수정 할 글 번호 읽어오기
		int num = Integer.parseInt(request.getParameter("num"));
		
		// 2. 수정 할 글의 정보 얻어오기
		BoardDto dto = new BoardDto();
		dto.setNum(num);
		BoardDto resultdto = BoardDao.getInstence().getData(dto);
		
		// 3. request 에 dto 를 담고
		request.setAttribute("dto", resultdto);
		
		// 4. 수정 할 글의 정보 출력해주기
		return new ActionForward("/views/board/private/updateform.jsp");
	}

	
}

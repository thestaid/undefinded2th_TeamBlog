package test.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.board.dao.BoardDao;
import test.board.dto.BoardDto;
import test.controller.Action;
import test.controller.ActionForward;

public class BoardUpdateAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 1. 폼 전송된 수정할 글의 정보 얻어오기
		int num = Integer.parseInt(request.getParameter("num"));
		String title = request.getParameter("title");
		String content = request.getParameter("ir1");
		
		// 2. DB 에 수정 반영하기
		BoardDto dto = new BoardDto();
		dto.setNum(num);
		dto.setTitle(title);
		dto.setContent(content);
		boolean isSuccess=BoardDao.getInstence().update(dto);
		if(isSuccess){
			request.setAttribute("alertMess", "글 수정 성공!");
			request.setAttribute("redirectUri", request.getContextPath()+"/board/detail.do?num="+dto.getNum());				
		}else{
			request.setAttribute("alertMess", "글 수정 성공!");
			request.setAttribute("redirectUri", request.getContextPath()+"/board/detail.do?num="+dto.getNum());				
		}
		// 3. redirect 이동하라고 응답하기
		return new ActionForward("/views/alert.jsp");
	}

}

package test.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.board.dao.BoardDao;
import test.board.dto.BoardDto;
import test.boardComment.dao.BoardCommentDao;
import test.boardComment.dto.BoardCommentDto;
import test.controller.Action;
import test.controller.ActionForward;

public class BoardDetailAction extends Action {
	//한 페이지에 나타낼 로우의 갯수
	private static final int PAGE_ROW_COUNT=5;
	//하단 디스플레이 페이지 갯수
	private static final int PAGE_DISPLAY_COUNT=10;
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//파라미터로 전달되는 글번호를 읽어온다.
		int num=Integer.parseInt(request.getParameter("num"));
		
		//보여줄 페이지의 번호
		int pageNum=1;
		//보여줄 페이지의 번호가 파라미터로 전달되는지 읽어온다.
		String strPageNum=request.getParameter("pageNum");
		if(strPageNum != null){//페이지 번호가 파라미터로 넘어온다면
			//페이지 번호를 설정한다.
			pageNum=Integer.parseInt(strPageNum);
		}
		//보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		//전체 row 의 갯수를 DB 에서 얻어온다.
		int totalRow = BoardCommentDao.getInstance().getCount(num);
		//전체 페이지의 갯수 구하기
		int totalPageCount=
				(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//시작 페이지 번호
		int startPageNum=
			1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//끝 페이지 번호가 잘못된 값이라면 
		if(totalPageCount < endPageNum){
			endPageNum=totalPageCount; //보정해준다. 
		}
		
		//시작 row 번호와 끝 row 번호를 dto 에 담는다. 
		BoardCommentDto commentDto=new BoardCommentDto();
		commentDto.setStartRowNum(startRowNum);
		commentDto.setEndRowNum(endRowNum);
		commentDto.setNum(num);
		//검색과 관련된 파라미터를 읽어와 본다.
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		
		BoardDao.getInstence().increaseViewCount(num);
		//4. 글번호에 해당되는 덧글 목록을 얻어온다.
		List<BoardCommentDto> commentList=BoardCommentDao.getInstance().getList(commentDto);
		//BoardDto 객체를 생성해서
		BoardDto dto=new BoardDto();
		if(keyword != null){ //검색어가 전달된 경우
			if(condition.equals("titlecontent")){ //제목+내용 검색
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")){//제목 검색
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){//작성자 검색
				dto.setWriter(keyword);
			}
			// list.jsp 뷰페이지에서 필요한 내용을 request 에 담는다.
			request.setAttribute("condition", condition);
			request.setAttribute("keyword", keyword);
		}
		//글번호도 dto 에 담는다.
		dto.setNum(num);
		
		//댓글 정보를 request에 담는다.
		request.setAttribute("commentList", commentList);
		String id = (String)request.getSession().getAttribute("id");
		boolean isLogin=false;
		if(id != null){
			isLogin=true;
		}
		//로그인 했는지 여부도 request 에 담는다.
		request.setAttribute("isLogin", isLogin);
		
		//글정보를 얻어온다.
		BoardDto resultDto=BoardDao.getInstence().getData(dto);
		//request 에 담는다.
		request.setAttribute("dto", resultDto);
		// 현재 페이지 번호 
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		// 전재 페이지의 갯수
		request.setAttribute("totalPageCount", totalPageCount);				
		//뷰페이지로 forward 이동 해서 글 정보를 출력해 준다.
		return new ActionForward("/views/board/detail.jsp");
	}

}

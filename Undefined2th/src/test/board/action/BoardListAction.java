package test.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.board.dao.BoardDao;
import test.board.dto.BoardDto;
import test.controller.Action;
import test.controller.ActionForward;

public class BoardListAction extends Action {
	// 한 페이지에 나타낼 로우의 갯수
	private static final int PAGE_ROW_COUNT = 10;
	// 하단 디스플레이 페이지 갯수
	private static final int PAGE_DISPLAY_COUNT = 5;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//검색과 관련된 파라미터를 읽어와 본다.
				String keyword=request.getParameter("keyword");
				String condition=request.getParameter("condition");
				
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
				int totalRow = BoardDao.getInstence().getCount(dto);
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
				dto.setStartRowNum(startRowNum);
				dto.setEndRowNum(endRowNum);
				
				//1. BoardDao 객체를 이용해서 글목록을 얻어온다. 
				List<BoardDto> list=BoardDao.getInstence().getList(dto);
				//2. 모델을 request 에 담는다.
				request.setAttribute("list", list);
				//현재 url주소
				String url = request.getRequestURL().toString();
				request.setAttribute("url", url);				
				
				// 현재 페이지 번호 
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("startPageNum", startPageNum);
				request.setAttribute("endPageNum", endPageNum);
				// 전재 페이지의 갯수
				request.setAttribute("totalPageCount", totalPageCount);
				
		return new ActionForward("/views/board/list.jsp");
	}
}

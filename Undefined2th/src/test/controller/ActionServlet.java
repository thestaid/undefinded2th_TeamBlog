package test.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//앞에 뭐가 오던간에 .do라는 요청명을 처리하겠다.
@WebServlet("*.do")
public class ActionServlet extends HttpServlet{
	//.을 포함한 요청 확장자의 길이
	public static final int INCLUDE_EXTENSION_LENGTH=3;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//요청 URI 읽어오기
		String uri=request.getRequestURI();
		//context name읽어오기
		String contextName=request.getContextPath();
		//순수 요청 경로를 추출한다.
		// command => /fortune or /time or /signin
		/*
		 * String substring(int begin, int end)
		 * 한 문자열에서 내용의 일부를 추출하는 메서드
		 * 주어진 시작위치(begin)부터 끝 위치(end) 범위에 포함된 문자열을 얻는다.
		 */
		String command=uri.substring
				(contextName.length(), 
				uri.length()-INCLUDE_EXTENSION_LENGTH);
		
		Action action=UserActionFactory.getInstance().action(command);
		
		if(action != null){
			//처리 가능한 command라면
			ActionForward af=null;
			try{
				af=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
			if(af.isRedirect()){//redirect 이동해야한다면
				//리다이렉트 할때는 경로에 Contextpath가 필요하다
				response.sendRedirect(contextName+af.getPath());
			}else{//forward이동해야한다면
				RequestDispatcher rd=request.getRequestDispatcher(af.getPath());
				rd.forward(request, response);
			}
		}else{//Action이 null이라면 존재하지 않는 *.do 요청이다.
			String msg="해당 페이지는 존재하지 않습니다.";
			request.setAttribute("msg", msg);
			RequestDispatcher rd=request.getRequestDispatcher("/views/error.jsp");
			rd.forward(request, response);
		}
		
		
	}
}

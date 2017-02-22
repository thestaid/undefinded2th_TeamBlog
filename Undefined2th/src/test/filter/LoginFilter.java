package test.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/*
 * 특정 요청에 대해서 필터링하기 위한 필터 클래스 정의하기
 */
// 필터링 할 곳이 여러군데이면 배열처럼 하여 다중으로 필터링 할수 있다.
// 원래의 요청을 처리할 서블릿이나 jsp에 가기전에 필터를 들렀다 간다.
// /members/* : members하위의 모든 파일
@WebFilter({"/board/*"})
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//원래 type 으로 casting 
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		
		//HttpSession 객체
		HttpSession session=request.getSession();
		
		//Context name 읽어오기 
		String contextPath=request.getContextPath();
		
		//원래 요청 uri 정보 얻어오기
		String uri=request.getRequestURI();
		session.setAttribute("uri", uri);
		chain.doFilter(req, res);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

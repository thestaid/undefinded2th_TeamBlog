package test.filter;

import java.io.IOException;

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

@WebFilter("/admin/*")
public class AdminFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, 
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null){//로그인을 하지 않은 경우 
			String contextPath=request.getContextPath();
			response.sendRedirect(contextPath+"/index.jsp");
			return;//메소드 종료 
		}
		
		if(id.equals("admin")){//관리자 계정으로 로그인 되어있으면
			//원래 하려던 작업 계속하기
			chain.doFilter(req, res);
		}else{//관리자 계정이 아니면
			//인덱스로 리다일렉트 시킨다.
			String contextPath=request.getContextPath();
			response.sendRedirect(contextPath+"/index.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}

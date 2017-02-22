package test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("*")
public class EncodingFilter implements Filter{
	private String myEncoding;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if(req.getCharacterEncoding()==null){
			req.setCharacterEncoding(myEncoding);
		}
	
		chain.doFilter(req,res);
	}
	@Override
	public void init(FilterConfig config) throws ServletException {
		myEncoding="utf-8";
		
	}

}

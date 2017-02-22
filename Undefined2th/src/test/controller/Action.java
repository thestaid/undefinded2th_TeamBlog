package test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {
	//추상 메서드 정의
	public abstract ActionForward execute
		(HttpServletRequest request, HttpServletResponse response);
	
}

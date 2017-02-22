package test.users.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.users.dao.UsersDao;

public class UsersDeleteAction extends Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		   String id=request.getParameter("id");
		   System.out.println(id);
		      boolean isSuccess=UsersDao.getInstance().delete(id);
		      if(isSuccess){
		         String sessionId=(String)request.getSession().getAttribute("id");
		         if(sessionId.equals("admin")&&!id.equals("admin")){
		            request.setAttribute("alertMess", "탈퇴되엇어요.");
		            request.setAttribute("redirectUri", request.getContextPath()+"/admin/list.do");
		         }else{
		            request.getSession().removeAttribute("id");
		            request.setAttribute("alertMess", "다음에 또 와줄거죠?");
		            request.setAttribute("redirectUri", request.getContextPath()+"/home.do");            
		         }
		      }else{
		         request.setAttribute("alertMess", "이렇게 보낼 수는 없어요.");
		         request.setAttribute("redirectUri", request.getContextPath()+"/home.do");
		      }
		      return new ActionForward("/views/alert.jsp");
		   }
	}












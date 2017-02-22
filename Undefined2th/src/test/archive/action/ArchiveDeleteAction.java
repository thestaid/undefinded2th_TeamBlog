package test.archive.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;

import test.archive.dao.ArchiveDao;
import test.archive.dto.ArchiveDto;
import test.controller.Action;
import test.controller.ActionForward;

public class ArchiveDeleteAction extends Action {
		
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		//삭제할 파일의 번호를 읽어온다.
		int num=Integer.parseInt(request.getParameter("num"));
		ArchiveDto dto = new ArchiveDto();
		dto.setNum(num);
		
		//삭제할 파일의 저장된 파일명(saveFileName)을 읽어온다.
		String saveFileName=
			ArchiveDao.getInstance().getData(dto).getSaveFileName();
		
		// DB 에서 파일 정보 삭제
		boolean isSuccess=ArchiveDao.getInstance().delete(num);
		
		//2. 파일 시스템에서 삭제 
		//삭제할 파일의 절대경로 
		String path=request.getSession().getServletContext().getRealPath("/upload")+
				File.separator+saveFileName;
		
		System.out.println(path);
		
		//파일객체 생성해서 삭제한다. 
		new File(path).delete();
		
		if(isSuccess){
			request.setAttribute("alertMess", "글 삭제 성공!");
			request.setAttribute("redirectUri", request.getContextPath()+"/archive/list.do");			
		}else{
			request.setAttribute("alertMess", "글 삭제 실패!");
			request.setAttribute("redirectUri", request.getContextPath()+"/archive/list.do");				
		}

		return new ActionForward("/views/alert.jsp");
	}

}

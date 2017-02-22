package test.archive.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

import test.archive.dao.ArchiveDao;
import test.archive.dto.ArchiveDto;
import test.controller.Action;
import test.controller.ActionForward;

public class ArchiveUploadAction extends Action {
		
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		//파일을 저장할 폴더의 절대 경로를 얻어온다.
		String realPath = request.getSession().getServletContext().getRealPath("/upload");
		//파일이 저장되는 경로를 콘솔에 테스트로 출력해보기
		System.out.println(realPath);
		//최대 업로드 사이즈
		int sizeLimit=1024*1000*50; //50 MByte
		
		//cos.jar 라이브러리에 있는 클래스를 이용해서 객체 생성하기 
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(request, realPath, sizeLimit,"utf-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//업로드된 파일에 대한 정보는 mr 객체를 통해서 얻어온다.
		String writer= mr.getParameter("writer");
		String title=mr.getParameter("title");
		String orgFileName=mr.getOriginalFileName("myFile");
		String saveFileName=mr.getFilesystemName("myFile");
		long fileSize=mr.getFile("myFile").length();
		
		 
		//업로드된 파일의 정보를 ArchiveDto 객체에 담는다.
		ArchiveDto dto=new ArchiveDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setOrgFileName(orgFileName);
		dto.setSaveFileName(saveFileName);
		dto.setFileSize(fileSize);
		
		//ArchiveDao 객체를 이용해서 파일의 정보를 DB 에 저장한다.
		boolean isSuccess=ArchiveDao.getInstance().upload(dto);	
		
		// redirect 이동 하라고 응답한다.
		if(isSuccess){
			request.setAttribute("alertMess", "업로드하였습니다");
			request.setAttribute("redirectUri", request.getContextPath()+"/archive/list.do");
		}else{
			request.setAttribute("alertMess", "업로드 실패!");
			request.setAttribute("redirectUri", request.getContextPath()+"/archive/list.do");			
		}
		
		return new ActionForward("/views/alert.jsp");
	}

}

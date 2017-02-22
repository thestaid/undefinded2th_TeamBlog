package test.archive.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.archive.dao.ArchiveDao;
import test.archive.dto.ArchiveDto;
import test.controller.Action;
import test.controller.ActionForward;

public class ArchiveDownloadAction extends Action {
		
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		//파라미터로 전달되는 다운로드 시켜줄 파일의 번호를 읽어온다.
		int num=Integer.parseInt(request.getParameter("num"));
		ArchiveDto dto = new ArchiveDto();
		dto.setNum(num);
		
	    //다운로드 시켜줄 파일의 정보를 ArchiveDao 를 이용해서 얻어온다.
		ArchiveDto downdto=ArchiveDao.getInstance().getData(dto);
		
	    //다운로드 시켜주기...
		//파일명을 읽어온다.
		
		String orgFileName=downdto.getOrgFileName(); //원본 파일명
		String saveFileName=downdto.getSaveFileName(); //저장된 파일명
		
				
		//다운로드 시켜줄 파일의 실제 경로 구성하기
		String path=request.getSession().getServletContext().getRealPath("/upload")+File.separator+orgFileName;
		System.out.println(path);		

		//다운로드 할 파일을 읽어올 스트림 객체 생성하기
		try {
			FileInputStream fis = new FileInputStream(path);
			
			//다운로드 시켜주는 작업을 한다. (실제 파일 데이터와 원본파일명을 보내줘야한다.)
			String encodedName=null;
			System.out.println(request.getHeader("User-Agent"));
			//한글 파일명 세부처리 
			if(request.getHeader("User-Agent").contains("Firefox")){
				//벤더사가 파이어 폭스인경우 
				encodedName=new String
						(orgFileName.getBytes("utf-8"),"ISO-8859-1");
			}else{ //그외 다른 벤더사 
				encodedName=URLEncoder.encode(orgFileName, "utf-8");
				//파일명에 공백이있는 경우 처리 
				encodedName=encodedName.replaceAll("\\+"," ");
			}
			//응답 헤더 정보 설정
			response.setHeader("Content-Disposition","attachment;filename="+encodedName);
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			//다운로드할 파일의 크기 읽어와서 다운로드할 파일의 크기 설정
			response.setContentLengthLong(downdto.getFileSize());
			
			//클라이언트에게 출력할수 있는 스트림 객체 얻어오기
			BufferedOutputStream bos=
					new BufferedOutputStream(response.getOutputStream());
			//한번에 최대 1M byte 씩 읽어올수 있는 버퍼
			byte[] buffer=new byte[1024*1000];
			int readedByte=0;
			//반복문 돌면서 출력해주기
			while(true){
				//byte[] 객체를 이용해서 파일에서 byte 알갱이 읽어오기
				readedByte = fis.read(buffer);
				if(readedByte == -1)break; //더이상 읽을 데이터가 없다면 반복문 빠져 나오기
				//읽은 만큼 출력하기
				bos.write(buffer, 0, readedByte);
				bos.flush(); //출력
			}
			//스트림 닫아주기
			bos.close();
			fis.close();
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}

}
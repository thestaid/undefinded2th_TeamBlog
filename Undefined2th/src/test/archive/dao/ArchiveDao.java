package test.archive.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import test.archive.dto.ArchiveDto;
import test.board.dto.BoardDto;
import test.mybatis.SqlMapConfig;

public class ArchiveDao {
	private static ArchiveDao dao;
	private static SqlSessionFactory factory;
	private ArchiveDao(){}
	public static ArchiveDao getInstance(){
		if(dao==null){
			dao=new ArchiveDao();
			factory = SqlMapConfig.getSqlSession();
		}
		return dao;
	}
	//파일 목록을 리턴해주는 메소드
	public List<ArchiveDto> getList(ArchiveDto dto){
		SqlSession session = factory.openSession();
		
		List<ArchiveDto> list = null;
		try{
			list = session.selectList("archive.getList", dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	public int getCount(ArchiveDto dto){
		SqlSession session=factory.openSession();
		int count=0;
		try {
			count=session.selectOne("archive.getCount", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return count;
	}
	
	//파일 정보를 저장하는 메소드
	public boolean upload(ArchiveDto dto){
		// auto commit 되는  SqlSession 객체 얻어오기
		SqlSession session = factory.openSession(true);
		boolean isSuccess = false;
		try {
			session.insert("archive.upload", dto);
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		} finally{
			session.close();
		}
		return isSuccess;
	}
	
	
	
	//파일정보를 리턴하는 메소드 
	public ArchiveDto getData(ArchiveDto dto){
		SqlSession session = factory.openSession();
		ArchiveDto resultdto = null;
		try {
			resultdto = session.selectOne("archive.getData", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultdto;
		
	}//getData()
	
	//인자로 전달되는 파일 번호에 해당하는 파일정보를 삭제하는 메소드 
	public boolean delete(int num){
		// factory.openSession(true); : auto commit 여부
		SqlSession session = factory.openSession(true);
		boolean isSuccess = false;
		try {
			session.delete("archive.delete", num);
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}finally{
			session.close();
		}
		return isSuccess;
	}//delete()	
	
}

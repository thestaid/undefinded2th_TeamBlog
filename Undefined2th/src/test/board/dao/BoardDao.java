package test.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import test.board.dto.BoardDto;
import test.mybatis.SqlMapConfig;

public class BoardDao {
	private static BoardDao dao;
	private static SqlSessionFactory factory;
	private BoardDao(){}
	public static BoardDao getInstence(){
		if(dao == null){
			dao = new BoardDao();
			factory = SqlMapConfig.getSqlSession();
		}
		return dao;
	}
	// 글목록을 리턴해주는 메소드
	public List<BoardDto> getList(BoardDto dto){
		SqlSession session = factory.openSession();
		List<BoardDto> list = null;
		try{
			list = session.selectList("board.getList", dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	public int getCount(BoardDto dto){
		SqlSession session=factory.openSession();
		int count=0;
		try {
			count=session.selectOne("board.getCount", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return count;
	}
	
	// 글정보 저장하는 메소드
	public boolean insert(BoardDto dto){
		// auto commit 되는  SqlSession 객체 얻어오기
		SqlSession session = factory.openSession(true);
		boolean isSuccess = false;
		try {
			session.insert("board.insert", dto);
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		} finally{
			session.close();
		}
		return isSuccess;
	}
	// 글정보를 리턴해주는 메소드
	public BoardDto getData(BoardDto dto){
		SqlSession session = factory.openSession();
		BoardDto resultdto = null;
		try {
			resultdto = session.selectOne("board.getData", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultdto;
	}
	
	// 조회수를 1 증가시키는 메소드
	public void increaseViewCount(int num){
		// auto commit 되는 SqlSession 객체의 참조값 얻어오기
		SqlSession session = factory.openSession(true);
		try {
			session.update("board.increaseViewCount", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public boolean update(BoardDto dto){
		SqlSession session = factory.openSession(true);
		boolean isSuccess = false;
		try {
			session.update("board.update", dto);
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}finally{
			session.close();
		}
		return isSuccess;
	}
	
	public boolean delete(int num){
		// factory.openSession(true); : auto commit 여부
		SqlSession session = factory.openSession(true);
		boolean isSuccess = false;
		try {
			session.delete("board.delete", num);
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}finally{
			session.close();
		}
		return isSuccess;
	}
}






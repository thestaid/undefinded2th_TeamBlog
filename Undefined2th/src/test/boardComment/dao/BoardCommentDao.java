package test.boardComment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import test.boardComment.dto.BoardCommentDto;
import test.mybatis.SqlMapConfig;

public class BoardCommentDao {
	private static BoardCommentDao dao;
	private static SqlSessionFactory factory;
	private BoardCommentDao(){};
	public static BoardCommentDao getInstance(){
		if(dao==null){
			dao=new BoardCommentDao();
			factory=SqlMapConfig.getSqlSession();
		}
		return dao;
	}
	//새 덧글을 저장하는 메소드
	public void insert(BoardCommentDto dto){
		 SqlSession session=factory.openSession(true);
		 try{
			 session.insert("boardComment.insert", dto);
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 session.close();
		 }
	}
	
	//덧글 목록을 리턴하는 메소드
	public List<BoardCommentDto> getList(BoardCommentDto dto){
		SqlSession session=factory.openSession();
		List<BoardCommentDto> list=null;
		try{
			list=session.selectList("boardComment.getList", dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	//새 덧글의 글번호(sequence) 값을 얻어내서 리턴해주는 메소드
	public int getSequence(){
		SqlSession session=factory.openSession();
		int num=0;
		try{
			num=session.selectOne("boardComment.getSequence");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return num;
	}
	
	public int getCount(int num){
		SqlSession session=factory.openSession();
		int count=0;
		try {
			count=session.selectOne("boardComment.getCount",num);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return count;
	}
	
	public BoardCommentDto getData(int num){
		SqlSession session=factory.openSession();
		BoardCommentDto dto=null;
		try{
			dto=session.selectOne("boardComment.getData", num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return dto;
	}
	public void update(BoardCommentDto dto){
		SqlSession session=factory.openSession(true);
		try{
			session.update("boardComment.update", dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	public void delete(int num){
		SqlSession session=factory.openSession(true);
		try{
			session.update("boardComment.delete", num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}

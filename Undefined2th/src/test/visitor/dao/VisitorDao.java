package test.visitor.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import test.mybatis.SqlMapConfig;
import test.visitor.dto.VisitorDto;

public class VisitorDao {
	private static VisitorDao dao;
	private static SqlSessionFactory factory;
	private VisitorDao(){}
	public static VisitorDao getInstance(){
		if(dao==null){
			dao=new VisitorDao();
			factory=SqlMapConfig.getSqlSession();
		}
		return dao;
	}
	
	//INSERT START
	public void insert(VisitorDto dto){
		SqlSession session=factory.openSession(true);
		try{
			session.insert("visitor.insert", dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}//INSERT END

	//GETLIST START
	public List<VisitorDto> getList(VisitorDto dto){
		SqlSession session=factory.openSession();
		List<VisitorDto> list=null;
		try{
			list=session.selectList("visitor.getList", dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}//GETLIST END
		
	//GETDATA START
	public VisitorDto getData(VisitorDto dto){
		SqlSession session=factory.openSession();
		VisitorDto resultdto=null;
		try{
			resultdto=session.selectOne("visitor.getData",dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return resultdto;
	}//GETDATA END
	
	public VisitorDto getDataform(int num){
		SqlSession session=factory.openSession();
		VisitorDto dto=null;
		try{
			dto=session.selectOne("visitor.getDataform",num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return dto;
	}//GETDATA END	
	
	//UPDATE START
	public void update(VisitorDto dto){
		SqlSession session=factory.openSession(true);
		try{
			session.update("visitor.update", dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}//UPDATE END
	
	//DELETE START
	public void delete(int num){
		SqlSession session=factory.openSession(true);
		try{
			session.delete("visitor.delete", num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}//DELETE END

	public int getCount(VisitorDto dto){
		SqlSession session=factory.openSession();
		int count=0;
		try{
			count=session.selectOne("visitor.getCount", dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();			
		}
		return count;
	}
}

package test.users.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import test.mybatis.SqlMapConfig;
import test.users.dto.UsersDto;

//회원정보 저장, 수정, 삭제를 처리할 UsersDao 
public class UsersDao {
	private static UsersDao dao;
	private static SqlSessionFactory factory;
	private UsersDao(){}
	public static UsersDao getInstance(){
		if(dao==null){
			dao=new UsersDao();
			factory=SqlMapConfig.getSqlSession();
		}
		return dao;
	}
	//회원정보를 저장하는 메소드 
	public boolean insert(UsersDto dto){
		SqlSession session=factory.openSession(true);
		boolean isSuccess = false;
		try{
			session.insert("users.insert", dto);
			isSuccess = true;
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
		}finally{
			session.close();
		}
		System.out.println(isSuccess);
		return isSuccess;	
	}//insert()
	
	//로그인 폼에 입력한 아이디와 비밀번호가 유효한 정보인지 여부를 
	//리턴해주는 메소드
	public boolean isValid(UsersDto dto){
		SqlSession session=factory.openSession();
		UsersDto resultDto=null;
		try{
			resultDto=session.selectOne("users.isValid", dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		if(resultDto == null){
			return false;
		}else{
			return true;
		}
	}//isValid()
	
	// 인자로 전달된 아이디에 해당하는 회원정보를 리턴해주는 메소드
	public UsersDto getData(String id){
		SqlSession session=factory.openSession();
		UsersDto dto=null;
		try{
			dto=session.selectOne("users.getData", id);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return dto;
	}
	
	//회원정보를 수정 반영하는 메소드
	public void update(UsersDto dto){
		SqlSession session=factory.openSession(true);
		try{
			session.update("users.update", dto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	//인자로 전달하는 id 회원을 삭제하는 메소드
	public boolean delete(String id){
		SqlSession session=factory.openSession(true);
		boolean isSuccess = false;
		int result=0;
		try{
			result=session.delete("users.delete", id);
			System.out.println(result);
			if(result>0){
				isSuccess = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return isSuccess;
	}
	
	//모든 회원의 정보를 리턴해주는 메소드(1)
	public List<UsersDto> getList(UsersDto dto){
		SqlSession session = factory.openSession();
		List<UsersDto> list = null;
		try {
			list = session.selectList("users.getList", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}//getList()
	
	//인자로 전달되는 아이디로 가입할수 있는지 여부를 리턴해주는 메소드
	public boolean canUseId(String id){
		
		SqlSession session=factory.openSession();
		String result=null;
		try{
			result=session.selectOne("users.isExistId", id);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		//result 가 null 이면 사용가능한 아이디 이다. 
		if(result==null){
			return true;
		}else{
			return false;
		}
	}
	public int getCount(UsersDto dto){
		SqlSession session=factory.openSession();
		int count=session.selectOne("users.getCount", dto);
		session.close();
		return count;
	}
	
}



























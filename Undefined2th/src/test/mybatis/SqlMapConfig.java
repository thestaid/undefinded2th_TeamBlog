package test.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {
	//static 맴버필드로 SqlSessionFactory Type 선언
	public static SqlSessionFactory sqlSession;
	//static 초기화
	static{
		String resource="test/mybatis/Configuration.xml";
		try{
			Reader reader=Resources.getResourceAsReader(resource);
			//SqlSessionFactory 객체의 참조값을 얻어내서 맴버필드에 저장
			sqlSession=new SqlSessionFactoryBuilder().build(reader);
			reader.close();
			System.out.println("SqlSessionFactory 객체 생성 성공!");
		}catch(Exception e){
			System.out.println("SqlSessionFactory 객체 생성 실패!");
		}
	}
	//static 맴버 메소드
	public static SqlSessionFactory getSqlSession(){
		return sqlSession;
	}
}

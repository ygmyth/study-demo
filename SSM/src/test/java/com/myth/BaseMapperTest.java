package com.myth;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.Reader;

public class BaseMapperTest {
	private static SqlSessionFactory sqlSessionFactory;
	
	@BeforeAll
	public static void init(){
		try {
            Reader reader = Resources.getResourceAsReader("mybatis-config-standalone.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException ignore) {
        	ignore.printStackTrace();
        }
	}
	
	public SqlSession getSqlSession(){
		return sqlSessionFactory.openSession();
	}
	
}
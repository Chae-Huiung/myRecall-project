package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.config.RootConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes ={RootConfig.class})
@Log4j
public class DataSourceTest{
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	//hikaricp를 통한 커넥션 획득 테스트
	@Test
	public void testConnection() {
		try {
			Connection con = dataSource.getConnection();
			log.info("hikaricp con test:"+con);
		}catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	//mybatis 연동 테스트 
	@Test 
	public void testMybatis() {
		try {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Connection con = sqlSession.getConnection();
			log.info("mybatis-test :"+con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
}

package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JdbcTests {

//	static {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	//오라클 db 연결 테스트
	@Test
	public void TestConnection() {
		try {
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "5449");	
			log.info(con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
			
			
	}
	
}

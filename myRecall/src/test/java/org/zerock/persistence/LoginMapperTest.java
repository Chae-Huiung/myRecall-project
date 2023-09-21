package org.zerock.persistence;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.CarVO;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.LoginMapper;
import org.zerock.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={org.zerock.config.RootConfig.class})
@Log4j
public class LoginMapperTest {
	
	@Autowired
	LoginMapper loginMapper;
	@Test
	public void loginTest(){
		String memberId = "test@test.com";
		String memberPw = "@aA12345678";
		
		try{
			MemberVO member = loginMapper.tryLogin(memberId, memberPw);
			log.info(member);
			
		}catch(Exception e){
			fail(e.getMessage());
		}
	
		
	}
}

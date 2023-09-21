package org.zerock.service;

import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.MemberVO;
import org.zerock.domain.MsgDTO;
import org.zerock.domain.SmsResponseDTO;

import lombok.Value;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {org.zerock.config.RootConfig.class})
@Log4j
public class MemberServiceTest {
	@Autowired
	MemberService memberServiceImpl;
	
	@Autowired
	SmsServiceImpl smsServiceImpl;
	
	@Test
	public void checkIdTest() throws Exception{
		String testId = "test@test.com";
		try{
			log.info("test결과 ------");
		String result = memberServiceImpl.checkIdDuple(testId);
		log.info("result :"+result);
			
		}catch(Exception e){
			log.info("예외 발생 ----");
			fail(e.getMessage());
		}
	
	}
	
	@Test
	public void smsSendTest() throws Exception{
		
		String phoneNum = "01079060722";
		String content ="test";
		String subject ="subject test";
		MsgDTO msgDTO =new MsgDTO(phoneNum,content,subject);
		
		SmsResponseDTO response = smsServiceImpl.sendSms(msgDTO);
		log.info("response:"+response);
	}
	
	
	@Test
	public void getMemberTest(){
		String memberId ="test@test.com";
		MemberVO member = memberServiceImpl.getMember(memberId);
		log.info(member);
		
	}



	
}
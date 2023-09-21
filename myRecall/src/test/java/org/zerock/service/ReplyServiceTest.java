package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {org.zerock.config.RootConfig.class})
@Log4j
public class ReplyServiceTest {

	@Autowired
	ReplyService replyService;
	@Test
	public void submitReplyTest(){
		String replyText="댓글등록 테스트";
		replyService.submitReply(replyText, 17577, "huiung011@gmail.com");
	}
}

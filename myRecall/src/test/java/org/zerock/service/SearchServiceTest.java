package org.zerock.service;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.RecallVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {org.zerock.config.RootConfig.class})
@Log4j
public class SearchServiceTest {

	@Autowired
	SearchService searchService;
	
	@Test
	public void getRecallPostsTest(){
		//String searchStr =" "; //검색창에 공백으로  입력되었을 때
		String searchStr ="아반"; // 검색창 검색어
		
		int page = 1;
		int size =10;
		ArrayList<RecallVO> posts =	searchService.getRecallPosts(searchStr, page, size);	
		
	}
}

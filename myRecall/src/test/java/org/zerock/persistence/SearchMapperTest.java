package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.CarVO;
import org.zerock.domain.PageCriteria;
import org.zerock.domain.RecallVO;
import org.zerock.mapper.SearchMapper;
import org.zerock.mapper.SelectionMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { org.zerock.config.RootConfig.class })
@Log4j
public class SearchMapperTest {

	@Autowired
	SearchMapper searchMapper;

	@Test
	public void getTotalPost() {
		ArrayList<String> searchStrs = new ArrayList<String>();

//		searchStrs.add("에어백");
//		searchStrs.add("현대");
//		searchStrs.add("아반떼");
		searchStrs.add("");

		int totalPostCount = searchMapper.getRecallPostCount(searchStrs);
		log.info(totalPostCount);
	}

	@Test
	public void getRecallPosts() {
		ArrayList<String> searchStrs = new ArrayList<String>();
		ArrayList<RecallVO> posts = new ArrayList<RecallVO>(); 
		int page = 1;
		int size = 100; 
		
		searchStrs.add("아반");
		int totalPostCount = searchMapper.getRecallPostCount(searchStrs);
		PageCriteria pageCri = new PageCriteria(totalPostCount, page, size);
		posts = searchMapper.getRecallPosts(searchStrs, pageCri);
		
		for(RecallVO post : posts){
			log.info(post);
		}
		
		
		}
	
}

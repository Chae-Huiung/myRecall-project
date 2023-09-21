package org.zerock.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.RecallVO;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {org.zerock.config.RootConfig.class})
@Log4j
public class getRecallDataServiceTest {
	@Autowired
	GetRecallDataService getRecallDataService;

	
	@Test //공공데이터 api로부터 리콜게시물 가져와서 recall_table에 추가하는 테스트
	public void StrToJsonTest() throws Exception {
		try{
			getRecallDataService.getRecallData();	
			log.info("recall_table 추가 완료");
		}catch(Exception e){
			fail(e.getMessage());
//		}
				
	}
}

	@Test // 공공데이터 api로부터 리콜게시물 가져와 recall_table에 게시물 추가 시 db에 없는 차종 car_table에 추가하는 테스트
	public void insertNewCarToCarTableTest(){
		try{
			getRecallDataService.insertNewCarToCarTable();
			log.info("test 완료");
		}catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void getRecallPosts(){
		
		String brand ="현대자동차(주)";
		String carName ="차량명";
		String fromDate ="생산기간";
		
		int page =2;
		int size =10;
	ArrayList<RecallVO> posts = getRecallDataService.getRecallPosts(brand, carName, fromDate,page,size);
		
	}
	
	@Test
	public void getRecallPost(){
		int recallNum =17577;
		getRecallDataService.getRecallPost(recallNum);
		log.info(recallNum);
	}
	
}
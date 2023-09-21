package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.CarVO;
import org.zerock.domain.RecallVO;
import org.zerock.mapper.RecallDataMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {org.zerock.config.RootConfig.class})
@Log4j
public class RecallDataMapperTest {

	@Autowired
	RecallDataMapper recallDataMapper;
	
	@Test
	public void insertDataTest(){
		
		ArrayList<RecallVO> recallList = new ArrayList<RecallVO>();
		RecallVO  recall1 = new RecallVO();
		recall1.setBrand("브랜드1");
		recall1.setCarName("차명1");
		recall1.setFromDate("2020-01-01");
		recall1.setToDate("2025-01-01");
		recall1.setRecallDate("2023-08-08");
		recall1.setRecallReason("리콜사유2");
		recallList.add(recall1);
		
		RecallVO  recall2 = new RecallVO();
		recall2.setBrand("브랜드2");
		recall2.setCarName("2020-01-01");
		recall2.setFromDate("2021-01-01");
		recall2.setToDate("2025-01-01");
		recall2.setRecallDate("2023-08-08");
		recall2.setRecallReason("리콜사유2");
		recallList.add(recall2);
		
		
		try{
			recallDataMapper.insertRecallData(recallList);
			log.info("insert 완료");
		}catch(Exception e){
			log.info("에러발생====");
			fail(e.getMessage());
		}
	}
	
	@Test
	public void compareCarTableWithRecallTableAndInsertTest(){
	ArrayList<CarVO> list = recallDataMapper.compareCarTableWithRecallTable();
	try{
		log.info("car_table과 recall_table 비교 후 새로운 차량 list ------");
		for(CarVO car: list){
			log.info(car);
		}
	recallDataMapper.insertNewCarsToCarTable(list);
	log.info("car_table에 신규 차량 등록 완료");
		
	}catch(Exception e){
		fail(e.getMessage());
	}
		
	
	}
	
	@Test
	public void getRecallPostCountTest(){
	int totalPostCount=	recallDataMapper.getRecallPostCount("test","test","test");
	log.info(totalPostCount);
	}

}

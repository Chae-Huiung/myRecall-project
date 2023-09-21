package org.zerock.persistence;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.CarVO;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={org.zerock.config.RootConfig.class})
@Log4j
public class MemberMapperTest {

	@Autowired
	private MemberMapper memberMapper;
	@Test
	//회원가입 시 id 중복 검사 테스트 
	public void checkIdDuplTest(){
//		 testIds[0]은 서버에 등록된 게정의 id
//		 testIds[1]은 등록되어 있지 않은 id
		String[] testIds = {"test@test.com","new@test.com"}; 
		
		String returnData;
		try{
			for(String testId: testIds){
				returnData = memberMapper.checkIdDupl(testId);
				if(returnData == null){
					log.info(testId+" :사용가능한 아이디");
				}
				else if(returnData.equals(testId)){
					log.info(returnData+": 이미 사용중인 아이디");
				}	
			}
				
		}catch(Exception e){
			fail(e.getMessage());
		}
		
//		log.info(memberMapper.checkIdDupl("test@test.com"));
			
	}
	
	@Test
	public void registerTest(){
		MemberVO member = new MemberVO(); //test할 정보 set
		member.setMemberId("test@test.com");
		member.setMemberPw("@hH12345678");
		member.setPhoneNum("01012345678");
		
		CarVO car = new CarVO(); // car_table에 있는 정보 set해야함
		car.setBrand("brand"); 
		car.setCarName("carName");
		car.setFromDate("2023-01-01");
		car.setToDate("2024-01-01");
		
		member.setCar(car);
		memberMapper.registerMember(member);
		
	}
	
}

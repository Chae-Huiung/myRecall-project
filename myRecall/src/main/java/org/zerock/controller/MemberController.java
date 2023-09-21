package org.zerock.controller;

import java.util.Random;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.MemberVO;
import org.zerock.domain.MsgDTO;
import org.zerock.domain.SmsResponseDTO;
import org.zerock.service.GetRecallDataService;
import org.zerock.service.GetRecallDataServiceImpl;
import org.zerock.service.MemberServiceImpl;
import org.zerock.service.SmsService;
import org.zerock.service.SmsServiceImpl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;

@Controller
@RequestMapping("/")
@Slf4j
public class MemberController{
	@Autowired
	MemberServiceImpl memberServiceImpl;
	@Autowired
	SmsServiceImpl smsServiceImpl;
	
	private String generatedCode ="";
	private String checkCode="";
	
//회원가입 버튼 클릭 시 제조사 셀렉션의 옵션 db 조회 후 Model 객체에 담아 전달 
@GetMapping("/signUp/brandSelectionOpt")
public void brandSelOpt(Model model){
	
}
	
//회원가입 시 아이디 중복 확인 
@PostMapping("/signUp/checkIdDupl")
@ResponseBody
public ResponseEntity<String> checkIdDupl(@RequestBody String id){
			
	try{
	
		String result = memberServiceImpl.checkIdDuple(id);
	
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}catch(Exception e){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
	
}

//회원가입 시 입력받은 전화번호로  sms code 발송 (naver cloud sms api)
@PostMapping("/signUp/sendCode")
@ResponseBody
public SmsResponseDTO sendCode(@RequestBody String phoneNum) throws Exception{
	//4자리 랜덤 숫자 생성
	String subject="myrecall 회원가입 인증 코드";
	Random randomNum = new Random();
	
	for(int i =0; i<4; i++){
		double base = randomNum.nextDouble()*10;
		int num =(int)Math.floor(base);
		log.info("base :"+base);
		log.info("num:"+num);
		generatedCode =generatedCode.concat(Integer.toString(num));	
	}
	log.info("code: "+generatedCode);
	
	MsgDTO msg = new MsgDTO(phoneNum,generatedCode,subject);
	checkCode=generatedCode;
	generatedCode = "";
	return smsServiceImpl.sendSms(msg); 
	}

//전송한 코드와 입력한 코드의 일치 여부 반환
@GetMapping("signUp/checkCode")
public ResponseEntity<String> checkCode(@RequestParam("code") String codeInput){
	 // 보낸 코드값과 
	if(codeInput.equals(checkCode)){
		return ResponseEntity.status(HttpStatus.OK).body("true");
	}else{
		return ResponseEntity.status(HttpStatus.OK).body("false");
	}
}


//서버에 회원 등록 후 mainPage로 redirect
@RequestMapping(value="/signUp/register", method=RequestMethod.POST, consumes="application/json")
public ResponseEntity<String> register(@RequestBody MemberVO member){
	
	HttpHeaders header = new HttpHeaders();
	header.set("Content-type","text/plain;charset=utf-8");
	try{
		memberServiceImpl.register(member);
		ResponseEntity<String> response = new ResponseEntity<String>("회원가입 완료",header,HttpStatus.OK);
		return response;
	}catch(Exception e){
		ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(),header,HttpStatus.OK);
		return response;
	}
	
	
	
}

}

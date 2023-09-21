package org.zerock.service;



import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.zerock.domain.MsgDTO;
import org.zerock.domain.SmsDTO;
import org.zerock.domain.SmsResponseDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

	@Value("${naver-cloud-sms.accessKey}")
	private String accessKey;
	@Value("${naver-cloud-sms.secretKey}")
	private String secretKey;
	@Value("${naver-cloud-sms.serviceId}")
	private String serviceId;
	@Value("${naver-cloud-sms.senderPhoneNum}")
	private String senderPhoneNum;
	
	
	//시그니쳐 생성 
	@Override
	public String makeSignature(String timeStamp) throws Exception {
		String space = " ";					// one space
		String newLine = "\n";					// new line
		String method = "POST";					// method
		String url = "/sms/v2/services/"+this.serviceId+"/messages";	// url (include query string)
		String timestamp = timeStamp;			// current timestamp (epoch)
		String accessKey = this.accessKey;			// access key id (from portal or Sub Account)
		String secretKey =this.secretKey;
	

		String message = new StringBuilder()
			.append(method)
			.append(space)
			.append(url)
			.append(newLine)
			.append(timestamp)
			.append(newLine)
			.append(accessKey)
			.toString();
		log.info("msg :" +message);
		log.info("timestamp :"+timeStamp);
		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(signingKey);
	
		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
		String encodeBase64String = Base64.encodeBase64String(rawHmac);

	  return encodeBase64String;
	}
	
	@Override
	//브라우저로부터 전화번호 받고 naver sms api에 response 전달
	public SmsResponseDTO sendSms(MsgDTO msgDTO) throws Exception{
		Long time = System.currentTimeMillis();
		String timestamp = time.toString();
		
		//1.http request 헤더 설정 
		HttpHeaders headers =new HttpHeaders();
		headers.set("Content-Type", "application/json; charset=utf-8");
		headers.set("x-ncp-apigw-timestamp",timestamp);
		headers.set("x-ncp-iam-access-key",this.accessKey);
		headers.set("x-ncp-apigw-signature-v2",makeSignature(timestamp));
		//2. http message body 설정 
		List<MsgDTO> messages = new ArrayList<MsgDTO>();
		messages.add(msgDTO);
		
		SmsDTO request = SmsDTO.builder()
				.type("SMS")
				.contentType("COMM")
				.countryCode("82")
				.from(this.senderPhoneNum)
				.subject(msgDTO.getSubject())
				.content(msgDTO.getContent())
				.messages(messages)
				.build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String body =objectMapper.writeValueAsString(request);
		HttpEntity<String> httpBody = new HttpEntity<>(body,headers);
		
		RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	    SmsResponseDTO response = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+this.serviceId+"/messages"), httpBody, SmsResponseDTO.class);
 
	    return response;	
	}

		
	
}

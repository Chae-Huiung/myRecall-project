package org.zerock.service;

import org.springframework.http.ResponseEntity;
import org.zerock.domain.MsgDTO;
import org.zerock.domain.SmsResponseDTO;

public interface SmsService {

	public String makeSignature(String timeStamp)throws Exception;
	
	public SmsResponseDTO sendSms(MsgDTO msgDTO) throws Exception;
}

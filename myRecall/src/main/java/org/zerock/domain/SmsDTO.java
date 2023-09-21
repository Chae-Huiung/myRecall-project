package org.zerock.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SmsDTO {
	String type;
	String contentType;
	String countryCode;
	String from;
	String subject;
	String content;
//	String reserveTime;
//	String reserveTimeZone;
	List<MsgDTO> messages;
	

}

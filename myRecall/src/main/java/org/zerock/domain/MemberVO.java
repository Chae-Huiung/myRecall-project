package org.zerock.domain;

import lombok.Data;

@Data
public class MemberVO {
	private String memberId;
	private String memberPw;
	private String PhoneNum;
	private boolean memberRole;
	private CarVO car;
	
}

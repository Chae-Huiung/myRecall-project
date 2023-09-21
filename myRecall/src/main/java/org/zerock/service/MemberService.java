package org.zerock.service;

import org.zerock.domain.MemberVO;

public interface MemberService {
	
	//아이디 중복 체크
	public String checkIdDuple(String id);
	//회원등록 
	public int register(MemberVO member);
	
	//memberId로 memberVO 반환 
	public MemberVO getMember(String memberId);
}

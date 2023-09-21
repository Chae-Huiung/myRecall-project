package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	//회원가입 시 아이디 중복 검사 중복 시 id 반환, 사용 가능 시 null 반환 
	@Override
	public String checkIdDuple(String memberId){
		
	String result =	memberMapper.checkIdDupl(memberId);
	return result;
		
	}

	@Override
	public int register(MemberVO member) {
		
		return memberMapper.registerMember(member);
	}

	@Override
	public MemberVO getMember(String memberId) {
		return memberMapper.getMember(memberId);

	}
	
	

}

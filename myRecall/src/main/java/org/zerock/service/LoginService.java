package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.LoginMapper;

@Service
public class LoginService {
	@Autowired
	LoginMapper loginMapper;
	public MemberVO tryLogin(String memberId, String memberPw){
		return loginMapper.tryLogin(memberId, memberPw);
	}
	
}

package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.domain.MemberVO;

@Mapper
public interface LoginMapper {
	
	//아이디 및 비밀번호 등록여부 검사 
	public MemberVO tryLogin(@Param("memberId") String memberId , @Param("memberPw") String memberPw);
}

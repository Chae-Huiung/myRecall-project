package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.domain.MemberVO;

@Mapper
public interface MemberMapper {

	//이메일(아이디) 중복 체크 ,사용 가능 시 null 반환, 사용 불가 시 등록된 아이디 반환 
	public String checkIdDupl(@Param("memberId") String memeberId);
	//회원가입 등록
	public int registerMember(@Param("member")MemberVO memberVO);
	
	//memberId 입력받아 memberVO 반환 
	public MemberVO getMember(@Param("memberId") String memberId);
}

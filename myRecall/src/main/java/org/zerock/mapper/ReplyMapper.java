package org.zerock.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.domain.PageCriteria;
import org.zerock.domain.ReplyVO;

@Mapper
public interface ReplyMapper {

	//리콜 게시물 등록번호와 사용자 아이디를 받아 댓글 등록 
	public int submitReply(@Param("reply") String reply, @Param("recallNum") int recallNum, @Param("memberId") String memberId );
	
	//recallPost에 해당하는 reply 개수 반환
	public int getReplyCount(@Param("recallNum")int recallNum);
	
	//recall 게시물의 reply 리스트 반환 
	public ArrayList<ReplyVO> getReplyList(@Param("recallNum") int recallNum , @Param("pageCri") PageCriteria pageCri); 
	
	// 댓글 번호 받아서 댓글 삭제 
	public int delReply(@Param("replyNum") String replyNum);
	
	// 댓글 번호 받아서 댓글 수정 
	public int updateReply(@Param("replyNum") String replyNum ,@Param("replyText") String replyText);
	
	
}

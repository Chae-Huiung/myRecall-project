package org.zerock.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.domain.CarVO;
import org.zerock.domain.PageCriteria;
import org.zerock.domain.RecallVO;

@Mapper
public interface SearchMapper {

	//검색조건에 해당하는 게시물 개수 반환 
	public int getRecallPostCount(@Param("searchStrs") ArrayList<String> searchStrs);
	
	//검색조건에 해당하는 게시물 리스트 반환 
	public ArrayList<RecallVO> getRecallPosts(@Param("searchStrs") ArrayList<String> searchStrs, @Param("pageCri") PageCriteria pageCri);
}

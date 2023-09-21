package org.zerock.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.domain.CarVO;
import org.zerock.domain.PageCriteria;
import org.zerock.domain.RecallVO;
import org.zerock.domain.ReplyVO;

import com.fasterxml.jackson.databind.JsonNode;

@Mapper
public interface RecallDataMapper {
	
	
	//공공 데이터 api호출을 통해 얻은 data를 db의 recall_table에 insert
	public void insertRecallData(ArrayList<RecallVO> list);
	
	//기존의 car_table과 recall_table을 비교하여 car_table에 없는 
	//신규 차량을 검색 후 list로 반환 
	public ArrayList<CarVO> compareCarTableWithRecallTable();
	
	//신규 차량을 car_table에 추가 
	public void insertNewCarsToCarTable(ArrayList<CarVO> vo);
	
	//검색조건에 맞는 게시물 개수 반환
	public int getRecallPostCount(@Param("brand")String brand, @Param("car") String car, @Param("fromDate") String fromDate);
	
	// select 메뉴를 통해 검색한 리콜 게시물 리스트 반환 
	public ArrayList<RecallVO> getRecallPosts(@Param("brand")String brand, @Param("car") String car, @Param("fromDate") String fromDate, @Param("pageCri") PageCriteria pageCri);
	
	//recallPost select 
	public RecallVO getRecallPost(@Param("recallNum") int recallNum);
	
	//게시물에 해당하는 댓글리스트 반환
	public ArrayList<ReplyVO> getReplyList(@Param("recallNum")int recallNum);
}

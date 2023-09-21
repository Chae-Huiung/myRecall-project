package org.zerock.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.PageCriteria;
import org.zerock.domain.RecallVO;
import org.zerock.mapper.SearchMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SearchService {
	
	@Autowired
	SearchMapper searchMapper;
	//검색창을 통해 검색 시 리콜 게시물 리스트 반환
	public ArrayList<RecallVO> getRecallPosts(String searchStr,int page, int size){
		//1. searchStr 공백 제거 후 단어로 분리 
		ArrayList<String> searchStrs = new ArrayList<String>();
		ArrayList<RecallVO> posts = new ArrayList<RecallVO>();
		String[] strArr = searchStr.split(" ");
		for (String str : strArr) {
			log.info("검색단어 :" + str);
			if(!str.equals(" ")){ //공백이 아닐 때만 리스트에 저장
				searchStrs.add(str);
			}
		}
		if(!searchStrs.isEmpty()){ //검색어 리스트가 있을 때
			int totalPostCount = searchMapper.getRecallPostCount(searchStrs);
			PageCriteria pageCri = new PageCriteria(totalPostCount, page, size);
			posts = searchMapper.getRecallPosts(searchStrs, pageCri);
			
			for (RecallVO post : posts) {
				post.setPageCri(pageCri);
			}
		}
		else{
			log.info("검색어 공백으로 인해 검색 로직 실행하지 않음");
		}
		for (RecallVO post : posts) {
			log.info(post.toString());
		}
		
		return posts;	
	}
}

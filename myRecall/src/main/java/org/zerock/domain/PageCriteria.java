package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageCriteria {

	int total; // 검색 조건에 해당하는 게시물 개수 
	int size; // 한 페이지 당 불러올 게시물 개수
	int page; // 현재 페이지 번호 
	int totalPages; //총 페이지 수 
	int firstPostNum;
	int lastPostNum; 
	boolean prev; // 이전 페이지 버튼 
	boolean next; // 다음 페이지 버튼 
	
	public PageCriteria(int total, int page, int size){
		this.prev =false;
		this.next =true;
		this.total = total;
		this.size = size;
		this.page = page;
		this.totalPages = (int)Math.ceil((double)total/size);
		this.firstPostNum =1+((page-1)*size);
		this.lastPostNum = page*size;
		
		if(page != 1){ //첫페이지가 아닐 때
			this.prev = true;
			if(page !=this.totalPages){ // 마지막 페이지가 아닐 때 
				this.next = true;
				}
			else{ //1이 아니고 마지막 페이지 일 때 
				this.next= false;
				this.lastPostNum = total;
			}
		}
		if(page == 1 && page ==totalPages){ // 첫페이지 이자 마지막 페이지 일 때
			 this.prev = false;
			 this.next = false;
		}
		
	}
}

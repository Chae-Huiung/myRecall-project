package org.zerock.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import oracle.sql.DATE;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ReplyVO {

	private int replyNum;
	private int memberNum;
	private String memberId;
	private int recallNum;
	private String replyText;
	private int likeCount;
	private Date replyDate;
	private PageCriteria pageCri;
}

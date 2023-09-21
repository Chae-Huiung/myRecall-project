package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RecallVO {
	private String recallNum;
	private String recallDate;
	private String recallReason;
	private String brand;
	private String carName;
	private String fromDate;
	private String toDate;
	private PageCriteria pageCri;

	
}

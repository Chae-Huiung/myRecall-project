package org.zerock.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsResponseDTO {

	String requestId;
	LocalDate requestTime;
	String statusName;
}

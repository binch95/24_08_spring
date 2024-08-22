package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class reactionPoint {

	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private int relId;
	private String relTypeCode;
	private int point;

}

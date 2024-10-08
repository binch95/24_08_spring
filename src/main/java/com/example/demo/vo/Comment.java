package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

	private int id;
	private String regDate;
	private String updateDate;
	private int articleId;
	private int memberId;
	private String body;
	private String nickname;
	private int viewNumber;
}

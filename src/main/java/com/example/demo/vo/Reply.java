package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

	private int id;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	private String body;
	private int relId;
	private String goodReactionPoint;
	private String badReactionPoint;
	
	private int memberId;
	private String extra__writer;

	private boolean userCanModify;
	private boolean userCanDelete;
}

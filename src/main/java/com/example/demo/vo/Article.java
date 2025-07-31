package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {

	int id;
	String title;
	String body;
	LocalDateTime regDate;
	LocalDateTime updateDate;

	public Article(String title, String body) {
		this.title = title;
		this.body = body;
	}

}
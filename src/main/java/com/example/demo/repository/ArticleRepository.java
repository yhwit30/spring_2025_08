package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleRepository {

	@Insert("INSERT INTO `article` SET `regDate` = NOW(), `updateDate` = NOW(), `title` = #{title}, `body` = #{body}, `memberId` = 1;")
	public Article writeArticle(String title, String body);

	public Article getArticleById(int id);

	public List<Article> getArticles();

	public Object modifyArticle(int id, String title, String body);

	public void deleteArticle(int id);

}

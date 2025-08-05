package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleRepository {

	public int writeArticle(String title, String body);

	public void deleteArticle(int id);

	public void modifyArticle(int id, String title, String body);

	public Article getArticleById(int id);

	public List<Article> getArticles();

	public int getLastInsertId();

}

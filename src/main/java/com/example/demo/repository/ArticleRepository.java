package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Article;

@Mapper
@Repository
public interface ArticleRepository {

	@Insert("INSERT INTO `article` SET `regDate` = NOW(), `updateDate` = NOW(), `title` = #{title}, `body` = #{body}, `memberId` = 1;")
	public int writeArticle(String title, String body);

	@Delete("DELETE FROM `article` WHERE `id` = #{id}")
	public void deleteArticle(int id);

	@Update("UPDATE `article` SET `updateDate` = NOW(), `title` = #{title}, `body` = #{body} WHERE `id` = #{id}")
	public Object modifyArticle(int id, String title, String body);

	@Select("SELECT * FROM `article` WHERE `id` = #{id}")
	public Article getArticleById(int id);

	@Select("SELECT * FROM `article`")
	public List<Article> getArticles();

}

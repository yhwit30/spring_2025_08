package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {

	@Autowired
	private ArticleService articleService;

	UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(HttpSession session, int id, String title, String body) {

		// 로그인체크
		boolean isLogined = false;
		int loginedMemberId = -1;
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		if (isLogined == false) {
			return ResultData.from("F-A", "로그인 하고 오세요.");
		}

		// 게시글 유무체크
		Article article = articleService.getArticleById(id);
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 글은 없습니다.", id));
		}

		// 권한체크
		ResultData loginedMemberAuthCheckRd = articleService.loginedMemberAuthCheck(loginedMemberId, article);
		if (loginedMemberAuthCheckRd.getResultCode().startsWith("F")) {
			return ResultData.from("F-A", loginedMemberAuthCheckRd.getMsg());
		}

		articleService.modifyArticle(id, title, body);

		article = articleService.getArticleById(id);

		return ResultData.from(loginedMemberAuthCheckRd.getResultCode(), loginedMemberAuthCheckRd.getMsg(), article, "수정한 글");
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Integer> doDelete(HttpSession session, int id) {
		// 로그인체크
		boolean isLogined = false;
		int loginedMemberId = -1;
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		if (isLogined == false) {
			return ResultData.from("F-A", "로그인 하고 오세요");
		}

		// 게시글 유무체크
		Article article = articleService.getArticleById(id);
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 글은 없습니다.", id));
		}

		// 권한체크
		ResultData loginedMemberAuthCheckRd = articleService.loginedMemberAuthCheck(loginedMemberId, article);
		if (loginedMemberAuthCheckRd.getResultCode().startsWith("F")) {
			return ResultData.from("F-A", loginedMemberAuthCheckRd.getMsg());
		}

		articleService.deleteArticle(id);

//		article = articleService.getArticleById(id);

		return ResultData.from("S-1", Ut.f("%d번 글이 삭제되었습니다.", id));
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(HttpSession session, String title, String body) {

		// 로그인체크
		boolean isLogined = false;
		int loginedMemberId = -1;

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		if (isLogined == false) {
			return ResultData.from("F-A", "로그인 하고 작성하세요.");
		}

		if (Ut.isEmptyOrNull(title)) {
			return ResultData.from("F-1", "제목을 입력하세요");
		}
		if (Ut.isEmptyOrNull(body)) {
			return ResultData.from("F-2", "내용을 입력하세요");
		}

		ResultData writeArticleRd = articleService.writeArticle(loginedMemberId, title, body);

		int id = (int) writeArticleRd.getData1();

		Article article = articleService.getArticleById(id);

		return ResultData.newData(writeArticleRd, article, "새로 작성된 글");
	}

	@RequestMapping("/usr/article/detail")
	public String getArticle(int id, Model model) {

		Article article = articleService.getArticleById(id);
//		if (article == null) {
//			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
//		}
		
		model.addAttribute("article", article);
		
		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/list")
	public String getArticles(Model model) {
		List<Article> articles = articleService.getArticles();

		model.addAttribute("articles",articles);
		
		return "usr/article/list";
	}

}

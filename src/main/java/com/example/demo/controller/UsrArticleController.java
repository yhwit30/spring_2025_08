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
		ResultData userCanModifyRd = articleService.userCanModify(loginedMemberId, article);
		if (userCanModifyRd.getResultCode().startsWith("F")) {
			return ResultData.from("F-A", userCanModifyRd.getMsg());
		}

		articleService.modifyArticle(id, title, body);

		article = articleService.getArticleById(id);

		return ResultData.from(userCanModifyRd.getResultCode(), userCanModifyRd.getMsg(), article, "수정한 글");
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpSession session, int id) {
		// 로그인체크
		boolean isLogined = false;
		int loginedMemberId = -1;
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		if (isLogined == false) {
//			return ResultData.from("F-A", "로그인 하고 오세요");
			return Ut.jsReplace("F-A", "로그인 하고 오세요", "../member/login");
		}

		// 게시글 유무체크
		Article article = articleService.getArticleById(id);
		if (article == null) {
//			return ResultData.from("F-1", Ut.f("%d번 글은 없습니다.", id));
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 글은 없습니다.", id));
		}

		// 권한체크
		ResultData userCanDeleteRd = articleService.userCanDelete(loginedMemberId, article);
		if (userCanDeleteRd.getResultCode().startsWith("F")) {
//			return ResultData.from("F-A", userCanModifyRd.getMsg());
			return Ut.jsHistoryBack("F-A", userCanDeleteRd.getMsg());
		}

		articleService.deleteArticle(id);

//		return ResultData.from("S-1", Ut.f("%d번 글이 삭제되었습니다.", id));
		return Ut.jsReplace(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg(), "../article/list");
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
	public String getArticle(HttpSession session, int id, Model model) {

		// 로그인체크
		boolean isLogined = false;
		int loginedMemberId = -1;

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		Article article = articleService.getForPrintArticle(loginedMemberId, id);
		
		model.addAttribute("article", article);

		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/list")
	public String getArticles(Model model) {
		List<Article> articles = articleService.getArticles();

		model.addAttribute("articles", articles);

		return "usr/article/list";
	}

}

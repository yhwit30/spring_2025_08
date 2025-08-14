package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller
public class UsrArticleController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private Rq rq;

	UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping("/usr/article/modify")
	public String modify(int id, Model model) throws IOException {
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		if(article == null) {
			rq.printHistoryBack(Ut.f("%d번 글은 없습니다.", id));
			return null;
		}

		model.addAttribute("article", article);

		return "/usr/article/modify";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {

		// 게시글 유무체크
		Article article = articleService.getArticleById(id);
		if (article == null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 글은 없습니다.", id));
		}

		// 권한체크
		ResultData userCanModifyRd = articleService.userCanModify(rq.getLoginedMemberId(), article);
		if (userCanModifyRd.getResultCode().startsWith("F")) {
			return Ut.jsHistoryBack("F-A", userCanModifyRd.getMsg());
		}

		articleService.modifyArticle(id, title, body);

		article = articleService.getArticleById(id);

		return Ut.jsReplace(userCanModifyRd.getResultCode(), userCanModifyRd.getMsg(), "../article/detail?id=" + id);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

//		Rq rq = (Rq) req.getAttribute("rq");

		// 게시글 유무체크
		Article article = articleService.getArticleById(id);
		if (article == null) {
//			return ResultData.from("F-1", Ut.f("%d번 글은 없습니다.", id));
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 글은 없습니다.", id));
		}

		// 권한체크
		ResultData userCanDeleteRd = articleService.userCanDelete(rq.getLoginedMemberId(), article);
		if (userCanDeleteRd.getResultCode().startsWith("F")) {
//			return ResultData.from("F-A", userCanModifyRd.getMsg());
			return Ut.jsHistoryBack("F-A", userCanDeleteRd.getMsg());
		}

		articleService.deleteArticle(id);

//		return ResultData.from("S-1", Ut.f("%d번 글이 삭제되었습니다.", id));
		return Ut.jsReplace(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg(), "../article/list");
	}

	@RequestMapping("/usr/article/write")
	public String showWrite() {
		
		return "/usr/article/write";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(String title, String body) {

//		Rq rq = (Rq) req.getAttribute("rq");

		if (Ut.isEmptyOrNull(title)) {
			return Ut.jsHistoryBack("F-1", "제목을 입력하세요");
		}
		if (Ut.isEmptyOrNull(body)) {
			return Ut.jsHistoryBack("F-2", "내용을 입력하세요");
		}

		ResultData writeArticleRd = articleService.writeArticle(rq.getLoginedMemberId(), title, body);

		int id = (int) writeArticleRd.getData1();

		Article article = articleService.getArticleById(id);

		return Ut.jsReplace(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), "../article/detail?id=" + id);
	}

	@RequestMapping("/usr/article/detail")
	public String getArticle(int id, Model model) {

//		Rq rq = (Rq) req.getAttribute("rq");

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		model.addAttribute("article", article);

		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model, int boardId) {
		List<Article> articles = articleService.getArticles();

		Board board = boardService.getBoardById(boardId);
		
		model.addAttribute("articles", articles);
		model.addAttribute("board", board);

		return "usr/article/list";
	}

}

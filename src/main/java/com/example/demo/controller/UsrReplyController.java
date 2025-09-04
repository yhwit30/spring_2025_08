package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Reply;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller
public class UsrReplyController {

	@Autowired
	private ReplyService replyService;
	@Autowired
	private Rq rq;

	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(int relId, String relTypeCode, String body) {

//		System.out.println("relId : " + relId);
//		System.out.println("relTypeCode : " + relTypeCode);
//		System.out.println("body : " + body);

		if (Ut.isEmptyOrNull(body)) {
			return Ut.jsHistoryBack("F-1", "내용을 입력하세요.");
		}

		ResultData writeReplyRd = replyService.doWrite(rq.getLoginedMemberId(), relId, relTypeCode, body);

		return Ut.jsReplace(writeReplyRd.getResultCode(), writeReplyRd.getMsg(), "../article/detail?id=" + relId);
	}

	@RequestMapping("/usr/reply/doDelete")
	@ResponseBody
	public String doDelete(int id, int articleId) {

		// 댓글 유무체크
		Reply reply = replyService.getReplyById(id);
		if (reply == null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 댓글은 없습니다.", id));
		}

		// 권한체크
		ResultData userCanDeleteRd = replyService.userCanDelete(rq.getLoginedMemberId(), reply);
		if (userCanDeleteRd.getResultCode().startsWith("F")) {
			return Ut.jsHistoryBack("F-A", userCanDeleteRd.getMsg());
		}

		replyService.deleteReply(id);

		return Ut.jsReplace(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg(),
				"../article/detail?id=" + articleId);
	}

	@RequestMapping("/usr/reply/doModify")
	@ResponseBody
	public String doModify(int id, String body) {

		// 댓글 유무체크
		Reply reply = replyService.getReplyById(id);
		if (reply == null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 글은 없습니다.", id));
		}

		// 권한체크
		ResultData userCanModifyRd = replyService.userCanModify(rq.getLoginedMemberId(), reply);
		if (userCanModifyRd.getResultCode().startsWith("F")) {
			return Ut.jsHistoryBack("F-A", userCanModifyRd.getMsg());
		}

		replyService.modifyReply(id, body);

		reply = replyService.getReplyById(id);

		return Ut.jsReplace(userCanModifyRd.getResultCode(), userCanModifyRd.getMsg(), "../article/detail?id=" + id);
	}
}

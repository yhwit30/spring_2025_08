package com.example.demo.controller;

import com.example.demo.service.ReactionPointService;
import com.example.demo.util.Ut;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrReactionPointController {

	@Autowired
    private ReactionPointService reactionPointService;
	@Autowired
	private Rq rq;

	@RequestMapping("/usr/reactionPoint/doGoodReaction")
	@ResponseBody
	public String doGoodReaction(String relTypeCode, int relId, String replaceUri) {
		
		int userReaction = reactionPointService.userCanReaction(rq.getLoginedMemberId(), relTypeCode, relId);
		
		if(userReaction == 1) {
			return Ut.jsHistoryBack("F-1", "이미 좋아요 함");
		}
		
		
		ResultData reactionRd = reactionPointService.increaseReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
		
		return Ut.jsReplace(reactionRd.getResultCode(), reactionRd.getMsg(), replaceUri);
		
		
		
	}
}








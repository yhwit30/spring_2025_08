package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ReplyRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Reply;
import com.example.demo.vo.ResultData;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyRepository replyRepository;

	public List<Reply> getForPrintReplies(String relTypeCode, int relId) {
		List<Reply> replies = replyRepository.getForPrintReplies(relTypeCode, relId);
		return replies;
	}

	public ResultData doWrite(int memberId, int relId, String relTypeCode, String body) {
		replyRepository.doWrite(memberId, relId, relTypeCode, body);
		
		int id = replyRepository.getLastInsertId();
		
		return ResultData.from("S-1", Ut.f("%d번 댓글이 등록되었습니다.", id), id, "등록된 댓글의 아이디");
	}

}

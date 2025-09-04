package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ReplyRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Reply;
import com.example.demo.vo.ResultData;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyRepository replyRepository;

	public List<Reply> getForPrintReplies(int loginedMemberId, String relTypeCode, int relId) {
		List<Reply> replies = replyRepository.getForPrintReplies(relTypeCode, relId);
		
		for(Reply reply : replies) {
			controlForPrintData(loginedMemberId, reply);
		}
		
		return replies;
	}

	public ResultData doWrite(int memberId, int relId, String relTypeCode, String body) {
		replyRepository.doWrite(memberId, relId, relTypeCode, body);
		
		int id = replyRepository.getLastInsertId();
		
		return ResultData.from("S-1", Ut.f("%d번 댓글이 등록되었습니다.", id), id, "등록된 댓글의 아이디");
	}

	public Reply getReplyById(int id) {
		Reply replay = replyRepository.getReplyById(id);
		return replay;
	}
	private void controlForPrintData(int loginedMemberId, Reply reply) {

		if (reply == null) {
			return;
		}
		ResultData userCanModifyRd = userCanModify(loginedMemberId, reply);
		reply.setUserCanModify(userCanModifyRd.isSuccess());

		ResultData userCanDeleteRd = userCanDelete(loginedMemberId, reply);
		reply.setUserCanDelete(userCanDeleteRd.isSuccess());
	}
	
	public ResultData userCanDelete(int loginedMemberId, Reply reply) {


		if (reply.getMemberId() != loginedMemberId) {
			return ResultData.from("F-A", Ut.f("%d번 게시글에 대한 권한 없음", reply.getId()));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시글 삭제됨", reply.getId()));
		
	}
	public ResultData userCanModify(int loginedMemberId, Reply reply) {
		if (reply.getMemberId() != loginedMemberId) {
			return ResultData.from("F-A", Ut.f("%d번 게시글에 대한 권한 없음", reply.getId()));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시글 수정됨", reply.getId()));
	}

	public void deleteReply(int id) {
		replyRepository.deleteReply(id);
		
	}

	public void modifyReply(int id, String body) {
		replyRepository.modifyReply(id, body);
	}

}












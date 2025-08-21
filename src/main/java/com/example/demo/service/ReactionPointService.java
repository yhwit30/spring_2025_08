package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ReactionPointRepository;
import com.example.demo.vo.ResultData;

@Service
public class ReactionPointService {
	
	@Autowired
	private ReactionPointRepository reactionPointRepository;

	public int userCanReaction(int loginedMemberId, String relTypeCode, int relId) {

		// 로그인 안한 상태
		if(loginedMemberId == 0) {
			return -2;
		}
		
		return reactionPointRepository.getSumReactionPoint(loginedMemberId, relTypeCode, relId);
	}

	public ResultData increaseReactionPoint(int loginedMemberId, String relTypeCode, int relId) {

		int affectedRow = reactionPointRepository.increaseReactionPoint(loginedMemberId, relTypeCode, relId);

		if(affectedRow != 1) {
			return ResultData.from("F-2", "좋아요 실패");
		}
		
		return ResultData.from("S-1", "좋아요!");
	}

}







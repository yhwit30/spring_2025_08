package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ReactionPointRepository;

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

}







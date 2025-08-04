package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {

		memberRepository.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

	}

}

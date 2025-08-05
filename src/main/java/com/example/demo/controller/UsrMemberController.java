package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {

		if (loginId == null || loginId.trim().length() == 0) {
			return "아이디를 입력하시오.";
		}
		if (loginPw == null || loginPw.trim().length() == 0) {
			return "비밀번호를 입력하시오.";
		}
		if (name == null || name.trim().length() == 0) {
			return "이름를 입력하시오.";
		}
		if (nickname == null || nickname.trim().length() == 0) {
			return "닉네임을 입력하시오.";
		}
		if (cellphoneNum == null || cellphoneNum.trim().length() == 0) {
			return "전화번호를 입력하시오.";
		}
		if (email == null || email.trim().length() == 0) {
			return "이메일를 입력하시오.";
		}

		int id = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

		// 중복된 데이터 체크 알림
		System.out.println("id : " + id);
		if (id == -1) {
			return "이미 사용 중인 아이디입니다.";
		}

		Member member = memberService.getMemberById(id);

		return member;
	}

}

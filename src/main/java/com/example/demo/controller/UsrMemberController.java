package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private Rq rq;

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {

//		Rq rq = (Rq) req.getAttribute("rq");
		rq.logout();

		return Ut.jsReplace("S-1", "로그아웃 되었습니다.", "/");
	}

	@RequestMapping("/usr/member/login")
	public String showLogin() {
		return "/usr/member/login";
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {

		if (Ut.isEmptyOrNull(loginId)) {
			return Ut.jsHistoryBack("F-2", "아이디를 입력하세요");
		}
		if (Ut.isEmptyOrNull(loginPw)) {
			return Ut.jsHistoryBack("F-3", "비밀번호를 입력하세요");
		}

		Member member = memberService.getMemberByLoginId(loginId);
		if (member == null) {
			return Ut.jsHistoryBack("F-4", Ut.f("%s는 없는 아이디야", loginId));
		}
		if (member.getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("F-5", "비밀번호가 일치하지 않음");
		}

//		Rq rq = (Rq) req.getAttribute("rq");
		rq.login(member);

		return Ut.jsReplace("S-1", Ut.f("%s님 환영합니다.", member.getNickname()), "/");

	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {

		if (Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-1", "아이디를 입력하시오.");
		}
		if (Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력하시오.");
		}
		if (Ut.isEmptyOrNull(name)) {
			return ResultData.from("F-3", "이름를 입력하시오.");
		}
		if (Ut.isEmptyOrNull(nickname)) {
			return ResultData.from("F-4", "닉네임을 입력하시오.");
		}
		if (Ut.isEmptyOrNull(cellphoneNum)) {
			return ResultData.from("F-5", "전화번호를 입력하시오.");
		}
		if (Ut.isEmptyOrNull(email)) {
			return ResultData.from("F-6", "이메일를 입력하시오.");
		}

		ResultData doJoinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (doJoinRd.isFail()) {
			return doJoinRd;
		}

		Member member = memberService.getMemberById((int) doJoinRd.getData1());

		return ResultData.newData(doJoinRd, member, "새로 가입한 회원");
	}

}

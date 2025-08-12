package com.example.demo.vo;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

@Getter
public class Rq {

	private boolean isLogined;
	private int loginedMemberId;

	private HttpServletRequest req;
	private HttpServletResponse resp;

	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;

		HttpSession httpSession = req.getSession();

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
	}

	public void printHistoryBack(String msg) throws IOException {

		resp.setContentType("text/html;charset=UTF-8");
		println("<script>");
		println("alert('rq 클래스 메시지 : " + msg + "');");
		println("history.back();");
		println("</script>");

	}

	public void println(String str) throws IOException {
		print(str + "\n");
	}

	public void print(String str) throws IOException {
		resp.getWriter().append(str);
	}

}

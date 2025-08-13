package com.example.demo.vo;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {

	private boolean isLogined = false;
	private int loginedMemberId = 0;

	private final HttpServletRequest req;
	private final HttpServletResponse resp;
	private final HttpSession session;

	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		this.session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
	}

	public void printHistoryBack(String msg) throws IOException {

		resp.setContentType("text/html;charset=UTF-8");
		println("<script>");
		println("alert('rq 클래스 메시지 : " + msg + "');");
//		println("history.back();");
		println("location.replace('/');");
		println("</script>");
		
		resp.getWriter().flush();
		resp.getWriter().close();
	}

	public void println(String str) throws IOException {
		print(str + "\n");
	}

	public void print(String str) throws IOException {
		resp.getWriter().append(str);
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
	}

	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}

	public void initBeforeActionInterceptor() {

		System.err.println("initBeforeActionInterceptor 실행됨");

	}

}

package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.err.println("로그인 체크 인터셉터");

		// 로그인체크
		Rq rq = (Rq) request.getAttribute("rq");
		if (rq.isLogined() == false) { // 로그인 안된 상태
			
			rq.printHistoryBack("로그인 하고 이용하세요");
			
			return false;
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}










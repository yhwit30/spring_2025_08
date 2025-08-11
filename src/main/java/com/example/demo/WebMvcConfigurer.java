package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.example.demo.interceptor.BeforeActionInterceptor;

@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**"); // 모든 요청이 들어가기 전에 인터셉터 실행하겠다.

	}

}

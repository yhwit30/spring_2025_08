package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrAjaxTestController {
	@RequestMapping("/usr/home/AjaxTest")
	public String AjaxTest() {
		return "/usr/home/AjaxTest";
	}
	
	@RequestMapping("/usr/home/AjaxTest2")
	public String AjaxTest2() {
		return "/usr/home/AjaxTest2";
	}
}

package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrAjaxTestController {
	@RequestMapping("/usr/home/AjaxTest")
	public String AjaxTest() {
		return "/usr/home/AjaxTest";
	}

	@RequestMapping("/usr/home/doPlus")
	@ResponseBody
	public String doPlus(int num1, int num2) {
		String msg = "더하기 성공";

		int rs = num1 + num2;

		return rs + "/" + msg + "/" + "S-1";
	}
}

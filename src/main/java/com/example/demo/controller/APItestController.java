package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APItestController {
	@RequestMapping("/usr/home/APItest")
	public String showAPITest() {
		return "/usr/home/APItest";
	}
	
	@RequestMapping("/usr/home/doPlus")
	@ResponseBody
	public String doPlus(int num1, int num2) {
		int sum = num1 + num2;
		return "" + sum;
	}
}

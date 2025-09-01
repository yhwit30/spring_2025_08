package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class APItestController {
	@RequestMapping("/usr/home/APItest")
	public String showAPITest() {
		return "/usr/home/APItest";
	}
}

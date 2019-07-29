package com.cafe24.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainPageController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String mainpage() {
		return "main";
	}
}

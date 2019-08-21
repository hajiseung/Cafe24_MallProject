package com.example.project_frontend.frontend.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project_frontend.frontend.dto.JSONResult;
import com.example.project_frontend.frontend.service.UserService;
import com.example.project_frontend.frontend.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/login")
	public String loginForm() {
		return "user/login";
	}

	@GetMapping(value = "/join")
	public String joinform() {
		return "user/join";
	}

	@PostMapping("/join")
	public String join(@ModelAttribute UserVo uservo) throws URISyntaxException {
		UserVo vo = service.joinUser(uservo);
		return "redirect:/";
	}

	@GetMapping("/checkid")
	@ResponseBody
	public JSONResult<Boolean> checkId(@RequestParam(value = "userid", defaultValue = "", required = true) String id)
			throws URISyntaxException {
		JSONResult<Boolean> result = service.checkId(id);
		return result;
	}

}

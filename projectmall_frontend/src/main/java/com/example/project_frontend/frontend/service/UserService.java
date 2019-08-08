package com.example.project_frontend.frontend.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.project_frontend.frontend.dto.JSONResult;
import com.example.project_frontend.frontend.vo.UserVo;

@Service
public class UserService {
	private String tmpUrl = "http://localhost:8080/projectmall_backend/api/user";
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserVo joinUser(UserVo uservo) throws URISyntaxException {
		uservo.setPw(passwordEncoder.encode(uservo.getPw()));
		System.out.println();
		System.out.println(uservo);
		System.out.println();
		RestTemplate template = new RestTemplate();
		URI requestUri = new URI(tmpUrl + "/join");
		JSONResult<UserVo> result = null;
		result = template.postForObject(requestUri, uservo, JSONResultUser.class);
		return result.getData();
	}

	public JSONResult<Boolean> checkId(String id) throws URISyntaxException {
		UserVo uservo = new UserVo();
		uservo.setId(id);

		RestTemplate template = new RestTemplate();
		URI requestUri = new URI(tmpUrl + "/checkuserid");
		JSONResult<Boolean> result = null;
		result = template.postForObject(requestUri, uservo, JSONResultUserCheck.class);
		return result;
	}

	public UserVo getUser(String id) throws URISyntaxException {
		UserVo uservo = new UserVo();
		uservo.setId(id);
		RestTemplate template = new RestTemplate();
		URI requestUri = new URI(tmpUrl + "/getuser");
		JSONResult<UserVo> result = null;
		result = template.postForObject(requestUri, uservo, JSONResultUser.class);
		System.out.println("@@@@@@@@@" + result + "@@@@@@@");
		return (UserVo) result.getData();
	}

	public UserVo login(String id) throws URISyntaxException {
		UserVo uservo = new UserVo();
		uservo.setId(id);

		RestTemplate template = new RestTemplate();
		URI requestUri = new URI(tmpUrl + "/login");
		JSONResult<UserVo> result = null;
		result = template.postForObject(requestUri, uservo, JSONResultUser.class);
		return (UserVo) result.getData();
	}

	// DTO Class
	private static class JSONResultUser extends JSONResult<UserVo> {
	}

	private static class JSONResultUserCheck extends JSONResult<Boolean> {
	}
}

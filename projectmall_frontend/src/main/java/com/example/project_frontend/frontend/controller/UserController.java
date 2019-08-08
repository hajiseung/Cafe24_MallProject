package com.example.project_frontend.frontend.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.project_frontend.frontend.dto.JSONResult;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/login")
	public String login() throws URISyntaxException {
//		RestTemplate restTemplate = new RestTemplate();
//		URI uri = new URI("http://localhost:8080/projectmall_backend/api/admin/memberlist");
//
//		ResponseEntity<JSONResult> a = restTemplate.getForEntity(uri, JSONResult.class);
//		System.out.println("========================================================");
//		System.out.println(a);
//		System.out.println("========================================================");
//		System.out.println(a.getBody().getData());
//		System.out.println("========================================================");
//		List b= new ArrayList();
//		b.addAll((Collection) a.getBody().getData());
//		System.out.println(b.get(0));
//		System.out.println("========================================================");
//		System.out.println(b.get(1));
//		System.out.println("========================================================");
		return "user/login";
	}

	@GetMapping(value = "/join")
	public String joinform() {
		return "user/join";
	}
}

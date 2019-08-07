package com.example.project_frontend.frontend.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {
	@RequestMapping({ "/", "/main" })
	public String main() throws URISyntaxException {

		return "main/index";
	}

	@RequestMapping("/test")
	public void test() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI("http://localhost:8080/projectmall_backend/api/admin/memberlist");
		String a = restTemplate.getForObject(uri, String.class);
		System.out.println(a);
	}
}

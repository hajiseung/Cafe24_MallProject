package com.example.project_frontend.frontend.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.project_frontend.frontend.dto.JSONResult;
import com.example.project_frontend.frontend.vo.AdminVo;
import com.example.project_frontend.frontend.vo.CategoryVo;

@Service
public class AdminService {
	private String tmpUrl = "http://localhost:8080/projectmall_backend/api/admin";

	// get 방식
	public Object getRestTemplateData(String uri, Object vo) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = new URI(uri);
		JSONResult result = null;
		if (vo instanceof List) {
			System.out.println("vo instanceof List ACCESS");
			result = restTemplate.getForObject(requestUri, JSONResultListCategoryVo.class);
		}
		return result.getData();
	}

	public Object postRestTemplateData(String uri, Object vo) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = new URI(uri);
		JSONResult result = null;
		if (vo instanceof AdminVo) {
			System.out.println("vo instanceof AdminVo ACCESS");
			result = restTemplate.postForObject(requestUri, vo, JSONResultAdminVo.class);
		} else if (vo instanceof CategoryVo) {
			System.out.println("vo instanceof CategoryVo ACCESS");
			result = restTemplate.postForObject(requestUri, vo, JSONResultCategoryVo.class);
		} else if (vo instanceof List) {
			System.out.println("vo instanceof List ACCESS");
			result = restTemplate.postForObject(requestUri, vo, JSONResultListCategoryVo.class);
		} else if (vo instanceof String) {
			System.out.println("vo instanceof String ACCESS");
			result = restTemplate.postForObject(requestUri, vo, JSONResultListCategoryVo.class);
		}
		return result.getData();
	}

//	public AdminVo login(AdminVo vo) throws URISyntaxException {
//		AdminVo result = (AdminVo) postRestTemplateData("http://localhost:8080/projectmall_backend/api/admin/login",
//				vo);
//		return result;
//	}

	public AdminVo login(String id) throws URISyntaxException {
		AdminVo vo = new AdminVo();
		vo.setId(id);
		AdminVo result = (AdminVo) postRestTemplateData(tmpUrl + "/login", vo);
		return result;
	}

	// DTO Class
	private static class JSONResultAdminVo extends JSONResult<AdminVo> {
	}

	private static class JSONResultCategoryVo extends JSONResult<CategoryVo> {
	}

	private static class JSONResultListCategoryVo extends JSONResult<List<CategoryVo>> {
	}

	private static class JSONResultGoodsList extends JSONResult<String> {
	}

	public CategoryVo addcategory(CategoryVo vo) throws URISyntaxException {
		CategoryVo result = (CategoryVo) postRestTemplateData(tmpUrl + "/category/add", vo);
		return result;
	}

	public Map<String, Set<String>> categoryList() throws URISyntaxException {
		List<CategoryVo> result = new ArrayList<>();
		result.addAll((Collection<CategoryVo>) getRestTemplateData(tmpUrl + "/category/view", result));
		Set<String> topcategory = new TreeSet<>();
		Set<String> lowcategory = new TreeSet<>();
		for (int i = 0; i < result.size(); i++) {
			topcategory.add(result.get(i).getTop_category());
			lowcategory.add(result.get(i).getLow_category());
		}
		Map<String, Set<String>> resultMap = new HashMap<>();
		resultMap.put("topcategory", topcategory);
		resultMap.put("lowcategory", lowcategory);
		return resultMap;
	}

	public Set<String> getLowCategory(CategoryVo vo) throws URISyntaxException {
		List<CategoryVo> result = new ArrayList<>();
		Set<String> lowcategory = new TreeSet<>();
		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = new URI(tmpUrl + "/lowcategory");

		JSONResult jsonResult = restTemplate.postForObject(requestUri, vo, JSONResultListCategoryVo.class);
		result.addAll((Collection<? extends CategoryVo>) jsonResult.getData());

		for (int i = 0; i < result.size(); i++) {
			lowcategory.add(result.get(i).getLow_category());
		}
		return lowcategory;
	}

}

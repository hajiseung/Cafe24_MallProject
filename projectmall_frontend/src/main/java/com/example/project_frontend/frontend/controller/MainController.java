package com.example.project_frontend.frontend.controller;

import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project_frontend.frontend.service.AdminService;
import com.example.project_frontend.frontend.vo.CategoryVo;
import com.example.project_frontend.frontend.vo.ItemVo;
import com.example.project_frontend.frontend.vo.UserVo;

@Controller
public class MainController {
	@Autowired
	private AdminService service;

	@RequestMapping({ "/", "/main" })
	public String main(Model model) throws URISyntaxException {
		Map<String, Set<String>> map = service.categoryList();
		List<String> list = new ArrayList<>();
		List<Set<String>> lowCategory = new ArrayList<Set<String>>();
		list.addAll(map.get("topcategory"));
		CategoryVo vo = new CategoryVo();

		for (int i = 0; i < map.get("topcategory").size(); i++) {
			vo.setTop_category(list.get(i));
			lowCategory.add(service.getLowCategory(vo));
		}
		model.addAttribute("topcategory", map.get("topcategory"));
		model.addAttribute("lowcategory", lowCategory);
		for (int i = 0; i < lowCategory.size(); i++) {
			model.addAttribute("lowcategory" + i, lowCategory.get(i));
		}
		List<ItemVo> itemList = service.itemList();
		model.addAttribute("item", itemList);

		return "main/index";
	}

	@RequestMapping("/item/{no}")
	public String itemDetail(@PathVariable("no") long no, Model model) throws URISyntaxException {
		ItemVo itemNo = new ItemVo();
		itemNo.setNo(no);
		ItemVo vo = service.getItem(itemNo);
		model.addAttribute("item", vo);
		return "main/itemdetail";
	}

	@RequestMapping(value = "/basket/add/{no}")
	public String basketAdd(@PathVariable("no") long no, Model model, Principal principal) throws URISyntaxException {
		UserVo vo = new UserVo();
		vo.setId(principal.getName());
		vo = service.getuser(vo, no);
		if (vo != null) {
			return "main/successbasket";
		}
		return null;

	}
//	@RequestMapping("/test")
//	public void test() throws URISyntaxException {
//		RestTemplate restTemplate = new RestTemplate();
//		URI uri = new URI("http://localhost:8080/projectmall_backend/api/admin/memberlist");
//		String a = restTemplate.getForObject(uri, String.class);
//		System.out.println(a);
//	}
}

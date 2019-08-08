package com.example.project_frontend.frontend.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.project_frontend.frontend.service.AdminService;
import com.example.project_frontend.frontend.vo.CategoryVo;
import com.example.project_frontend.frontend.vo.ItemVo;

@Controller
public class AdminController {

	@Autowired
	private AdminService service;

	@GetMapping("/loginadmin")
	public String loginAdmin() {
		return "admin/loginadmin";
	}

	@GetMapping("/additem")
	public String addItem(Model model) throws URISyntaxException {
		Map<String, Set<String>> map = service.categoryList();
		System.out.println(map);
		model.addAttribute("categoryMap", map);
		return "admin/additem";
	}

	@GetMapping("/addcategory")
	public String addCategory(Model model) throws URISyntaxException {
		Map<String, Set<String>> map = service.categoryList();

		model.addAttribute("categoryList", map);
		return "admin/addcategory";
	}

	@PostMapping("/getlowcategory")
	@ResponseBody
	public Set<String> getLowCategory(@ModelAttribute("top_category") CategoryVo top, Model model)
			throws URISyntaxException {
		System.out.println(top);
		if ("null".equals(top)) {
			return null;
		}
		Set<String> set = service.getLowCategory(top);
		return set;
	}

	@PostMapping("/addcategory")
	public String addCategory(CategoryVo vo) throws URISyntaxException {
		CategoryVo result = service.addcategory(vo);
		return "redirect:/addcategory";
	}

	@PostMapping("/additem")
	public String addItem(@ModelAttribute @Valid ItemVo itemVo, BindingResult result, Model model) throws URISyntaxException {
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "admin/additem";
		}
		service.additem(itemVo);
		return "redirect:/";
	}
}

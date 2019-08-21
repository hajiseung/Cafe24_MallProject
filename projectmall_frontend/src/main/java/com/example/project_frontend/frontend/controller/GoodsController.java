package com.example.project_frontend.frontend.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project_frontend.frontend.security.SecurityUser;
import com.example.project_frontend.frontend.service.AdminService;
import com.example.project_frontend.frontend.service.GoodsService;
import com.example.project_frontend.frontend.vo.BasketListVo;
import com.example.project_frontend.frontend.vo.OrderVo;
import com.example.project_frontend.frontend.vo.PaymentVo;
import com.example.project_frontend.frontend.vo.UserVo;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private GoodsService service;

	@Autowired
	private AdminService adminService;
//	@ResponseBody
//	@RequestMapping("/list/{page}")
//	public String list(@PathVariable("page") Integer page) {
////		goodsService.getList();
//		return "ok";
//	}

	@RequestMapping("/detail")
	public String detail() {
		return "goods/itemdetail";
	}

	@RequestMapping("/list")
	public String list(@AuthenticationPrincipal SecurityUser user, Model model) throws URISyntaxException {
		UserVo vo = new UserVo();
		vo.setNo(user.getNo());
		List<BasketListVo> basketListVo = adminService.itemList(vo);
		model.addAttribute("basketlist", basketListVo);

		return "goods/basketlist";
	}

	@PostMapping("/orderreg")
	public String orderReg(@AuthenticationPrincipal SecurityUser user, @ModelAttribute BasketListVo basketListVo,
			Model model) throws URISyntaxException {
		UserVo vo = service.getUserOneAllData(user);
		List<PaymentVo> list = service.getMethodOfPayment();
		model.addAttribute("basketlist", basketListVo.getBasketListVo());
		model.addAttribute("uservo", vo);
		model.addAttribute("payment", list);
		return "goods/order";
	}

	@PostMapping("/purchase")
	public String purchase(@ModelAttribute OrderVo orderVo, Model model) throws URISyntaxException {
		String result = service.purchase(orderVo);
		if("fail".equals(result)) {
			return "goods/orderfail";
		}
		return "goods/ordersuccess";
	}
}

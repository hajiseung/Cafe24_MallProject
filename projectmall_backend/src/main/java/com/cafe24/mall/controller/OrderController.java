package com.cafe24.mall.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.OrderService;
import com.cafe24.mall.vo.OrderVo;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	// 주문 등록
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> addPurchase(@RequestBody OrderVo orderVo, BindingResult result) {
		// 등록 오류시 에러 출력
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		orderService.addPurchase(orderVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(orderVo));
	}
}

package com.cafe24.mall.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.BasketService;
import com.cafe24.mall.vo.BasketVo;
import com.cafe24.mall.vo.ItemVo;
import com.cafe24.mall.vo.ListVo;
import com.cafe24.mall.vo.NonUserVo;

@RestController
@RequestMapping(value = "/api/basket")
public class BasketController {
	@Autowired
	private BasketService basketService;

	// 바로구매
	@RequestMapping(value = "/immediate", method = RequestMethod.POST)
	public void immediatelyPurchase(ItemVo itemVo) {
		basketService.immediatelyPurchase(itemVo);
	}

	// 장바구니 저장
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> addItemToBasket(@RequestBody BasketVo basketVo) {
		NonUserVo isRegNonUser = new NonUserVo();
		if (basketVo.getMember_no() != 0) {
			basketVo.setMember_no(basketVo.getMember_no());
			basketVo.setNonmember_no(0);
		} else {
			NonUserVo tmp = basketService.getNonMemberNo(basketVo.getNonUserVo().getMac_addr());
			isRegNonUser.setNo(tmp == null ? 0 : tmp.getNo());

			// 비회원 테이블에 이미 등록되어있는 비회원일때
			if (isRegNonUser.getNo() != 0) {
				basketVo.setNonmember_no(isRegNonUser.getNo());
			} else {
				// 비회원 테이블 추가
				isRegNonUser.setNo((basketService.addNonMember(basketVo.getNonUserVo().getMac_addr())).getNo());
				// 비회원 구분번호 설정
				basketVo.setNonmember_no(isRegNonUser.getNo());
				basketVo.setMember_no(0);
			}
		}
		basketVo = basketService.addItemToBasket(basketVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(basketVo));
	}

	// 장바구니 리스트 호출
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void getBasketList(@RequestBody BasketVo basketVo) {
		List<ListVo> list = basketService.getBasketList(basketVo);
	}

	// 장바구니-물품 삭제
	@RequestMapping(value = { "/delete/{memberNo:[\\d]+}/{itemNo}/{basketNo}",
			"/delete/{nonMember}/{itemNo}/{basketNo}" }, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> deleteItemFromBasket(@PathVariable Optional<Integer> memberNo,
			@PathVariable Optional<String> nonMember, @PathVariable("itemNo") int itemNo,
			@PathVariable("basketNo") int basketNo, @RequestBody BasketVo basketVo) {

		if (memberNo.isPresent()) {
			basketVo.setMember_no(memberNo.get());
			basketVo.setNonmember_no(0);
		} else {
			NonUserVo nonUserVo = basketService.getNonMemberNo(nonMember.get());
			basketVo.setNonmember_no(nonUserVo.getNo());
			basketVo.setMember_no(0);
		}
		basketVo.setNo(basketNo);
		basketVo.setItem_no(itemNo);

		System.out.println(basketVo);
		basketService.deleteItemFromBasket(basketVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(basketVo));
	}

	// 장바구니 물품 주문 페이지
	@RequestMapping(value = "/form/orderfrombasket", method = RequestMethod.GET)
	public String getOrderFromBasket() {
		return "api/basket/form/orderfrombasket";
	}

	// 장바구니 물품 주문
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public void orderFromBasket() {
		basketService.orderFromBasket();
	}
}

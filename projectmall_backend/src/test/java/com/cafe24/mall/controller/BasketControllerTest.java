package com.cafe24.mall.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.mall.config.AppConfig;
import com.cafe24.mall.config.TestWebConfig;
import com.cafe24.mall.service.BasketService;
import com.cafe24.mall.vo.BasketVo;
import com.cafe24.mall.vo.NonUserVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
public class BasketControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private BasketService basketService;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testBasketServiceNotNull() {
		assertNotNull(basketService);
	}

	// 바로구매 페이지 이동 테스트
	@Test
	public void testImmedaiteBasket() throws Exception {
		BasketVo vo = new BasketVo();
		NonUserVo userVo = new NonUserVo();
		// 회원 장바구니 추가
		vo.setItem_no(1L);
		vo.setMember_no(5L);
		vo.setItem_count(2L);
		vo.setAccmulate(100);
		vo.setOption_no(1L);
		vo.setNonUserVo(userVo);

		ResultActions resultActions = mockMvc.perform(
				post("/api/basket/immediate").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());

		// 비회원 장바구니 추가(처음x)
		vo = new BasketVo();
		userVo = new NonUserVo();
		userVo.setMac_addr("nonmem");
		vo.setItem_no(1L);
		vo.setMember_no(0);
		vo.setItem_count(2L);
		vo.setAccmulate(100);
		vo.setOption_no(1L);
		vo.setNonUserVo(userVo);

		resultActions = mockMvc.perform(
				post("/api/basket/immediate").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());
	}

	// 장바구니 등록 테스트
	@Test
	public void testInsertBaseket() throws Exception {
		BasketVo vo = new BasketVo();
		NonUserVo userVo = new NonUserVo();
		// 회원 장바구니 추가
		vo.setItem_no(1L);
		vo.setMember_no(5L);
		vo.setItem_count(2L);
		vo.setAccmulate(100);
		vo.setOption_no(1L);
		vo.setNonUserVo(userVo);

		ResultActions resultActions = mockMvc.perform(
				post("/api/basket/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());

		// 비회원 장바구니 추가
		userVo.setMac_addr("nomem1");
		vo.setItem_no(1L);
		vo.setMember_no(0L);
		vo.setItem_count(2L);
		vo.setAccmulate(100);
		vo.setOption_no(1L);
		vo.setNonUserVo(userVo);

		resultActions = mockMvc.perform(
				post("/api/basket/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());
	}

	// 장바구니 삭제 테스트
	@Test
	public void testDeleteBasket() throws Exception {
		BasketVo vo = new BasketVo();
		ResultActions resultActions = mockMvc.perform(get("/api/basket/delete/nomem1/1/3")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());
	}

	// 장바구니 리스트
	@Test
	public void testBasketList() throws Exception {
		BasketVo vo = new BasketVo();
		vo.setMember_no(5);

		ResultActions resultActions = mockMvc.perform(
				post("/api/basket/list").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());
	}
}

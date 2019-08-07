package com.cafe24.mall.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.cafe24.mall.service.AdminService;
import com.cafe24.mall.vo.AdminVo;
import com.cafe24.mall.vo.BasketVo;
import com.cafe24.mall.vo.CategoryVo;
import com.cafe24.mall.vo.ItemVo;
import com.cafe24.mall.vo.TermVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
public class AdminControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private AdminService adminService;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testDIAdminService() {
		assertNotNull(adminService);
	}

	@Test
	public void testAdminLogin() throws Exception{
		AdminVo vo = new AdminVo();
		vo.setId("admin");
		vo.setPw("1234");
		ResultActions resultActions = mockMvc.perform(
				post("/api/admin/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());

	}
	// 카테고리 등록
	@Test
	public void testCategoryAdd() throws Exception {
		// 정상 insert
		CategoryVo vo = new CategoryVo();
		vo.setTop_category("상의");
		vo.setLow_category("티셔츠");
		ResultActions resultActions = mockMvc.perform(
				post("/api/admin/category/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());

		// 유효성 검사 탈락
		vo = new CategoryVo();
		vo.setTop_category("상의");
		resultActions = mockMvc.perform(
				post("/api/admin/category/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}

	// 카테고리 수정
	@Test
	public void testCategoryModify() throws Exception {
		CategoryVo vo = new CategoryVo();
		vo.setNo(2);
		vo.setTop_category("악세사리");
		vo.setLow_category("손목시계");
		ResultActions resultActions = mockMvc.perform(post("/api/admin/category/modify")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());

		// 상위 카테고리 미 입력
		vo = new CategoryVo();
		vo.setNo(2);
		vo.setLow_category("손목시계");
		resultActions = mockMvc.perform(post("/api/admin/category/modify")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions.andExpect(status().isBadRequest()).andDo(print());
		
		// 하위 카테고리 미 입력
		vo = new CategoryVo();
		vo.setNo(2);
		vo.setTop_category("악세사리");
		resultActions = mockMvc.perform(post("/api/admin/category/modify")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions.andExpect(status().isBadRequest()).andDo(print());

	}

	// 카테고리 삭제
	public void testCategoryDelete() throws Exception {
		CategoryVo vo = new CategoryVo();
		ResultActions resultActions = mockMvc.perform(post("/api/admin/category/delete")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());

	}

	// 카테고리 조회
	@Test
	public void testCategoryView() throws Exception {
		CategoryVo vo = new CategoryVo();
		vo.setNo(1);
		ResultActions resultActions = mockMvc.perform(post("/api/admin/category/view")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		resultActions.andExpect(status().isOk()).andDo(print());

		vo = new CategoryVo();
		vo.setNo(1);
		resultActions = mockMvc.perform(post("/api/admin/category/view1").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(vo)));
		resultActions.andExpect(status().isOk()).andDo(print());

		vo = new CategoryVo();
		vo.setNo(1);
		resultActions = mockMvc.perform(post("/api/admin/category/view1/11").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(vo)));
		resultActions.andExpect(status().isOk()).andDo(print());

		vo = new CategoryVo();
		vo.setNo(1);
		resultActions = mockMvc.perform(post("/api/admin/category/view/1/1").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(vo)));
		resultActions.andExpect(status().isOk()).andDo(print());

	}

	// 물품 등록 Test
	@Test
	public void testAdminInsertItem() throws Exception {
		List<String> option = new ArrayList<String>();
		List<Boolean> isMain = new ArrayList<Boolean>();
		List<String> photo = new ArrayList<String>();

		option.add("빨강");
		option.add("노랑");
		isMain.add(true);
		isMain.add(false);
		photo.add("사진1");
		photo.add("사진2");

		ItemVo vo = new ItemVo();
		CategoryVo categoryVo = new CategoryVo();

		// Item Add 성공
		vo.setCategoryVo(categoryVo);
		vo.setTitle("티셔츠");
		vo.setAmount(100);
		vo.setAvailable_amount(100);
		vo.setDesc_html("설명");
		vo.setDisplaystatus(false);
		vo.setName(option);
		vo.setNon_amount(false);
		vo.setPrice(10000);
		vo.setReg_date("2019-07-10 00:00:00");
		vo.setSalestatus(true);
		categoryVo.setTop_category("하의");
		categoryVo.setLow_category("반바지");
		vo.setPhoto(photo);
		vo.setIs_main(isMain);

		ResultActions resultActions = mockMvc.perform(
				post("/api/admin/item/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());

		// Item Add 실패
		vo.setTitle(null);
		vo.setAmount(100);
		vo.setAvailable_amount(100);
		vo.setDesc_html("설명");
		vo.setDisplaystatus(false);
		vo.setName(option);
		vo.setPrice(10000);
		vo.setReg_date("2019-07-10 00:00:00");
		vo.setSalestatus(true);
		vo.setPhoto(photo);
		categoryVo.setTop_category("옷");
		categoryVo.setLow_category("상의");
		vo.setIs_main(isMain);

		resultActions = mockMvc.perform(
				post("/api/admin/item/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}

	// 물품 상태 변경 Test
	@Test
	public void testItemStatus() throws Exception {
		ItemVo vo = new ItemVo();
		BasketVo basketVo = new BasketVo();
		vo.setNo(1);
		vo.setSalestatus(false);
		vo.setBasketVo(basketVo);
		basketVo.setMember_no(1);
		basketVo.setItem_no(1);
		ResultActions resultActions = mockMvc.perform(
				post("/api/admin/item/status").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());
	}

	// 물품 수정 Test
	@Test
	public void testItemModify() throws Exception {
		ItemVo vo = new ItemVo();
		vo.setNo(1);
		vo.setItem_detail_no(1);
		vo.setTitle("타이틀 정보 수정");
		vo.setDesc_html("본문 수정");
		vo.setCategory_no(1);
		vo.setAmount(50);
		vo.setAvailable_amount(10);
		vo.setPrice(2000);
		vo.setNon_amount(true);
		vo.setDisplaystatus(true);
		vo.setSalestatus(true);

		ResultActions resultActions = mockMvc.perform(
				post("/api/admin/item/modify").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());

//		// price Null
		vo = new ItemVo();
		vo.setAmount(1);
		vo.setAvailable_amount(1);
		vo.setNon_amount(true);
		vo.setDisplaystatus(true);
		vo.setSalestatus(true);
		resultActions = mockMvc.perform(
				post("/api/admin/item/modify").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}

	// 물품 삭제 Test
	@Test
	public void testItemDelte() throws Exception {
		ItemVo vo = new ItemVo();
		vo.setNo(2);
		ResultActions resultActions = mockMvc.perform(
				post("/api/admin/item/delete").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());
	}

	// 약관 동의 등록 Test
	@Test
	public void testAddTerms() throws Exception {
		TermVo vo = new TermVo();
		vo.setTitle("제목1");
		vo.setContents("동의서1");
		vo.setIsnecessary(true);
		vo.setRegistrant("하지승");
		ResultActions resultActions = mockMvc.perform(
				post("/api/admin/terms/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());

		// 동의서 제목 없을 때
		vo = new TermVo();
		vo.setContents("동의서1");
		vo.setIsnecessary(true);
		vo.setRegistrant("하지승");
		resultActions = mockMvc.perform(
				post("/api/admin/terms/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
		
		// 동의서 내용 없을 때
		vo = new TermVo();
		vo.setTitle("제목1");
		vo.setIsnecessary(true);
		vo.setRegistrant("하지승");
		resultActions = mockMvc.perform(
				post("/api/admin/terms/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions.andExpect(status().isBadRequest()).andDo(print());
		
		// 동의서 작성자 없을 때
		vo = new TermVo();
		vo.setTitle("제목1");
		vo.setContents("동의서1");
		vo.setIsnecessary(true);
		resultActions = mockMvc.perform(
				post("/api/admin/terms/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}

	// 약관 동의 수정 Test
	@Test
	public void testModifyTerms() throws Exception {
		TermVo vo = new TermVo();
		vo.setNo(3);
		vo.setTitle("제목");
		vo.setContents("내용변환 1");
		vo.setModifier("넬라스");
		ResultActions resultActions = mockMvc.perform(
				post("/api/admin/terms/modify").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());
	}

	// 약관 동의 조회 Test
	@Test
	public void testViewTerms() throws Exception {
		TermVo vo = new TermVo();
		ResultActions resultActions = mockMvc.perform(
				post("/api/admin/terms/view").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());
	}

	// 약관 동의 삭제 Test
	@Test
	public void testDeleteTerms() throws Exception {
		TermVo vo = new TermVo();
		vo.setNo(2);
		ResultActions resultActions = mockMvc.perform(
				post("/api/admin/terms/delete").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print());
	}

	// 사용자 출력 Test
	@Test
	public void testUserList() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/admin/memberlist"));
		resultActions.andExpect(status().isOk()).andDo(print());
	}
}

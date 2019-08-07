package com.cafe24.mall.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import com.cafe24.mall.service.UserService;
import com.cafe24.mall.vo.UserVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
public class UserControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private UserService userService;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testDIUserService() {
		assertNotNull(userService);
	}

	// 회원가입 Test
	@Test
	public void testUserJoin() throws Exception {

		UserVo vo = new UserVo();
		ResultActions resultActions;
		// 정상 가입
		vo.setId("tmdwlgk0109");
		vo.setEmail("tmdwlgk0109@naver.com");
		vo.setPw("1q2w3e4r@");
		vo.setName("하지승");
		vo.setNickname("Nomacs");
		vo.setAddr("동탄");
		vo.setTell_ph(null);
		vo.setCell_ph("010-3333-7777");
		vo.setBirthday("901006");
		vo.setEmail_recv(false);
		vo.setSms_recv(false);
		vo.setNo(1L);
		resultActions = mockMvc
				.perform(post("/api/user/join").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.data.no", is((int) vo.getNo())));
		// 아이디 오류
		vo.setId("tmdwlgk0109@@");
		vo.setEmail("tmdwlgk0109@naver.com");
		vo.setPw("1q2w3e4r@");
		vo.setName("하지승");
		vo.setNickname("Nomacs");
		vo.setAddr("동탄");
		vo.setTell_ph(null);
		vo.setCell_ph("010-3333-7777");
		vo.setBirthday("901006");
		vo.setEmail_recv(false);
		vo.setSms_recv(false);
		resultActions = mockMvc
				.perform(post("/api/user/join").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		resultActions.andExpect(status().isBadRequest()).andDo(print());

		// 이메일 오류
		vo.setId("tmdwlgk0109");
		vo.setEmail("tmdwlgk0109");
		vo.setPw("1q2w3e4r@");
		vo.setName("하지승");
		vo.setNickname("Nomacs");
		vo.setAddr("동탄");
		vo.setTell_ph(null);
		vo.setCell_ph("010-3333-7777");
		vo.setBirthday("901006");
		vo.setEmail_recv(false);
		vo.setSms_recv(false);
		resultActions = mockMvc
				.perform(post("/api/user/join").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		resultActions.andExpect(status().isBadRequest()).andDo(print());

		// 전화번호 오류
		vo.setId("tmdwlgk0109");
		vo.setEmail("tmdwlgk0109@naver.com");
		vo.setPw("1q2w3e4r@");
		vo.setName("하지승");
		vo.setNickname("Nomacs");
		vo.setAddr("동탄");
		vo.setTell_ph(null);
		vo.setCell_ph("111-3333-7777");
		vo.setBirthday("901006");
		vo.setEmail_recv(false);
		vo.setSms_recv(false);
		resultActions = mockMvc
				.perform(post("/api/user/join").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		resultActions.andExpect(status().isBadRequest()).andDo(print());

		// 비밀번호 오류
		vo.setId("tmdwlgk0109");
		vo.setEmail("tmdwlgk0109@naver.com");
		vo.setPw("1");
		vo.setName("하지승");
		vo.setNickname("Nomacs");
		vo.setAddr("동탄");
		vo.setTell_ph(null);
		vo.setCell_ph("010-3333-7777");
		vo.setBirthday("901006");
		vo.setEmail_recv(false);
		vo.setSms_recv(false);
		resultActions = mockMvc
				.perform(post("/api/user/join").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}

	// ID 중복 체크 Test
	@Test
	public void testUserIdCheck() throws Exception {
		UserVo vo = new UserVo();
		// DB에 ID 없을 때
		vo.setId("tmdwlgk01092");
		ResultActions resultActions = mockMvc.perform(
				post("/api/user/checkuserid").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.data", is(true)));

		// DB에 ID 있을 때
		vo.setId("tmdwlgk0109");
		resultActions = mockMvc.perform(
				post("/api/user/checkuserid").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.data", is(false)));

		// ID형식 틀렸을 때
		vo.setId("asd");
		resultActions = mockMvc.perform(
				post("/api/user/checkuserid").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}

	// 로그인 Test
	@Test
	public void testUserLogin() throws Exception {
		UserVo vo = new UserVo();

		// ID/PW 제대로 입력
		vo.setId("tmdwlgk0109");
		vo.setPw("1q2w3e4r@");
		vo.setNo(1L);

		ResultActions resultActions = mockMvc.perform(
				post("/api/user/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.data.no", is((int) vo.getNo())));

		// PW 입력 오류
		vo.setId("tmdwlgk0109");
		vo.setPw("1q2w3e4r");
		vo.setNo(1L);

		resultActions = mockMvc.perform(
				post("/api/user/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());

		// ID 입력 오류
		vo.setId("tmdwlgk010");
		vo.setPw("1q2w3e4r@");
		vo.setNo(1L);

		resultActions = mockMvc.perform(
				post("/api/user/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		resultActions.andExpect(status().isBadRequest()).andDo(print());

		// ID,PW 입력 오류
		vo.setId("tmdwlgk010");
		vo.setPw("1q2w3e");
		vo.setNo(1L);

		resultActions = mockMvc.perform(
				post("/api/user/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}

	// 회원 정보 수정 Test
	@Test
	public void testUserModify() throws Exception {
		UserVo vo = new UserVo();
		vo.setId("tmdwlgk0109");
		vo.setEmail("tmdwlgk0109@daum.com");
		vo.setPw("1q2w3e4r@");
		vo.setName("하지승");
		vo.setAddr("DongTan");
		vo.setTell_ph(null);
		vo.setCell_ph("010-3337-7686");
		vo.setBirthday("901006");
		vo.setEmail_recv(true);
		vo.setSms_recv(true);

		vo.setNickname("Nellas");
		ResultActions resultActions = mockMvc.perform(
				post("/api/user/modify").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print())/* .andExpect(jsonPath("$.data.no", is(1))) */;
		
		// E-Mail형식오류
		vo = new UserVo();
		vo.setId("tmdwlgk0109");
		vo.setEmail("tmdwlgk0109");
		vo.setPw("1q2w3e4r@");
		vo.setName("하지승");
		vo.setAddr("DongTan");
		vo.setTell_ph(null);
		vo.setCell_ph("010-3337-7686");
		vo.setBirthday("901006");
		vo.setEmail_recv(true);
		vo.setSms_recv(true);

		vo.setNickname("Nellas");
		resultActions = mockMvc.perform(
				post("/api/user/modify").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
		
		// 핸드폰 형식오류
		vo = new UserVo();
		vo.setId("tmdwlgk0109");
		vo.setEmail("tmdwlgk0109@naver.com");
		vo.setPw("1q2w3e4r@");
		vo.setName("하지승");
		vo.setAddr("DongTan");
		vo.setTell_ph(null);
		vo.setCell_ph("01654987");
		vo.setBirthday("901006");
		vo.setEmail_recv(true);
		vo.setSms_recv(true);
		
		vo.setNickname("Nellas");
		resultActions = mockMvc.perform(
				post("/api/user/modify").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions.andExpect(status().isBadRequest()).andDo(print());
		
		// 비밀번호 형식오류
		vo = new UserVo();
		vo.setId("tmdwlgk0109");
		vo.setEmail("tmdwlgk0109@naver.com");
		vo.setPw("1q2w3e4r");
		vo.setName("하지승");
		vo.setAddr("DongTan");
		vo.setTell_ph(null);
		vo.setCell_ph("010-3337-7686");
		vo.setBirthday("901006");
		vo.setEmail_recv(true);
		vo.setSms_recv(true);
		
		vo.setNickname("Nellas");
		resultActions = mockMvc.perform(
				post("/api/user/modify").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}

	// 회원 삭제 Test
	@Test
	public void testUserDelete() throws Exception {
		UserVo vo = new UserVo();
		vo.setNo(4);
		ResultActions resultActions = mockMvc.perform(
				post("/api/user/delete").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.data", is(1)));
	}
}

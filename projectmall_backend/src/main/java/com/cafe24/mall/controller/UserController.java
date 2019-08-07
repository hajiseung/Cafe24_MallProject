package com.cafe24.mall.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.UserService;
import com.cafe24.mall.vo.UserVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	// 회원 가입 폼 이동
	@RequestMapping(value = "/joinform", method = RequestMethod.GET)
	public String joinFormUser() {
		return "api/user/join";
	}

	// 회원 가입
	@ApiOperation(value = "회원 가입")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "아이디", required = true, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "email", value = "이메일", required = true, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "pw", value = "비밀번호", required = true, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "name", value = "이름", required = true, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "nickname", value = "별명", required = true, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "tell_ph", value = "집전화", required = false, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "cell_ph", value = "핸드폰", required = true, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "join_date", value = "가입일", required = false, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "addr", value = "주소", required = true, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "point", value = "포인트", required = false, dataType = "string", defaultValue = "100"),
			@ApiImplicitParam(name = "saving", value = "적립금", required = false, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "birthday", value = "생일", required = false, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "massive_mount", value = "누적금액", required = false, dataType = "int", defaultValue = "0"),
			@ApiImplicitParam(name = "grade", value = "등급", required = true, dataType = "MemberEnum", defaultValue = "BRONZE"),
			@ApiImplicitParam(name = "email_recv", value = "Email수신여부", required = false, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "sms_recv", value = "sms수신여부", required = false, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "purchase_cnt", value = "구매횟수", required = false, dataType = "int", defaultValue = "0"),
			@ApiImplicitParam(name = "islogin", value = "로그인 유무", required = false, dataType = "boolean", defaultValue = "false") })
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> joinUser(@RequestBody @Valid UserVo userVo, BindingResult result) {

		// 가입 오류시 에러 출력
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}

		UserVo userNo = userService.joinUser(userVo);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(userNo));
	}

	// 회원 로그인 폼
	@RequestMapping(value = "/loginform", method = RequestMethod.GET)
	public String loginFormUser() {
		return "api/user/login";
	}

	// 회원 로그인
	@ApiOperation(value = "회원 로그인")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "아이디", required = true, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "pw", value = "비밀번호", required = true, dataType = "string", defaultValue = "")})
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> loginUser(@RequestBody UserVo userVo) {
		UserVo result = userService.loginUser(userVo);
		if (result == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("로그인에 실패 하였습니다."));
		}
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}

	// 회원 아이디 중복 체크
	@ApiOperation(value = "회원 아이디 중복체크")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "아이디", required = true, dataType = "string", defaultValue = "")})
	@RequestMapping(value = "/checkuserid", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> checkuserId(@RequestBody UserVo userVo) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<UserVo>> validatorResults = validator.validateProperty(userVo, "id");

		if (validatorResults.isEmpty() == false) {
			for (ConstraintViolation<UserVo> validatorResult : validatorResults) {
				String message = messageSource.getMessage("NotEmpty.userVo.id", null, LocaleContextHolder.getLocale());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(message));
			}
		}

		boolean resultB = userService.checkUserId(userVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(resultB));
	}

	// 회원정보 수정 폼

	// 회원정보 수정
	@ApiOperation(value = "회원 정보 수정")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "title", value = "제목", required = true, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "desc", value = "본문", required = false, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "amount", value = "총 수량", required = false, dataType = "int", defaultValue = ""),
			@ApiImplicitParam(name = "available_amount", value = "판매 가능 수량", required = true, dataType = "long", defaultValue = ""),
			@ApiImplicitParam(name = "price", value = "가격", required = true, dataType = "long", defaultValue = ""),
			@ApiImplicitParam(name = "non_amount", value = "재고여부 상품", required = true, dataType = "boolean", defaultValue = "false"),
			@ApiImplicitParam(name = "displaystatus", value = "디스플레이 여부", required = true, dataType = "boolean", defaultValue = "false"),
			@ApiImplicitParam(name = "salestatus", value = "세일 여부", required = true, dataType = "boolean", defaultValue = "false"),
			@ApiImplicitParam(name = "reg_date", value = "등록일", required = false, dataType = "String", defaultValue = ""),
			@ApiImplicitParam(name = "multiPartPhoto", value = "Multipart사진", required = true, dataType = "List<MultipartFile>", defaultValue = ""),
			@ApiImplicitParam(name = "photo", value = "사진명", required = true, dataType = "List<String>", defaultValue = ""),
			@ApiImplicitParam(name = "is_main", value = "메인사진 여부 확인", required = true, dataType = "List<Boolean>", defaultValue = "false"),
			@ApiImplicitParam(name = "name", value = "옵션 명", required = true, dataType = "List<String>", defaultValue = ""),
			@ApiImplicitParam(name = "top_category", value = "상위 카테고리", required = true, dataType = "String", defaultValue = ""),
			@ApiImplicitParam(name = "low_category", value = "하위 카테고리", required = true, dataType = "String", defaultValue = "")
			})
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> modifyUser(@RequestBody @Valid UserVo userVo, BindingResult result) {
		// 오류시 에러 출력
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		int sqlresult = userService.modifyUser(userVo);
		System.out.println(sqlresult);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(userVo));
//		return "redirect:/";
	}

	// 회원 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}

	// 회원 탈퇴
	@ApiOperation(value = "회원 탈퇴")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "no", value = "회원번호", required = true, dataType = "int", defaultValue = "")})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> secessionUser(@RequestBody UserVo userVo) {
		int result = userService.secessionUser(userVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(result));
	}

}

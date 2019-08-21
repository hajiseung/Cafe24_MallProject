package com.cafe24.mall.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.AdminService;
import com.cafe24.mall.vo.AdminVo;
import com.cafe24.mall.vo.BasketListVo;
import com.cafe24.mall.vo.CategoryVo;
import com.cafe24.mall.vo.ItemVo;
import com.cafe24.mall.vo.TermVo;
import com.cafe24.mall.vo.UserVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	// 관리자 로그인 페이지
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> adminLogin(@RequestBody AdminVo vo, BindingResult result) {
		// 등록 오류시 에러 출력
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		AdminVo adminvo = adminService.login(vo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(adminvo));
	}

	// 카테고리 등록
	@RequestMapping(value = "/category/add", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> addCategory(@RequestBody @Valid CategoryVo categoryVo, BindingResult result) {
		// 등록 오류시 에러 출력
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		adminService.addCategory(categoryVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(categoryVo));
	}

	// 카테고리 수정
	@RequestMapping(value = "/category/modify", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> modifyCategory(@RequestBody @Valid CategoryVo categoryVo, BindingResult result) {
		// 등록 오류시 에러 출력
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		int resultSql = adminService.modifyCategory(categoryVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(resultSql));
	}

	// 카테고리 삭제
	// 카테고리 조회
	@RequestMapping(value = { "/category/view**", "/category/view/**",
			"/category/view**/**" }, method = RequestMethod.GET)
	public ResponseEntity<JSONResult> viewCategory() {
		List<CategoryVo> resultSql = adminService.viewCategory();
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(resultSql));
	}

	// 카테고리 no 가져오기
	@RequestMapping(value = "/getcategoryno", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> getCategoryNo(@RequestBody ItemVo itemVo) {
		CategoryVo vo = adminService.getCategoryNo(itemVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
	}

	// low카테고리 조회
	@RequestMapping(value = "/lowcategory", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> lowCategory(@RequestBody CategoryVo topCategory) {
		List<CategoryVo> resultSql = adminService.lowCategory(topCategory);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(resultSql));
	}

	// 물품 등록 페이지
	@RequestMapping(value = "/form/item", method = RequestMethod.GET)
	public String getaddItemForm() {
		return "api/admin/form/item";
	}

	// 물품 등록
	@ApiOperation(value = "물품 등록")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "email", value = "이메일", required = true, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "pw", value = "비밀번호", required = false, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "nickname", value = "별명", required = false, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "tell_ph", value = "집 전화번호", required = false, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "cell_ph", value = "핸드폰 번호", required = true, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "addr", value = "주소", required = true, dataType = "string", defaultValue = ""),
			@ApiImplicitParam(name = "email_recv", value = "email 수신 여부", required = true, dataType = "boolean", defaultValue = "false"),
			@ApiImplicitParam(name = "sms_recv", value = "sms 수신 여부", required = true, dataType = "boolean", defaultValue = "false"), })
	@RequestMapping(value = "/item/add", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> addItem(@RequestBody @Valid ItemVo itemVo, BindingResult result) {
		// 등록 오류시 에러 출력
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		ItemVo vo = adminService.addItem(itemVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
	}

	@RequestMapping(value = "/item/get", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> getItem(@RequestBody ItemVo itemVo, BindingResult result) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		ItemVo vo = adminService.getItem(itemVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
	}

	// 아이템 리스트
	@RequestMapping(value = "/item/list", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> listItem() {
		List<ItemVo> resultSql = adminService.listItem();
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(resultSql));
	}

	@RequestMapping(value = "/item/list/{no}", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> listItem(@PathVariable("no") long no) {
		List<BasketListVo> resultSql = adminService.listItem(no);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(resultSql));
	}

	// 판매 상태 API
	@RequestMapping(value = "/item/status", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> statusItem(@RequestBody ItemVo itemVo, BindingResult result) {
		// 에러 출력
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		int resultSql = adminService.statusItem(itemVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(resultSql));
	}

	// 물품 정보 수정
	@RequestMapping(value = "/item/modify", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> modifyItem(@RequestBody @Valid ItemVo itemVo, BindingResult result) {
		// 수정 에러 출력
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		int resultSql = adminService.modifyItem(itemVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(resultSql));
	}

	// 삭제 API
	@RequestMapping(value = "/item/delete", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> deleteItem(@RequestBody ItemVo itemVo, BindingResult result) {
		// 삭제 에러 출력
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		int resultSql = adminService.deleteItem(itemVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(resultSql));
	}

	// 회원 관리 페이지
	@RequestMapping(value = "/form/memberlist", method = RequestMethod.GET)
	public String getMemberList() {
		return "api/admin/form/memberlist";
	}

	// 회원 리스트
	@ApiOperation(value = "회원 리스트")
	@RequestMapping(value = "/memberlist", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> memberList() {
		List<UserVo> list = adminService.memberList();
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(list));
	}

	// 주문 관리 페이지 호출
	@RequestMapping(value = "/form/orderlist", method = RequestMethod.GET)
	public String getorderListForm() {
		return "api/admin/form/orderlist";
	}

	// 주문 관리 페이지 변경시
	@RequestMapping(value = "/orderlist", method = RequestMethod.POST)
	public void orderList() {
		adminService.orderList();
	}

	// 재고 관리 페이지 호출
	@RequestMapping(value = "/form/itemlist", method = RequestMethod.GET)
	public String getitemListForm() {
		return "api/admin/form/itemlist";
	}

	// 재고 관리
	@RequestMapping(value = "/itemlist", method = RequestMethod.GET)
	public void itemList() {
		adminService.itemList();
	}

	// 약관 동의서 등록
	@RequestMapping(value = "/terms/add", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> addTerms(@RequestBody @Valid TermVo termVo, BindingResult result) {
		// 등록 에러 출력
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		TermVo resultSql = adminService.addTerms(termVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(resultSql));
	}

	// 약관 동의서 수정
	@RequestMapping(value = "/terms/modify", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> modifyTerms(@RequestBody TermVo termVo, BindingResult result) {
		// 수정 에러 출력
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		int resultSql = adminService.modifyTerms(termVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(resultSql));
	}

	// 약관 동의서 조회
	@RequestMapping(value = "/terms/view", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> viewTerms(@RequestBody TermVo termVo, BindingResult result) {
		// 조회 에러 출력
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		List<TermVo> resultSql = adminService.viewTerms(termVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(resultSql));
	}

	// 약관 동의서 삭제
	@RequestMapping(value = "/terms/delete", method = RequestMethod.POST)
	public ResponseEntity<JSONResult> deleteTerms(@RequestBody TermVo termVo, BindingResult result) {
		// 삭제 에러 출력
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
			}
		}
		int resultSql = adminService.deleteTerms(termVo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(resultSql));
	}
}

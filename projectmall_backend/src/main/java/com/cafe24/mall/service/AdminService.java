package com.cafe24.mall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.repository.AdminDao;
import com.cafe24.mall.repository.BasketDao;
import com.cafe24.mall.vo.AdminVo;
import com.cafe24.mall.vo.BasketListVo;
import com.cafe24.mall.vo.BasketVo;
import com.cafe24.mall.vo.CategoryVo;
import com.cafe24.mall.vo.ItemVo;
import com.cafe24.mall.vo.TermVo;
import com.cafe24.mall.vo.UserVo;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private BasketDao basketDao;

	// 물품추가
	public ItemVo addItem(ItemVo itemVo) {
		return adminDao.addItem(itemVo);
	}

	// 사용자 List 출력
	public List<UserVo> memberList() {
		return adminDao.getUserList();
	}

	public void orderList() {
	}

	public void itemList() {
	}

	// 물품 판매 상태 변경
	public int statusItem(ItemVo itemVo) {
		int result = 0;
		// 판매 상태 변경
		result = adminDao.statusItem(itemVo);

		// 판매 안함으로 변경시 User 장바구니에서 제거
		result += basketDao.deleteItemToBasket(itemVo.getBasketVo());
		return result;
	}

	// 물품 삭제
	public int deleteItem(ItemVo itemVo) {
		return adminDao.deleteItem(itemVo);
	}

	// 물품 수정
	public int modifyItem(ItemVo itemVo) {
		return adminDao.modifyItem(itemVo);
	}

	// 카테고리 등록
	public CategoryVo addCategory(CategoryVo categoryVo) {
		return adminDao.addCategory(categoryVo);
	}

	public int modifyCategory(CategoryVo categoryVo) {
		return adminDao.modifyCategory(categoryVo);
	}

	public List<CategoryVo> viewCategory() {
		return adminDao.viewCategory();
	}

	public TermVo addTerms(TermVo termVo) {
		return adminDao.addTerms(termVo);
	}

	public List<TermVo> viewTerms(TermVo termVo) {
		return adminDao.viewTerms(termVo);
	}

	public int deleteTerms(TermVo termVo) {
		return adminDao.deleteTerms(termVo);
	}

	public int modifyTerms(TermVo termVo) {
		return adminDao.modifyTerms(termVo);
	}

	public AdminVo login(AdminVo vo) {
		return adminDao.login(vo);
	}

	public List<CategoryVo> lowCategory(CategoryVo topCategory) {
		return adminDao.getLowCategory(topCategory);
	}

	public CategoryVo getCategoryNo(ItemVo itemVo) {
		return adminDao.getCategoryNo(itemVo);
	}

	public List<ItemVo> listItem() {
		return adminDao.getItemList();
	}

	public ItemVo getItem(ItemVo itemVo) {
		return adminDao.getItem(itemVo);
	}

	public List<BasketListVo> listItem(long no) {
		UserVo vo = new UserVo();
		vo.setNo(no);
		List<BasketListVo> result = adminDao.getItemList(vo);
		return result;
	}
}

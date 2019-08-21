package com.cafe24.mall.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mall.vo.AdminVo;
import com.cafe24.mall.vo.BasketListVo;
import com.cafe24.mall.vo.CategoryVo;
import com.cafe24.mall.vo.ItemVo;
import com.cafe24.mall.vo.TermVo;
import com.cafe24.mall.vo.UserVo;

@Repository
public class AdminDao {

	@Autowired
	private SqlSession sqlSession;

	@Transactional
	public ItemVo addItem(ItemVo itemVo) {

		Map<String, Object> map = new HashMap<>();
		// 카테고리 추출
		CategoryVo vo = sqlSession.selectOne("admin.getCategoryNo", itemVo.getCategoryVo());
		itemVo.setCategory_no(vo.getNo());
		// Item 등록
		sqlSession.insert("admin.addItem", itemVo);
		// Item_detail 등록
		sqlSession.insert("admin.addItemDetail", itemVo);

		// Item_option
		for (int i = 0; i < itemVo.getName().size(); i++) {
			map.put("no", itemVo.getNo());
			map.put("option", itemVo.getName().get(i));
			sqlSession.insert("admin.addItemOption", map);
			map.clear();
		}

		// Item_photo
		for (int i = 0; i < itemVo.getPhoto().size(); i++) {
			map.put("no", itemVo.getNo());
			map.put("photo", itemVo.getPhoto().get(i));
			map.put("is_main", itemVo.getIs_main().get(i));
			sqlSession.insert("admin.addItemPhoto", map);
			map.clear();
		}
		return itemVo;
	}

	public List<UserVo> getUserList() {
		return sqlSession.selectList("admin.getUserList");
	}

	@Transactional
	public int statusItem(ItemVo itemVo) {
		return sqlSession.update("admin.statusItem", itemVo);
	}

	public int deleteItem(ItemVo itemVo) {
		int result;
		result = sqlSession.delete("admin.deleteItem_option", itemVo);
		result += sqlSession.delete("admin.deleteItem_photo", itemVo);
		result += sqlSession.delete("admin.deleteItem_detail", itemVo);
		result += sqlSession.delete("admin.deleteItem", itemVo);
		return result;
	}

	// 물품 수정
	public int modifyItem(ItemVo itemVo) {
		// title update
		int result = 0;
		result += sqlSession.update("admin.updateItem", itemVo);
		result += sqlSession.update("admin.updateItemDetail", itemVo);
		return result;
	}

	// 카테고리 등록
	public CategoryVo addCategory(CategoryVo categoryVo) {
		sqlSession.insert("admin.addCategory", categoryVo);
		return categoryVo;
	}

	// 카테고리 수정
	public int modifyCategory(CategoryVo categoryVo) {
		return sqlSession.update("admin.modifyCategory", categoryVo);
	}

	public List<CategoryVo> viewCategory() {
		return sqlSession.selectList("admin.viewCategory");
	}

	public TermVo addTerms(TermVo termVo) {
		sqlSession.insert("admin.addTerms", termVo);
		return termVo;
	}

	// 약관 보기
	public List<TermVo> viewTerms(TermVo termVo) {
		return sqlSession.selectList("admin.viewTerms");
	}

	public int deleteTerms(TermVo termVo) {
		return sqlSession.delete("admin.deleteTerms", termVo);
	}

	public int modifyTerms(TermVo termVo) {
		return sqlSession.update("admin.modifyTerms", termVo);
	}

	public AdminVo login(AdminVo vo) {
		AdminVo result = sqlSession.selectOne("admin.login", vo);
		return result;
	}

	public List<CategoryVo> getLowCategory(CategoryVo topCategory) {
		List<CategoryVo> list = sqlSession.selectList("admin.getlowcategory", topCategory);
		return list;
	}

	public CategoryVo getCategoryNo(ItemVo itemVo) {
		ItemVo itemVo2 = sqlSession.selectOne("admin.getcategoryno", itemVo);
		CategoryVo vo = new CategoryVo();
		vo.setNo(itemVo2.getCategory_no());
		return vo;
	}

	public List<ItemVo> getItemList() {
		List<ItemVo> list = sqlSession.selectList("admin.itemlist");
		return list;
	}

	public List<BasketListVo> getItemList(UserVo vo) {
		List<BasketListVo> list = sqlSession.selectList("admin.itemListInBasket", vo);
		return list;

	}

	public ItemVo getItem(ItemVo itemVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		ItemVo vo = sqlSession.selectOne("admin.getitem", itemVo);
		List<ItemVo> option = sqlSession.selectList("admin.getOption", itemVo);
		List<String> tmp = new ArrayList<String>();

		for (int i = 0; i < option.size(); i++) {
			tmp.add(option.get(i).getOption_name());
		}

		vo.setName(tmp);

		return vo;
	}

}

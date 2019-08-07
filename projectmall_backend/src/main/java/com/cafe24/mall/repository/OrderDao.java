package com.cafe24.mall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mall.vo.OrderVo;

@Repository
public class OrderDao {

	@Autowired
	private SqlSession sqlSession;

	public OrderVo addPurchase(OrderVo orderVo) {
		sqlSession.insert("order.addPurchase", orderVo);
		return orderVo;
	}
}

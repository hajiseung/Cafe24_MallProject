package com.cafe24.mall.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mall.vo.OrderVo;
import com.cafe24.mall.vo.PaymentVo;

@Repository
public class OrderDao {

	@Autowired
	private SqlSession sqlSession;

	@Transactional
	public OrderVo addPurchase(OrderVo orderVo) {
		for (int i = 0; i < orderVo.getItem_no().size(); i++) {
			orderVo.setItem_no_element(orderVo.getItem_no().get(i));
			orderVo.setTotal_price_element(orderVo.getTotal_price().get(i));
			sqlSession.insert("order.addPurchase", orderVo);
		}
		sqlSession.delete("order.deletebasket", orderVo);
		return orderVo;
	}

	public List<PaymentVo> getPayment() {
		List<PaymentVo> list = sqlSession.selectList("order.getPayment");
		return list;
	}
}

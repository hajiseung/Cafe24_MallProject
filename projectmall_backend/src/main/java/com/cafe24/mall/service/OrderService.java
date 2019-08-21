package com.cafe24.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.repository.OrderDao;
import com.cafe24.mall.vo.OrderVo;
import com.cafe24.mall.vo.PaymentVo;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	public OrderVo addPurchase(OrderVo orderVo) {
		return orderDao.addPurchase(orderVo);
	}

	public List<PaymentVo> getPayment() {
		return orderDao.getPayment();
	}

}

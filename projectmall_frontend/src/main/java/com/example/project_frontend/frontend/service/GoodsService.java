package com.example.project_frontend.frontend.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.project_frontend.frontend.dto.JSONResult;
import com.example.project_frontend.frontend.security.SecurityUser;
import com.example.project_frontend.frontend.vo.BasketVo;
import com.example.project_frontend.frontend.vo.OrderVo;
import com.example.project_frontend.frontend.vo.PaymentVo;
import com.example.project_frontend.frontend.vo.UserVo;

@Service
public class GoodsService {

	private RestTemplate restTemplate = new RestTemplate();

	public List<BasketVo> getList(Long no) {
		BasketVo vo = new BasketVo();
		vo.setMember_no(no);
		String endpoint = "http://localhost:8080/projectmall_backend/api/basket/list";
		JSONResultGoodsList jsonResult = restTemplate.postForObject(endpoint, vo, JSONResultGoodsList.class);
		return jsonResult.getData();
	}

	public UserVo getUserOneAllData(SecurityUser user) throws URISyntaxException {
		UserVo vo = new UserVo();
		vo.setNo(user.getNo());

		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = new URI("http://localhost:8080/projectmall_backend/api/user/getuseronefromno");
		JSONResultUserVo jsonReultUser = restTemplate.postForObject(requestUri, vo, JSONResultUserVo.class);
		return jsonReultUser.getData();
	}

	// DTO Class
	private static class JSONResultGoods extends JSONResult<BasketVo> {
	}

	private static class JSONResultGoodsList extends JSONResult<List<BasketVo>> {
	}

	private static class JSONResultUserVo extends JSONResult<UserVo> {
	}

	private static class JSONResultPayment extends JSONResult<List<PaymentVo>> {
	}

	public List<PaymentVo> getMethodOfPayment() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = new URI("http://localhost:8080/projectmall_backend/api/order/getpayment");
		JSONResultPayment jsonReultUser = restTemplate.getForObject(requestUri, JSONResultPayment.class);
		return jsonReultUser.getData();
	}

	public String purchase(OrderVo orderVo) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI requestUri = new URI("http://localhost:8080/projectmall_backend/api/order/add");
		JSONResult jsonReultUser = restTemplate.postForObject(requestUri, orderVo, JSONResult.class);
		return jsonReultUser.getResult();
	}

}
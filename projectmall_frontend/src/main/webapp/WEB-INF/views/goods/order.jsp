<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Shop Homepage - Start Bootstrap Template</title>
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-login.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="basketlist" />
	</c:import>
	<!-- /.Navigation -->
 	<div class="container" style="max-width: 1500px;">
 		
            <form method="post" action="purchase" class="form-signin" name="joinForm">
		 		<table class="table table-hover">
				  <thead>
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">사진</th>
				      <th scope="col">제목</th>
				      <th scope="col">가격</th>
				      <th scope="col">수량</th>
				      <th scope="col">적립금</th>
				      <th scope="col">총금액</th>
				      
				    </tr>
				  </thead>
				    <c:forEach items="${basketlist }" var="basketlist" varStatus="index">
				    <tr>
				      <th scope="row">${index.count }</th>
				      <td height="50" width="50"> <img alt="" src="${pageContext.servletContext.contextPath }${basketlist.main_photo }" height="50" width="50"> </td>
				      <td>${basketlist.title }</td>
				      <td>${basketlist.price }</td>
				      <td>${basketlist.item_count }</td>
				      <td>${basketlist.accmulate }</td>
				      <td>${basketlist.total_item_price }</td>
				    </tr>
		            <input type="hidden" class="form-control" name="item_no[${index.index }]" value="${basketlist.item_no }" required>
		            <input type="hidden" class="form-control" name="total_price[${index.index }]" value="${basketlist.total_item_price }" required>
				    </c:forEach>
				</table>
				<div class="card card-container">
	                <input type="hidden" class="form-control" name="member_no" value="${uservo.no }" required>
	                <input type="hidden" class="form-control" name="item_no" value="${uservo.no }" required>
	                <input type="text" class="form-control" placeholder="이름" name="b_member" value="${uservo.name }" required>
	                <input type="text" class="form-control" placeholder="핸드폰번호(-포함)" name="b_cell_ph" value="${uservo.cell_ph }" required>
	                <input type="text" class="form-control" placeholder="주소" name="shipping" value="${uservo.addr }" required>
	                <select name="payment_no" class="form-control">
	                	<option class="form-control" value="결제 수단">결제 수단</option>
	                	<c:forEach items="${payment }" var="payment">
	                	<option class="form-control" value="${payment.no }">${payment.method_of_payment }</option>
	                	</c:forEach>
	                </select><br>
	                
	                <input type="text" class="form-control" placeholder="메모" name="memo">
	                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">주문하기</button>
 		       </div>
            </form><!-- /form -->
        <!-- /.card-container -->
	</div>
	<!-- /.container -->
	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>
<script>
</script>
</html>
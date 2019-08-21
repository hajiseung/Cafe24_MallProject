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
 		<form action="orderreg" method="POST">
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
		      <td>${basketlist.item_no }</td>
		    </tr>
			<input type="hidden" name="basketListVo[${index.index }].title" value="${basketlist.title }">
			<input type="hidden" name="basketListVo[${index.index }].price" value="${basketlist.price }">
			<input type="hidden" name="basketListVo[${index.index }].item_count" value="${basketlist.item_count }">
			<input type="hidden" name="basketListVo[${index.index }].accmulate" value="${basketlist.accmulate }">
			<input type="hidden" name="basketListVo[${index.index }].total_item_price" value="${basketlist.total_item_price }">
			<input type="hidden" name="basketListVo[${index.index }].item_no" value="${basketlist.item_no }">
		    </c:forEach>
		</table>
		<input type="submit" class="btn btn-outline-info" value="주문하기">
		</form>
	</div>
	<!-- /.container -->
	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>
<script>
/* $(function(){
	$('#orderreg').click(function(){
		console.log(${basketlist });
	});
	
}) */
</script>
</html>
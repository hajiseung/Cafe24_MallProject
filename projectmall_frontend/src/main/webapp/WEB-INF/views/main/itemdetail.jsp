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
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-homepage.css" rel="stylesheet">
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="addcategory" />
	</c:import>
	<!-- /.Navigation -->

 	<div class="container" style="max-width: 1500px;">
 		<div class="row">
			<div class="col-lg-9">
				<div class="card mt-4">
					<img class="card-img-top img-fluid"	src="${pageContext.servletContext.contextPath }${item.main_photo}" alt="">
					<div class="card-body">
						<h3 class="card-title">제목 : ${item.title}</h3>
						<h4>가격 : ${item.price }원</h4>
						<h4>옵션 :<c:set value="${item.option_names }" var="option"/>${fn:join(option,",") }</h4>
						<p class="card-text">
							내용 : ${item.desc_html }
						</p>
						<a href="${pageContext.servletContext.contextPath }/basket/add/${item.no}">장바구니 추가</a>
					</div>
				</div>
				<!-- /.card -->
			</div>
 		</div>
  		<%-- <table class="table">
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">이미지</th>
		      <th scope="col">제목</th>
		      <th scope="col">본문</th>
		      <th scope="col">옵션</th>
		      <th scope="col">가격</th>
		      <th scope="col">재고</th>
		      <th scope="col">장바구니 추가</th>
		    </tr>
		  </thead>
		    <tr>
		      <th scope="row">${item.no }</th>
		      <td><img class="card-img-top" alt="" src="${pageContext.servletContext.contextPath }${item.main_photo}"></td>
		      <td>${item.title}</td>
		      <td>${item.desc_html }</td>
		      <td>
		      <c:set value="${item.option_names }" var="option"/>${fn:join(option,",") }
		      </td>
		      <td>${item.price }</td>
		      <td>${item.amount }</td>
		      <td><a href="${pageContext.servletContext.contextPath }/basket/add/${item.no}">장바구니 추가</a></td>
		    </tr>
		</table> --%>
	</div>
	
	<!-- /.container -->
	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>
</html>
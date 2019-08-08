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
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="addcategory" />
	</c:import>
	<!-- /.Navigation -->

 	<div class="container" style="max-width: 1500px;">
  		<table class="table">
		  <caption>List of users</caption>
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">No</th>
		      <th scope="col">Id</th>
		      <th scope="col">E-Mail</th>
		      <th scope="col">이름</th>
		      <th scope="col">별명</th>
		      <th scope="col">휴대폰번호</th>
		      <th scope="col">가입일</th>
		      <th scope="col">주소</th>
		      <th scope="col">포인트</th>
		      <th scope="col">등급</th>
		    </tr>
		  </thead>
		    <c:forEach items="${userVo }" var="userVo" varStatus="index">
		    <tr>
		      <th scope="row">${index.index }</th>
		      <td>${userVo.no }</td>
		      <td>${userVo.id }</td>
		      <td>${userVo.email }</td>
		      <td>${userVo.name }</td>
		      <td>${userVo.nickname }</td>
		      <td>${userVo.cell_ph }</td>
		      <td>${userVo.join_date }</td>
		      <td>${userVo.addr }</td>
		      <td>${userVo.point }</td>
		      <td>${userVo.grade }</td>
		    </tr>
		    </c:forEach>
		</table>
	</div>
	<!-- /.container -->
	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>
</html>
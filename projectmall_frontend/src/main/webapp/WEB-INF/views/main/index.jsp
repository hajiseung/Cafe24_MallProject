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
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script>
$(function(){
	var acc = document.getElementsByClassName("topcategory");
	var i;
	var list = [];
	for (i = 0; i < acc.length; i++) {
		list.push(acc[i].innerText);
		acc[i].addEventListener("click", function() {
			this.classList.toggle("active");
		 	var panel = this.nextElementSibling;
			if (panel.style.display === "block") {
			   panel.style.display = "none";
			} else {
			  panel.style.display = "block";
			}
		});
	}
});

</script>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="shopping" />
	</c:import>
	<!-- /.Navigation -->
	
	<div class="container">
		<div class="row">

			<div class="col-lg-3">
				<h1 class="my-4">PJMall</h1>
				<h4 class="my-4">카테고리</h4>
				<div class="list-group">
					<c:forEach items="${topcategory }" var="topcategory" varStatus="status">
					<button id="topcategory[${status.index }]" class="accordion list-group-item topcategory">${topcategory }</button>
						<c:forEach items="${lowcategory}" var="lowcategory" begin="${status.index }" end="${status.index }">
							<div class="panel">
							<c:forEach items="${lowcategory }" var="lowcategory" varStatus="data">
								<a href="#" id="lowcategory[${data.index }]" class="list-group-item topcategory">${lowcategory }</a>
							</c:forEach>
							</div>
						</c:forEach>
					</c:forEach>
				</div>
			</div>
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">

				<div class="row">
					<c:forEach items="${item }" var="item">
						<div class="col-lg-4 col-md-6 mb-4">
							<div class="card h-100">
								<a href="${pageContext.servletContext.contextPath }/item/${item.no}"><img class="card-img-top" src="${pageContext.servletContext.contextPath }${item.main_photo}" alt=""></a>
								<div class="card-body">
									<h4 class="card-title">
										<a href="${pageContext.servletContext.contextPath }/item/${item.no}">${item.title }</a>
									</h4>
									<h5>${item.price }원</h5>
									<p class="card-text">${item.desc_html }</p>
								</div>
							</div>
						</div>
					</c:forEach>

				</div>
				<!-- /.row -->
			</div>
			<!-- /.col-lg-9 -->
			
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>

</html>

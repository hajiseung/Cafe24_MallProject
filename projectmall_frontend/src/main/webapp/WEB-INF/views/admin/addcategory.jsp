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

 	<div class="container">
 	${categoryList }
 		<div class="card card-container">
        	<!-- <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
            <p id="profile-name" class="profile-name-card"></p> -->
            <form method="post" action="addcategory" class="form-signin" name="categoryform">
                <!-- <span id="reauth-email" class="reauth-email"></span> -->
            </form><!-- /form -->
            <form method="post" action="addcategory" class="form-signin" name="categoryform">
                <!-- <span id="reauth-email" class="reauth-email"></span> -->
                <input type="text" id="inputEmail" class="form-control" placeholder="최상위 카테고리" name="top_category" required autofocus>
                <input type="text" id="inputEmail" class="form-control" placeholder="하위 카테고리" name="low_category" required>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">카테고리 등록</button>
            </form><!-- /form -->
        </div>
        <!-- /.card-container -->
        
        
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>
</html>
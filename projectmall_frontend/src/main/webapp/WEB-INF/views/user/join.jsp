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
		<c:param name="active" value="join" />
	</c:import>
	<!-- /.Navigation -->

 	<div class="container">
 		<div class="card card-container">
            <form method="post" action="join" class="form-signin" name="joinForm">
                <input type="text" class="form-control" placeholder="아이디" name="id" required autofocus>
                <input type="email" id="inputEmail" class="form-control" placeholder="이메일" name="email" required>
                <input type="password" id="inputPassword" class="form-control" placeholder="비밀번호" name="pw" required>
                <input type="text" class="form-control" placeholder="이름" name="name" required>
                <input type="text" class="form-control" placeholder="별명" name="nickname" required>
                <input type="text" class="form-control" placeholder="전화번호(-포함)" name="tell_ph" required>
                <input type="text" class="form-control" placeholder="핸드폰번호(-포함)" name="cell_ph" required>
                <input type="text" class="form-control" placeholder="주소" name="addr" required>
                생일
                <input type="date" class="form-control" placeholder="생일" name="birthday" required>
                이메일 수신
                <label for="email_recvTrue">Ture</label>
                <input type="radio" id="email_recvTrue" name="email_recv">
                <label for="email_recvFalse">False</label>
                <input type="radio" id="email_recvFalse" name="email_recv"><br>
                문자 수신
                <label for="sms_recvTrue">Ture</label>
                <input type="radio" id="sms_recvTrue"  name="sms_recv">
                <label for="sms_recvFalse">False</label>
                <input type="radio" id="sms_recvFalse" name="sms_recv"><br>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">회원 가입</button>
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
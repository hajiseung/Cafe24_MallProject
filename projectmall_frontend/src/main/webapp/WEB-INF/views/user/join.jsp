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
	<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery.js"></script>
</head>
<body>
<script>
$(function(){
		$('#user-id').change(function(){
			$('#btn-checkemail').show();
			$('#img-checkemail').hide();
		});
		$('#btn-checkemail').click(function(){
			var id = $('#user-id').val();
			if(id==''||id=='admin'){
				alert('사용할 수 없는 Email입니다.');
				$('#user-id').focus();
				$('#user-id').val('');
				return;}
			/* ajax 통신 */
			$.ajax({
				url:"${pageContext.servletContext.contextPath }/user/checkid?userid="+id,
				type:"GET",/* get이냐 post이냐 */
				dataType:"JSON",
				data:"",
				success:function(response){
					if(response.result != "success"){
						console.error(response);
						console.error(response.message);
						return;
					}
					if(response.data == false){
						alert('존재하는 Email입니다.');
						$('#user-id').focus();
						$('#user-id').val('');
						return;
					}else{
						$('#btn-checkemail').hide();
						$('#img-checkemail').show();
						$('#userJoin').removeAttr('disabled');
						
					}
				},
				error:function(xhr,error){
					console.error('Error!!!:'+error);
				}
			});
			console.log(id);
		})
	});
</script>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="join" />
	</c:import>
	<!-- /.Navigation -->

 	<div class="container">
 		<div class="card card-container">
            <form method="post" action="join" class="form-signin" name="joinForm">
                <input id="user-id" type="text" class="form-control" placeholder="아이디" name="id" required autofocus>
                <input id="btn-checkemail" class="form-control" type="button" value="id 중복체크">
                <img id="img-checkemail" style="display: none;"
				src="${pageContext.servletContext.contextPath}/assets/images/check.png">
                <br>
                <input type="email" id="inputEmail" class="form-control" placeholder="이메일" name="email" required>
                <input type="password" id="inputPassword" class="form-control" placeholder="비밀번호" name="pw" required>
                <input type="text" class="form-control" placeholder="이름" name="name" required>
                <input type="text" class="form-control" placeholder="별명" name="nickname" required>
                <input type="text" class="form-control" placeholder="전화번호(-포함)" name="tell_ph">
                <input type="text" class="form-control" placeholder="핸드폰번호(-포함)" name="cell_ph" required>
                <input type="text" class="form-control" placeholder="주소" name="addr" required>
                생일
                <input type="date" class="form-control" placeholder="생일" name="birthday" required>
                이메일 수신
                <label for="email_recvTrue">Ture</label>
                <input type="radio" id="email_recvTrue" name="email_recv" checked="checked">
                <label for="email_recvFalse">False</label>
                <input type="radio" id="email_recvFalse" name="email_recv"><br>
                문자 수신
                <label for="sms_recvTrue">Ture</label>
                <input type="radio" id="sms_recvTrue"  name="sms_recv" checked="checked">
                <label for="sms_recvFalse">False</label>
                <input type="radio" id="sms_recvFalse" name="sms_recv"><br>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit" disabled="disabled" id="userJoin">회원 가입</button>
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
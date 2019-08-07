<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
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
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script>
<!-- jquery로 파일 첨부 추가 -->
$(document).ready(function() {
    //add more file components if Add is clicked
    $('#addFile').click(function() {
        var fileIndex = ($('#fileview tbody tr').children().length)/2;
        $('#fileview').append(
                '<tr><td>'+
                '   <input type="file" name="multiPartPhoto['+ fileIndex +']" style="width: 250px;" />'+
                '   <td><input type="checkbox" name="is_main['+fileIndex+']"></td>'+
                '</td></tr>');
    });
	$('.item_category').change(function(){
		var test = $('.item_category').val();
		var tagdata ='';
		$('.low_category').html('<option value="">하위 카테고리</option>');
		$.ajax({
			url:"${pageContext.servletContext.contextPath }/getlowcategory",
			data:{"top_category":test},
			type:"post",
			success:function(response){
				$.each(response,function(index,value){
					tagdata+="<option value='"+value+"'>"+value+"</option>"
				})
				$('.low_category').append(tagdata);
			}
		});
	});
});
</script>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="additem" />
	</c:import>
	<!-- /.Navigation -->

 	<div class="container">
 		<div class="card card-container">
            <form method="post" action="${pageContext.servletContext.contextPath }/additem" class="form-signin" name="loginForm" enctype="multipart/form-data">
                <input type="text" class="form-control" placeholder="물품명" name="title" required autofocus>
                <input type="text" class="form-control" placeholder="물품설명" name="desc_html" required>
                <input type="text" class="form-control" placeholder="수량" name="amount" required>
                <input type="text" class="form-control" placeholder="판매 가능 수량" name="available_amount" required>
                <input type="text" class="form-control" placeholder="가격" name="price" required>
                <select class="item_category"  name="top_category">
					<option value="상위 카테고리">상위 카테고리</option>
				    <c:forEach items="${categoryMap.topcategory }" var="map">
				    	<option value="${map }">${map }</option>
				    </c:forEach>
				</select>
				<select class="low_category" name="low_category">
					<option value="하위 카테고리">하위 카테고리</option>
				</select>
				<br>
                
           		<label>재고 유무</label>
                <input type="radio" name="non_amount" value="true">True
                <input type="radio" name="non_amount" value="false">False<br>
                
                <label>전시 상태</label>
                <input type="radio" name="displaystatus" value="true">True
                <input type="radio" name="displaystatus" value="false">False<br>
                
                <label>판매 상태</label>
                <input type="radio" name="salestatus" value="true">True
                <input type="radio" name="salestatus" value="false">False<br>
                
				<input id="addFile" type="button" value="파일 추가" />  
                <table id="fileview">
			        <tr>
			            <td><input type="file" name="multiPartPhoto[0]" style="width: 250px;"/></td>
			            <td><input type="checkbox" name="is_main[0]"></td>
			        </tr>        
			    </table>
                
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">물품 등록</button>
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
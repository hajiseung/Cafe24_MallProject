<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="#">&nbsp;</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
			<!-- 관리자 -->
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<c:choose>
					<c:when test='${param.active == "addcategory" }'>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }">홈</a>
						</li>
						
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/addcategory">카테고리 등록<span class="sr-only">(current)</span></a>
						</li>
						
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/additem">물품 등록</a>
						</li>
						
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/memberlist">회원 리스트</a>
						</li>
						
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/admin/logout">로그아웃</a>
						</li>
					</c:when>		
					<c:when test='${param.active == "additem" }'>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }">홈</a>
						</li>
						
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/addcategory">카테고리 등록</a>
						</li>
						
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/additem">물품 등록<span class="sr-only">(current)</span></a>
						</li>
						
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/memberlist">회원 리스트</a>
						</li>
						
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/admin/logout">로그아웃</a>
						</li>
					</c:when>		
								
					<c:otherwise>
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }">홈<span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/addcategory">카테고리 등록</a>
						</li>
						
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/additem">물품 등록</a>
						</li>
						
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/memberlist">회원 리스트</a>
						</li>
						
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/admin/logout">로그아웃</a>
						</li>
					</c:otherwise>				
				</c:choose>
			</sec:authorize>
			
			<!-- 사용자 -->			
			<sec:authorize access="hasRole('ROLE_USER')">
				<c:choose>
					<c:when test='${param.active == "cs" }'>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }">홈</a>
						</li>
						
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/cs">고객센터<span class="sr-only">(current)</span></a>
						</li>
	
	
	
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/admin/logout">로그아웃</a>
						</li>
					</c:when>		
									
					<c:otherwise>
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }">홈<span class="sr-only">(current)</span></a>
						</li>
						
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/cs">고객센터</a>
						</li>
						
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/admin/logout">로그아웃</a>
						</li>
					</c:otherwise>				
				</c:choose>
			</sec:authorize>
			<sec:authorize access="isAnonymous()">
				<c:choose>
					<c:when test='${param.active == "login" }'>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }">홈</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/login">로그인<span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/join">회원가입</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/cs">고객센터</a>
						</li>
					</c:when>
					<c:when test='${param.active == "join" }'>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }">홈</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/login">로그인</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/join">회원가입<span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/cs">고객센터</a>
						</li>
						
					</c:when>
					
					<c:when test='${param.active == "cs" }'>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }">홈</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/login">로그인</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/join">회원가입</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/cs">고객센터<span class="sr-only">(current)</span></a>
						</li>
						
					</c:when>		
								
					<c:when test='${param.active == "loginadmin" }'>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }">홈</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/login">로그인</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/join">회원가입</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/cs">고객센터</a>
						</li>
						
					</c:when>
									
					<c:otherwise>
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }">홈<span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/login">로그인</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/user/join">회원가입</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/cs">고객센터</a>
						</li>
						
					</c:otherwise>				
				</c:choose>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>
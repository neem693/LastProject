<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/resources/css/main/header.css"> 
<title>pickBaseball</title>

<%-- 현재 Context Root : ${ pageContext.request.contextPath } <br> --%>

<script type="text/javascript">
function send_main(){
	location.href = "${ pageContext.request.contextPath }/main/main_list.do";
	
}

</script>

</head>

<body>

<div class = "header" onclick="send_main()">
	
	<div id="header_img">
		
			<span id="login_button">
				<!-- 로그인 -->
				<a href="${pageContext.request.contextPath}/member/login.do"><img src="${pageContext.request.contextPath}/resources/images/main/header/helmet.png" /></a>
			</span>
			<br>	
			
			<span id="join_button">
				<!-- 회원가입 -->
				<a href="${pageContext.request.contextPath}/join.do"><img src="${pageContext.request.contextPath}/resources/images/main/header/glove.png" /></a> 
			</span>	
			
	</div>
		
	<div class="topnav">
		<a href="${ pageContext.request.contextPath }/joonggo/list.do">중고거래</a>
		<a href="${ pageContext.request.contextPath }/toto/toto_list.do">토토</a>
		<a href="${ pageContext.request.contextPath }/party/party_list.do">파티</a>
		<a href="${ pageContext.request.contextPath }/borad/baord_list.do">게시판</a>
	</div>
	
	
</div>
	
</body>
</html>
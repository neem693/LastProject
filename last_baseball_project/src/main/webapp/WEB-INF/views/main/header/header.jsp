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

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<title>pickBaseball</title>

<%-- 현재 Context Root : ${ pageContext.request.contextPath } <br> --%>

<script type="text/javascript">
	function send_main() {
		location.href = "${ pageContext.request.contextPath }/main/main_list.do";

	}
</script>



</head>

<body>

	<div class="header" onclick="send_main()">


		<div id="header_img">


			<!-- 로그인이 안된 상태 -->
			<c:if test="${ empty user }">

				<span id="login_button" title="로그인"> <a
					href="${pageContext.request.contextPath}/member/login.do"><img
						src="${pageContext.request.contextPath}/resources/images/main/header/login.png" /></a>
				</span>


				<br>
				<span id="join_button" title="회원가입"> <!-- 회원가입 --> <a
					href="${pageContext.request.contextPath}/join.do"><img
						src="${pageContext.request.contextPath}/resources/images/main/header/join.png" /></a>
				</span>
			</c:if>


			<!-- 로그인이 되어있으면 -->
			<c:if test="${ not empty user }">
				<span id="logout" title="logout"> <a
					href="${pageContext.request.contextPath}/member/logout.do"><img
						src="${pageContext.request.contextPath}/resources/images/main/header/logout.png" /></a><br>
				</span>

				<br>
				<span id="mypage" title="mypage"> <!-- mypage --> <a href="#"><img
						src="${pageContext.request.contextPath}/resources/images/main/header/mypage.png"></a>
					<br> ${ user.m_name }님 환영합니다
				</span>

			</c:if>

		</div>

		<div class="topnav">
			<a class="joonggo"
				href="${ pageContext.request.contextPath }/joonggo/list.do"><img
				src="${pageContext.request.contextPath}/resources/images/main/header/nav_junggo.png">중고거래</a>
			<a class="betting"
				href="${ pageContext.request.contextPath }/toto/toto_list.do"><img
				src="${pageContext.request.contextPath}/resources/images/main/header/nav_betting.png">BETTING</a>
			<a class="party"
				href="${ pageContext.request.contextPath }/party/party_list.do"><img
				src="${pageContext.request.contextPath}/resources/images/main/header/nav_party.png">PARTY</a>
			<a class="normal"
				href="${ pageContext.request.contextPath }/normal/list.do"><img
				src="${pageContext.request.contextPath}/resources/images/main/header/nav_board_1.png">자유게시판</a>
		</div>

	</div>


</body>
</html>
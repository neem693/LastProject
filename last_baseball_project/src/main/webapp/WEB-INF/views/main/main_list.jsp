<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>pickBaseball</title>

<%-- 현재 Context Root : ${ pageContext.request.contextPath } <br> --%>
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/resources/css/main/main.css">

<script type="text/javascript">
	window.onload = function() {
		var slideIndex = 0;
		showSlides();

		function showSlides() {
			var i;
			var slides = document.getElementsByClassName("mySlides");
			var dots = document.getElementsByClassName("dot");
			for (i = 0; i < slides.length; i++) {
				slides[i].style.display = "none";
			}
			slideIndex++;
			if (slideIndex > slides.length) {
				slideIndex = 1
			}
			for (i = 0; i < dots.length; i++) {
				dots[i].className = dots[i].className.replace(" active", "");
			}
			slides[slideIndex - 1].style.display = "block";
			dots[slideIndex - 1].className += " active";
			setTimeout(showSlides, 3000); // Change image every 2 seconds
		}

		function clickBtn() {
			$("title")
					.click(
							function() {
								location.href = "http://localhost:9090/pickBaseball/main/main_list.do.jsp";
							});

		}
	}
</script>

</head>

<body>

	<div class="header">
		<!--href 에 이동할 게시판 JSP 넣는다 -->
		<%-- <div class="title">
			<a href="#"><img
				src="${pageContext.request.contextPath}/resources/images/main/header/title.png"
				onclick="clickBtn();" border="0" /></a>
		</div> --%>


		<div id="header_img">
			<div class="login_join">
				<a href="${pageContext.request.contextPath}/경로확인 login_form.do">
					<span class="login">로그인</span>
				</a> <a href="${pageContext.request.contextPath}/경로확인 join_form.do">
					<span class="join">회원가입</span>
				</a>
			</div>
		</div>
		<%-- <img class="header_img"
				src="${pageContext.request.contextPath}/resources/images/main/header/baseballheader.jpg">
 --%>
		<div class="topnav">
			<a href="${ pageContext.request.contextPath }/joonggo/list.do">중고거래</a>
			<a href="${ pageContext.request.contextPath }/toto/toto_list.do">토토</a>
			<a href="${ pageContext.request.contextPath }/party/party_list.do">파티</a>
			<a href="${ pageContext.request.contextPath }/borad/baord_list.do">게시판</a>
		</div>

	</div>

	<div class="row">

		<div class="leftcolumn">
			<div class="card">

				<h2>Automatic Slideshow</h2>

				<div class="slideshow-container">

					<div class="mySlides fade">
						<div class="numbertext">1 / 4</div>
						<img
							src="${ pageContext.request.contextPath }/resources/images/main/slide/pitch.jpg"
							style="width: 100%">
						<div class="text">Caption Text</div>
					</div>

					<div class="mySlides fade">
						<div class="numbertext">2 / 4</div>
						<img
							src="${ pageContext.request.contextPath }/resources/images/main/slide/bat.jpg"
							style="width: 100%">
						<div class="text">Caption Two</div>
					</div>

					<div class="mySlides fade">
						<div class="numbertext">3 / 4</div>
						<img
							src="${ pageContext.request.contextPath }/resources/images/main/slide/sliding.jpg"
							style="width: 100%">
						<div class="text">Caption Three</div>
					</div>

					<div class="mySlides fade">
						<div class="numbertext">4 / 4</div>
						<img
							src="${ pageContext.request.contextPath }/resources/images/main/slide/bench.jpg"
							style="width: 100%">
						<div class="text">Caption Four</div>
					</div>

				</div>
				<br>

				<div style="text-align: center">
					<span class="dot"></span> <span class="dot"></span> <span
						class="dot"></span> <span class="dot"></span>
				</div>
			</div>


		</div>


		<div class="rightcolumn">
			<div class="card">
				<h2>오늘의 경기</h2>
				<div class="fakeimg" style="height: 100px;">달력</div>
				<p>경기1- A:B</p>
				<p>경기2- C:D</p>
				<p>경기3- E:F</p>
				<p>경기4- G:H</p>
				<p>경기5- I:J</p>
				<p>경기를 클릭하면 파티로 넘어가?</p>

			</div>
			<div class="card">
				<h3>팀 순위</h3>
				<div class="fakeimg">
					<p>Image</p>
				</div>
				<div class="fakeimg">
					<p>Image</p>
				</div>
				<div class="fakeimg">
					<p>Image</p>
				</div>
			</div>
		</div>
	</div>



	<div class="footer">
		<h2>우리회사 주소,전화번호 etc</h2>
		<div id="footer_content">
			(주)인크레파스 A강의실 <a href="">개인정보관리정책</a><br> 대표자: 홍대표<br> 주 소:
			서울시 구로구 구로3동<br> 관리자: 홍관리 전화)010-111-2345
		</div>
	</div>


</body>
</html>
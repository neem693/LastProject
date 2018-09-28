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
							}

					);

		}
	}
</script>

</head>

<body>


	<%@include file="/WEB-INF/views/main/header/header.jsp"%>


	<div class="row">

		<div class="leftcolumn">
			<div class="card">


				<div class="slideshow-container">

					<div class="mySlides fade">
						<div class="numbertext">1 / 4</div>
						<img
							src="${ pageContext.request.contextPath }/resources/images/main/slide/bench.jpg"
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
							src="${ pageContext.request.contextPath }/resources/images/main/slide/pitch.jpg"
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
				
				<div class="fakeimg">
				<span class = "team_item">순위</span>
				<span class = "team_item">팀</span>
				<span class = "team_item">경기</span>
				<span class = "team_item">승</span>
				<span class = "team_item">무</span>
				<span class = "team_item">패</span>
				
				<div style = "clear:both"></div>
				<c:forEach var="team" items="${ranking}">
				
					<span class = "rank"> ${team.t_rank}</span>
					<span class = "team">${team.t_name}</span>
					<span class = "game">${team.t_nom}</span>
					<span class = "win">${team.t_win}</span>
					<span class = "draw">${team.t_draw}</span>
					<span class = "lose">${team.t_lose}</span>
					
					<br>
				</c:forEach>
				<div style = "clear: both"></div>
</div>
			</div>

			<div class="card">
				<h3>오늘의 소식</h3>
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


	<%@include file="/WEB-INF/views/main/footer/footer.jsp"%>


</body>
</html>
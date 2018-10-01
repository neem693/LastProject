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
	
	function go_party(){
		location.href = "${ pageContext.request.contextPath }/party/party_list.do";
		
	}
</script>
<style type="text/css">
.day {
	width: 14%;
	box-sizing: border-box;
	float: left; //
	border: 1px solid black;
	padding: 15px 0;
	text-align: center;
}

.today {
	background-color: rgb(128, 128, 128);
	color: white;
}

.month {
	text-align: center;
	font-weight: 900;
	font-size: xx-large;
}
.play_list{
cursor: pointer;
border: 1px solid rgb(217, 217, 217);
}
.play{

	text-align: center;
	font-size: large;
}
</style>

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

		<c:set var="day" value="0" />
		<div class="rightcolumn">
			<div class="card">
				<h2>오늘의 경기</h2>
				<div class="fakeimg">
					<div class="month">${this_year}/${this_month }</div>
					<c:forEach var="i" begin="0" end="5">
						<c:forEach var="j" begin="1" end="7">
							<c:set var="is_create" value="true" />
							<%-- is_create는 해당 당일에 파티생성을 해도될지에 대한것 처음 값을 투르로 주고, 밑에 값에서 체크하여 false를 줌 --%>
							<c:choose>
								<c:when test="${first_day > j || day>last_day}">
									<span class="day"></span>
								</c:when>
								<c:otherwise>
									<c:set var="day" value="${day+1}"></c:set>
									<c:set var="today"></c:set>
									<c:if test="${day eq this_day}">
										<c:set var="today">today</c:set>
									</c:if>
									<span class="day ${today}">${day}</span>
									<c:if test="${fisrt_day != 0}">
										<c:set var="first_day" value="0"></c:set>
									</c:if>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:forEach>
					<div style="clear: both"></div>



				</div>

				<div onclick="go_party();" class="play_list">
					<c:choose>
						<c:when test="${fn:length(play_list) eq 0}">오늘은 경기가 없습니다.</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${fn:length(play_list) ne 0}">
							<c:forEach var="vo" items="${play_list}">
							
								<div class = "play">${vo.t_home }<span> VS </span>${vo.t_away}<span> </span>${vo.hour_minute}</div>
							
							</c:forEach>
						</c:when>
					</c:choose>

				</div>
			</div>
			<div class="card">

				<div class="fakeimg">
					<span class="team_item">순위</span> <span class="team_item">팀</span>
					<span class="team_item">경기</span> <span class="team_item">승</span>
					<span class="team_item">무</span> <span class="team_item">패</span>

					<div style="clear: both"></div>
					<c:forEach var="team" items="${ranking}">

						<span class="rank"> ${team.t_rank}</span>
						<span class="team">${team.t_name}</span>
						<span class="game">${team.t_nom}</span>
						<span class="win">${team.t_win}</span>
						<span class="draw">${team.t_draw}</span>
						<span class="lose">${team.t_lose}</span>

						<br>
					</c:forEach>
					<div style="clear: both"></div>
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
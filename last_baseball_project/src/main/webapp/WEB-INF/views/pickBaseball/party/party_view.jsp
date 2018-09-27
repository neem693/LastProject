<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=1025388614b7b8c70d0002c6339d84f4&libraries=services
"></script>
<script
	src="${pageContext.request.contextPath}/resources/jquery/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/daum_map_view_mode.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/party_join.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		var acc = document.getElementsByClassName("accordion");
		var i;

		for (i = 0; i < acc.length; i++) {
			acc[i].onclick = function() {
				this.classList.toggle("active");
				var panel = this.nextElementSibling;
				panel.classList.toggle("show");
			}
		}
		make_map(${party.x},${party.y});
		
		
		
		var fail = '${param.fail}';
		if(fail != ''){
			setTimeout(function(){
			if(fail == 'joined')
				alert("이미 오늘 파티가 예약된 파티가 있어 참여하지 못합니다.")
			else if(fail == 'partyFull')
				alert("파티 인원이 이미 다 찼기 때문에 참여하지 못합니다.");
			else if(fail == 'partyClosed')
				alert("파티가 이미 마감되었기 때문에 참가하지 못합니다.");
			
			
			},400);
		}
		
		var party_b= document.getElementById("party_button");
		
		setTimeout(function(){
			party_b.classList.toggle("active");
			var panel = party_b.nextElementSibling;
			panel.classList.toggle("show");
		},800);
		
		
	});
	
	
	
</script>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/party/button_panel.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/party/font.css">
<style type="text/css">

/* 
뷰메인 */
.view_main {
	margin-left: 15%;
	margin-right: 15%;
	min-height: 1000px;
	border: 1px solid black;
}

.view {
	width: 70%;
	float: left;
	border: 1px solid black;
}

.view_aside {
	float: right;
	width: 25%;
	border: 1px solid black;
}

.view_item, .long_item {
	width: 33%;
	/* border: 1px solid black; */
	border-top: 1px solid #d0e1e1;
	border-bottom: 1px solid #d0e1e1;
	float: left;
	box-sizing: border-box;
	min-height: 100px;
}

.view_item:before, .long_item:before {
	display: block;
	text-align: center;
	font-size: x-large;
	font-weight: bold;
}

.long_item {
	width: 100%;
}

.team img {
	height: 80px;
}

.team img {
	display: block;
	margin: 0 auto;
}

.t_away:before {
	content: "원정";
}

.t_home:before {
	content: "홈";
}

.VS {
	padding: 50px;
	border-bottom: none;
}

.VS:after {
	display: block;
	content: "VS";
	text-align: center;
	font-size: xx-large;
	font-weight: bold;
	font-style: italic;
}

.team>span {
	display: block;
	text-align: center;
	font-weight: bold;
	font-size: xx-large;
}

.play_time:before {
	content: "경기시간";
}

.stadium:before {
	content: "경기장";
}

.stadium, .sta_addr, .sta_all_seat, .play_time, .leader_name,
	.leader_count, .leader_tel, .leader_email, .party_name,
	.party_condition, .party_purpose, .party_people, .party_addr,
	.party_time, .party_coordinate, .leader_team, .party_member,
	.party_leader {
	text-align: center;
	font-size: large;
}

.sta_addr:before {
	content: "경기장 주소";
}

.sta_all_seat:before {
	content: "좌석 수 ";
}

.leader_name:before {
	content: "리더";
}

.leader_count:before {
	content: "리더 횟수";
}

.leader_tel:before {
	content: "전화번호";
}

.leader_email:before {
	content: "이메일";
	ㅣ
}

.party_name:before {
	content: "파티명"
}

.party_name {
	font-size: x-large;
}

.party_condition:before {
	content: "파티 상태"
}

.party_purpose:before {
	content: "파티 목적"
}

.party_people:before {
	content: "파티 인원"
}

.party_time:before {
	content: "모이는 시간"
}

.party_addr:before {
	content: "모이는 곳(주소)"
}

.party_coordinate:before {
	content: "모이는 곳(좌표)"
}

.leader_team:before {
	content: "응원 팀";
}

.party_member, .party_leader {
	font-size: x-large;
}

.party_member:before {
	content: "멤버";
}

.party_leader:before {
	content: "리더";
}

.map {
	width: 100%;
	height: 500px;
}

.view_buttons {
	clear: both;
	text-align: right;
	display: block;
}
</style>
</head>
<body>

	<%@include file="/WEB-INF/views/main/header/header.jsp"%>


	<article class="view_main">
		<section class="view">

			<button class="accordion">경기</button>
			<div class="panel">
				<p class="view_item team t_away">
					<img
						src="${pageContext.request.contextPath}${play.t_away_image_dir}">
					<span style="text-align: center;">${play.t_away}</span>
				</p>


				<p class="view_item VS"></p>
				<p class="view_item team t_home">
					<img
						src="${pageContext.request.contextPath}${play.t_home_image_dir}">
					<span style="text-align: center;">${play.t_home}</span>
				</p>
				<p class="view_item play_time">${party.p_date}</p>
				<%--play의 p_date를 쓰는게 우선이나, 인코딩을 party vo에서 함으로 이렇게 가져온다. --%>
				<p class="view_item stadium">${stadium.s_name}<br>(${stadium.t_name}의
					제${stadium.o_secondQ}경기장)
				</p>
				<p class="view_item sta_addr">${stadium.s_addr}</p>
				<p class="view_item sta_all_seat">${stadium.s_seat}</p>


			</div>

			<button class="accordion">리더</button>
			<div class="panel">
				<p class="view_item leader_name">${party_leader.m_nick}</p>
				<p class="view_item leader_count">${leader_count}</p>
				<c:if test="${user eq null}">
					<p class="view_item leader_tel">
						<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>을
						해야 볼 수 있습니다.
					</p>
				</c:if>
				<c:if test="${user != null}">
					<p class="view_item leader_tel">${party_leader.m_tel }</p>
				</c:if>
				<p class="view_item leader_email">${party_leader.m_email}</p>
				<p class="view_item leader_team">${party.t_name}</p>

			</div>
			<button id= "party_button" class="accordion">파티</button>
			<div class="panel">

				<p class="long_item party_name">${party.pt_name}</p>
				<p class="view_item party_condition">${party.pt_condition}</p>
				<p class="view_item party_purpose">${party.pt_purpose}</p>
				<p class="view_item party_people">${party.pt_maxPeople}/${party.pt_people}</p>
				<p class="view_item party_time">${party.datetime}<br>${party.pt_day_str}</p>
				<p class="view_item party_addr">${party.addr1}</p>
				<div class="long_item party_coordinate">
					<div id="centerAddr"></div>
					<div class="map" id="map"></div>
				</div>



			</div>
			<button class="accordion">파티 디테일</button>
			<div class="panel">
				<div class="long_item party_detail">${party.pt_text}</div>
			</div>
			<button class="accordion">파티멤버</button>
			<div class="panel">
				<c:if test="${(fn:length(party_member)) eq 1 }">
					<div class="long_item party_member">
						아직 누구도 파티에 들어오지 않았습니다.ㅜㅜ<br>지금 보고 있는 당신 이 파티에 참여하세요!!
					</div>
				</c:if>
				<c:if test="${(fn:length(party_member)) > 1 }">

					<c:forEach var="vo" items="${party_member}">
						<c:if test="${(user.m_idx eq vo.m_idx)}">
							<c:set var="already_join" value="true"></c:set>

						</c:if>

						<c:if test="${(user.m_idx eq party_leader.m_idx)}">
							<c:set var="leader_guy" value="true"></c:set>

						</c:if>
						<c:if test="${vo.b_leader eq 10}">
							<p class="view_item party_leader">${vo.m_nick}</p>
						</c:if>
						<c:if test="${vo.b_leader eq 1}">
							<p class="view_item party_member">${vo.m_nick}</p>
						</c:if>


					</c:forEach>
				</c:if>

			</div>

			<div class="view_buttons">
				<form method="POST">
					<input type="hidden" name="year" value="${param.year}"> <input
						type="hidden" name="month" value="${param.month}"> <input
						type="hidden" name="day" value="${param.day}"> <input
						type="hidden" name="team" value="${param.team}"> <input
						type="hidden" name="pt_idx" value="${party.pt_idx}">


					<c:if test="${user ne null && already_join ne true}">
						<button class="view_button join" id="view_join"
							onclick="party_join(this.form)">참여</button>
					</c:if>
					<c:if test="${user ne null }">
						<button class="view_button delete" id="view_delete">삭제</button>
						<button class="view_button modify" id="view_modify">수정</button>
					</c:if>
					<c:if test="${already_join eq true && leader_guy ne true }">
						<button class="view_button delete" id="view_delete"
							onclick="party_leave(this.form)">참여해제</button>
					</c:if>
				</form>
			</div>


		</section>

		<aside class="view_aside">이곳은 리스트가 올 것이다.</aside>

	</article>
	<div style="clear: both;"></div>
	<%@include file="/WEB-INF/views/main/footer/footer.jsp"%>


</body>
</html>
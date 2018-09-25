<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<style>
.party_template {
	border: 1px solid black;
	width: 100%;
	clear: both;
}

.not_have_party_list {
	background-color: gray;
	color: white;
	text-align: center;
	padding: 100px 0;
	font-size: xx-large;
}

.team_img {
	height: 100px;
	box-sizing: border-box;
	display: inline-block;
}

.team_img>img {
	margin: 0 0 0 5px;
	height: 100%;
}

.summary {
	box-sizing: border-box;
	display: inline-block; //
	border: 1px solid black;
	width: 80%;
	height: 100%;
	vertical-align: top;
	text-align: center;
}

.summary>span {
	display: inline-block;
	margin: 10px 2%;
	width: 25%;
	text-align: center; //
	border: 1px solid black;
	border-radius: 10px;
}

.summary>span.statu:before {
	content: "<상태>";
	display: block;
	font-weight: bold;
}

.summary>span.statu {
	background-color: BurlyWood;
}

.summary>span.purpose:before {
	content: "<목적>";
	display: block;
	font-weight: bold;
}

.summary>span.purpose {
	background-color: Beige;
}

.summary>span.leader_count:before {
	content: "<리더횟수>";
	display: block;
	font-weight: bold;
}

.summary>span.leader_count {
	background-color: Bisque;
}

.summary>span.maxPeople:before {
	content: "<최대인원>";
	display: block;
	font-weight: bold;
}

.summary>span.maxPeople {
	background-color: Gainsboro;
}

.summary>span.people:before {
	content: "<참가인원>";
	display: block;
	font-weight: bold;
}

.summary>span.people {
	background-color: Bisque;
}

.summary>span.leader_name:before {
	content: "<리더>";
	display: block;
	font-weight: bold;
}

.summary>span.leader_name {
	background-color: Gold;
}

.party_name {
	cursor: pointer; 
	text-align : center;
	font-weight: bold;
	font-size: xx-large;
	text-align: center;
}

.p_date {
	background-color: gray;
	color: yellow;
}

.s_name {
	background-color: lime;
	color: black;
}

.pt_date:before, .pt_location:before {
	color: black;
	font-weight: bold;
}

.pt_date:before {
	content: "<모임시간>:"
}

.pt_location:before {
	content: "<모일장소>:"
}

.pt_date, .pt_location {
	background-color: WhiteSmoke;
	color: SlateBlue;
}

.pt_location {
	display: block;
}

.paging {
	margin: 20px 0;
	text-align: center;
	font-size: x-large;
	text-align: center;
}

.page, .groupPage {
	border: 1px solid #ddd;
}

.page {
	margin: 0 1px;
	height: 10px;
	padding: 0 5px;
	border: 1px solid #ddd;
}

.groupPage {
	height: 10px;
}

.next_page, .change_page, .pre_page {
	cursor: pointer;
	transition: all 0.5s;
}

.current_page {
	background-color: #3399ff;
	font-weight: bold;
	color: white;
}

.next_page:hover, .change_page:hover, .pre_page:hover {
	background-color: #3399ff;
	color: white;
}

@media screen and (max-width: 850px) {
	.party_template>.team_img {
		float: none;
		display: block;
	}
	.party_template>.team_img>img {
		display: block;
		margin: 0 auto;
	}
	.party_template>.summary {
		float: none;
		display: block;
		width: 100%;
	}
}

@media screen and (max-width: 450px) {
	.pt_date {
		display: block;
	}
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${fn:length(list) eq 0}">
		<div class="party_template ">
			<p class="not_have_party_list">
				파티가 없습니다. ㅜㅜ<br> 달력 해당 당일에 오른쪽 숫자를 확인해주세요!
			</p>
		</div>

	</c:if>

	<c:forEach var="vo" items="${list}">
		<c:choose>
			<c:when test="${vo.t_name eq '두산'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/doosan_bears_small.png</c:set>
				<c:set var="team_back_color">#110f29</c:set>
				<c:set var="team_color_css">color: white;</c:set>
			</c:when>
			<c:when test="${vo.t_name eq 'SK'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/sk_wyverns_small.png</c:set>
				<c:set var="team_back_color">#e1002a</c:set>
				<c:set var="team_color_css">color: #ff7f00; text-shadow: 0px 0px 2px black;</c:set>
			</c:when>
			<c:when test="${vo.t_name eq '한화'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/hanwha_eagles_small.png</c:set>
				<c:set var="team_back_color">#f37321</c:set>
				<c:set var="team_color_css">color: black;</c:set>
			</c:when>
			<c:when test="${vo.t_name eq '넥센'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/nexen_heroes_small.png</c:set>
				<c:set var="team_back_color">#900020</c:set>
				<c:set var="team_color_css">color: white;</c:set>
			</c:when>
			<c:when test="${vo.t_name eq 'LG'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/lg_twins_small.png</c:set>
				<c:set var="team_back_color">#b31c38</c:set>
				<c:set var="team_color_css">color: #000000;</c:set>
			</c:when>
			<c:when test="${vo.t_name eq 'KIA'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/kia_tigers_small.png</c:set>
				<c:set var="team_back_color">#af1e2b</c:set>
				<c:set var="team_color_css">color: white;</c:set>

			</c:when>
			<c:when test="${vo.t_name eq '삼성'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/samsung_lions_small.png</c:set>
				<c:set var="team_back_color">#0066b3</c:set>
				<c:set var="team_color_css">color: #bfc1c3;</c:set>

			</c:when>
			<c:when test="${vo.t_name eq '롯데'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/lotte_giants_small.png</c:set>
				<c:set var="team_back_color">#231f20</c:set>
				<c:set var="team_color_css">color: #f14e23;</c:set>

			</c:when>
			<c:when test="${vo.t_name eq 'NC'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/nc_dinos_small.png</c:set>
				<c:set var="team_back_color">#264e90</c:set>
				<c:set var="team_color_css">color: #c1ab86;</c:set>

			</c:when>
			<c:when test="${vo.t_name eq 'KT'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/kt_wiz_small.png</c:set>
				<c:set var="team_back_color">#000000</c:set>
				<c:set var="team_color_css">color: red;</c:set>

			</c:when>
			<c:otherwise>
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/korean_baseball_organization.png</c:set>
				<c:set var="team_back_color">initial</c:set>
				<c:set var="team_color_css">color:initial;</c:set>

			</c:otherwise>
		</c:choose>

		<div class="party_template"
			style="background-color:${team_back_color};">
			<div onclick="show_view(${vo.pt_idx})" class="party_name" style="${team_color_css}">${vo.pt_name }</div>
			<div class="team_img">
				<img alt="team_logo" src="${team_img}">
			</div>
			<div class="summary top">
				<span class="statu">${vo.pt_condition}</span><span class="purpose">${vo.pt_purpose}</span><span
					class="leader_count">${vo.leader_count}</span><br> <span
					class="maxPeople"><c:if test="${vo.pt_maxPeople eq 0}">제한없음</c:if>
					<c:if test="${vo.pt_maxPeople > 0}">${vo.pt_maxPeople}</c:if></span><span
					class="people">${vo.pt_people}</span><span class="leader_name">${vo.m_nick }</span>

			</div>

			<div class="dates">
				<span class="p_date">${vo.p_date}</span><span class="s_name">${vo.s_name}</span>
				<span class="pt_date">${vo.datetime}</span><span class="pt_location">${vo.addr1}</span>
			</div>
		</div>
	</c:forEach>
	<div class="paging">${page_html}</div>



</body>
</html>
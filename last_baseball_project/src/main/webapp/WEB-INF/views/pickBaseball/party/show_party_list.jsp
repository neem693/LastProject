<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.party_template {
	border: 1px solid black;
	clear:both;
	max-height: 300px;
}

.team_img{
width: 20%;
height: 100px;
box-sizing:border-box;
display:inline-block;
}
.team_img>img {
height: 100%;


	
}



.summary {
	box-sizing: border-box;
	display:inline-block;
	
	
	border: 1px solid black;
	width: 76%;
	height: 100%;
	vertical-align: top;
	
}

.summary>span {
	display: inline-block;
	padding: 20px 0;
	width: 30%;
	text-align:center;
	border: 1px solid black;
}

.leader_count {
	
}

.party_name {
	text-align: center;
	font-weight: bold;
}

.dates{
clear:both;

}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



	<c:forEach var="vo" items="${list}">
		<c:choose>
			<c:when test="${vo.t_name eq '두산'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/doosan_bears_small.png</c:set>
			</c:when>
			<c:when test="${vo.t_name eq 'SK'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/sk_wyverns_small.png</c:set>
			</c:when>
			<c:when test="${vo.t_name eq '한화'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/hanwha_eagles_small.png</c:set>
			</c:when>
			<c:when test="${vo.t_name eq '넥센'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/nexen_heroes_small.png</c:set>
			</c:when>
			<c:when test="${vo.t_name eq 'LG'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/lg_twins_small.png</c:set>
			</c:when>
			<c:when test="${vo.t_name eq 'KIA'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/kia_tigers_small.png</c:set>
			</c:when>
			<c:when test="${vo.t_name eq '삼성'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/samsung_lions_small.png</c:set>
			</c:when>
			<c:when test="${vo.t_name eq '롯데'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/lotte_giants_small.png</c:set>
			</c:when>
			<c:when test="${vo.t_name eq 'NC'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/nc_dinos_small.png</c:set>
			</c:when>
			<c:when test="${vo.t_name eq 'KT'}">
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/kt_wiz_small.png</c:set>
			</c:when>
			<c:otherwise>
				<c:set var="team_img">${pageContext.request.contextPath}/resources/images/small/korean_baseball_organization.png</c:set>
			</c:otherwise>
		</c:choose>

		<div class="party_template">
			<div class="party_name">${vo.pt_name }</div>
			<div class="team_img">
				<img alt="team_logo" src="${team_img}">
			</div>
			<div class="summary top">
				<span class="statu">${vo.pt_condition}</span> <span class="purpose">${vo.pt_purpose}</span>
				<span class="leader_count">${vo.leader_count}</span><br> <span
					class="maxPeople"><c:if test="${vo.pt_maxPeople eq 0}">제한없음</c:if>
					<c:if test="${vo.pt_maxPeople > 0}">${vo.pt_maxPeople}</c:if></span><span
					class="people">${vo.pt_people}</span><span class="leader_name">${vo.m_nick }</span>

			</div>
			
			<div class="dates">
				<span class="p_date">${vo.p_date}</span><span class="s_name">${vo.s_name}</span>
				<span class="pt_date">${vo.datetime}</span>
			</div>
		</div>
	</c:forEach>




</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.main {
	margin-left: 15%;
	margin-right: 15%;
}

.play_ul {
	border: 1px solid black;
	padding-left: 1px;
	list-style: none;
}

.play_list_main {
	float: left;
	width: 70%;
	box-sizing: border-box;
}

.right_side {
	border: 1px solid black;
	margin-left: 5%;
	float: right;
	width: 25%;
	box-sizing: border-box;
	width: 25%;
	float: right;
}

.calendar {
	width: 100%;
	border-collapse: collapse;
}

.calendar td {
	width: 14%;
	height: 200px;
}

tr, td, th {
	border: 1px solid black;
}
</style>
</head>
<body>
	<c:set var="day" value="0"></c:set>
	<!-- n은 기본적으로 반복문을 위한 것 -->
	<c:set var="n" value="0"></c:set>
	<!-- c는 기본적으로 n의 범위를 뜻함 -->
	<c:set var="c" value="${n}"></c:set>
	<!-- c로 해당 경기 범위를 결정함 -->
	<div class="main">
		<div class="play_list_main">
			<table class="calendar">
				<tr>
					<th>일</th>
					<th>월</th>
					<th>화</th>
					<th>수</th>
					<th>목</th>
					<th>금</th>
					<th>토</th>
				</tr>
				<c:forEach var="i" begin="0" end="5">
					<tr>
						<c:forEach var="j" begin="1" end="7">
							<c:choose>
								<c:when test="${first_day > j || day>last_day}">
									<td></td>
								</c:when>
								<c:otherwise>
									<td>${day = day +1}<c:if test="${fisrt_day != 0}">
											<c:set var="first_day" value="0"></c:set>
										</c:if> <c:forEach var="i" begin="${n}" end="${n+4}">
											<c:if test="${list[i].day eq day}">
												<p>
													<%--${ list[i].day}${day } ${i}${c} --%>
												</p>
												<c:set var="c" value="${c+1}" />
											</c:if>
											<!-- 경기가 있을 떄마다 c를 증가 밑 foreach문이 해당 c값 만큼 경기 갯수 출력 -->

										</c:forEach> <c:if test="${c>n}">
											<ul class="play_ul">
												<c:forEach var="i" begin="${n}" end="${c-1}">
													<c:choose>
														<c:when test="${list[i].p_score eq 'C'}">

															<li title="${list[i].p_rts}">${list[i].t_away}-
																${list[i].t_home} <%-- ${n} ${c} --%>
															</li>


														</c:when>
														<c:when test="${list[i].p_score eq 'T'}">

															<li>${list[i].t_away}<span class="li_playing">
																	진행중</span> ${list[i].t_home} <%-- ${n} ${c} --%>
															</li>

														</c:when>


														<c:otherwise>

															<li>${list[i].t_away}${list[i].p_score}
																${list[i].t_home} <%-- ${n} ${c} --%>
															</li>

														</c:otherwise>


													</c:choose>
													<c:set var="n" value="${n+1}"></c:set>
												</c:forEach>
											</ul>
										</c:if>
									</td>



								</c:otherwise>
							</c:choose>
						</c:forEach>


					</tr>
				</c:forEach>
			</table>
		</div>

		<div class="right_side">이곳은 팀이 올 자리이다.</div>
	</div>

</body>
</html>
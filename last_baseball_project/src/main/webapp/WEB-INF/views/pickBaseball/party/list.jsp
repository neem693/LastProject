<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.calendar {
	width: 80%;
	border-collapse: collapse;
}

tr, td, th {
	border: 1px solid black;
}
</style>
</head>
<body>
	<c:set var="day" value="0"></c:set>
	<c:set var="n" value="0"></c:set>
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
								</c:if> <c:if test="${list[n].day eq day}">
									<ul>

										<c:forEach var="i" begin="${n}" end="${n+4}">
											<li>${list[i].t_away}${list[i].p_score}${list[i].t_home}</li>

										</c:forEach>
									</ul>
									<c:set var="n" value="${n+5}"></c:set>
								</c:if>
							</td>



						</c:otherwise>
					</c:choose>

				</c:forEach>


			</tr>
		</c:forEach>
	</table>



</body>
</html>
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

.match_day {
	cursor: pointer;
}

.match_button { //
	display: none;
	visibility: hidden;
	opacity: 0;
	height: 0;
	transition: all 0.3s;
}

.match_count {
	float: right;
	font-weight: bold;
	font-size: large;
}
</style>
<script
	src="${pageContext.request.contextPath}/resources/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
var bs;
var month_num = ${month};
var year_num = ${year};

$(document).ready(function(){
	bs = document.getElementsByClassName("match_button");
});

function show_party_list(d){
	
	var op = {
		    url:'show_party_list.do',
		    data:{
		        'year':'${year}',
		        'month':'${month}',
		        'team':'${team}',
		        'day':d
		        
		    },
		    async:false,
		    success:function(result){
		        //alert(result);
		        var res= result;
		        res = eval(res);
		  
		    }
	}

	
	$.ajax(op);
	
	
	
}

function showButton(m){
	for(var i=0;i<bs.length;i++){
	//bs[i].style.display = "none";
		bs[i].style.visibility = "hidden";
		bs[i].style.height = "0";
		bs[i].style.opacity  = "0";
		
		
	}
	
	var bs_one = m.getElementsByClassName("match_button");
	bs_one[0].style.visibility = "visible";
	bs_one[0].style.opacity = "1";
	bs_one[0].style.height = "50px";
	
	//console.log(day_num);
	console.log(month_num);

	
	
}
function create_party(day_num){
	
	
	
	
	location.href = "${pageContext.request.contextPath}/party/insert_party.do?year=" + year_num + "&month=" + month_num +"&day=" + day_num; 
	
}


</script>
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
									<td><c:set var="day" value="${day+1 }"></c:set> ${ day}<span
										class="match_count"><c:set var="day_key">${day}</c:set>
											<%-- day를 key값으로 설정해주기 위한 것이다. --%> <c:out
												value="${ party_count[day_key]}" /> </span>
									<c:if test="${fisrt_day != 0}">
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
											<!-- 매치에 해당하는 파티 갯수를 출력하는 것을 말한다. -->

											<a class="match_day" onclick="showButton(this);">
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
												<ul class="match_button">
													<c:choose>
														<c:when
															test="${(this_month==month&&this_year==year&&today <=day)||(this_month<=month&&this_year==year)}">
															<li><button onclick="create_party(${day})">파티생성</button></li>
														</c:when>

													</c:choose>
													<li><button
															onclick="show_party_list(${day}); return false;">파티리스트보기</button></li>
												</ul>
										</c:if> </a></td>



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
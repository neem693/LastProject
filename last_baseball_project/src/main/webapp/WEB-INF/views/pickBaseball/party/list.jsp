<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/party/font.css">
<style>

.ajaxLoadingModal {
	z-index: 1000;
	position: fixed;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
	display: none;
}

.ajaxLoadingModal img {
	padding-top: 10%;
	display: block;
	margin: auto;
	display: block;
}

.ajaxLoadingModal .loading_text {
	color: white;
	text-align: center;
	font-size: x-large;
	letter-spacing: 10px;
}

.main {
	margin-left: 15%;
	margin-right: 15%;
}

.play_ul {
	width: 100%;
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

.match_button li{
	list-style: none;
	margin: 0;
	
}
.match_button li button{
	width: 100%;
	background-color: #c2d6d6;
	color: gray;
	border: none;
	padding: 10px 0;

}
.match_button li button:hover{
	
	background-color: gray;
	color:#c2d6d6;

}

.match_button {
	margin: 0;
	padding: 0;
	visibility: hidden;
	opacity: 0;
	height: 0;
	transition: all 0.3s;
}

.match_button.open {
	visibility: visible;
	height: 100px;
	opacity: 1;
}

.match_count {
	float: right;
	font-weight: bold;
	font-size: large;
}

.show_party_list {
	float: left;
	width: 70%;
	box-sizing: border-box;
}

@media screen and (max-width: 1200px) {
	.play_list_main {
		float: none;
		width: 100%;
		overflow: scroll;
	}
	.calendar{
	width: 1200px;
	}
	.right_side {
		float: none;
		width: 100%;
		margin-left: 0;
	}
	.show_party_list {
		float: none;
		width: 100%;
	}
}

@media screen and (max-width: 1000px) {
	.main {
		margin: 0 2px;
	}
/* 	.calendar {
		table-layout: fixed;
	} */
}
</style>
<script
	src="${pageContext.request.contextPath}/resources/jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
var bs; //buttons 즉 버튼들의 집합
var month_num = ${month};
var year_num = ${year};
var team = '${team}';
var party_list_section;
var party_list_top_offset;
var day_saved; //최근 일에 대한걸 저장함.
var page_saved; //페이징 처리시 최근 페이지에 대해서 저장함;


$(document).ready(function(){
	bs = document.getElementsByClassName("match_button");
	$party_list_section = $("#party_list");
	
	setTimeout(function(){
		party_list_top_offset = $party_list_section.offset().top;
	//	alert(party_list_top_offset);
	
	$(document).ajaxStart(function(){
		$('.ajaxLoadingModal').css("display","block");
	});
	$(document).ajaxStop(function(){
		$('.ajaxLoadingModal').css("display","none");
	});
		
	},800);
	

	
	$(window).resize(function(){
		setTimeout(function(){
		party_list_top_offset = $party_list_section.offset().top;
		//console.log(party_list_top_offset);
		},300);
	});
	
	var fail = '${param.fail}';
	setTimeout(function(){
		if(fail=="joined")
			alert("이미 이날 파티가 예약되어 있어 파티를 생성하지 못합니다.");
		else if(fail=='not_found')
			alert("잘못된 접근입니다.");
	},500);
	
		
	
});




function show_party_list(d){
	
	day_saved = d;
	

	
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
		    	$party_list_section.html(result);
		    	//밑으로 자연스럽게 옮겨주는 것
		    	party_list_top_offset = $party_list_section.offset().top;
		    	$('html,body').animate({scrollTop: party_list_top_offset},800);
		  
		    }
	}

	
	$.ajax(op);
	
	
	
}


function party_list_page(d,page){
	
	
	day_saved = d;
	page_saved = page;

	
	var op = {
		    url:'show_party_list.do',
		    data:{
		        'year':'${year}',
		        'month':'${month}',
		        'team':'${team}',
		        'day':d,
		        'page':page
		        
		    },
		    async:false,
		    success:function(result){
		        //alert(result);
		    	$party_list_section.html(result);
		    	//밑으로 자연스럽게 옮겨주는 것
		    	party_list_top_offset = $party_list_section.offset().top;
		    	$('html,body').animate({scrollTop: party_list_top_offset},800);
		  
		    }
	}

	
	$.ajax(op);
	
	
	
}

function showButton(m){
	for(var i=0;i<bs.length;i++){
	//bs[i].style.display = "none";
	/* 	bs[i].style.visibility = "hidden";
		bs[i].style.height = "0";
		bs[i].style.opacity  = "0"; */
		
		bs[i].classList.remove("open");
		
		
	}
	
	var bs_one = m.getElementsByClassName("match_button");
	bs_one[0].classList.add("open");
	/* bs_one[0].style.visibility = "visible";
	bs_one[0].style.opacity = "1";
	bs_one[0].style.height = "50px";
	 */
	//console.log(day_num);
	console.log(month_num);

	
	
}
function create_party(day_num){
	
	
	
	
	location.href = "${pageContext.request.contextPath}/party/insert_party.do?year=" + year_num + "&month=" + month_num +"&day=" + day_num; 
	
}


function show_view(pt_idx){
	
	
	
	location.href = "${pageContext.request.contextPath}/party/view.do?year=" + year_num + "&month=" + month_num +"&day=" + day_saved + "&team="+team+"&pt_idx="+pt_idx; 
	
	
	
}

</script>
</head>
<body>

<div class="ajaxLoadingModal">
		<img
			src="${pageContext.request.contextPath}/resources/images/ajax_loading/Reload.gif">
		<p class="loading_text">LOADING</p>
	</div>
<%@include file="/WEB-INF/views/main/header/header.jsp" %>	
	
	<c:set var="day" value="0"></c:set>
	<%-- n은 기본적으로 반복문을 위한 것 --%>
	<c:set var="n" value="0"></c:set>
	<%-- c는 기본적으로 n의 범위를 뜻함 --%>
	<c:set var="c" value="${n}"></c:set>
	<%-- c로 해당 경기 범위를 결정함 --%>
	
	
	
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
						<c:set var ="is_create" value = "true"/>
				<%-- is_create는 해당 당일에 파티생성을 해도될지에 대한것 처음 값을 투르로 주고, 밑에 값에서 체크하여 false를 줌 --%>
							<c:choose>
								<c:when test="${first_day > j || day>last_day}">
									<td></td>
								</c:when>
								<c:otherwise>
									<td><c:set var="day" value="${day+1 }"></c:set> ${ day}<span
										class="match_count"><c:set var="day_key">${day}</c:set>
											<%-- day를 key값으로 설정해주기 위한 것이다. --%> <c:out
												value="${ party_count[day_key]}" /> </span> <c:if
											test="${fisrt_day != 0}">
											<c:set var="first_day" value="0"></c:set>
										</c:if> <c:forEach var="i" begin="${n}" end="${n+4}">
											<c:if test="${list[i].day eq day}">
												<%-- <!-- 경기가 있을 떄마다 c를 증가 밑 foreach문이 해당 c값 만큼 경기 갯수 출력 -->	
											<p>
													${ list[i].day}${day } ${i}${c}
												</p> --%>
												<c:set var="c" value="${c+1}" />
											</c:if>


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
																		<c:if test="${is_create && true}">
																		<%-- is_create가 true라면 false로 바꿔준다는 것 --%>
																			<c:set var="is_create" value="false"></c:set>
																		</c:if>
																</li>

															</c:when>
															
															<c:when test="${list[i].p_score eq '예정'}">

																<li>${list[i].t_away} ${list[i].p_score}
																	${list[i].t_home} <%-- ${n} ${c} --%>
																		
																</li>

															</c:when>

															<c:otherwise>

																<li>${list[i].t_away}${list[i].p_score}
																	${list[i].t_home} <%-- ${n} ${c} --%>
																		<c:if test="${is_create && true}">
																		<%-- is_create가 true라면 false로 바꿔준다는 것 --%>
																			<c:set var="is_create" value="false"></c:set>
																		</c:if>
																</li>

															</c:otherwise>





														</c:choose>
											<%-- 		${is_create} --%>
														<c:set var="n" value="${n+1}"></c:set>
													</c:forEach>
												</ul>
												<ul class="match_button">
													<c:choose>
														<c:when
															test="${((this_month==month&&this_year==year&&today <=day)||(this_month<month&&this_year==year))&&(is_create)}">
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

		<aside class="right_side">이곳은 팀이 올 자리이다.</aside>
		<div class="show_party_list" id="party_list"></div>
	</div>
	<div style="clear: both;"></div>
	<%@include file="/WEB-INF/views/main/footer/footer.jsp" %>	


</body>
</html>
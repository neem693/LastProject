<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/party/font.css">
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
	border-top: 1px solid black;
	border-bottom: 1px solid black;
	
	padding-left: 1px;
	list-style: none;
}

.play_list_main {
	float: left;
	width: 70%;
	box-sizing: border-box;
}

.right_side {
	/* border: 1px solid black; */
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

}

.match_day {
	cursor: pointer;
}

.match_button li {
	list-style: none;
	margin: 0;
}

.match_button li button {
	width: 100%;
	background-color: #c2d6d6;
	color: gray;
	border: none;
	padding: 10px 0;
}

.match_button li button:hover {
	background-color: gray;
	color: #c2d6d6;
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
	text-shadow: 5px 5px 5px black;
	border: 1px solid black;
	border-bottom: none;
	padding: 5px;
	
	
}

.show_party_list {
	float: left;
	width: 70%;
	box-sizing: border-box;
}

.cal_nav {
/* 	border: 1px solid black; */
border-bottom: 1px solid black;
	text-align: center;
}

.pre_cal, .after_cal {
	font-size: xx-large;
	font-weight: 900;
	text-decoration: none;
	
}

.pre_cal {
	float: left;
}

.after_cal {
	float: right;
}

.cal_year_month {
	font-size: xx-large;
}

.team_list {
	position: relative;
	height: 100px;
	/* 	border: 1px solid black;
 */
	transition: all 0.5s;
	cursor: pointer;
}

.team_list_img {
	object-fit: cover;
	width: 150px;
	height: 100px;
	transition: all 0.5s;
}

.team_list_img_selected {
	width: 200px;
}

.team_list_back {
	position: absolute;
	top: 0;
	opacity: 0;
	width: 100%;
	height: 100%;
	text-align: right;
	font-size: xx-large;
	padding: 20px;
	transition: all 0.5s;
}

.team_list_back_selected {
	opacity: 1;
}

.team_intro {
	font-size: xx-large;
	text-align: center;
	border-bottom: 1px solid black;
}



.pre_day{
	background-color: rgb(230, 230, 230);
}

.today{
	background-color: rgb(242, 255, 204);
}

.after_day{

	background-color: rgb(204, 230, 255);

}



@media screen and (max-width: 1200px) {
	.play_list_main {
		float: none;
		width: 100%;
		overflow: scroll;
	}
	.calendar {
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
var $party_list_section;
var party_list_top_offset;
var day_saved; //최근 일에 대한걸 저장함.
var page_saved; //페이징 처리시 최근 페이지에 대해서 저장함;


//선택된 팀
var team_selt = '${team}';

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
	
	
	////team_selt는 팀 슬렉트를 나타내고, 비어있지 않다면, 뭔가가 선택된걸 의미한다.
	if(team_selt!=''){
		var img =document.getElementById(team_selt).getElementsByClassName("team_list_img")[0];
		var back =document.getElementById(team_selt).getElementsByClassName("team_list_back")[0];
		img.classList.add("team_list_img_selected");
		back.classList.add("team_list_back_selected");
			
	}
	//팀 전체를 선택하도록 한다.
	else if(team_selt==''){
		team_selt = "team_list_all";
		var img =document.getElementById(team_selt).getElementsByClassName("team_list_img")[0];
		var back =document.getElementById(team_selt).getElementsByClassName("team_list_back")[0];
		img.classList.add("team_list_img_selected");
		back.classList.add("team_list_back_selected");
	}
		
	
	//팀 이벤트
	$(".team_list").mouseenter(function(){
		var img = this.getElementsByClassName("team_list_img")[0];
		var back = this.getElementsByClassName("team_list_back")[0];
		img.classList.add("team_list_img_selected");
		back.classList.add("team_list_back_selected");
		
	});
	
	$(".team_list").mouseleave(function(){
		if(team_selt == this.id)
			return;
		var img = this.getElementsByClassName("team_list_img")[0];
		var back = this.getElementsByClassName("team_list_back")[0];
		img.classList.remove("team_list_img_selected");
		back.classList.remove("team_list_back_selected");
		
	});
	
	
		
	
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
	
	
	
	
	location.href = "${pageContext.request.contextPath}/party/insert_party.do?year=" + year_num + "&month=" + month_num +"&day=" + day_num + "&team=" + team; 

	
}


function show_view(pt_idx){
	
	
	
	location.href = "${pageContext.request.contextPath}/party/view.do?year=" + year_num + "&month=" + month_num +"&day=" + day_saved + "&team="+team+"&pt_idx="+pt_idx; 
	
	
	
}


function changeTeam(team){
	
	var team_group = document.getElementsByClassName("team_list");

	for(var i =0;i<team_group.length;i++){
		if(team_group[i].id == team.id)
			continue;
		
		team_group[i].getElementsByClassName("team_list_back")[0].classList.remove("team_list_back_selected");
		team_group[i].getElementsByClassName("team_list_img")[0].classList.remove("team_list_img_selected");
		
	}
	
	team_selt = team.id;
	
	setTimeout(function(){
		location.href="?year=${year}&month=${month}&team=" + team.id;
		
	},1000)
	
	
}

</script>
</head>
<body>

	<div class="ajaxLoadingModal">
		<img
			src="${pageContext.request.contextPath}/resources/images/ajax_loading/Reload.gif">
		<p class="loading_text">LOADING</p>
	</div>
	<%@include file="/WEB-INF/views/main/header/header.jsp"%>

	<c:set var="day" value="0"></c:set>
	<%-- n은 기본적으로 반복문을 위한 것 --%>
	<c:set var="n" value="0"></c:set>
	<%-- c는 기본적으로 n의 범위를 뜻함 --%>
	<c:set var="c" value="${n}"></c:set>
	<%-- c로 해당 경기 범위를 결정함 --%>

	<c:set var="after_year" value="${year}" />
	<c:set var="after_month" value="${month +1}" />
	<c:set var="before_year" value="${year}" />
	<c:set var="before_month" value="${month -1}" />


	<c:choose>
		<c:when test="${month eq 1}">
			<c:set var="before_year" value="${before_year -1}"></c:set>
			<c:set var="before_month" value="12"></c:set>
		</c:when>
		<c:when test="${month eq 12 }">
			<c:set var="after_year" value="${after_year +1}"></c:set>
			<c:set var="after_month" value="1"></c:set>
		</c:when>
	</c:choose>


	<div class="main">
		<div class="play_list_main">
			<div class="cal_nav">
				<a href="?year=${before_year}&month=${before_month}&team=${team}"
					class="pre_cal">&lt;&lt;</a> <span class="cal_year_month">${year}/${month}</span><a
					href="?year=${after_year}&month=${after_month}&team=${team}"
					class="after_cal">&gt;&gt;</a>
			</div>
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
							<c:set var="is_create" value="true" />
							<%-- is_create는 해당 당일에 파티생성을 해도될지에 대한것 처음 값을 투르로 주고, 밑에 값에서 체크하여 false를 줌 --%>
							<c:choose>
								<c:when test="${first_day > j || day>last_day}">
									<td></td>
								</c:when>
								<c:otherwise>



									<c:set var="day" value="${day+1 }"></c:set>
										<%--오늘인지 어제인지 내일인지 판단 --%>
										<c:choose>


											<c:when test="${year gt this_year}">
												<c:set var="day_attr">after_day</c:set>
											</c:when>
											<c:when test="${year eq this_year  && month gt this_month}">
												<c:set var="day_attr">after_day</c:set>
											</c:when>
											<c:when
												test="${year eq this_year  && month eq this_month && day  gt this_day}">
												<c:set var="day_attr">after_day</c:set>
											</c:when>
											<c:when
												test="${year eq this_year  && month eq this_month && day  eq this_day}">
												<c:set var="day_attr">today</c:set>
											</c:when>
											<c:when
												test="${year eq this_year  && month eq this_month && day  lt this_day}">
												<c:set var="day_attr">pre_day</c:set>
											</c:when>
											<c:when test="${year eq this_year  && month lt this_month}">
												<c:set var="day_attr">pre_day</c:set>
											</c:when>
											<c:when test="${year lt this_year}">
												<c:set var="day_attr">pre_day</c:set>
											</c:when>


										</c:choose>

										<td class="${day_attr}">
									 ${ day}  <c:set var="day_key">${day}</c:set>
										<%-- day를 key값으로 설정해주기 위한 것이다. --%> 
										
										
										
										
										
										
										<c:if test="${not empty party_count[day_key] }">
										<span class="match_count"><c:out
											value="${ party_count[day_key]}" /> </span>
											</c:if>
											
											
											
											
											
											
											
											
											
											
											
											
									<c:if test="${fisrt_day != 0}">
										<c:set var="first_day" value="0"></c:set>
									</c:if>
									<c:forEach var="i" begin="${n}" end="${n+5}">
										<c:if test="${list[i].day eq day}">
											<%-- <!-- 경기가 있을 떄마다 c를 증가 밑 foreach문이 해당 c값 만큼 경기 갯수 출력 -->	
											<p>
													${ list[i].day}${day } ${i}${c}
												</p> --%>
											<c:set var="c" value="${c+1}" />
										</c:if>


									</c:forEach>
									<c:if test="${c>n}">
										<!-- 매치에 해당하는 파티 갯수를 출력하는 것을 말한다. -->

										<a class="match_day" onclick="showButton(this);">

											<ul class="play_ul">
												<c:forEach var="i" begin="${n}" end="${c-1}">
													<c:choose>
														<c:when test="${list[i].p_score eq 'C'}">

															<li title="${list[i].p_rts}">${list[i].t_away}<span>
																	- </span> ${list[i].t_home} <%-- ${n} ${c} --%>

															</li>


														</c:when>
														<c:when test="${list[i].p_score eq 'T'}">

															<li>${list[i].t_away}<span class="li_playing">
																	진행중</span> ${list[i].t_home} <%-- ${n} ${c} --%> <c:if
																	test="${is_create && true}">
																	<%-- is_create가 true라면 false로 바꿔준다는 것 --%>
																	<c:set var="is_create" value="false"></c:set>
																</c:if>
															</li>

														</c:when>

														<c:when test="${list[i].p_score eq '예정'}">

															<li>${list[i].t_away}<%-- 차라리 엔터가 붙어야지만 --%>
																${list[i].p_score} ${list[i].t_home} <%-- ${n} ${c} --%>

															</li>

														</c:when>

														<c:otherwise>

															<li>${list[i].t_away}<%-- 차라리 엔터가 붙어야지만 --%>
																${list[i].p_score} ${list[i].t_home} <%-- ${n} ${c} --%>
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
									</c:if>
									</a>
									</td>



								</c:otherwise>
							</c:choose>
						</c:forEach>


					</tr>
				</c:forEach>
			</table>
		</div>

		<aside class="right_side">
			<div class="team_intro">팀선택</div>
			<div class="team_list" id="team_list_all" onclick="changeTeam(this)">
				<img class="team_list_img"
					src="${pageContext.request.contextPath}/resources/images/big/korean_baseball_organization.png">
				<div class="team_list_back"
					style="background-image: linear-gradient(to right, rgba(255, 255, 255, 0), initial); color: initial;">팀_전체</div>
			</div>
			<c:forEach var="vo" items="${t_list}">
				<c:choose>
					<c:when test="${vo.t_name eq '두산'}">
						<c:set var="team_img">${pageContext.request.contextPath}/resources/images/big/doosan_bears.png</c:set>
						<c:set var="team_back_color">#110f29</c:set>
						<c:set var="team_color_css">color: white;</c:set>
						<c:set var="id">doosan</c:set>
					</c:when>
					<c:when test="${vo.t_name eq 'SK'}">
						<c:set var="team_img">${pageContext.request.contextPath}/resources/images/big/sk_wyverns.png</c:set>
						<c:set var="team_back_color">#e1002a</c:set>
						<c:set var="team_color_css">color: #ff7f00; text-shadow: 0px 0px 2px black;</c:set>
						<c:set var="id">SK</c:set>
					</c:when>
					<c:when test="${vo.t_name eq '한화'}">
						<c:set var="team_img">${pageContext.request.contextPath}/resources/images/big/hanwha_eagles.png</c:set>
						<c:set var="team_back_color">#f37321</c:set>
						<c:set var="team_color_css">color: black;</c:set>
						<c:set var="id">한화</c:set>
					</c:when>
					<c:when test="${vo.t_name eq '넥센'}">
						<c:set var="team_img">${pageContext.request.contextPath}/resources/images/big/nexen_heroes.png</c:set>
						<c:set var="team_back_color">#900020</c:set>
						<c:set var="team_color_css">color: white;</c:set>
						<c:set var="id">넥센</c:set>
					</c:when>
					<c:when test="${vo.t_name eq 'LG'}">
						<c:set var="team_img">${pageContext.request.contextPath}/resources/images/big/lg_twins.png</c:set>
						<c:set var="team_back_color">#b31c38</c:set>
						<c:set var="team_color_css">color: #000000;</c:set>
						<c:set var="id">LG</c:set>
					</c:when>
					<c:when test="${vo.t_name eq 'KIA'}">
						<c:set var="team_img">${pageContext.request.contextPath}/resources/images/big/kia_tigers.png</c:set>
						<c:set var="team_back_color">#af1e2b</c:set>
						<c:set var="team_color_css">color: white;</c:set>
						<c:set var="id">KIA</c:set>

					</c:when>
					<c:when test="${vo.t_name eq '삼성'}">
						<c:set var="team_img">${pageContext.request.contextPath}/resources/images/big/samsung_lions.png</c:set>
						<c:set var="team_back_color">#0066b3</c:set>
						<c:set var="team_color_css">color: #bfc1c3;</c:set>
						<c:set var="id">Samsung</c:set>
					</c:when>
					<c:when test="${vo.t_name eq '롯데'}">
						<c:set var="team_img">${pageContext.request.contextPath}/resources/images/big/lotte_giants.png</c:set>
						<c:set var="team_back_color">#231f20</c:set>
						<c:set var="team_color_css">color: #f14e23;</c:set>
						<c:set var="id">Lotte</c:set>

					</c:when>
					<c:when test="${vo.t_name eq 'NC'}">
						<c:set var="team_img">${pageContext.request.contextPath}/resources/images/big/nc_dinos.png</c:set>
						<c:set var="team_back_color">#264e90</c:set>
						<c:set var="team_color_css">color: #c1ab86;</c:set>
						<c:set var="id">NC</c:set>

					</c:when>
					<c:when test="${vo.t_name eq 'KT'}">
						<c:set var="team_img">${pageContext.request.contextPath}/resources/images/big/kt_wiz.png</c:set>
						<c:set var="team_back_color">#000000</c:set>
						<c:set var="team_color_css">color: red;</c:set>
						<c:set var="id">KT</c:set>
					</c:when>
					<c:otherwise>
						<c:set var="team_img">${pageContext.request.contextPath}/resources/images/big/korean_baseball_organization.png</c:set>
						<c:set var="team_back_color">initial</c:set>
						<c:set var="team_color_css">color:initial;</c:set>
						<c:set var="id">None_team</c:set>

					</c:otherwise>
				</c:choose>
				<div class="team_list" id="${vo.t_name}" onclick="changeTeam(this)">
					<img class="team_list_img" src="${team_img}">
					<div class="team_list_back"
						style="background-image:linear-gradient(to right,rgba(255,255,255,0),${team_back_color}); ${team_color_css}">${vo.t_name}</div>
				</div>
			</c:forEach>



		</aside>
		<div class="show_party_list" id="party_list"></div>
	</div>
	<div style="clear: both;"></div>
	<%@include file="/WEB-INF/views/main/footer/footer.jsp"%>


</body>
</html>
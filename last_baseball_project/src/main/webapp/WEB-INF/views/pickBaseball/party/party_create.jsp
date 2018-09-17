<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=1025388614b7b8c70d0002c6339d84f4&libraries=services
"></script>
<script
	src="${pageContext.request.contextPath}/resources/jquery/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/js/daum_map.js"></script>

<script type="text/javascript">
	var map_class;
	var centeraddr;

	function send_form() {
		var f = document.getElementById("baseball_party");
		//console.log(f.date);
		var date = f.date.value;
		
		
		
		if(date == "time")
			f.pt_day.value = f.pt_day2.value;
		else if(date == "day")
			f.pt_day.value = f.pt_day1.value;
		else {
			alert("예약유형을 선택해주세요");
			return;
		}
		console.log(f.pt_name.value);
		console.log(f.date.value);
		console.log(f.pt_day.value);
		
			

		//form_dd.submit();
	}
	function check_radio(radio) {
		var value = radio.value;
		console.log(value);
		var day = document.getElementById("day");
		var hour = document.getElementById("hour");

		var date_day_hour = document.getElementsByClassName("hide");
		console.log(date_day_hour.length)

		for (var i = 0; i < date_day_hour.length; i++) {
			date_day_hour[i].style.display = "none";
		}

		if (value == "day") {
			day.style.display = "block";
		} else if (value == "time") {
			hour.style.display = "block";
		}
	}

	function check_purpose(p) {

	}

	function search_location() {
		var search_text = document.getElementById("search_addr").value;
		ps.keywordSearch(search_text, placesSearchCB);
	}

	function ajax_for_stadium(play) {

		var p_idx = play.value;
		var res;
		var stadium = document.getElementById("stadium");

		var op = {
			url : 'select_stadium.do',
			data : {
				'p_idx' : p_idx
			},
			async : false,
			success : function(result) {

				result = eval(result);
				console.log(result);
				res = result[0].result;
				stadium.value = res;
				/////////검색결과로
				ps.keywordSearch(res, placesSearchCB);
				////////해당 맵으로 이동만 함
				if (map_class.style.opacity == 0) {
					map_class.style.opacity = "1";
					centeraddr_id.style.opacity = "1";
				}

			}

		}
		$.ajax(op);

	}
	function location_pick(that) {
		var value = that.value;
		var addr_search = document.getElementById("search_addr");
		var addr_search_b = document.getElementById("search_addr_b");
		var help = document.getElementById("search_help");
		console.log(addr_search_b.value)

		addr_search.style.display = "none";
		addr_search_b.style.display = "none";
		help.style.display = "none"

		if (value == 1) {
			addr_search.style.display = "block";
			addr_search_b.style.display = "block";
			help.style.display = "block";

		}

	}

	$(document)
			.ready(
					function() {
						map_class = document.getElementsByClassName("map")[0];
						centeraddr_id = document.getElementById("centerAddr");
						make_map();

						CKEDITOR
								.replace(
										'party_editor',
										{
											filebrowserUploadUrl : '${pageContext.request.contextPath}/party_image_upload.do',
											filebrowserUploadMethod : 'form',
											language : 'ko'
										});
						CKEDITOR.on('dialogDefinition', function(ev) {
							var dialogName = ev.data.name;
							var dialogDefinition = ev.data.definition;
							switch (dialogName) {
							case 'image': //Image Properties dialog
								//dialogDefinition.removeContents('info');
								dialogDefinition.removeContents('Link');
								dialogDefinition.removeContents('advanced');
								break;
							}
						});

					});
</script>
<style type="text/css">
table {
	border-collapse: collapse;
	width: 70%;
}

.select {
	
}

.hide {
	display: none;
}

.addr1 {
	width: 50%;
}

.map {
	width: 100%;
	height: 500px;
}

#centerAddr, .map {
	opacity: 0;
	transition: all 1s;
}

#search_addr, #search_addr_b, #search_help {
	display: none;
}
</style>
</head>
<body>

	<!-- 삽입해야 할 것. 파티명, 날짜, 목적, 경기, 최대인원수, 모일장소, 파티설명  -->
	<p class="date">
		일시:<span class="year_value">${param.year}</span>년<span
			class="month_value">${param.month}</span>월<span class="day_value">${param.day}</span>일
	</p>

	<form id="baseball_party">
		<table class="form_table">
			<tr>
				<td>파티 이름</td>
				<td><input name="pt_name"></td>
			</tr>
			<tr>
				<td>예약유형</td>
				<td>골라주세요<input type="radio" name="date" value="none"
					checked="checked" onclick="check_radio(this)"><br>
					시간으로 잡기<input type="radio" name="date" value="time"
					onclick="check_radio(this)"><br> 날짜로 잡기<input
					type="radio" name="date" value="day" onclick="check_radio(this)"><br>
					<select name="pt_day1" id="day" class="hide">
						<option value="-1" selected="selected">경기시작 1일 전</option>
						<option value="-2">경기시작 2일 전</option>
						<option value="-10">경기시작 3일 전 이상</option>
				</select> <select name="pt_day2" id="hour" class="hide">

						<option value="+2">경기시작 2시간 후</option>
						<option value="+1">경기시작 1시간 후</option>
						<option value="0">경기시간에 딱 맞춰서</option>
						<option value="-1" selected="selected">경기시작 1시간 전</option>
						<option value="-2">경기시작 2시간 전</option>
						<option value="-3">경기시작 3시간 전</option>
						<option value="-4">경기시작 4시간 전</option>
						<option value="-5">경기시작 5시간 전</option>
						<option value="-6">경기시작 6시간 전</option>
						<option value="-12">그 이상</option>

				</select><br>
				<input type="hidden" name="pt_day"></td>
			</tr>
			<tr>
				<td>파티 목적</td>
				<td><select id="purpose" name="pt_purpose"
					onchange="check_purpose(this)">
						<option value="0" selected="selected">목적 선택</option>
						<option value="1">구장내 특수시설 이용</option>
						<option value="2">신나는 응원</option>
						<option value="3">일단 모여라!</option>
						<option value="10">기타</option>
				</select></td>
			</tr>
			<tr>
				<td>최대인원수</td>
				<td>※해당 인원수는 본인 포함입니다.<br> <select name="pt_maxPeople"
					id="max_size"><option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="0">제한없음</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>경기 선택</td>
				<td><select id="select_match" name="p_idx"
					onchange="ajax_for_stadium(this);">
						<option value="0" selected="selected">경기 선택</option>
						<c:forEach var="o" items="${match_list}">
							<option value="${o.p_idx}">${o.t_away}VS${o.t_home}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>파티 설명</td>
				<td><textarea id="party_editor" name="pt_text"></textarea></td>
			</tr>

			<tr>
				<td>모일장소</td>

				<td><label id="stadium_label">경기장:</label><input name="stadium"
					id="stadium" disabled="disabled"> <select
					onchange="location_pick(this);"><option
							selected="selected" value="0">경기장 주위</option>
						<option value="1">경기장 외부</option></select><input id="search_addr">
					<button value="버튼" id="search_addr_b"
						onclick="search_location();return false;">위치 검색</button> <br>
					<p id="search_help">※위치를 검색하면 해당 주소로 맵이 이동합니다.</p>
					<p>※지도를 클릭하여 마크를 만들어서 모일 장소를 명확히 지정해주세요</p> <br>
					<div id="centerAddr"></div>
					<div class="map" id="map"></div> <label>실제주소:</label> <input
					readonly="readonly" class="addr1" id="real_addr" name="addr1"><br>
					<label>주소메모:</label><input class="addr2" id="some_addr"
					name="addr2"> <input type="hidden" readonly="readonly"
					id="coor_x" name="addr3"><input readonly="readonly"
					type="hidden" id="coor_y" name="addr4"> <input
					type="hidden" name="pt_location"> <!-- addr1은 도로명 주소_번지주소\ addr2는 메모주소 \ 가장 중요한 addr3는 x좌표 \ 또 중요한 addr4는 y좌표 -->
				</td>
			</tr>




		</table>
	</form>

	<button onclick="send_form();return false;">보내기</button>

</body>
</html>
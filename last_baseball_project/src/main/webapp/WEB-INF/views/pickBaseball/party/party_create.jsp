<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
<script type="text/javascript">

var map;


	function send_form() {
		var form_dd = document.getElementById("baseball_party");

		form_dd.submit();
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

	function ajax_for_stadium(play) {

		////////////////해당 주소로 맵 이동////////////////////

	

		// 장소 검색 객체를 생성합니다
		var ps = new daum.maps.services.Places();

		// 키워드로 장소를 검색합니다
		ps.keywordSearch('고척스카이돔', placesSearchCB);

		// 키워드 검색 완료 시 호출되는 콜백함수 입니다
		function placesSearchCB(data, status, pagination) {
			if (status === daum.maps.services.Status.OK) {

				// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
				// LatLngBounds 객체에 좌표를 추가합니다
				var bounds = new daum.maps.LatLngBounds();

				for (var i = 0; i < 1; i++) {
					//displayMarker(data[i]);    
					bounds.extend(new daum.maps.LatLng(data[i].y, data[i].x));
				}

				// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
				map.setBounds(bounds);
				map.setLevel(3);
			}
		}
		//////////////////////////////
		/////////선택시 해당 주소 출력///////
		/////////////////////////////
	
		///////////////////여기까지/////////////////

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
				res = result;
				stadium.value = res;

			}

		}
		$.ajax(op);

	}

	$(document).ready(function() {
		
		var infowindow = new daum.maps.InfoWindow({
			zIndex : 1
		});

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new daum.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
			level : 5
		// 지도의 확대 레벨
		};

		// 지도를 생성합니다    
		map = new daum.maps.Map(mapContainer, mapOption);
		
		var geocoder = new daum.maps.services.Geocoder();

		var marker = new daum.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
		infowindow = new daum.maps.InfoWindow({
			zindex : 1
		}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

		// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
		searchAddrFromCoords(map.getCenter(), displayCenterInfo);

		// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
		daum.maps.event.addListener(map, 'click', function(mouseEvent) {
			searchDetailAddrFromCoords(mouseEvent.latLng, function(result,
					status) {
				if (status === daum.maps.services.Status.OK) {
					var detailAddr = !!result[0].road_address ? '<div>도로명주소 : '
							+ result[0].road_address.address_name + '</div>'
							: '';
					detailAddr += '<div>지번 주소 : '
							+ result[0].address.address_name + '</div>';

					var content = '<div class="bAddr">'
							+ '<span class="title">법정동 주소정보</span>'
							+ detailAddr + '</div>';

					// 마커를 클릭한 위치에 표시합니다 
					marker.setPosition(mouseEvent.latLng);
					marker.setMap(map);

					// 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
					infowindow.setContent(content);
					infowindow.open(map, marker); 
				}
			});
		});

		// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
		daum.maps.event.addListener(map, 'idle', function() {
			searchAddrFromCoords(map.getCenter(), displayCenterInfo);
		});

		function searchAddrFromCoords(coords, callback) {
			// 좌표로 행정동 주소 정보를 요청합니다
			geocoder.coord2RegionCode(coords.getLng(), coords.getLat(),
					callback);
		}

		function searchDetailAddrFromCoords(coords, callback) {
			// 좌표로 법정동 상세 주소 정보를 요청합니다
			geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
		}

		// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
		function displayCenterInfo(result, status) {
			if (status === daum.maps.services.Status.OK) {
				var infoDiv = document.getElementById('centerAddr');

				for (var i = 0; i < result.length; i++) {
					// 행정동의 region_type 값은 'H' 이므로
					if (result[i].region_type === 'H') {
						infoDiv.innerHTML = result[i].address_name;
						break;
					}
				}
			}
		}

	});
</script>
<style type="text/css">
.select {
	
}

.hide {
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
				<td><input name="party_name"></td>
			</tr>
			<tr>
				<td>예약유형</td>
				<td>골라주세요<input type="radio" name="date" value="none"
					checked="checked" onclick="check_radio(this)"><br>
					시간으로 잡기<input type="radio" name="date" value="time"
					onclick="check_radio(this)"><br> 날짜로 잡기<input
					type="radio" name="date" value="day" onclick="check_radio(this)"><br>
					<select name="day" id="day" class="hide">
						<option value="-1" selected="selected">경기시작 1일 전</option>
						<option value="-2">경기시작 2일 전</option>
						<option value="-10">경기시작 3일 전 이상</option>
				</select> <select name="hour" id="hour" class="hide">

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

				</select><br></td>
			</tr>
			<tr>
				<td>파티 목적</td>
				<td><select id="purpose" name="purpose"
					onchange="check_purpose(this)">
						<option value="0" selected="selected">목적 선택</option>
						<option value="1">구장내 특수시설 이용</option>
						<option value="2">신나는 응원</option>
						<option value="3">일단 모여라!</option>
						<option value="10">기타</option>
				</select></td>
			</tr>
			<tr>
				<td>경기 선택</td>
				<td><select id="select_match" name="select_match"
					onchange="ajax_for_stadium(this);">
						<option value="0" selected="selected">경기 선택</option>
						<c:forEach var="o" items="${match_list}">
							<option value="${o.p_idx}">${o.t_away}VS${o.t_home}</option>
						</c:forEach>
				</select></td>
			</tr>


			<tr>
				<td>모일장소</td>

				<td><label id="stadium_label">경기장:</label><input name="stadium"
					id="stadium" disabled="disabled"> <select name="location"
					id="location"></select>
					<div id="centerAddr"></div>
					<div id="map" style="width: 500px; height: 400px;"> </div></td>



			</tr>



		</table>
	</form>

	<button onclick="send_form();return false;">보내기</button>

</body>
</html>
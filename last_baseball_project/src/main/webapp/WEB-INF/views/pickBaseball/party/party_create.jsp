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

		////////////////�ش� �ּҷ� �� �̵�////////////////////

	

		// ��� �˻� ��ü�� �����մϴ�
		var ps = new daum.maps.services.Places();

		// Ű����� ��Ҹ� �˻��մϴ�
		ps.keywordSearch('��ô��ī�̵�', placesSearchCB);

		// Ű���� �˻� �Ϸ� �� ȣ��Ǵ� �ݹ��Լ� �Դϴ�
		function placesSearchCB(data, status, pagination) {
			if (status === daum.maps.services.Status.OK) {

				// �˻��� ��� ��ġ�� �������� ���� ������ �缳���ϱ�����
				// LatLngBounds ��ü�� ��ǥ�� �߰��մϴ�
				var bounds = new daum.maps.LatLngBounds();

				for (var i = 0; i < 1; i++) {
					//displayMarker(data[i]);    
					bounds.extend(new daum.maps.LatLng(data[i].y, data[i].x));
				}

				// �˻��� ��� ��ġ�� �������� ���� ������ �缳���մϴ�
				map.setBounds(bounds);
				map.setLevel(3);
			}
		}
		//////////////////////////////
		/////////���ý� �ش� �ּ� ���///////
		/////////////////////////////
	
		///////////////////�������/////////////////

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

		var mapContainer = document.getElementById('map'), // ������ ǥ���� div 
		mapOption = {
			center : new daum.maps.LatLng(37.566826, 126.9786567), // ������ �߽���ǥ
			level : 5
		// ������ Ȯ�� ����
		};

		// ������ �����մϴ�    
		map = new daum.maps.Map(mapContainer, mapOption);
		
		var geocoder = new daum.maps.services.Geocoder();

		var marker = new daum.maps.Marker(), // Ŭ���� ��ġ�� ǥ���� ��Ŀ�Դϴ�
		infowindow = new daum.maps.InfoWindow({
			zindex : 1
		}); // Ŭ���� ��ġ�� ���� �ּҸ� ǥ���� �����������Դϴ�

		// ���� ���� �߽���ǥ�� �ּҸ� �˻��ؼ� ���� ���� ��ܿ� ǥ���մϴ�
		searchAddrFromCoords(map.getCenter(), displayCenterInfo);

		// ������ Ŭ������ �� Ŭ�� ��ġ ��ǥ�� ���� �ּ������� ǥ���ϵ��� �̺�Ʈ�� ����մϴ�
		daum.maps.event.addListener(map, 'click', function(mouseEvent) {
			searchDetailAddrFromCoords(mouseEvent.latLng, function(result,
					status) {
				if (status === daum.maps.services.Status.OK) {
					var detailAddr = !!result[0].road_address ? '<div>���θ��ּ� : '
							+ result[0].road_address.address_name + '</div>'
							: '';
					detailAddr += '<div>���� �ּ� : '
							+ result[0].address.address_name + '</div>';

					var content = '<div class="bAddr">'
							+ '<span class="title">������ �ּ�����</span>'
							+ detailAddr + '</div>';

					// ��Ŀ�� Ŭ���� ��ġ�� ǥ���մϴ� 
					marker.setPosition(mouseEvent.latLng);
					marker.setMap(map);

					// ���������쿡 Ŭ���� ��ġ�� ���� ������ �� �ּ������� ǥ���մϴ�
					infowindow.setContent(content);
					infowindow.open(map, marker); 
				}
			});
		});

		// �߽� ��ǥ�� Ȯ�� ������ ������� �� ���� �߽� ��ǥ�� ���� �ּ� ������ ǥ���ϵ��� �̺�Ʈ�� ����մϴ�
		daum.maps.event.addListener(map, 'idle', function() {
			searchAddrFromCoords(map.getCenter(), displayCenterInfo);
		});

		function searchAddrFromCoords(coords, callback) {
			// ��ǥ�� ������ �ּ� ������ ��û�մϴ�
			geocoder.coord2RegionCode(coords.getLng(), coords.getLat(),
					callback);
		}

		function searchDetailAddrFromCoords(coords, callback) {
			// ��ǥ�� ������ �� �ּ� ������ ��û�մϴ�
			geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
		}

		// ���� ������ܿ� ���� �߽���ǥ�� ���� �ּ������� ǥ���ϴ� �Լ��Դϴ�
		function displayCenterInfo(result, status) {
			if (status === daum.maps.services.Status.OK) {
				var infoDiv = document.getElementById('centerAddr');

				for (var i = 0; i < result.length; i++) {
					// �������� region_type ���� 'H' �̹Ƿ�
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

	<!-- �����ؾ� �� ��. ��Ƽ��, ��¥, ����, ���, �ִ��ο���, �������, ��Ƽ����  -->
	<p class="date">
		�Ͻ�:<span class="year_value">${param.year}</span>��<span
			class="month_value">${param.month}</span>��<span class="day_value">${param.day}</span>��
	</p>

	<form id="baseball_party">
		<table class="form_table">
			<tr>
				<td>��Ƽ �̸�</td>
				<td><input name="party_name"></td>
			</tr>
			<tr>
				<td>��������</td>
				<td>����ּ���<input type="radio" name="date" value="none"
					checked="checked" onclick="check_radio(this)"><br>
					�ð����� ���<input type="radio" name="date" value="time"
					onclick="check_radio(this)"><br> ��¥�� ���<input
					type="radio" name="date" value="day" onclick="check_radio(this)"><br>
					<select name="day" id="day" class="hide">
						<option value="-1" selected="selected">������ 1�� ��</option>
						<option value="-2">������ 2�� ��</option>
						<option value="-10">������ 3�� �� �̻�</option>
				</select> <select name="hour" id="hour" class="hide">

						<option value="+2">������ 2�ð� ��</option>
						<option value="+1">������ 1�ð� ��</option>
						<option value="0">���ð��� �� ���缭</option>
						<option value="-1" selected="selected">������ 1�ð� ��</option>
						<option value="-2">������ 2�ð� ��</option>
						<option value="-3">������ 3�ð� ��</option>
						<option value="-4">������ 4�ð� ��</option>
						<option value="-5">������ 5�ð� ��</option>
						<option value="-6">������ 6�ð� ��</option>
						<option value="-12">�� �̻�</option>

				</select><br></td>
			</tr>
			<tr>
				<td>��Ƽ ����</td>
				<td><select id="purpose" name="purpose"
					onchange="check_purpose(this)">
						<option value="0" selected="selected">���� ����</option>
						<option value="1">���峻 Ư���ü� �̿�</option>
						<option value="2">�ų��� ����</option>
						<option value="3">�ϴ� �𿩶�!</option>
						<option value="10">��Ÿ</option>
				</select></td>
			</tr>
			<tr>
				<td>��� ����</td>
				<td><select id="select_match" name="select_match"
					onchange="ajax_for_stadium(this);">
						<option value="0" selected="selected">��� ����</option>
						<c:forEach var="o" items="${match_list}">
							<option value="${o.p_idx}">${o.t_away}VS${o.t_home}</option>
						</c:forEach>
				</select></td>
			</tr>


			<tr>
				<td>�������</td>

				<td><label id="stadium_label">�����:</label><input name="stadium"
					id="stadium" disabled="disabled"> <select name="location"
					id="location"></select>
					<div id="centerAddr"></div>
					<div id="map" style="width: 500px; height: 400px;"> </div></td>



			</tr>



		</table>
	</form>

	<button onclick="send_form();return false;">������</button>

</body>
</html>
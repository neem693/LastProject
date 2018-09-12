<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
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
	<p>${param.id}${param.partyname}
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
				<td><select id = "select_match" name = "select_match"></select></td>
			</tr>



		</table>
	</form>

	<button onclick="send_form();return false;">보내기</button>

</body>
</html>
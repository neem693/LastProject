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

	<!-- �����ؾ� �� ��. ��Ƽ��, ��¥, ����, ���, �ִ��ο���, �������, ��Ƽ����  -->
	<p>${param.id}${param.partyname}
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
				<td><select id = "select_match" name = "select_match"></select></td>
			</tr>



		</table>
	</form>

	<button onclick="send_form();return false;">������</button>

</body>
</html>
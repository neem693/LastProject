<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function send_form() {
		var form_dd = document.getElementById("dd");
		
		form_dd.submit();
	}
</script>
</head>
<body>
	<p>${param.id}${param.partyname}
	<form id="dd">
		<input name="id"> <input name="partyname">

	</form>

	<button onclick="send_form();return false;">보내기</button>

</body>
</html>
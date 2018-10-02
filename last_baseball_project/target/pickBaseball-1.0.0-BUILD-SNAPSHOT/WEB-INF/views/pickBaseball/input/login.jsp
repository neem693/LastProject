<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta  charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title> </title>
<link rel = "stylesheet" href="${pageContext.request.contextPath}/resources/css/login_form.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lobster">

<script>
function login(){
	var f = document.getElementById("login");
	
	var id = f.m_id.value;
	var pwd = f.m_pwd.value;
	
	f.method = "POST";
	f.action = "login_action.do";
	f.submit();
	
	
}


</script>


</head>
<body style="background-image:url('${pageContext.request.contextPath}/resources/images/input/baseball_login.jpg');">

<%@include file="/WEB-INF/views/main/header/header.jsp" %>	


	<div class="container ">
		<label class="text_brown lobster_title">LOGIN</label>



		<form id = "login" class="login_box" >
		
			<div class="pading">
				<label class="text_brown lobster"><b>ID</b></label> <input
					class="login_input" autofocus="autofocus" name="m_id" type="text"> <label
					class="text_brown  lobster">PASSWORD</label> <input
					class="login_input"  name="m_pwd" type="password">
			</div>


			<button onclick="login();return false;" class="btn_return cyan ">
				<b>로그인</b>
			</button>
			<button onclick="location.href = '${pageContext.request.contextPath}/join.do';return false;" class="btn_join cyan">
				<b>회원가입</b>
			</button>





		</form>

	</div>


<%@include file="/WEB-INF/views/main/footer/footer.jsp" %>	



</body>
</html>
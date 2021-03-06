<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale = 1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title></title>


<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	//프론트용 
	function pop() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
				// 예제를 참고하여 다양한 활용법을 확인해 보세요.
				//alert("우편번호:" + data.zonecode + "\n지번주소:" +data.roadAddress);
				document.getElementById("zip_code").value = data.zonecode;
				document.getElementById("addr").value = data.roadAddress;
			}
		}).open();
	}

</script>


<script>
	//백엔드 파트 script

	function send(f) {

		if (check_val() && check_input_value()) {
			// 유효성검사
			f.method = 'POST';
			f.action = 'test_modify.do';
			f.submit();//전송	

			alert("ok");

		}

	}

	function check_input_value() {
		//정규식 체크

		//자바 스크립트 정규식
		//id_check  영문자로 시작하는 4자리에서 10자리
		var idreg = /^[A-Za-z]{1}[A-Za-z0-9]{3,10}$/;
		//name_check 한글2~4자리 
		var name = /^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/;
		//nick_check 닉네임2~7자리 
		var nick = /^[가-힣]{2,8}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/;
		var pwd = /^.*(?=.{4,10})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
		var e_check = /^[\w\-]+@(?:(?:[\w\-]{2,}\.)+[a-zA-Z]{2,})$/;
		var phone = /^\d{3}-\d{3,4}-\d{4}$/;

		var pwd_a = $('#pwd').val();
		var pwd_b = $('#pwd_check').val();

		if (!idreg.test($('#id').val())) {
			alert("아이디는 영문자로 시작하는 4~10자 영문자 또는 숫자이어야 합니다.");
			return false;
		}

		if (!name.test($('#name').val())) {
			alert("한글2~4글자 띄어쓰기 금지 입니다.");
			return false;
		}
		if (!nick.test($('#nick_name').val())) {
			alert("한글2~8글자 띄어쓰기 금지 입니다.");
			return false;
		}

		if (!pwd.test($('#pwd').val())) {
			alert("영문자 숫자포함 4자리 이상 10자리 미만입니다.");
			return false;
		}

		if (pwd_a != pwd_b) {
			alert("입력한 비밀번호가 다릅니다.");
			return false;

		}
		if (!e_check.test($('#e_mail').val())) {
			alert("이메일 형식이 다릅니다.");
			return false;
		}

		if (!phone.test($('#tel').val())) {
			alert("핸드폰번호 형식이 다릅니다.000-0000-0000으로 부탁합니다.");
			return false;
		}

		return true;

	}

	function check_val() {

		if ($('#id').val() == '') {
			alert('아이디를 입력하세요');
			return;
		}
		if ($('#name').val() == '') {
			alert('이름를 입력하세요');
			return;
		}
		if ($('#nick_name').val() == '') {
			alert('닉네임를 입력하세요');
			return;
		}
		if ($('#pwd').val() == '') {
			alert('비밀번호를 입력하세요');
			return;
		}
		if ($('#pwd_check').val() == '') {
			alert('비밀번호확인칸을 확인하세요');
			return;
		}
		if ($('#e_mail').val() == '') {
			alert('이메일을 입력하세요');
			return;
		}
		if ($('#tel').val() == '') {
			alert('전화번호를 입력하세요');
			return;
		}
		if ($('#zip_code').val() == '') {
			alert('우편번호를 입력하세요');
			return;
		}
		if ($('#addr').val() == '') {
			alert('주소를 입력하세요');
			return;
		}

		return true;
	}
</script>


</head>

<!--배경 이미지 셋팅-->
<body>

	<div>

		<div class="center">회원 정보 수정</div>


		<form>

			<input type="hidden" name="m_idx" value="${vo.m_idx}">

			<!-- input:입력창 css , input_s 입력창 크기  -->
			<div class="center_form">
				<span> <input id="id" name="m_id" value="${vo.m_id}"
					class="input input_s" placeholder="아이디(4~10자리)"> <input
					class="button button_id" type="button" value="중복확인">
				</span> <br> <input id="name" name="m_name" value="${vo.m_name}"
					class="input input_s" placeholder="이름(한글2~4자리)"><br> <input
					id="nick_name" name="m_nick" value="${vo.m_nick}"
					class="input input_s" placeholder="닉네임(2~8자리 한글)"> <input
					class="button button_id" type="button" value="중복확인"><br>

			<!-- 코멘트입력   -->
				<input id="comment" name="m_comment"
					class="input input_s" type="text" placeholder="자기소개" value="${vo.m_comment}"> <br>
			
				<input id="pwd" name="m_pwd" value="aaa111" class="input input_s"
					type="password" placeholder="비밀번호(영어 숫자 포함  4~10)"><br>
				<input id="pwd_check" value="aaa111" class="input input_s"
					type="password" placeholder="비밀번호 확인"><br> <input
					id="e_mail" value="${vo.m_email}" name="m_email"
					class="input input_m" placeholder="E-MAIL(XXXX@XXXX.XXX형식입니다.)"><br>
				<input id="tel" value="${vo.m_tel}" name="m_tel"
					class="input input_s" placeholder="연락처"><br>


				<!-- 주소검색 버튼  -->
				<input id="zip_code" value="${vo.m_zip_code}" name="m_zip_code"
					class="input input_s" type="text" placeholder="우편번호"> <input
					type="button" class="button button_id" value="검색" onclick="pop();"><br>


				<input id="addr" value="${vo.m_addr}" name="m_addr"
					class="input input_m" placeholder="주소"> <br> <input
					class="input input_s" type="text" placeholder="프로필사진"> <br>

				<div class="green_text">
					<h2 style="text-shadow: 1px 1px 0 #444">선호 팀 선택</h2>
				</div>


				<img class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/기아.png">
				<input type="radio" checked="checked" name="t_name" value="KIA">
				<img class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/넥센.png">
				<input type="radio" name="t_name" value="넥센"> <img
					class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/두산.png">
				<input type="radio" name="t_name" value="두산"> <img
					class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/로떼.png">
				<input type="radio" name="t_name" value="롯데"> <img
					class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/삼성.png">
				<input type="radio" name="t_name" value="삼성"> <br> <img
					class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/에스케이.png">
				<input type="radio" name="t_name" value="SK"> <img
					class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/엔씨.png">
				<input type="radio" name="t_name" value="NC"> <img
					class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/엘쥐.png">
				<input type="radio" name="t_name" value="LG"> <img
					class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/케이티_위즈.png">
				<input type="radio" name="t_name" value="KT"> <img
					class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/한화.png">
				<input type="radio" name="t_name" value="한화"><br> <input
					id="sub" type="button" class="button button_id" value=" 수정하기"
					onclick="send(this.form);"><br>
		</form>

	</div>




</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale = 1.0" />
<meta charset="utf-8">
<title></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/join_form.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<script
	src="${pageContext.request.contextPath}/resources/jquery/jquery-3.3.1.min.js"></script>
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

	//백엔드 파트 script

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
		if (!nick.test($('#nick').val())) {
			alert("한글2~8글자 띄어쓰기 금지 입니다.");
			return false;
		}

		if (!pwd.test($('#pwd').val())) {
			alert("영문자 숫자포함 4자리 이상 10자리 미만입니다.");
			return;
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

	function check_id() {

		if (id == '') {
			alert('아이디를 입력하세요');
			return;
		}
		var m_id = $('#id').val();

		$.ajax({
			url : 'check_id.do',
			data : {
				'm_id' : m_id
			},
			success : function(data) {
				var json = eval(data);
				if (json[0].result == 'no') { //배열로 받아서 비교하는게 제일 안전하다.
					alert('이미 사용중인 아이디 입니다.');
					return;
				}
				alert('사용 가능한 id입니다.');
			}

		});

	}

	function check_nick() {

		if (id == '') {
			alert('닉네임 입력하세요');
			return;
		}

		var m_nick = $('#nick').val();

		$.ajax({
			url : 'check_nick.do',
			data : {
				'm_nick' : m_nick
			},
			success : function(data) {
				var json = eval(data);
				if (json[0].result == 'no') { //배열로 받아서 비교하는게 제일 안전하다.
					alert('이미 사용중인 닉네임 입니다.');
					return;
				}
				alert('사용 가능한 닉네임입니다.');
			}

		});

	}

	function photo_upload() {

		if ($('#photo_up').val() == '') {
			alert('사진을  올려주세요');
			return;
		}

		var photo = document.getElementById("photo_form");
		var formData = new FormData($("#photo_form")[0]); //해당 업로드된 파일을 ajax를 통해 보낸다.
		// FormData 객체는 각 폼을 네임과 값 한쌍으로 1:1 키 형식으로 저장한다.(파일명,파일 포함 파일도 가능)

		alert('클릭');
		$.ajax({
			type : 'post', //파일 객체도 보낼때 사용함
			url : 'photo_upload.do',
			data : formData,//not jason 
			processData : false,//파일보낼때 써주어야됨
			contentType : false,//파일보낼때 써주어야됨
			success : function(data) {

				alert(data);
				$("#real_photo").val(data);
				$("#img_form_url").attr("src", data);

			}

		});

	}
	$(document)
			.ready(
					function() {

						document.getElementById('sub').onclick = function() {
							if (check_val() && check_input_value()) {
								// 유효성검사

								check_id();
								alert("ok");
								document.getElementById('join_form').submit();
								return false;
							}

						}

						var team = document
								.getElementsByClassName("picture_size");
						for (var i = 0; i < team.length; i++) {

							console.log(team[i].getAttribute("value"));

							$(team[i])
									.on(
											"click",
											function() {
												//console.log($(this).attr("value"));
												var teams = document
														.getElementsByClassName("picture_size");
												var form = document
														.getElementById("join_form");
												for (var i = 0; i < teams.length; i++) {
													teams[i].classList
															.remove("picture_size_selected");
													/* teams[i].style.filter = "gradyscale(100%)";
													teams[i].style.opacity = "0.6"; */
												}
												this.classList
														.add("picture_size_selected");
												/* this.style.filter = "grayscale(0%)";
												this.style.opacity = "1.0"; */
												form.t_name.value = this
														.getAttribute("value");
											});

						}
						document.getElementById("no_team").classList
								.add("picture_size_selected");
						
						
						

					});
</script>


</head>

<!--배경 이미지 셋팅-->
<body>
	<%@include file="/WEB-INF/views/main/header/header.jsp"%>
	<div class="center_c"
		style="background-image : url('${pageContext.request.contextPath}/resources/images/back_join_form.png')">



		<div class="center">
			<label class="welcom_font">WELCOME B.B JOIN~♥♥♥</label>
		</div>

		<div class="center_form">
			<form action='test_insert.do' id="join_form" method="post">

				<!-- input:입력창 css , input_s 입력창 크기  -->

				<span> <input id="id" name="m_id" autofocus="autofocus"
					class="input input_s" placeholder="아이디(4~10자리)"> <input
					class="button button_id" type="button" value="중복확인"
					onclick="check_id();">
				</span> <br> <input id="name" name="m_name" class="input input_s"
					placeholder="이름(한글2~4자리)"><br> <input id="nick"
					name="m_nick" class="input input_s" placeholder="닉네임(2~8자리 한글)">

				<input class="button button_id" value="중복확인" type="button"
					onclick="check_nick();"><br> <input id="pwd"
					name="m_pwd" class="input input_s" type="password"
					placeholder="비밀번호(영어 숫자 포함  4~10)"><br> <input
					id="pwd_check" class="input input_s" type="password"
					placeholder="비밀번호 확인"><br> <input id="e_mail"
					name="m_email" class="input input_m"
					placeholder="E-MAIL(XXXX@XXXX.XXX형식입니다.)"><br> <input
					id="tel" name="m_tel" class="input input_s" placeholder="연락처"><br>
				<!-- 코멘트입력   -->
				<input id="comment" name="m_comment" class="input input_s"
					type="text" placeholder="자기소개"> <br>
				<!-- 주소검색 버튼  -->
				<input id="zip_code" name="m_zip_code" class="input input_s"
					type="text" placeholder="우편번호" readonly="readonly"> <input
					type="button" class="button button_id" value="검색" onclick="pop();"><br>
				<input id="addr" name="m_addr" class="input input_m"
					placeholder="주소" readonly="readonly"> <br>


				<div class="green_text">
					<h2 style="text-shadow: 5px 5px 3px black">선호 팀 선택</h2>
				</div>

				<input name="t_name" type="hidden"> <img
					class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/big/kia_tigers.png"
					value="KIA"> <img class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/big/nexen_heroes.png"
					value="넥센"> <img class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/big/doosan_bears.png"
					value="두산"> <img class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/big/lotte_giants.png"
					value="롯데"> <img class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/big/samsung_lions.png"
					value="삼성"> <img class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/big/sk_wyverns.png"
					value="SK"> <img class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/big/nc_dinos.png"
					value="NC"> <img class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/big/lg_twins.png"
					value="LG"> <img class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/big/kt_wiz.png"
					value="KT"> <img class="picture_size"
					src="${pageContext.request.contextPath}/resources/images/big/hanwha_eagles.png"
					value="한화"> <br>
				<div class="picture_size no_team" value="" id="no_team">팀 없음</div>
				<input name ="m_photo" type="hidden"id= "real_photo">
			</form>
			<div class="green_text">
				<h2 style="text-shadow: 5px 5px 3px black">프로필 업로드</h2>
			</div>
			<form id="photo_form" enctype="multipart/form-data" method="post">
				<input id="photo_up"
					style="color: white; text-shadow: 5px 5px 5px black; background-color: gray"
					type="file" name="m_photo" class="input input_s"
					placeholder="프로필사진">
			</form>
			<input class="button button_id" type="button" value="업로드하기"
				onclick="photo_upload();"> <img id="img_form_url"> <br>

			<input id="sub" type="button" class="button button_id"
				value="     	   가입    	     "><br>



		</div>

	</div>

	<%@include file="/WEB-INF/views/main/footer/footer.jsp"%>

</body>
</html>
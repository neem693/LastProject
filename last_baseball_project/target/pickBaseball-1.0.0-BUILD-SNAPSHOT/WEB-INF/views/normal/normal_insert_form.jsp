<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="${pageContext.request.contextPath}/resources/jquery/jquery-3.3.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript">


	$(document)
			.ready(
					function() {

						CKEDITOR
								.replace(
										'nc_contents',
										{
											filebrowserUploadUrl : '${pageContext.request.contextPath}/normal_image_upload.do',
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
	function normal_home() {
		location.href = "${pageContext.request.contextPath}/normal/list.do";

	}
	function normal_insert(){
		var f = document.getElementById("normal_form");
		var editor  = CKEDITOR.instances.nc_contents.getData();
		
		if (f.nc_title.value == '') {
			alert('제목을 입력하세요');
			f.nc_title.focus();
			return false;
		}

		if (editor == '') {
			alert('내용을 입력하세요');
			CKEDITOR.instances.nc_contents.focus();
			return;
		}

		f.action = "insert.do"
		f.method = "POST";
		f.submit();
	}
	
	function send_check(f) {
	
		if (f.nc_title.value == '') {
			alert('제목을 입력하세요');
			f.nc_tetle.focus();
			return false;
		}
	
		if (f.nc_contents == '') {
			alert('내용을 입력하세요');
			f.nc_contents.focus();

			return;
		}

		f.action = "normal_insert.do"
		f.submit();

	}
</script>
<style type="text/css">
.normal_c {
	min-height: 80%;
	margin-left: 15%;
	margin-right: 15%;
}

.item {
	padding: 20px;
	background-color: rgb(250, 250, 250);
	margin: 5px 5px;
}

.n_input {
	width: 60%;
}

.normal_header {
	font-size: xx-large;
	text-shadow: 5px 5px 5px shadow;
}

.normal_home {
	cursor: pointer;
}

.normal_insert {
	font-size: x-large;
}
</style>
<body>
	<%@include file="/WEB-INF/views/main/header/header.jsp"%>

	<div class="normal_c">
		<div class="normal_header">
			<span onclick="normal_home();" class="normal_home">자유게시판</span>&gt;<span
				class="normal_insert">글쓰기</span>
		</div>
		<form id="normal_form">
		<input type="hidden" value = "${user.m_idx}" name = "m_idx" >
			<div class="item">
				<input class="n_input subject" name="nc_title">
			</div>
			<div class="item">
				<textarea class="n_input text" id="nc_contents" name="nc_contents"></textarea>
			</div>

		</form>
		<button onclick="normal_insert();">등록</button>
	</div>
	
	<%@include file="/WEB-INF/views/main/footer/footer.jsp"%>
</body>

</html>
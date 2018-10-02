<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">
function check_id() {
	var id = $('#id').val();
	if(id==''){
		alert('아이디를 입력하세요');
		return;
	}
	
	$.ajax({
		url:'check_id.do',
		data: null,
		success:function(result){
			
			var json = result;
			if(json=='fail'){
				alert('로그인 해주세요');
				
			}
			
		}
	});
}

function check_nick() {
	var nick = $('#nick').val();
	if(nick==''){
		alert('닉네임을 입력하세요');
		return;
	}
	
	$.ajax({
		url:'check_nick.do',
		data:{'nick' : nick},
		success:function(result){
			var json_array = eval(result);
			var json = json_array[0];
			
			if(json.result=='no'){
				alert('이미 사용중인 닉네임 입니다');
				return;
			}
			alert('사용가능한 닉네임 입니다');
			
			$('#nick').attr('readOnly',true);
			
			$('#bt_reg').attr('disabled',false);
		}
	});
}

function send(f){
	
	var reg_tel = /^\d{3}-\d{3,4}-\d{4}$/;
	var reg_email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;  
	
	var m_name = f.m_name.value;
	var m_id = f.m_id.value;
	var m_pwd = f.m_pwd.value;
	var m_nick = f.m_nick.value;
	var m_tel = f.m_tel.value;
	var m_zipcode = f.m_zipcode.value;
	var m_addr = f.m_zipcode.value;
	var m_email = f.m_email.value;
	
	if(m_name==''){
		alert('이름을 입력하세요');
		f.m_name.focus();
		return;
	}
	
	if(m_id==''){
		alert('아이디를 입력하세요');
		f.m_id.focus();
		return;
	}
	
	if(m_pwd==''){
		alert('비밀번호를 입력하세요');
		f.m_pwd.focus();
		return;
	}
	
	if(m_nick==''){
		alert('닉네임을 입력하세요');
		f.m_nick.focus();
		return;
	}
	
	if(m_tel==''){
		alert('전화번호를 입력하세요');
		f.m_tel.focus();
		return;
	}
	
	if(m_zipcode==''){
		alert('우편번호를 입력하세요');
		f.m_zipcode.focus();
		return;
	}
	
	if(m_addr==''){
		alert('상세 주소를 입력하세요');
		f.m_addr.focus();
		return;
	}
	
	if(m_email==''){
		alert('이메일 주소를 입력하세요');
		f.m_email.focus();
		return;
	}
	
	f.action='member_join.do';
	
	f.submit();
	
}

</script>
</head>
<body>
<form>
	<table border="1" align="center" width="700">
		<tr>
			<th>이름</th>
			<td colspan="2"><input type="text" name="m_name" placeholder="이름을 입력하세요"></td>
		</tr>
		
		<tr>
			<th> 아이디</th>
			<td><input type="text" name="m_id" placeholder="아이디를 입력하세요"></td>
			<td><input type="button" value="중복확인" onclick="check_id();"></td>
		</tr>
		
		<tr>
			<th>닉네임</th>
			<td><input type="text" name="m_nick" placeholder="닉네임을 입력하세요"></td>
			<td><input type="button" value="중복확인" onclick="check_nick();"></td>
		</tr>
		
		<tr>
			<th>비밀번호</th>
			<td colspan="2"><input type="password" name="m_pwd" placeholder="비밀번호를 입력하세요"></td>
		</tr>
		
		<tr>
			<th>비밀번호재입력</th>
			<td colspan="2"><input type="password" name="c_pwd"></td>
		</tr>
		
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="m_tel" ></td>
		</tr>
		
		<tr>
			<th>우편번호</th>
			<td>
			<input name="m_zipcode" id="zipcode">
			<input type="button" value="주소검색" onclick="find()">
			</td>
		</tr>
		
		<tr>
			<th>상세주소</th>
			<td><input name="m_addr" id="addr" style="width:98%; "></td>
		</tr>
		
		<tr>
			<th>이메일</th>
			<td colspan="2"><input type="text" name="m_email"></td>
		</tr>
	
		<tr>
			<th>KBO응원팀</th>
			<td colspan="2"><select name="t_name">
				<option value="없음">없음</option>
				<option value="넥센">넥센히어로즈</option>
				<option value="두산">두산베어스</option>
				<option value="LG">LG 트윈스</option>
				<option value="SK">SK 와이번스</option>
				<option value="KT">KT 위즈</option>
				<option value="한화">한화 이글스</option>
				<option value="KIA">KIA 타이거즈</option>
				<option value="삼성">삼성 라이온즈</option>
				<option value="롯데">롯데 자이언츠</option>
				<option value="NC">NC 다이노스</option>
			</select></td>
		</tr>
		
		<tr>
			<th>인사글</th>
			<td colspan="2"><textarea name="m_comment" rows="5" cols="70"></textarea></td>
		</tr>
	
		<tr>
			<th>프로필사진 업로드</th>
			<td><input type="file" name="m_photo">
				<input type="button" value="올리기" onclick="photo();"></td>
		</tr>
	
		<tr>
			<td colspan="3" align="center">
				<input type="button" value="가입하기" id="bt_reg" disabled="disabled" onclick="send(this.form);">
				<input type="button" value="로그인창으로 돌아가기" onclick="location.href='login_form.jsp'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
</head>

<script type="text/javascript">
function del(f){

	if(confirm('정말삭제 하시겠습니까')==false)return;
	f.method = 'POST';
	f.action = 'test_delete_list.do'; 
	f.submit();//전송
	
}

function modify_form(f){
	
	if(confirm('정말수정 하시겠습니까')==false)return;
	f.method = 'POST';
	f.action = 'test_member_modify_form.do'; 
	f.submit();//전송

}

function join_list(f){
	
	f.method = 'POST';
	f.action = 'join.do'; 
	f.submit();//전송

}

</script>


<body>

	 회원 명부 테스트 <br>
 	
 	번호 이름 아이디 비번 날짜 이메일 사진 자기소개 주소 상세주소 전화번호 선택팀 닉네임<br>
 	<c:forEach var="vo" items="${list}">				
				${vo.m_idx}
				${vo.m_name}
				${vo.m_id}
				${vo.m_pwd}
				${vo.m_date}
				${vo.m_email}
				${vo.m_photo}
				${vo.m_comment}
				${vo.m_addr}
				${vo.m_zip_code}
				${vo.m_tel}
				${vo.t_name}
				${vo.m_nick}<br>
			</c:forEach>

<form>
	
	수정할 번호를 입력하세요<br>  
	<input id="idx_modify" name="m_idx">
	<input type="button" value="회원정보수정" onclick="modify_form(this.form);">
	
</form>

<form>
	삭제할 번호를 입력하세요 <br> 
	<input id="idx_del" name="m_idx"> 
	<input type="button" value="싹제" onclick="del(this.form);"><br>
</form>

<form>
	회원가입
	<input type="button" value="회원가입" onclick="join_list(this.form);">
</form>



</body>
</html>
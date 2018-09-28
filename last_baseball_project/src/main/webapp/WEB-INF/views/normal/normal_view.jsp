<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>Insert title here</title>
<style>
	*{ margin:5;}
	
	#nc_comment{
		width: 1000px;
		border: 1px solid red;
	}
	
	#nc_comment_input{
		width: 100%;
	}
	
	#content{
		width: 80%;
		height:60px;
	}

</style>

<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
	function del(){
		if(confirm('정말 삭제하시겠습니까?')==false)return;
		
		f.action="normal_delete.do"
		f.submit();
	}
	
	function modify() {
		//수정할 글의 idx번호
		location.href='modify_form.do?idx={vo.nc_idx}&page=${param.page}';
	}
	
	function comment_send() {
		if('${empty user}'==true){
			
			if(confirm('댓글은 로그인하신후에 사용가능합니다\n 로그인 하시겠습니까?')== false)return;
			
			location.href='../member/login_form.do?url=' + encodeURIComponent(location.href);
			
			return;
		}
		
		//댓글쓰기
		var nc_idx = '${ vo.nc_idx}';
		var m_id = '${user.m_id}';
		var m_nick = '${user.m_nick}';
		var c_content = $('#c_content').val();
		if(c_content==''){
			alert('댓글 내용을 입력하세요');
			$('#c_content').focus();
			return;
		}
		
		$.ajax({
			url: 'comment_normal_insert.do',
			data:{'${ vo.nc_idx }':nc_idx,'${user.m_id}':m_id,'${user.m_nick}':m_nick,'${c_content}':c_content},
			dataType:'json',
			success:function(data){
				if(data.result == 'fail'){
					alert('댓글달기 실패!!');
					return;
				}
				
				$('#nc_contnet').val('');
				$('#nc_contnet').focus();
				
				comment_list(1);
			}
		});
		
	}

	//댓글
	function comment_list(page){
		
		var nc_idx = '${vo.nc_idx}';
		$.ajax({
			url: 'comment_list.do',
			data: {'nc_idx': nc_idx,'page':page},
			success:function(data){
				$('#disp').html(data)
			}
		});
	}

</script>
</head>
<body>
<form>
<input type="hidden" name="idx" value="${vo.nc_idx }">
<input type="hidden" name="page" value="${param.page }">
<table width="1000" border="1" align="center">
	<tr>
		<td width="150" height="25" bgcolor="FF33CC">제목</td>
		<td>${ vo.nc_title}</td>
	</tr>
	<tr>
		<td width="150" height="25" bgcolor="FF33CC">작성자 닉네임</td>
		<td>${ vo.m_nick }</td>
	</tr>
	<tr>
		<td width="150" height="25" bgcolor="FF33CC">게시된 날짜</td>
		<td>${ fn:substring(vo.nc_regdate,0,10) }</td>
	</tr>
	<tr>
		<td colspan="2" height="200" style="word-wrap:break-word:word-break:break-all">
		<pre>${ vo.nc_contents }</pre></td>
	</tr>
	<tr align="right">
		<td colspan="2">
			<input type="button" value="목록으로" onclick="location.href='list.do?page=${param.page};'">
			<%-- <c:if test="${ request Scope.vo.m_id eq sessionScope.user.m_id }">
				<input type="button" value="수정" onclick="modify()">
				<input type="button" value="삭제" onclick="del()">
			</c:if> --%>
		</td>
	</tr>
</table>
</form>

<!-- 댓글 작성 -->
<div align="center">
<div id="nc_comment">
	<div id="#nc_comment_input">
		작성자 : <c:if test="${ not empty user }">${ user.m_nick}</c:if>
	<textarea id="content"></textarea>
	</div>
	<input type="button" value="전송" onclick="comment_send();">
</div>
</div>
<hr>

<!-- 댓글 목록을 출력 -->
<div id="disp"  align="center">
	<c:if test="${ comment }">
	<div id="nc_comment">
	<div id="#nc_comment_input">
		작성자 : <c:if test="${ not empty user }">${ user.m_nick}</c:if>
	<textarea id="content"></textarea>
	</div>
	<input type="button" value="전송" onclick="comment_add();">
	</div>
	</c:if>
	<!-- 댓글이 없으면 -->
	<c:if test="${ empty comment }">
	<div>
		당신이 이글의 첫번째 댓글의 주인공이 되어주세요
	</div>
	<div>
		${ ncpagemenu }
	</div>
	</c:if>
</div>
</body>
</html>
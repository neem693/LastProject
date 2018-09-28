<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type=src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
	function normal_del(idx){
		
		$.ajax({
			url:'normal_comment_delete.do',
			data:{'c_idx':c_idx},
			dataType: 'json',
			success:function(data){
				if(data.result=='fail'){
					alert('댓글 삭제 실패');
					return;
				}
				
				normal_comment_list(1);
			}
		});
		
	}

</script>
</head>
<body>
<c:if test="${ not empty normal_list }">
	<div align="center">${ normalpageMenu }</div>
	<hr>
</c:if>

<c:forEach var="vo" items="${ normal_list }">

	<div>
		작성자:${ vo.m_nick }
		<c:if test="user.m_id eq vo.m_id">
			<input type="button" value="x" onclick="del('${vo.c_idx}')">
		</c:if>
	</div>
	<div><pre>${ vo.c_content }</pre></div>
	<c:forEach begin="1" end="${ vo.c_depth }">
		&nbsp;&nbsp;
	</c:forEach>

	<!-- 댓글의 답글이면 ㄴ 을 달아라 -->
	<c:if test="${ vo.c_depth != 0 }">
	ㄴ 
	</c:if>
	<hr>
	<br>
	<br>
</c:forEach>
</body>
</html>
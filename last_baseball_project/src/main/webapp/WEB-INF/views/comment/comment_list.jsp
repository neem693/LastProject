<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
  function del1(idx){
	  //alert('삭제할 idx='+ idx );
	  
	  if(confirm('정말삭제 하시겠습니까?')==false)return;
	  
	  $.ajax({
		  url:'comment_delete.do', //CommentDeleteAction
		  data:{'idx': idx  },
		  dataType: 'json',
		  success:function(data){
			  
			  if(data.result=='fail'){
				  alert('댓글 삭제 실패');
				  return;
			  }
			  
			  //갱신목록 가져오기
			  comment_list(1);
			  
		  }
	  });
	  
  }
</script>
</head>
<body>

<!-- 페이징 메뉴 작성 -->
<c:if test="${ not empty list }">
	<div align="right">${ pageMenu }</div>
	<hr>
</c:if>
<!-- <a  onclick="comment_list('1')">1</a> &nbsp;
<a  onclick="comment_list('2')">2</a> &nbsp;
<a  onclick="comment_list('3')">3</a> &nbsp; -->


<!--  for(CommentVo vo : list ) -->
<c:forEach var="vo" items="${ list }">
  
  <div>
        작성자: ${ vo.m_nick }(${ vo.m_id }) 
        <c:if test="${ user.id eq vo.m_id }">
            <input type="button"  value="x" onclick="del1('${ vo.c_idx }');">
        </c:if>
  </div>
  <div><pre>${ vo.c_content }</pre></div>
  <hr> 
  <br>
  <br>
</c:forEach>





</body>
</html>
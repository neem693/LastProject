<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
  function del1(c_idx){
	  //alert('삭제할 idx='+ idx );
	  
	  if(confirm('정말삭제 하시겠습니까?')==false)return;
	  
	  $.ajax({
		  url:'comment_delete.do', //CommentDeleteAction
		  data:{'c_idx': c_idx },
		  dataType: 'json',
		  success:function(data){
			  
			  if(data.result_json=='fail'){
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
<input type="hidden" name="j_idx" value="${vo.j_idx }">
<input type="hidden" name="m_id" value="${user.m_id }">
<!-- 페이징 메뉴 작성 -->
<%-- <c:if test="${ not empty list }">
	<div align="right">${ pageMenu }</div>
	<hr>
</c:if> --%>
<!-- <a  onclick="comment_list('1')">1</a> &nbsp;
<a  onclick="comment_list('2')">2</a> &nbsp;
<a  onclick="comment_list('3')">3</a> &nbsp; -->


<!--  for(CommentVo vo : list ) -->
<c:forEach var="vo" items="${ list }">
  
  <div>
         ${ vo.m_nick }[${fn:substring(vo.c_date,0,16)}]
         <c:if test="${ user.m_id eq vo.m_id }">
            <input type="button"  value="삭제" onclick="del1('${ vo.c_idx }');">
        </c:if>
  </div>
  <div><pre>${ vo.c_content }</pre></div>
  <hr> 
  <br>
  <br>
</c:forEach>
</body>
</html>
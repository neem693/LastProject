<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="${ pageContext.request.contextPath }/resources/css/comment/comment.css?ver=1" rel="stylesheet" id="bootstrap-css">
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
  function edit_comment(c,c_idx){
	  return;
	
	  /* var op = 
	  {
			  url: 'comment_modify.do',
			  data: {
				  
			  }
	  } */
	  
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
<div class="container">
    <div class="row">
        <div class="panel panel-default widget">
            <%-- <div class="panel-heading">
                <span class="glyphicon glyphicon-comment"></span>
                <h3 class="panel-title">
                    Recent Comments</h3>
                <lable>댓글</lable>
                <span class="label label-info">
                    </span>
                    <lable>댓글</lable>
                <span class="label label-info">
                    </span>
                    <lable></lable>
                <span class="label label-info">
                    ${vo.j_readhits }</span>
            </div> --%>
            <div class="panel-body">
                <ul class="list-group">
                <c:forEach var="vo" items="${ list }">
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-xs-2 col-md-1">
                                <img src="http://placehold.it/80" class="img-circle img-responsive" alt="" /></div>
                            <div class="col-xs-10 col-md-11">
                               
                          <div class = "comment">
                                    <div class="mic-info">
                                       ${ vo.m_nick }[${fn:substring(vo.c_date,0,16)}]
                                    </div>
                             
                                <div class="comment-text">
                                    ${ vo.c_content }
                                </div>
                                <div class="action">
                                    <button type="button" class="btn btn-primary btn-xs" title="Edit" onclick="edit_comment(this,${vo.c_idx})">
                                        <span class="glyphicon glyphicon-pencil"></span>
                                    </button>
                                 
                                    <button type="button" class="btn btn-danger btn-xs" title="Delete" onclick="del1('${ vo.c_idx }');">
                                        <span class="glyphicon glyphicon-trash"></span>
                                    </button>
                                </div>
                                 </div>
                            </div>
                        </div>
                    </li>
                    </c:forEach>
                </ul>
               
            </div>
        </div>
     
    </div>
</div>

</body>
</html>
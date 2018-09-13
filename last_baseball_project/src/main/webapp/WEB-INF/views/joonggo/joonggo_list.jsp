<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/joonggo/joonggo.css">
<style type="text/css">

}
</style>
</head>
<body>
<div id = "main_box">
<!-- <div class = "center" id = "header">중고 물품</div> -->

<div class = "content" >
<c:forEach var = "vo" items = "${list }">
			<ul>
				<li><img src="${ pageContext.request.contextPath }/resources/photo_upload/${ vo.j_filename}">
					<div class="caption">
						<h6>${vo.j_title }</h6>
						<p> ${vo.j_price }</p>
						<p align="center">
							<a class="btn btn-primary btn-block" onclick ="location.href='view.do?j_idx=${vo.j_idx}'">Open</a>
						</p>
					</div>
					</li>
			</ul>

</c:forEach>
</div>


<div class = "footer">

<!-- 페이지 처리 -->	
<div class="page">
              <ul id = "q" class="pagination" >
				<li class="disabled"><a href="#">«</a></li>
				<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">»</a></li>
			</ul>

</div>


 <!-- 글쓰기 -->
<div class="pull-right">
              <button type="submit" class="btn btn-primary" onclick ="location.href='insert_form.do'">글쓰기</button>
          </div> 
          

 
 <!-- 검색메뉴 -->
<div align = "center">
<select id = "search">
<option value = "all">전체</option>
<option value = "title">제목</option>
<option value = "nick">작성자</option>
<option value = "title_content">제목+내용</option>
</select>
<input id = "search_text" value = "">
<input type = "button" value = "검색" onclick = "">
</div>

</div>
</div>



</body>
</html>
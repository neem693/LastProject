<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<style type="text/css">

}
</style>
</head>
<body>
<div id = "main_box">
<!-- <div class = "center" id = "header">중고 물품</div> -->

<div class = "content" >

			<ul>
				<li><img src="${ pageContext.request.contextPath }/image/1.png">
					<div class="caption">
						<h4>글러브</h4>
						<p>10,000원</p>
						<p align="center">
							<a href="http://bootsnipp.com/" class="btn btn-primary btn-block">Open</a>
						</p>
					</div>
					</li>
			</ul>

<ul>
<li>
<img src="${ pageContext.request.contextPath }/image/1.png">
                        <div class="caption">
						<h4>이대호 사인볼</h4>
						<p>10원</p>
						<p align="center">
							<a href="http://bootsnipp.com/" class="btn btn-primary btn-block">Open</a>
						</p>
					</div>
</li>
</ul>

<ul>
<li>
<img src="${ pageContext.request.contextPath }/image/1.png">
<div class="caption">
						<h4>오재원 사인모자</h4>
						<p>20,000원</p>
						<p align="center">
							<a href="http://bootsnipp.com/" class="btn btn-primary btn-block">Open</a>
						</p>
					</div>
</li>
</ul>

<ul>
<li>
<img src="${ pageContext.request.contextPath }/image/1.png">
<div class="caption">
						<h4>김성근 잠바</h4>
						<p>500,000원</p>
						<p align="center">
							<a href="http://bootsnipp.com/" class="btn btn-primary btn-block">Open</a>
						</p>
					</div>
</li>
</ul>

<ul>
<li>
<img src="${ pageContext.request.contextPath }/image/1.png">
<div class="caption">
						<h4>김광현 사인볼</h4>
						<p>-50,000원</p>
						<p align="center">
							<a href="http://bootsnipp.com/" class="btn btn-primary btn-block">Open</a>
						</p>
					</div>
</li>
</ul>

<ul>
<li>
<img src="${ pageContext.request.contextPath }/image/1.png">
<div class="caption">
						<h3>Header Name</h3>
						<p>Description</p>
						<p align="center">
							<a href="http://bootsnipp.com/" class="btn btn-primary btn-block">Open</a>
						</p>
					</div>
</li>
</ul>

<ul>
<li>
<img src="${ pageContext.request.contextPath }/image/1.png">
<div class="caption">
						<h3>Header Name</h3>
						<p>Description</p>
						<p align="center">
							<a href="http://bootsnipp.com/" class="btn btn-primary btn-block">Open</a>
						</p>
					</div>
</li>
</ul>

<ul>
<li>
<img src="${ pageContext.request.contextPath }/image/1.png">
<div class="caption">
						<h3>Header Name</h3>
						<p>Description</p>
						<p align="center">
							<a href="http://bootsnipp.com/" class="btn btn-primary btn-block">Open</a>
						</p>
					</div>
</li>
</ul>

<ul>
<li>
<img src="${ pageContext.request.contextPath }/image/1.png">
<div class="caption">
						<h3>Header Name</h3>
						<p>Description</p>
						<p align="center">
						<a href="http://bootsnipp.com/" class="btn btn-primary btn-block">Open</a>
						</p>
					</div>
</li>
</ul>

<ul>
<li>
<img src="${ pageContext.request.contextPath }/image/1.png">
<div class="caption">
						<h3>Header Name</h3>
						<p>Description</p>
						<p align="center">
							<a href="http://bootsnipp.com/" class="btn btn-primary btn-block">Open</a>
						</p>
					</div>
</li>
</ul>

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
              <button type="submit" class="btn btn-primary">글쓰기</button>
          </div> 
          

 
 <!-- 검색메뉴 -->
<div align = "center">
<select id = "search">
<option value = "all">전체</option>
<option value = "title">제목</option>
<option value = "name">작성자</option>
<option value = "title_content">제목+내용</option>
</select>
<input id = "search_text" value = "">
<input type = "button" value = "검색" onclick = "">
</div>

</div>
</div>



</body>
</html>
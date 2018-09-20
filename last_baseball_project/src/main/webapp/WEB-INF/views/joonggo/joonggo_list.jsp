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
<script type="text/javascript">
window.onload = function()
{
	var search = document.getElementById("search");
	
	var search_array = ['', 'title','nick','content', 'title_content'];
	  for(var i=0; i<search_array.length; i++)
	  {
	    	if("${ param.search }" == search_array[i])
	    	{
	    		search[i].selected = true;
	    	}
	    }
	}
	
function search(){
	
	var search = document.getElementById("search").value;
	var search_text = document.getElementById("search_text").value;
	
	if(search!='all' && search_text=='')
		{
		alert('검색내용을 입력하세요');
		return;
		}
	
	// 검색 요청
	location.href = 'list.do?search=' + search + '&search_text=' + encodeURIComponent(search_text);
	
}

</script>




</head>
<body>
<div id = "main_box">
<!-- <div class = "center" id = "header">중고 물품</div> -->

<div class = "content" >
<c:forEach var = "vo" items = "${list }">
			<ul>
			<div class="s_container">
				<li><img src="${ pageContext.request.contextPath }/resources/photo_upload/${ vo.j_filename}">
				 <c:if test="${vo.j_sell_yn eq 'y'}">
                  <div class="s_center"><div class ="s_mid"></div></div>
			    </c:if>
			    </div>
			    
			     <p class = "s_content"></p>
                </div>
                
					<div class="s_caption">
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
      <!-- 게시글이 없으면 -->           
            <c:if test="${ empty list }">
				<tr>
					<td align="center" colspan="11" width="100%" height="50" style="border:1 solid #efefef">
						현재 등록된 글이 없습니다.
					</td>
				</tr>
			</c:if>




<div class = "footer">

<!-- 페이지 처리 -->	
<div class="page">
              <ul id = "q" class="pagination" >
				<li class="disabled"><a href="#">«</a></li>
				<li class="active"><a href="list.do?page=1">1 <span class="sr-only">(current)</span></a></li>
				<li><a href="list.do?page=2">2</a></li>
				<li><a href="list.do?page=3">3</a></li>
				<li><a href="list.do?page=4">4</a></li>
				<li><a href="list.do?page=5">5</a></li>
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

<option value = "content">내용</option>

<option value = "title_content">제목+내용</option>
</select>
<input id="search_text" value=${ (param.search_text =='null') ? '' : param.search_text }>
<input type = "button" value = "검색" onclick = "search();">
</div>
		<div>
				<div width="7"></div>
   				<div class="f11" align="center">
                    <!-- 페이지 메뉴  -->
                    ${ pageMenu }
                    
			</div>
			</div>		

</div>

</div>



</body>
</html>
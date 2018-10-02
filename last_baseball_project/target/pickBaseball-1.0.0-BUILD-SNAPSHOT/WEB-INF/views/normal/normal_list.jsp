<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>커뮤니티 게시판</title>
<script type="text/javascript">
function insert_form(){
	
	//로그인 하지 않은 상태
	if('${ empty user }'=='true'){
		if(confirm('글쓰기는 로그인 하신후에 가능합니다\n로그인하시겠습니까?')==false)return;
		
		//로그인 폼으로 이동
		location.href='${pageContext.request.contextPath}/member/login.do';
		return;
	}
	
	location.href='insert_form.do'
}

function search(){
	
	var nc_search = document.getElementById("nc_search").value;
	var nc_search_text = document.getElementById("nc_search_text").value;
	
	if(nc_search!='all' && nc_search_text==''){
		alert('검색내용을 입력하세요!!!');
		return;
	}
	
	//검색요청
	location.href = 'list.do?nc_search=' + nc_search + '&nc_search_text=' + encodeURIComponent(nc_search_text);
	
}

window.onload = function(){
	
	var nc_search = document.getElementById("nc_search");
	
	var nc_search_array = ['','m_nick','nc_title','nc_contents']
	for(var i=0;i<nc_search_array.length;i++){
		if("${param.nc_search}"== nc_search_array[i]){
			nc_search[i].selected = true;
		}
	}
	
	
}

</script>
</head>
<body>

<%@include file="/WEB-INF/views/main/header/header.jsp"%>

<table width="1000" align="center" border="0" cellpadding="0" cellspacing="0" bgcolor="#F1F5F4">
<tr>
	<td align="center">
	<select id="nc_search">
	
		<option value="m_nick">작성자 닉네임</option>
		<option value="nc_title">제목</option>
		<option value="nc_contents">내용</option>
		<option value="title_nick_contents">닉네임+제목+내용</option>
	</select>
	<input id="nc_search_text">
	<input type="button" value="검색" onclick="search();">
	</td>
	<td align="right">
		<input type="button" value="글쓰기" onclick="insert_form();">
	</td>
</tr>
</table>
<table width="1000" align="center">
	<tr bordercolor="blue">
		<td width="9%" align="center">글번호</td>
		<td width="50%" align="center">제목</td>
		<td width="10%" align="center">작성자</td>
		<td width="13%" align="center">작성일</td>
		<td width="9%" align="center">조회수</td>
		<td width="9%" align="center">댓글수</td>
	</tr>

	<c:forEach var="vo" items="${ list }">
	

	<tr>
		<td width="10%" align="center">${ vo.nc_idx }</td>
		<td width="50%" align="center"><a href="view.do?nc_idx=${ vo.nc_idx }"/>${ vo.nc_title }</td>
		<td width="10%" align="center">${ vo.m_nick }</td>
		<td width="13%" align="center">${ fn:substring(vo.nc_regdate,0,10) }</td>
		<td width="9%" align="center">${ vo.nc_views }</td>
		<td width="9%" align="center">${ vo.nc_count }</td>
	</tr>
	</c:forEach>
	
	<!-- 게시글이 없으면 -->
	<c:if test="${empty list }">
		<tr>
			<td align="center" colspan="5" width="100%" height="50" style="border: 1 solid #efefef">
				현재 등록된 글이 없습니다.
			</td>
		</tr>
	</c:if>
</table>

<table align="center">
	<tr>
		<td>
			${ pageMenu}
		</td>
	</tr>
</table>
<%@include file="/WEB-INF/views/main/footer/footer.jsp"%>
</body>
</html>
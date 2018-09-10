<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
<%@taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>   --%>
      
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pickBaseball</title>

<%-- 현재 Context Root : ${ pageContext.request.contextPath } <br> --%>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/main.css">

<!-- Ajax기능을 사용하기위한 js연결  -->
<script type="text/javascript"  src="${ pageContext.request.contextPath }/resources/js/httpRequest.js"></script>

</head>

<body>

<div class="header">
  <h1>크보는 끝났어, 이리와</h1>

</div>


<!--href 에 이동할 게시판 JSP 넣는다 -->
<div class="topnav">
  <a href="http://www.naver.com">중고거래</a>
  <a href="http://www.naver.com">토토</a>
  <a href="http://www.naver.com">파티원 모집</a>
  <a href="http://www.naver.com">게시판</a>
</div>

<div class="row">
  <div class="leftcolumn">
    <div class="card">
      <h2>시속 195가 됩니다</h2>
      <h5>Title description, Dec 7, 2017</h5>
      <p>메뉴를 클릭하면 여기 위치에 그 게시판(jsp) 내용이 뜬다</p>
      <p>로그인 and 회원가입 버튼과 위치는 어디로?</p>
     <img src="../img/195.gif">
      <!--gif이미지 크게넣기-->
      <div class="mainimg" style="height:auto;">image</div>
    </div>
  </div>
  
  <div class="rightcolumn">
   <div class="card">
      <h2>오늘의 경기</h2>
      <div class="fakeimg" style="height:100px;">달력</div>
      <p>경기1- A:B</p>
      <p>경기2- C:D</p>
      <p>경기3- E:F</p>
      <p>경기4- G:H</p>
      <p>경기5- I:J</p>
      <p>경기를 클릭하면 파티로 넘어가?</p>

    </div>
    <div class="card">
      <h3>팀 순위</h3>
      <div class="fakeimg"><p>Image</p></div>
      <div class="fakeimg"><p>Image</p></div>
      <div class="fakeimg"><p>Image</p></div>
    </div>
   </div>
</div>
  

<div class="footer">
  <h2>우리회사 주소,전화번호 etc</h2>
  <div id="footer_content">
		(주)인크레파스 A강의실  <a href="">개인정보관리정책</a><br>
		대표자: 홍대표<br>
		주  소: 서울시 구로구 구로3동<br>  
		관리자: 홍관리	전화)010-111-2345
  </div>
</div>
  

</body>
</html>
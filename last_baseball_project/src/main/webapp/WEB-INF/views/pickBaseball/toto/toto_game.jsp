<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
</head>
<style>


.outline{
    
	border-collapse: collapse;
	}


tr, td, th {
	border: 1px solid black;
}


.main {
	margin-left: 15%;
	margin-right:5%;
	float: left;
}



.right_side {
	/* border: 1px solid black; */

	float: right;
	width: 600px;
	box-sizing: border-box;
	margin-top: 5%;
	
	
}
.center_text{
text-align: center;
}


</style>




<script type="text/javascript">


$(document).ready(function() {
	
	
	$.ajax({		
		url:'check_login.do',
		data:{'id' : 'id'},
		success:function(data){
	
			if(data=='fail'){
				alert('로그인해주세요');			
				location.href = "${pageContext.request.contextPath}/member/login.do"; 
				return;
			}
		}
	});
	
	
	
	document.getElementById('sub').onclick = function() {    
		
		alert("주사위는 던져졌다.");
			document.getElementById('bat').submit();
			
			return;
	}

});


function check(chk){

	
	 //체크밖스 하나만 선택되게 하는거 이딴거 하는데 4시간걸림뻑큐
	         if ($(chk).prop('checked')) {       
	        	$("input[name="+ chk.name + "]").prop('checked', false);
	            $(chk).prop('checked', true);
	        } 
}

</script>



<body>


<%@include file="/WEB-INF/views/main/header/header.jsp"%>

	 <div class="main">
	 <p class=" w3-jumbo center_text">토토 게임 리스트 </p><br>
 
 	
 	<form action ='bat_game.do' id="bat"  method="get">
 		<input type="hidden" name="m_id" value="${user.m_id}">
 		<input type="hidden" name="m_idx" value="${user.m_idx}">
 	
 		<p class=" w3-xlarge">배팅해빨리:<input type="text" name="bat_price">
 		
 		<input id="sub" type="button" value="배팅하기" ></p>
 	
		<table class="outline">
			<tr>
			<th>번호</th>
			<th>팀</th>
			<th>배당선택</th>
			<th>날짜</th>
			<th>경기장소</th>
			</tr>
		
		<c:forEach var="vo" items="${list}"  varStatus="i">			
			
			<tr>	
				<td class="team_name w3-lime">			
				${vo.toto_idx}	
				</td>
				<td class="team_name w3-green" >
				<div>홈팀:${vo.t_home} VS 원정팀:${vo.t_away}</div> 						
				</td>
				<td class="team_name w3-khaki" >
					홈팀 승리:${vo.winner_ratio} <input type="checkbox"  name="${vo.p_idx}" value="${vo.winner_ratio},win" onclick="check(this);"><br> 
					홈팀 패배:${vo.lose_ratio}  <input type="checkbox"   name="${vo.p_idx}" value="${vo.lose_ratio},lose"   onclick="check(this);">
				<%-- <input type="hidden"     name="${vo.p_idx}" value="${vo.p_idx}">  --%>
				</td>
				<td class="team_name w3-indigo" >	
					날짜:${vo.p_date} 
				</td>
				<td class="team_name w3-brown">
					경기장: ${vo.toto_place} 
				</td>
				
			</tr>
		 
		</c:forEach>
       
		</table>	
			
	</form>
		
		</div>	
				<div >
					<aside class="right_side">
							사용자 배팅정보1<br>
							<table class="outline">	
						<tr>
						<th>사용자 아이디 :${member.m_id}</th><th>현재 보유금액:${member.m_money}</th>
						</tr>
						
						<c:forEach var="bat_list" items="${my_bat_list}"  varStatus="i">	
						<tr>
						<td>게임경기:${bat_list.p_idx}</td> 
						<td>배팅금액:${bat_list.bat_price}${bat_list.bat_win_lose}</td>
						</tr>
						</c:forEach>
						
						</table>
					
					
					</aside>
				</div>	
			

	
	<div style="clear: both;"></div>
	<%@include file="/WEB-INF/views/main/footer/footer.jsp"%>

</body>
</html>
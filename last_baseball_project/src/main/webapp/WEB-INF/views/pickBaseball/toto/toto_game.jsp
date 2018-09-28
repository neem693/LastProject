<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
</head>
<style>


.outline{
	width: 100%;
	border-collapse: collapse;
	}

.list_main {
	float: left;
	width: 70%;
	box-sizing: border-box;
}
tr, td, th {
	border: 1px solid black;
}


</style>




<script type="text/javascript">


$(document).ready(function() {
	
	document.getElementById('sub').onclick = function() {    
			alert("주사위는 던져졌다.");
			document.getElementById('bat').submit();
			return false;   
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

	 토토 게임 리스트 <br>
 	
 	경기장 장소 	홈팀    원정팀     승리배당      패배배당<br>
 	<div class="list_main">
 	
 	<form action ='bat_game.do' id="bat"  method="get">
 		<input type="hidden" name="m_id" value="player">
 		
 		배팅금액:<input type="text" name="bat_price">
 		<input id="sub" type="button" value="배팅하기" >
 		
		<table class="outline">
	
		<c:forEach var="vo" items="${list}"  varStatus="i">			
		
			<tr>	
				<td>			
					${vo.toto_idx} 	
				</td>
				<td>
					홈팀:${vo.t_home} VS 원정팀:${vo.t_away}						
				</td>
				<td>
					홈팀 승리:${vo.winner_ratio} <input type="checkbox"  name="${vo.p_idx}" value="${vo.winner_ratio},win" onclick="check(this);"><br> 
					홈팀 패배:${vo.lose_ratio}  <input type="checkbox"   name="${vo.p_idx}" value="${vo.lose_ratio},lose"   onclick="check(this);">
												<%-- <input type="hidden"     name="${vo.p_idx}" value="${vo.p_idx}">  --%>
				</td>
				<td>	
					날짜:${vo.p_date} 
				</td>
				<td>
					경기장: ${vo.toto_place} 
				</td>
				<td>
					히든처리예정:${vo.p_idx}
				</td>
			</tr>
		 
		</c:forEach>
       
		</table>	
			
	</form>
		
		</div>	
			
			
			
			
		
			
			
		
			
				
			

</body>
</html>
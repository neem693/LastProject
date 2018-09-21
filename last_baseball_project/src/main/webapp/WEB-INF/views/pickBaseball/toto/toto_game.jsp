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


</script>


<body>

	 토토 게임 리스트 <br>
 	
 	경기장 장소 	홈팀    원정팀     승리배당      패배배당<br>
 	<div class="list_main">
 	
 	<form>
 	
		<table class="outline">
		<tr>
		<th>번호</th> <th>경기정보</th> <th>배당률</th> <th>날짜</th>
		<th>경기장</th> <th>히든처리예정</th>	
		</tr>
	
		<c:forEach var="vo" items="${list}">			
			<tr>	
			<td>
		${vo.toto_idx} 	
			</td>
			<td>
		홈팀:${vo.t_home} VS 원정팀:${vo.t_away}						
			</td>
			<td>
		홈팀 승리:${vo.winner_ratio}<br> 
		홈팀 패배:${vo.lose_ratio} 
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
			</c:forEach>

		</table>	
			
	</form>
		
		</div>	
			
			
			
			
		
			
			
		
			
				
			

</body>
</html>
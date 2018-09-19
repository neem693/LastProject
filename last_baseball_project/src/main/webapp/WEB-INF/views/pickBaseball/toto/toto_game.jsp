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

<script type="text/javascript">
function del(f){

	if(confirm('정말삭제 하시겠습니까')==false)return;
	f.method = 'POST';
	f.action = 'test_delete_list.do'; 
	f.submit();//전송
	
}



</script>


<body>

	 토토 게임 리스트 <br>
 	
 	경기장 장소 	홈팀    원정팀     승리배당      패배배당<br>
 	<c:forEach var="vo" items="${list}">				
	
			<div>	
	<span>${vo.toto_place}</span>	
			
			<span>	${vo.home_team}  </span>
			<span>	${vo.away_team}  </span>
			<span>	${vo.winner_ratio}  </span>
			<span>	${vo.lost_ratio}  </span>
				<br>
				
			
			</div>	
			
			</c:forEach>





</body>
</html>
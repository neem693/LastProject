<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

	 회원 명부 테스트 <br>
 	<c:forEach var="vo" items="${list}">
				
				${vo.m_idx}
				${vo.m_name}
				${vo.m_id}
				${vo.m_pwd}
				${vo.m_date}
				${vo.m_email}
				${vo.m_photo}
				${vo.m_comment}
				${vo.m_addr}
				${vo.m_zip_code}
				${vo.m_tel}
				${vo.t_name}
				${vo.m_nick}<br>
			</c:forEach>


</body>
</html>
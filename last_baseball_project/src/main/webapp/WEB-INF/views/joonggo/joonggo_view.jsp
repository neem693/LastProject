<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- <link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css"> -->
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<!-- <link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" /> -->
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/resources/css/joonggo/joonggo_view.css?ver=1" rel="stylesheet" type="text/css">
<script type="text/javascript">

/* function write(f){
		f.action = "insert_form.do";
		f.submit();
	}
	 */
	function del(f) {
		if (confirm('정말 삭제하시겠습니까?') == false)
			return;

		f.action = "delete.do";
		f.submit();

	}

	function send(f) {
		if (confirm("정말 수정하시겠습니까") == false)
			return;

		f.action = "update_form.do?j_idx=${vo.j_idx}";
		/*  location.href='update_form.do?j_idx=' + j_idx;  */
		/* f.submit(); */

	}
	// 판매 완료
	function sell(f) {
		var j_sell_yn = $('#j_sell_yn').val();
		var j_idx = $('#j_idx').val();

		if (j_sell_yn = '') {
			alert('판매 완료로 변경하시겠습니까?');
			return;
		}

		f.action = "sell.do";
		f.method = 'GET';
		f.subimt();
	}

	function comment_send() {
		//로그인이 안된경우
		if ('${ empty user }' == 'true') {

			if (confirm('댓글은 로그인하신후에 사용가능합니다\n로그인 하시겠습니까?') == false)
				return;

			//alert(location.href);

			// 로그인후에 현재 위치로 다시 돌아올 목적
			location.href = "../../member/login.do?url=' + encodeURIComponent(location.href)'";

			return;
		}

		//댓글쓰기
		var j_idx = '${ vo.j_idx }';
		var m_id = '${ user.m_id }';
		var m_nick = '${ user.m_nick }';
		var c_content = $('#c_content').val(); // document.getElementById("content").value
		if (c_content == '') {
			alert('댓글 내용을 입력하세요');
			$('#c_content').focus();
			return;
		}

		//Ajax전송
		$.ajax({
			url : 'comment_insert.do', //CommentInsertAction
			data : {
				'j_idx' : j_idx,
				'm_id' : m_id,
				'm_nick' : m_nick,
				'c_content' : c_content
			},
			/* dataType : 'json', */
			success : function(data) {
				//Controller 값 받아와서 결과값 처리
				//data = {"result_json":"success"}  or {"result_json":"fail"},
				if (data.result_json == 'fail') {
					alert('댓글달기 실패!!');
					return;
				}
				//이전내용 지우기
				$('#c_content').val('');
				$('#c_content').focus();

				//성공=> 댓글목록 가져오기
				comment_list(1);

			}
		});

	}

	//댓글목록 가져오기(Ajax)   
	function comment_list(page) {

		var j_idx = '${ vo.j_idx }';
		$.ajax({
			url : 'comment_list.do', //CommentListAction
			data : {
				'j_idx' : j_idx,
				'page' : page
			},
			success : function(data) {
				//데이터를 가져와서 div id = disp 부분에 data 값을 출력한다.
				$('#disp').html(data);

			}
		});

	}

	//초기화 이벤트
	$(document).ready(function() {
		comment_list(1);
	});
</script>
</head>
<body>
<%@include file="/WEB-INF/views/main/header/header.jsp" %>	
	<!-- method="post" -->
	<form>
	<div class = "main_box">
	<div class="container center_cont">
		
			<input type="hidden" name="j_idx" value="${vo.j_idx }">
			<%-- 	    <input type="hidden" name="m_idx" value="${user.m_idx }"> --%>
			<input type="hidden" name="page" value="${param.page}">
		
            <c:if test="${vo.j_sell_yn eq 'y'}"><c:set var="selled_img">selledImg</c:set></c:if>

			<div class="title">
				<label class="col-sm-10">${vo.j_title }</label>
                <c:if test="${requestScope.vo.m_nick eq sessionScope.user.m_nick }">
			     <!-- (1 eq 1) : true를 만들어줘서 삭제 버튼을 보여줌 -->
				<div class="pull-right">
					<!-- <img> tag form tag가 아닌 form tag인 <input type="image">이용하던지 
                                                  <button><button> 이용하던지 
                                                  주의사항) 기본이벤트 onsubmit() call
                                                  onclick = "retrun false;" <= 자동submit() 하지말아라-->
					<!-- 	<img src="../img/btn_delete.gif" alt="답변 또는 댓글이 있을 경우 삭제가 되지 않습니다."> -->
					<button id="button1" onclick="del(this.form);">삭제</button>
				</div>

				<div class="pull-right">
					<button id="button1" onclick="send(this.form);">수정</button>
				</div>
				</c:if>
			</div>
			<br>
			<hr class="five">
			<table id="file">
				<tr>
					<td width="300"><div id="s_container">
							<img id="image" class = "${selled_img }"
								src="${pageContext.request.contextPath }/resources/photo_upload/${vo.j_filename}">

							<c:if test="${vo.j_sell_yn eq 'y'}">
								<div class="center">
									<div class="mid"></div>
									
								</div>
							</c:if>

						</div></td>
					<p class="content"></p>


					<td width="1119" align="left" valign="top" >
						<div class="title1">
							<label class="col-sm-10">${vo.j_title }</label>
							<c:if test="${requestScope.vo.m_nick eq sessionScope.user.m_nick }">
		                      	<!-- (1 eq 1) : true를 만들어줘서 삭제 버튼을 보여줌 -->
							<div class="pull-right">
								<button id="button2" onclick="sell(this.form); return false;">판매완료</button>
							</div>
							</c:if>
						</div>
						
						<div class="price">
							<label class="col-sm-10"><fmt:formatNumber
									value="${vo.j_price }" />원</label><br>
						</div>
						
					
					<div class="col-md-4">
					<div class="input-group">
						<span class="input-group-addon"><i class="material-icons">face</i></span>
						<input class="form-control" value="${vo.m_nick }" readonly>
					</div>
				</div>
				<div class="col-md-4 reduceleftpadding">
					<div class="input-group">
						<span class="input-group-addon"><i class="material-icons">call</i></span>
						<input class="form-control" value="${vo.m_tel }" readonly>
					</div>
				</div>
				
				<div class="col-md-4 reduceleftpadding">
					<div class="input-group date" data-provide="datepicker">
						<div class="input-group-addon">
							<i class="material-icons">email</i>
						</div>
						<input class="form-control" placeholder="  이메일"
							value="${vo.m_email }" readonly>
					</div>
				</div>
				<br>
				<div class="col-md-4">
					<div class="input-group">
						<span class="input-group-addon"><i class="material-icons">access_time</i></span>
						<input class="form-control"
							value="${fn:substring(vo.j_date,0,16)}" readonly>
					</div>
				</div>
				<c:choose>
					<c:when test="${vo.j_category eq '1'}">
						<c:set var="category">구매합니다.</c:set>
					</c:when>
					<c:when test="${vo.j_category eq '2'}">
						<c:set var="category">판매합니다.</c:set>
					</c:when>
					<c:when test="${vo.j_category eq '3'}">
						<c:set var="category">교환합니다.</c:set>
					</c:when>
					<c:otherwise>
						<c:set var="category">카테고리선택</c:set>
					</c:otherwise>

				</c:choose>
				<div class="col-md-4 reduceleftpadding">
					<div class="input-group">
						<span class="input-group-addon"><i class="material-icons">storage</i></span>
						<input class="form-control" value="${category}" readonly>



					</div>
				</div>
			
				</td>
				</tr>
				
				
			</table>

			<hr class="five">
		<%-- 	<div class="row">
				<div class="col-md-4">
					<div class="input-group">
						<span class="input-group-addon"><i class="material-icons">face</i></span>
						<input class="form-control" value="${vo.m_nick }" readonly>
					</div>
				</div>
				<div class="col-md-4 reduceleftpadding">
					<div class="input-group">
						<span class="input-group-addon"><i class="material-icons">call</i></span>
						<input class="form-control" value="${vo.m_tel }" readonly>
					</div>
				</div>
				<div class="col-md-4 reduceleftpadding">
					<div class="input-group date" data-provide="datepicker">
						<div class="input-group-addon">
							<i class="material-icons">email</i>
						</div>
						<input class="form-control" placeholder="  이메일"
							value="${vo.m_email }" readonly>
					</div>
				</div>
			</div> --%>
			<div class="row">
	<%-- 			<div class="col-md-4">
					<div class="input-group">
						<span class="input-group-addon"><i class="material-icons">access_time</i></span>
						<input class="form-control"
							value="${fn:substring(vo.j_date,0,16)}" readonly>
					</div>
				</div>
				<c:choose>
					<c:when test="${vo.j_category eq '1'}">
						<c:set var="category">구매합니다.</c:set>
					</c:when>
					<c:when test="${vo.j_category eq '2'}">
						<c:set var="category">판매합니다.</c:set>
					</c:when>
					<c:when test="${vo.j_category eq '3'}">
						<c:set var="category">교환합니다.</c:set>
					</c:when>
					<c:otherwise>
						<c:set var="category">카테고리선택</c:set>
					</c:otherwise>

				</c:choose>
				<div class="col-md-4 reduceleftpadding">
					<div class="input-group">
						<span class="input-group-addon"><i class="material-icons">storage</i></span>
						<input class="form-control" value="${category}" readonly>



					</div>
				</div>
				<div class="cgg ol-md-4 reduceleftpadding">
					<div class="input-group">
						<span class="input-group-addon">@</span> <input
							class="form-control" placeholder="Username">
					</div>
				</div>  --%>
				<div class="content">
					<div class="col-md-6">
						<div class="form-group">${vo.j_content }</div>
					</div>
				</div>
			</div>
		<br>
		<table>
		<tr class = "count">
		<th>댓글</th>
		<td><!-- 댓글갯수 -->
		
        <c:if test="${ vo.c_count ne 0 }">
	    <font>${ vo.c_count }</font>
		</c:if>
	
		</td>
		<th>조회수</th>
		<td>${vo.j_readhits }</td>
		</tr>
		</table>
		<!-- 댓글작성  -->
		<div class="comment_box">
			<div id="comment_input_box">
				<%-- 			<div>
				작성자:
				<c:if test="${ not empty user }">${ user.m_nick }(${ user.m_id })</c:if>
			</div>
			<span style="text-align: center">
			<textarea id="c_content" ></textarea>
			<input id="bt_reg" type="button" value="등록"
				onclick="comment_send(); "> --%>

				<!--댓글목록을 출력  -->
				<div id="disp"></div>
				<hr class="five">

				<div class="form-group">
					<textarea id="c_content" class="form-control" rows="3"></textarea>

				</div>
				<button class="button3" onclick="comment_send();">등록</button>
			
				
			</div>
		</div>
		
	</div>


   <br>

	<div class="button_div">

		<c:if test="${requestScope.vo.m_nick eq sessionScope.user.m_nick }">
			<!-- (1 eq 1) : true를 만들어줘서 삭제 버튼을 보여줌 -->
			<!-- 글쓰기 -->
			<button class="button1" onclick ="location.href='insert_form.do?page=${param.page}&search=${ param.search}&search_text=${ param.search_text}'; return false;">글쓰기</button>
		    <button class="button1" onclick="send(this.form);">수정</button>
		    <button class="button1" onclick="del(this.form);">삭제</button>
		</c:if>
	

		<button type="submit" class="button1"
			onclick="location.href='list.do?page=${param.page}&search=${ param.search}&search_text=${ param.search_text}'; return false;">목록보기</button>

	</div>
	</div>
</form>
</body>

</html>
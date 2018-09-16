<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/joonggo/j_view.css">

</head>
<script type="text/javascript">
function del(f)
{
	if(confirm('정말 삭제하시겠습니까?')==false)
		return;
	
      f.action = "delete.do";
      f.submit();
	
	}
function send(f)
{
	if(confirm("정말 수정하시겠습니까")==false)
		return;
	
	f.action = "update_form.do?j_idx=${vo.j_idx}";
   /*  location.href='update_form.do?j_idx=' + j_idx;  */
	/* f.submit(); */
		
	}
	
//댓글쓰기
function comment_send(){
/* 	
	//로그인이 안된경우
	if('${ empty user }'=='true'){
		
		if(confirm('댓글은 로그인하신후에 사용가능합니다\n로그인 하시겠습니까?')==false) return;
		
		//alert(location.href);
		
		// 로그인후에 현재 위치로 다시 돌아올 목적
		location.href='../member/login_form.do?url=' + encodeURIComponent(location.href);
		
		return;
	} */
	
	//댓글쓰기
	var b_idx = '${ vo.idx }';
	var id    = '${ user.id }';
	var name  = '${ user.name }';
	var content = $('#c_comment').val(); // document.getElementById("content").value
	if(content==''){
		alert('댓글 내용을 입력하세요');
		$('#c_comment').focus();
		return;
	}
	
	//Ajax전송
	$.ajax({
		url: 'comment_insert.do', //CommentInsertAction
		data:{'j_idx': j_idx,'m_id':m_id,'m_nick':m_nick,'c_comment':c_commnet},
		dataType: 'json',
		success:function(data){
			// data = {"result":"success"}  or {"result":"fail"}
			if(data.result == 'fail'){
				alert('댓글달기 실패!!');
				return;
			}
			
			
							
			//이전내용 지우기
			$('#comment').val('');
			$('#comment').focus();
			
			
			//성공=> 댓글목록 가져오기
			comment_list(1);
			
		}
	});
	
}


//댓글목록 가져오기(Ajax)   
function comment_list(page){
	
	var j_idx = '${ vo.idx }';
	$.ajax({
		url: 'comment_list.do', //CommentListAction
		data:{'j_idx' : jc_idx ,'page': page},
		success:function(data){
			$('#disp').html(data);
		}
	});
	
	//초기화 이벤트
	$(document).ready(function(){
		comment_list(1);
	});
}



</script>
<body>

<form method="post">
<input type = "hidden" name ="j_idx" value = "${vo.j_idx }">
<input type = "hidden" name = "page" value = "${param.page}">
<div class="container">

    <div class="row">
        <div class="form-group col-md-12">
            <img src="${pageContext.request.contextPath }/resources/photo_upload/${vo.j_filename}">
        </div>
    </div>
    
    <div class="title"> 
     <label class="col-sm-10">${vo.j_title }</label>
    </div>
   
    <div class="price"> 
     <label class="col-sm-10">${ vo.j_price}</label>
    </div>
    
	<div class="row">
	    <div class="col-md-4">
            <div class="input-group">
              <span class="input-group-addon"><i class="material-icons">face</i></span>
              <input class="form-control" value = "${vo.m_nick }" placeholder="  닉네임">
            </div>
		</div>
	    <div class="col-md-4 reduceleftpadding">
            <div class="input-group">
              <span class="input-group-addon"><i class="material-icons">call</i></span>
              <input class="form-control" value = "${vo.m_tel }" placeholder="  전화번호">
            </div>	        
		</div>
	    <div class="col-md-4 reduceleftpadding">
            <div class="input-group date" data-provide="datepicker">
                <div class="input-group-addon"><i class="material-icons">email</i></div>
                <input class="form-control" placeholder="  이메일" value="${vo.m_email }" data-date-end-date="0d">
            </div>	        
		</div>		
	</div>
	<div class="row">
	    <div class="col-md-4">
            <div class="input-group">
              <span class="input-group-addon"><i class="material-icons">access_time</i></span>
              <input class="form-control" value = "${vo.j_date }" placeholder="  등록일자">
            </div>
		</div>
	    <div class="col-md-4 reduceleftpadding">
            <div class="input-group">
              <span class="input-group-addon"><i class="material-icons">storage</i></span>
              <input class="form-control" value = "${vo.j_category }" placeholder=" 카테고리">
            </div>	        
		</div>
	    <div class="col-md-4 reduceleftpadding">
            <div class="input-group">
              <span class="input-group-addon">@</span>
              <input class="form-control" placeholder="Username">
            </div>	        
		</div>
		<div class="content">
                 <div class="col-md-6">
                        <div class="form-group">
                            <p value = "${vo.j_content }" class="form-control"></p>
                        </div>
                    </div>
                </div>	
                </div>
  
<div class="pull-right">
<button class="btn btn-primary btn-block" onclick ="del(this.form)">삭제</button>
</div>	


<div class="pull-right">
<button class="btn btn-primary btn-block" onclick ="send(this.form)">수정</button>
</div>
	</div>	
<br>

 </div>
 </form>
 
 <p><br>
 <!-- 댓글작성  -->
<div id="comment_box">
    <div id="#comment_input_box">
        <div>
              작성자: <c:if test="${ not empty user }">${ user.name }(${ user.id })</c:if>
        </div>
        <textarea id="content"></textarea>
        <input id="bt_reg" type="button" value="댓글쓰기"  onclick="comment_send();">
    </div>
    
    <hr>
    <!--댓글목록을 출력  -->
    <div id="disp"></div>
    
</div>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/joonggo/joonggo.css">
<!-- smart_editor2 -->
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script src='js/SE2B_imgupload.js' charset='utf-8'></script>
<script type="text/javascript">
function send_check(f) {
	if(f.nc_tetle.value ==''){
			alert('제목을 입력하세요');
			f.nc_tetle.focus();
			return false;
		}
	
	if(editor==''){
		alert('내용을 입력하세요');
		f.nc_content.focus();
		
		return;
	}
	
	f.action="normal_insert.do"
	f.submit();
	
}
</script>
<body>
	<input type = "hidden" name = "idx" value="${list.nc_idx }">
<form method="post" id="insertBoardFrm" enctype="multipart/form-data">
	<div  id = main_insert_form align="center">
	<div class="container">
	<div class="panel panel-default">
    <div class="panel-heading clearfix">
    <h3 class="panel-title">커뮤니티 게시판 글쓰기</h3>
</div>
<div class="panel-body">
	<div class="form-group">
	  <label class="col-sm-2">제목</label>
	  <div class="col-sm-10"><input type="text" class="form-control" id="nc_title" placeholder="*필수"></div><br>
	</div>
<br>
<textarea name="editor" id="editor" style="width: auto; height: auto;" ></textarea>
 <div class="pull-right" aling = "centor">
     <!--  <button type="submit" id="insertBoard" class="btn btn-primary">확인</button>
     <input type="button" id="savebutton" name="savebutton" value="전송" /> -->
     <input type="button" id="insertBoard" class="btn btn-primary" value="등록" onclick = "send(this.form)"/>
     <button type="submit" class="btn btn-default">취소</button>
 </div>
</body>
<script type="text/javascript">
$(function(){
    //전역변수
    var obj = [];              
    //스마트에디터 프레임생성
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: obj,
        elPlaceHolder: "editor",
        sSkinURI: "<%=request.getContextPath()%>/resources/editor/SmartEditor2Skin.html",
        htParams : {
            // 툴바 사용 여부
            bUseToolbar : true,            
            // 입력창 크기 조절바 사용 여부
            bUseVerticalResizer : false,    
            // 모드 탭(Editor | HTML | TEXT) 사용 여부
            bUseModeChanger : true,
        }
    });
    //전송버튼
    $("#insertBoard").click(function(){
        //id가 smarteditor인 textarea에 에디터에서 대입
        obj.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
        //폼 submit
        $("#insertBoardFrm").submit();
    });
});
</script>
</html>
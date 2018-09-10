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

<!-- smart_editor2 -->
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>

<%-- <script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script> --%>

<script type="text/javascript">
function send(f)
{
	var name = f.name.value.trim();
	//var content = f.content.value.trim();
	var content = CKEDITOR.instances.content.getData();
	var pwd  = f.pwd.value.trim();
	
	if(name == ''){
		alert('이름을 입력하세요!!');
		f.name.focus();
		return;
	}
	
	if(content == ''){
		alert('내용을 입력하세요!!');
		f.content.focus();
		return;
	}
	
	if(pwd == ''){
		alert('비빌번호를 입력하세요!!');
		f.pwd.focus();
		return;
	}
	
	f.action = "insert.do";
	//절대주의 :  f.submit 에러는 안남 동작안된다
	//            함수호출해야된다  
	f.submit();
}
</script>


<style type="text/css">
*{
margin : 0px;
}
div#main_insert_form{
width : 1000px;
heigth : auto;
margin : 0 auto;
}


</style>

</head>
<body>

<form method="post" id="insertBoardFrm" enctype="multipart/form-data">
	<div  id = main_insert_form align="center">
		<div class="container">
		<div class="panel panel-default">
        <div class="panel-heading clearfix">
          <h3 class="panel-title">글 쓰기</h3>
        </div>
        
        <div class="panel-body">
                <div class="form-group">
                  <label class="col-sm-2" for="inputTo">제목</label>
                  <div class="col-sm-10"><input type="email" class="form-control" id="inputTo" placeholder="comma separated list of recipients"></div><br>
                </div>
                <br>
                <div class="form-group">
                  <label class="col-sm-2" for="inputSubject">가격</label>
                  <div class="col-sm-10"><input type="text" class="form-control" id="inputSubject" placeholder="subject"></div><br>
                </div>
                <br>
                  <div class="form-group">
                  <label class="col-sm-2" for="inputSubject">이름</label>
                  <div class="col-sm-10"><input type="text" class="form-control" id="inputSubject" placeholder="subject"></div><br>
                </div>
                <br>
                  <div class="form-group">
                  <label class="col-sm-2" for="inputSubject">연락처</label>
                  <div class="col-sm-10"><input type="text" class="form-control" id="inputSubject" placeholder="subject"></div><br>
                </div>
                <br>
                  <div class="form-group">
                  <label class="col-sm-2" for="inputSubject">이메일</label>
                  <div class="col-sm-10"><input type="text" class="form-control" id="inputSubject" placeholder="subject"></div><br>
                </div>

                <br>

                    <textarea name="editor" id="editor" rows="10" cols="100"style="width: 900px; height: 500px;"></textarea>

		</div>

</div>
              <div class="pull-right" aling = "centor">
              <button type="submit" id="insertBoard" class="btn btn-primary">확인</button>
              <!-- <input type="button" id="savebutton" name="savebutton" value="전송" />
              <input type="button" id="insertBoard" value="등록" /> -->
                <button type="submit" class="btn btn-default">취소</button>
          </div>
</div>
</div>
</form>
</body>
<script type="text/javascript">
$(function(){
    //전역변수
    var obj = [];              
    //스마트에디터 프레임생성
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: obj,
        elPlaceHolder: "editor",
        sSkinURI: "../resources/editor/SmartEditor2Skin.html",
        htParams : {
            // 툴바 사용 여부
            bUseToolbar : true,            
            // 입력창 크기 조절바 사용 여부
            bUseVerticalResizer : true,    
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
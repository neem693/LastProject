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
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script src='js/SE2B_imgupload.js' charset='utf-8'></script>
<script type="text/javascript">
function send(f)
{
	var j_category = f.j_category.value;
	var j_title = f.j_title.value;
	var j_price = f.j_price.value;
	var j_Filedata = f.j_Filedata.value;
	var j_content = f.j_content.value;
	
	if(j_category=='')
		{
		alert("카테고리를 선택하세요");
		f.j_category.focus();
		return;
		}
	
	if(j_title=='')
	{
	alert("제목을 입력하세요.");
	f.j_title.focus();
	return;
	}
	
	if(j_price=='')
	{
	alert("가격을 입력하세요.");
	f.j_price.focus();
	return;
	}
	
	if(j_Filedata=='')
	{
	alert("대표 사진을 등록하세요.");
	f.j_Filedata.focus();
	return;
	}
	
	if(j_content=='')
	{
	alert("내용을 입력하세요.");
	f.j_content.focus();
	return;
	}
	}
}


</script>



</head>
<body>
<form method="post" id="insertBoardFrm" enctype="multipart/form-data">
<input type = "hidden" name = "j_idx" value="${vo.j_idx }">
<input type = "hidden" name = "page" value="${param.page }">
	<div  id = main_insert_form align="center">
		<div class="container">
		<div class="panel panel-default">
        <div class="panel-heading clearfix">
          <h3 class="panel-title">수정</h3>
        </div>
        <br>
        
      <div class = "category">  
        <label class="col-sm-2">카테고리</label>
        <select id="j_category" name="j_category" align = "list">
        		<option value="0">카테고리 선택</option>
        		<option value="1">구매합니다</option>
        		<option value="2">판매합니다</option>
        		<option value="3">교환합니다</option>
        </select>
    </div>
    
        <div class="panel-body">
                <div class="form-group">
                  <label class="col-sm-2">제목</label>
                  <div class="col-sm-10"><input type="text" class="form-control" name="j_title" value = "${vo.j_title }" placeholder="제목을 입력하세요."></div><br>
                </div>
                <br>
                <div class="form-group">
                  <label class="col-sm-2">가격</label>
                  <div class="col-sm-10"><input type="text" class="form-control" name="j_price" value = "${vo.j_price }"placeholder="가격을 입력하세요."></div><br>
                </div>
                <br>
                  <div class="form-group">
                  <label class="col-sm-2">이름</label>
                  <div class="col-sm-10"><input type="text" class="form-control" name="m_name" value="${vo.m_name }" placeholder="이름을 입력하세요." ></div><br>
                </div>
                <br>
                  <div class="form-group">
                  <label class="col-sm-2">연락처</label>
                  <div class="col-sm-10"><input type="text" class="form-control" name="m_tel" value="${vo.m_tel }" placeholder="연락처를 입력하세요." ></div><br>
                </div>
                <br>
                  <div class="form-group">
                  <label class="col-sm-2">이메일</label>
                  <div class="col-sm-10"><input type="text" class="form-control" name="m_email" value="${vo.m_email }" placeholder="E_MAIL을 입력하세요." ></div><br>
                </div>
                
                  <br>
                  <div class="form-group">
                  <label class="col-sm-2" >대표이미지</label>
                  <div class="col-sm-10"><input type="file" name="Filedata" class = "J_image"></div>
                  <img src="${ pageContext.request.contextPath }/resources/photo_upload/${ vo.j_filename}"> 

						</div>


                <br>
                      <!-- 스마트 에디터2 -->
                    <textarea name="j_content" id="editor" class = "editor" value ="${vo.j_content }" ></textarea>

		</div>

</div>
              <div class="pull-right" aling = "centor">
             <!--  <button type="submit" id="insertBoard" class="btn btn-primary">확인</button>
             <input type="button" id="savebutton" name="savebutton" value="전송" /> -->
            <!--  <input type="button" id="insertBoard" value="등록" /> -->
          <!--   <input type = "button" value = "좀 들어가" onclick = "send1(this.form)"> -->
              <input type="button" id="insertBoard" class="btn btn-primary" value="수정"/> 
                <button class="btn btn-default" onclick = "location.href='view.do'">목록보기</button>
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
        $form = $("#insertBoardFrm");
        /* 
        $("#formId").attr("action", "action.jsp"); */
        $form.attr("action","update.do");
        $form.submit();
    });
});
</script>
<!-- 카테고리 db값 가져오기-->
<script type="text/javascript">
var inputVal = "${vo.j_category}"; //받아온값

/* java script 방법 */
 for(var i = 0; i < document.getElementById("j_category").options.length; i++)

{

  if(inputVal ==  document.getElementById("j_category").options[i].value)

  {

    document.getElementById("j_category").options[i].selected = true;

  }
}
</script>

</html>
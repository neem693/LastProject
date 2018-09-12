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
</head>
<body>
<form method="post" id="insertBoardFrm" enctype="multipart/form-data">
<input type = "hidden" name = "idx" value="1">
	<div  id = main_insert_form align="center">
		<div class="container">
		<div class="panel panel-default">
        <div class="panel-heading clearfix">
          <h3 class="panel-title">글 쓰기</h3>
        </div>
        <br>
      <div class = "category">  
       <div name></div>
        
    </div>
    
        <div class="panel-body">
                <div class="form-group">
                  <label class="col-sm-2">제목</label>
                  <div class="col-sm-10"><input type="text" class="form-control" name="j_title" placeholder="제목을 입력하세요."></div><br>
                </div>
                <br>
                <div class="form-group">
                  <label class="col-sm-2">가격</label>
                  <div class="col-sm-10"><input type="text" class="form-control" name="j_price" placeholder="가격을 입력하세요."></div><br>
                </div>
                <br>
                  <div class="form-group">
                  <label class="col-sm-2">이름</label>
                  <div class="col-sm-10"><input type="text" class="form-control" name="m_name" placeholder="이름을 입력하세요." value=""></div><br>
                </div>
                <br>
                  <div class="form-group">
                  <label class="col-sm-2">연락처</label>
                  <div class="col-sm-10"><input type="text" class="form-control" name="m_tel" placeholder="연락처를 입력하세요." value=""></div><br>
                </div>
                <br>
                  <div class="form-group">
                  <label class="col-sm-2">이메일</label>
                  <div class="col-sm-10"><input type="text" class="form-control" name="m_email" placeholder="E_MAIL을 입력하세요." value=""></div><br>
                </div>
                
                  <br>
                  <div class="form-group">
                  <label class="col-sm-2" >대표이미지</label>
                  <div class="col-sm-10"><input type="file" name="Filedata" class = "J_image"></div><br>
                </div>


                <br>
                      <!-- 스마트 에디터2 -->
                    <textarea name="j_content" id="editor" style="width: auto; height: auto;" ></textarea>

		</div>

</div>
              <div class="pull-right" aling = "centor">
             <!--  <button type="submit" id="insertBoard" class="btn btn-primary">확인</button>
             <input type="button" id="savebutton" name="savebutton" value="전송" /> -->
            <!--  <input type="button" id="insertBoard" value="등록" /> -->
          <!--   <input type = "button" value = "좀 들어가" onclick = "send1(this.form)"> -->
              <input type="button" id="insertBoard" class="btn btn-primary" value="등록" /> 
                <button type="submit" class="btn btn-default">취소</button>
          </div>
</div>
</div>
</form>
</body>
</html>
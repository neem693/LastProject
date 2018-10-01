package util;
/*
        pageURL:페이징처리할 리스트페이지      
        nowPage:현재페이지
        rowTotal:전체데이터갯수
        blockList:한페이지당 게시물수
        blockPage:한화면에 나타낼 페이지 목록수
 */
public class Paging {
	public static String getPaging(
			String pageURL, // /board/list.do
			int nowPage, 
			int rowTotal,
			int blockList, 
			int blockPage){
		
		int totalPage/*전체페이지수*/,
            startPage/*시작페이지번호*/,
            endPage;/*마지막페이지번호*/

		boolean  isPrevPage,isNextPage;
		
		
		StringBuffer sb; //모든 상황을 판단하여 HTML코드를 저장할 곳
		
		
		isPrevPage=isNextPage=false;
		//입력된 전체 자원을 통해 전체 페이지 수를 구한다..
		totalPage = rowTotal/blockList;
		if(rowTotal%blockList != 0)totalPage++; 
		

		//만약 잘못된 연산과 움직임으로 인하여 현재 페이지 수가 전체 페이지 수를
		//넘을 경우 강제로 현재페이지 값을 전체 페이지 값으로 변경
		if(nowPage > totalPage)nowPage = totalPage;
		

		//시작 페이지와 마지막 페이지를 구함.
		startPage = (int)(((nowPage-1)/blockPage)*blockPage+1);
		endPage = startPage + blockPage - 1; //
		
		
		
		//마지막 페이지 수가 전체페이지수보다 크면 마지막페이지 값을 변경
		if(endPage > totalPage)endPage = totalPage;
		
		//마지막페이지가 전체페이지보다 작을 경우 다음 페이징이 적용할 수 있도록
		//boolean형 변수의 값을 설정
		if(endPage < totalPage) isNextPage = true;
		//시작페이지의 값이 1보다 작으면 이전페이징 적용할 수 있도록 값설정
		if(startPage > 1)isPrevPage = true;
		
		//HTML코드를 저장할 StringBuffer생성=>코드생성
		sb = new StringBuffer();
//-----그룹페이지처리 이전 --------------------------------------------------------------------------------------------		
		if(isPrevPage){
			sb.append("<a href ='"+pageURL+"?page=");
			sb.append(startPage-1); // nowPage-blockPage
			/*sb.append("'>◀</a>");*/
			sb.append("<li class=\'disabled\'><a href='list.do?page='>«</a></li>");
		}
		else
			sb.append("<li class=\"'disabled\'><a>«</a></li>");
		
//------페이지 목록 출력 -------------------------------------------------------------------------------------------------
		
		/*<li class="disabled"><a href="#">«</a></li>
		<li class="active"><a href="list.do?page=1">1 <span class="sr-only">(current)</span></a></li>
		<li><a href="list.do?page=2">2</a></li>
		<li><a href="list.do?page=3">3</a></li>
		<li><a href="list.do?page=4">»</a></li>*/
		
	/*	sb.append("|");*/
		for(int i=startPage; i<= endPage ;i++){
			//if(i>totalPage)break;
			if(i == nowPage){ //현재 있는 페이지
				/*sb.append("&nbsp;<b><font size='3' color='red'>[");*/
				/*sb.append(i);*/
				/*sb.append("]</font></b>");*/
				sb.append("<li class=\'active\'><a href=\'list.do?page="+i+"\'>"+i+"<span class=\'sr-only\'>(current)</span></a></li>");
				
			}
			else{//현재 페이지가 아니면
				sb.append("&nbsp;<a href='"+pageURL+"?page=");
				/*sb.append(i);*/
				/*sb.append("'>");*/
				sb.append(i);
				sb.append("</a>");
				sb.append("<li><a href=\'list.do?page="+i+"\'>"+i+"<span class=\'sr-only\'>(current)</span></a></li>");
				
			}
		}// end for
		
		sb.append("&nbsp;");
		
//-----그룹페이지처리 다음 ----------------------------------------------------------------------------------------------
		if(isNextPage){
			sb.append("<a href='"+pageURL+"?page=");

			sb.append(endPage + 1);
			
			sb.append("<li><a href='list.do?page='>»</a></li>");
		}
		else
			sb.append("<li><a href=\"#\">»</a></li>");
			/*sb.append("'>▶</a>");
			
		}
		else
			sb.append("▶");*/
//---------------------------------------------------------------------------------------------------------------------	    

		return sb.toString();
	}
	
	
	public static String getPaging(
			String pageURL, // /board/list.do
			int nowPage, 
			int rowTotal,
			String search,
			String search_text,
			int blockList, 
			int blockPage){
		
		int totalPage/*전체페이지수*/,
            startPage/*시작페이지번호*/,
            endPage;/*마지막페이지번호*/

		boolean  isPrevPage,isNextPage;
		
		
		StringBuffer sb; //모든 상황을 판단하여 HTML코드를 저장할 곳
		
		
		isPrevPage=isNextPage=false;
		//입력된 전체 자원을 통해 전체 페이지 수를 구한다..
		totalPage = rowTotal/blockList;
		if(rowTotal%blockList != 0)totalPage++; 
		

		//만약 잘못된 연산과 움직임으로 인하여 현재 페이지 수가 전체 페이지 수를
		//넘을 경우 강제로 현재페이지 값을 전체 페이지 값으로 변경
		if(nowPage > totalPage)nowPage = totalPage;
		

		//시작 페이지와 마지막 페이지를 구함.
		startPage = (int)(((nowPage-1)/blockPage)*blockPage+1);
		endPage = startPage + blockPage - 1; //
		
		
		
		//마지막 페이지 수가 전체페이지수보다 크면 마지막페이지 값을 변경
		if(endPage > totalPage)endPage = totalPage;
		
		//마지막페이지가 전체페이지보다 작을 경우 다음 페이징이 적용할 수 있도록
		//boolean형 변수의 값을 설정
		if(endPage < totalPage) isNextPage = true;
		//시작페이지의 값이 1보다 작으면 이전페이징 적용할 수 있도록 값설정
		if(startPage > 1)isPrevPage = true;
		
		//HTML코드를 저장할 StringBuffer생성=>코드생성
		sb = new StringBuffer();
//-----그룹페이지처리 이전 --------------------------------------------------------------------------------------------		
		if(isPrevPage){
			sb.append("<a href ='"+pageURL+"?page=");
			sb.append(startPage-1); // nowPage-blockPage
			
			sb.append("&search=");
			sb.append(search);
			sb.append("&search_text=");
			sb.append(search_text);
			
			
			/*sb.append("'>◀</a>");*/
			sb.append("<li class=\'disabled\'><a href=\'#\'>«</a></li>");
		}
		else
			/*sb.append("◀");*/
             sb.append("<li class=\'disabled\'><a href=\'\'>«</a></li>");
		
//------페이지 목록 출력 -------------------------------------------------------------------------------------------------
		sb.append("&nbsp;");
		for(int i=startPage; i<= endPage ;i++){
			//if(i>totalPage)break;
			if(i == nowPage){ //현재 있는 페이지
			/*	sb.append("&nbsp;<b><font size='3' color='red'>[");
				sb.append(i);
				sb.append("]</font></b>");*/
				sb.append("<li class=\'active\'><a href=\'list.do?page="+i+"\'>"+i+"<span class=\'sr-only\'>(current)</span></a></li>");
			}
			else{//현재 페이지가 아니면
				sb.append("&nbsp;<a href='"+pageURL+"?page=");
				sb.append(i);
				
				sb.append("&search=");
				sb.append(search);
				sb.append("&search_text=");
				sb.append(search_text);
				
				/*sb.append("'>");
				sb.append(i);
				sb.append("</a>");*/
				sb.append("<li><a href=\'list.do?page=" + i +"\'>"+i+"</a></li>");
			}
		}// end for
		
		sb.append("&nbsp;");
		
		/*<li class="disabled"><a href="#">«</a></li>
		<li class="active"><a href="list.do?page=1">1 <span class="sr-only">(current)</span></a></li>
		<li><a href="list.do?page=2">2</a></li>
		<li><a href="list.do?page=3">3</a></li>
		<li><a href="list.do?page=4">»</a></li>*/
		
//-----그룹페이지처리 다음 ----------------------------------------------------------------------------------------------
		if(isNextPage){
			sb.append("<a href='"+pageURL+"?page=");

			sb.append(endPage + 1);
			
			sb.append("&search=");
			sb.append(search);
			sb.append("&search_text=");
			sb.append(search_text);
			
			/*sb.append("'>▶</a>");*/
			sb.append("<li><a href='list.do?page='>»</a></li>");
		}
		else
			sb.append("<li><a href=\"#\">»</a></li>");
//---------------------------------------------------------------------------------------------------------------------	    

		return sb.toString();
	}
	
	public static String getCommentPaging(
			int nowPage, 
			int rowTotal,
			int blockList, 
			int blockPage){
		
		int totalPage/*전체페이지수*/,
            startPage/*시작페이지번호*/,
            endPage;/*마지막페이지번호*/

		boolean  isPrevPage,isNextPage;
		
		
		StringBuffer sb; //모든 상황을 판단하여 HTML코드를 저장할 곳
		
		
		isPrevPage=isNextPage=false;
		//입력된 전체 자원을 통해 전체 페이지 수를 구한다..
		totalPage = rowTotal/blockList;
		if(rowTotal%blockList != 0)totalPage++; 
		

		//만약 잘못된 연산과 움직임으로 인하여 현재 페이지 수가 전체 페이지 수를
		//넘을 경우 강제로 현재페이지 값을 전체 페이지 값으로 변경
		if(nowPage > totalPage)nowPage = totalPage;
		

		//시작 페이지와 마지막 페이지를 구함.
		startPage = (int)(((nowPage-1)/blockPage)*blockPage+1);
		endPage = startPage + blockPage - 1; //
		
		
		
		//마지막 페이지 수가 전체페이지수보다 크면 마지막페이지 값을 변경
		if(endPage > totalPage)endPage = totalPage;
		
		//마지막페이지가 전체페이지보다 작을 경우 다음 페이징이 적용할 수 있도록
		//boolean형 변수의 값을 설정
		if(endPage < totalPage) isNextPage = true;
		//시작페이지의 값이 1보다 작으면 이전페이징 적용할 수 있도록 값설정
		if(startPage > 1)isPrevPage = true;
		
		//HTML코드를 저장할 StringBuffer생성=>코드생성
		sb = new StringBuffer();
//-----그룹페이지처리 이전 --------------------------------------------------------------------------------------------		
		if(isPrevPage){
			sb.append("<a onclick='comment_list(");
			sb.append(startPage-1); // nowPage-blockPage
			sb.append(");'>◀</a>");
		}
		else
			sb.append("◀");
		
//------페이지 목록 출력 -------------------------------------------------------------------------------------------------
		sb.append("|");
		for(int i=startPage; i<= endPage ;i++){
			//if(i>totalPage)break;
			if(i == nowPage){ //현재 있는 페이지
				sb.append("&nbsp;<b><font size='3' color='red'>[");
				sb.append(i);
				sb.append("]</font></b>");
			}
			else{//현재 페이지가 아니면
				sb.append("&nbsp;<a onclick='comment_list(");
				sb.append(i);
				sb.append(");'>");
				sb.append(i);
				sb.append("</a>");
			}
		}// end for
		
		sb.append("&nbsp;|");
		
//-----그룹페이지처리 다음 ----------------------------------------------------------------------------------------------
		if(isNextPage){
			sb.append("<a onclick='comment_list(");

			sb.append(endPage + 1);
			
			sb.append(");'>▶</a>");
		}
		else
			sb.append("▶");
//---------------------------------------------------------------------------------------------------------------------	    

		return sb.toString();
	}
}
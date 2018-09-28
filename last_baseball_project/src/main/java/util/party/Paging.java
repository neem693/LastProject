package util.party;
/* 이 페이지는 파티 전용 페이징 입니다.
 * 
 * 
        nowPage:현재페이지
        rowTotal:전체데이터갯수
        blockList:한페이지당 게시물수
        blockPage:한화면에 나타낼 페이지 목록수
 */

/*
 * function party_list_page(d,page)
 * 해당 함수이다. d는 day이다.
 * 
 */
public class Paging {
	public static String getPaging(int nowPage, int rowTotal, int blockList, int blockPage, int day) {

		int totalPage/* 전체페이지수 */, startPage/* 시작페이지번호 */, endPage;/* 마지막페이지번호 */

		boolean isPrevPage, isNextPage;
		StringBuffer sb; // 모든 상황을 판단하여 HTML코드를 저장할 곳

		isPrevPage = isNextPage = false;
		// 입력된 전체 자원을 통해 전체 페이지 수를 구한다..
		totalPage = (int) (rowTotal / blockList);
		if (rowTotal % blockList != 0)
			totalPage++;

		// 만약 잘못된 연산과 움직임으로 인하여 현재 페이지 수가 전체 페이지 수를
		// 넘을 경우 강제로 현재페이지 값을 전체 페이지 값으로 변경
		if (nowPage > totalPage)
			nowPage = totalPage;

		// 시작 페이지와 마지막 페이지를 구함.
		startPage = (int) (((nowPage - 1) / blockPage) * blockPage + 1);
		endPage = startPage + blockPage - 1; //

		// 마지막 페이지 수가 전체페이지수보다 크면 마지막페이지 값을 변경
		if (endPage > totalPage)
			endPage = totalPage;

		// 마지막페이지가 전체페이지보다 작을 경우 다음 페이징이 적용할 수 있도록
		// boolean형 변수의 값을 설정
		if (endPage < totalPage)
			isNextPage = true;
		// 시작페이지의 값이 1보다 작으면 이전페이징 적용할 수 있도록 값설정
		if (startPage > 1)
			isPrevPage = true;

		// HTML코드를 저장할 StringBuffer생성=>코드생성
		sb = new StringBuffer();
		// -----그룹페이지처리 이전
		// --------------------------------------------------------------------------------------------
		if (isPrevPage) {
			String arnc_tag = String.format("<a class = 'groupPage pre_page' onclick='party_list_page(%d,%d)'>", day, nowPage - blockPage);
			sb.append(arnc_tag);
			sb.append("◀</a>");
		} else
			sb.append("<span class ='groupPage'>◀</span>");

		// ------페이지 목록 출력
		// -------------------------------------------------------------------------------------------------
		//sb.append("|");
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage)
				break;
			if (i == nowPage) { // 현재 있는 페이지
				sb.append("<span class = 'page current_page'>");
				sb.append(i);
				sb.append("</span>");
			} else {// 현재 페이지가 아니면
//				sb.append("<a href='" + pageURL + "?page=");
//				sb.append(i);
//				sb.append("'>");
				String arnc_tag = String.format("<a class = 'page change_page' onclick='party_list_page(%d,%d)'>", day, i);
				sb.append(arnc_tag);
				sb.append(i);
				sb.append("</a>");
			}
		} // end for

		//sb.append("&nbsp;|");

		// -----그룹페이지처리 다음
		// ----------------------------------------------------------------------------------------------
		if (isNextPage) {
			//sb.append("<a href='" + pageURL + "?page=");
			if (nowPage + blockPage > totalPage)
				nowPage = totalPage;
			else
				nowPage = nowPage + blockPage;
			String arnc_tag = String.format("<a class = 'groupPage next_page' onclick='party_list_page(%d,%d)'>", day, nowPage);
			sb.append(arnc_tag);
			//sb.append(nowPage);
			sb.append("▶</a>");
		} else
			sb.append("<span class ='groupPage'>▶</span>");
		// ---------------------------------------------------------------------------------------------------------------------

		return sb.toString();
	}
}
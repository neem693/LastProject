package util.party;
/* �� �������� ��Ƽ ���� ����¡ �Դϴ�.
 * 
 * 
        nowPage:����������
        rowTotal:��ü�����Ͱ���
        blockList:���������� �Խù���
        blockPage:��ȭ�鿡 ��Ÿ�� ������ ��ϼ�
 */

/*
 * function party_list_page(d,page)
 * �ش� �Լ��̴�. d�� day�̴�.
 * 
 */
public class Paging {
	public static String getPaging(int nowPage, int rowTotal, int blockList, int blockPage, int day) {

		int totalPage/* ��ü�������� */, startPage/* ������������ȣ */, endPage;/* ��������������ȣ */

		boolean isPrevPage, isNextPage;
		StringBuffer sb; // ��� ��Ȳ�� �Ǵ��Ͽ� HTML�ڵ带 ������ ��

		isPrevPage = isNextPage = false;
		// �Էµ� ��ü �ڿ��� ���� ��ü ������ ���� ���Ѵ�..
		totalPage = (int) (rowTotal / blockList);
		if (rowTotal % blockList != 0)
			totalPage++;

		// ���� �߸��� ����� ���������� ���Ͽ� ���� ������ ���� ��ü ������ ����
		// ���� ��� ������ ���������� ���� ��ü ������ ������ ����
		if (nowPage > totalPage)
			nowPage = totalPage;

		// ���� �������� ������ �������� ����.
		startPage = (int) (((nowPage - 1) / blockPage) * blockPage + 1);
		endPage = startPage + blockPage - 1; //

		// ������ ������ ���� ��ü������������ ũ�� ������������ ���� ����
		if (endPage > totalPage)
			endPage = totalPage;

		// �������������� ��ü���������� ���� ��� ���� ����¡�� ������ �� �ֵ���
		// boolean�� ������ ���� ����
		if (endPage < totalPage)
			isNextPage = true;
		// ������������ ���� 1���� ������ ��������¡ ������ �� �ֵ��� ������
		if (startPage > 1)
			isPrevPage = true;

		// HTML�ڵ带 ������ StringBuffer����=>�ڵ����
		sb = new StringBuffer();
		// -----�׷�������ó�� ����
		// --------------------------------------------------------------------------------------------
		if (isPrevPage) {
			String arnc_tag = String.format("<a class = 'groupPage pre_page' onclick='party_list_page(%d,%d)'>", day, nowPage - blockPage);
			sb.append(arnc_tag);
			sb.append("��</a>");
		} else
			sb.append("<span class ='groupPage'>��</span>");

		// ------������ ��� ���
		// -------------------------------------------------------------------------------------------------
		//sb.append("|");
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage)
				break;
			if (i == nowPage) { // ���� �ִ� ������
				sb.append("<span class = 'page current_page'>");
				sb.append(i);
				sb.append("</span>");
			} else {// ���� �������� �ƴϸ�
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

		// -----�׷�������ó�� ����
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
			sb.append("��</a>");
		} else
			sb.append("<span class ='groupPage'>��</span>");
		// ---------------------------------------------------------------------------------------------------------------------

		return sb.toString();
	}
}
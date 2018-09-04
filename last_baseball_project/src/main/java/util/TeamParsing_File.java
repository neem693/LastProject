package util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import vo.TeamVo;

public class TeamParsing_File {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String file_url = "src/main/java/util/html/팀순위   순위   KBO.htm";
	//	URL url = new URL(file_url);
		boolean start = false;
		String[][] all_table_text = new String[10][12];
		int all = 0;
		TeamVo[] team_vo = new TeamVo[10];
		
		
		for(int i =0;i<team_vo.length;i++) {
			team_vo[i] = new TeamVo();
		}

		// 초기화
		for (int i = 0; i < all_table_text.length; i++)
			for (int j = 0; j < all_table_text[i].length; j++)
				all_table_text[i][j] = "";

		// 입력(byte)스트림 획득
		InputStream is = new FileInputStream(new File(file_url));

		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		int count = 0;
		int team = 0;

		while (true) {

			String str = br.readLine();
			if (str == null)
				break;

			if (str.contains("<tbody") && all == 0) {
				System.out.println("티바디가 여기있네");
				start = true;
				all = 1;
			}
			if (start && str.contains("<td")) {
				System.out.println(str);// 여기서 println을 하면 \n이 추가 되가지고 하나씩 계속 밀려진다.
				all_table_text[count][team] = str.trim();
				team++;
				if (team == 12) {
					count++;
					team = 0;
				}

			}

			if (str.contains("/tbody")) {
				System.out.println("티바디가 끝나네");
				start = false;
			}

		}
		// System.out.println("반복 횟수:" + count);
		// System.out.println(all_table_text);
		for (int i = 0; i < all_table_text.length; i++) {
			for (int j = 0; j < all_table_text[i].length; j++) {
				all_table_text[i][j] = all_table_text[i][j].replaceAll("</td>", "");
				all_table_text[i][j] = all_table_text[i][j].replaceAll("<td.*>", "");
				System.out.println(all_table_text[i][j]);
				switch (j) {
				case 0:
					team_vo[i].setT_rank(Integer.parseInt(all_table_text[i][j]));
					break;
				case 1:
					team_vo[i].setT_name(all_table_text[i][j]);
					break;
				case 2:
					team_vo[i].setT_nom(Integer.parseInt(all_table_text[i][j]));
					break;
				case 3:
					team_vo[i].setT_win(Integer.parseInt(all_table_text[i][j]));
					break;
				case 4:
					team_vo[i].setT_lose(Integer.parseInt(all_table_text[i][j]));
					break;
				case 5:
					team_vo[i].setT_draw(Integer.parseInt(all_table_text[i][j]));
					break;
				case 6:
					team_vo[i].setT_winpo(Double.parseDouble(all_table_text[i][j]));
					break;
				case 7:
					team_vo[i].setT_leading(Double.parseDouble(all_table_text[i][j]));
					break;
				case 8:
					team_vo[i].setT_recent10(all_table_text[i][j]);
					break;
				case 9:
					team_vo[i].setT_contn(all_table_text[i][j]);
					break;
				case 10:
					team_vo[i].setT_home(all_table_text[i][j]);
					break;
				case 11:
					team_vo[i].setT_away(all_table_text[i][j]);
					

				}
			}
			// team_vo[i].setT_rank();
		}
		System.out.println("팀_파싱완료");

	}

}

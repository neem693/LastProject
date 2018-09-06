package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import myconst.Myconst;
import vo.PlayVo;

public class MatchParsing_v2 {
	String str_url;

	public PlayVo[] matchParsing(int mon, int year) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date match_day;
		Date today = new Date();
		DateUtil dateUtil = new DateUtil();

		int thisyear = year;
		int month = mon;
		String month_str = String.format("%02d", month);
		// System.out.println(month_str);
		String str_url = "https://www.koreabaseball.com/ws/Schedule.asmx/GetScheduleList?gameMonth=09&leId=1&seasonId=2018&srIdList=0,9&teamId=";
		URL url = new URL(str_url);

		// System.out.println(Da);

		// System.out.println(new Date().toString());

		InputStream is = url.openStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");

		BufferedReader br = new BufferedReader(isr);

		StringBuffer sb = new StringBuffer();
		while (true) {
			String str = br.readLine();

			if (str == null)
				break;
			sb.append(str);

		}

		String json_str = sb.toString();
		// System.out.println(json_str);
		JSONObject jb = new JSONObject(json_str);
		JSONArray rows = jb.getJSONArray("rows");
		JSONObject rows_seq;
		JSONArray row;
		JSONObject text;
		String[][] match_text = new String[rows.length()][];
		for (int i = 0; i < rows.length(); i++) {
			rows_seq = rows.getJSONObject(i);
			row = rows_seq.getJSONArray("row");
			match_text[i] = new String[row.length()];

			for (int j = 0; j < row.length(); j++) {
				text = row.getJSONObject(j);

				match_text[i][j] = text.getString("Text");
				// System.out.println(text.getString("Text"));

			}

		}

		/*
		 * 
		 * 9일때 0(날짜),1(시간),2(팀,점수),7(경기장),8(취소될 경우 사유) 
		 * 8일때 0(시간),1(팀,점수),6(경기장),7(비고 취소될
		 * 경우 사유) 정규표현식 ([a-zA-Z가-힣]+)([0-9]*)vs([0-9]*)([a-zA-Z가-힣]+)
		 */

		int length;
		String day = null;
		String time;
		String day_time = null;

		String pattren = "([a-zA-Z가-힣]+)([0-9]*)vs([0-9]*)([a-zA-Z가-힣]+)";
		Pattern p = Pattern.compile(pattren);
		Matcher m = null;
		String day_idx = null;
		int home_score = 0, away_score = 0;

		PlayVo[] vo = new PlayVo[match_text.length];

		for (int i = 0; i < match_text.length; i++) {
			length = match_text[i].length;
			vo[i] = new PlayVo();
			if (length == 9) {
				day = match_text[i][0];
				day = day.replaceAll("\\(.*\\)", "");
				day = day.replaceAll("\\.", "-");
				time = match_text[i][1];
				time = time.replaceAll("<.*?>", "");
				day_time = String.format("%d-%s %s:00", thisyear, day, time);
				vo[i].setP_date(day_time);

				match_text[i][2] = match_text[i][2].replaceAll("<.*?>", "");

				m = p.matcher(match_text[i][2]);
				m.find();
				vo[i].setT_away(m.group(1));
				vo[i].setT_home(m.group(4));
				if (m.group(2).equals("")) {

					match_day = format.parse(day_time);
					if (dateUtil.isBeforeDay(match_day, today)) {
						vo[i].setP_rts(match_text[i][8]);
						vo[i].setP_score("C");
					} else {

						vo[i].setP_rts("-");
						vo[i].setP_score("예정");

					}
				} else {
					away_score = Integer.parseInt(m.group(2));
					home_score = Integer.parseInt(m.group(3));

					if (away_score > home_score)
						vo[i].setP_rts("W");
					else if (away_score == home_score)
						vo[i].setP_rts("D");
					else if (away_score < home_score)
						vo[i].setP_rts("L");
					vo[i].setP_score(String.format("%d-%d", away_score, home_score));
				}
				vo[i].setS_idx(setStadium(match_text[i][7]));

				day_idx = day.replaceAll("-", "");
				vo[i].setP_idx(String.format("%d%s_%s%s", thisyear, day_idx, m.group(1), m.group(4)));

			} else {
				time = match_text[i][0];
				time = time.replaceAll("<.*?>", "");
				day_time = String.format("%d-%s %s:00", thisyear, day, time);
				vo[i].setP_date(day_time);

				match_text[i][1] = match_text[i][1].replaceAll("<.*?>", "");
				m = p.matcher(match_text[i][1]);
				m.find();
				vo[i].setT_away(m.group(1));
				vo[i].setT_home(m.group(4));

				if (m.group(2).equals("")) {
					match_day = format.parse(day_time);
					if (dateUtil.isBeforeDay(match_day, today)) {
						vo[i].setP_rts(match_text[i][8]);
						vo[i].setP_score("C");
					} else {

						vo[i].setP_rts("-");
						vo[i].setP_score("예정");

					}
				} else {
					away_score = Integer.parseInt(m.group(2));
					home_score = Integer.parseInt(m.group(3));

					if (away_score > home_score)
						vo[i].setP_rts("W");
					else if (away_score == home_score)
						vo[i].setP_rts("D");
					else if (away_score < home_score)
						vo[i].setP_rts("L");
					vo[i].setP_score(String.format("%d-%d", away_score, home_score));
				}
				vo[i].setS_idx(setStadium(match_text[i][6]));
				vo[i].setP_idx(String.format("%d%s_%s%s", thisyear, day_idx, m.group(1), m.group(4)));

			}

			vo[i].setP_date(day_time);

		}

		// System.out.println(ja.length());
		//

		// for(int i =0; i<)

		// jb.get(key)
		// System.out.println(jb);

		System.out.println("경기 파싱 완료");
		System.out.println(vo.length);
		return vo;

	}

	public int setStadium(String stadium) {
		int result = 0;
		if (stadium.equals("대전"))
			result = 1;
		else if (stadium.equals("청주"))
			result = 2;
		else if (stadium.equals("대구"))
			result = 3;
		else if (stadium.equals("포항"))
			result = 4;
		else if (stadium.equals("사직"))
			result = 5;
		else if (stadium.equals("울산"))
			result = 6;
		else if (stadium.equals("광주"))
			result = 7;
		else if (stadium.equals("마산"))
			result = 8;
		else if (stadium.equals("잠실"))
			result = 9;
		else if (stadium.equals("문학"))
			result = 10;
		else if (stadium.equals("고척"))
			result = 11;
		else if (stadium.equals("수원"))
			result = 12;
		else if (stadium.equals("창원"))
			result = 13;
		else if (stadium.equals("군산"))
			result = 14;
		if (result == 0)
			System.out.println("오류발생" + stadium);
		return result;
	}

}

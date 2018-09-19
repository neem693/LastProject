package util.parsing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;
import org.json.JSONArray;
import org.json.JSONObject;

import myconst.Myconst;
import vo.PlayVo;

public class MatchParsing {
	String str_url;

	public static void main(String[] args) throws Exception {

		int thisyear = Myconst.ParsingDateCheck.YEAR;
		int month = 9;
		String month_str = String.format("%02d", month);
		// System.out.println(month_str);
		String str_url = "https://www.koreabaseball.com/ws/Schedule.asmx/GetMonthSchedule?gameMonth=" + month_str
				+ "&leId=1&seasonId=2018&srIdList=0,1,3,4,5,7,9";
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
		System.out.println(json_str);

		// System.out.println(str);
		JSONObject json = new JSONObject(json_str);
		// System.out.println(json.getString("rows"));
		// System.out.println(set);

		JSONArray array_rows = json.getJSONArray("rows");
		JSONObject mid_object;
		JSONArray array_row;
		String[] text = new String[50];

		int count = 0;
		for (int i = 0; i < array_rows.length(); i++) {
			mid_object = array_rows.getJSONObject(i);
			array_row = mid_object.getJSONArray("row");
			for (int j = 0; j < array_row.length(); j++) {

				text[count] = array_row.getJSONObject(j).getString("Text");

				// text[count] = text[count].replaceAll("<\\/a>", "");
				// text[count] = text[count].replaceAll("<a ?.*?>", "");

				text[count] = text[count].replaceAll("<li.*?>", "(");
				text[count] = text[count].replaceAll("<\\/li>", "");
				text[count] = text[count].replaceAll("<.*?>", "");

				System.out.println(text[count]);

				if (!(text[count].equals(""))) {

					count++;

				}
			}
		}
		String match_list[][] = new String[31][];

		int size;
		count = 0;
		for (int i = 0; i < text.length; i++) {

			if (text[i] == null)
				break;
			size = text[i].length();
			if (size <= 10)
				continue;
			match_list[count] = text[i].split("\\(");
			count++;
		}
		System.out.println(count); // °æ±â°¡ ÀÖ´ø ³¯¿¡ ´ëÇÑ°Í Áï, ÇØ´ç count * 5°¡ ÃÑ °æ±â¼ö°¡ µÈ´Ù.

		String pattern = "([°¡-ÆRa-zA-Z]+) ([0-9]*) ?: ?([0-9]*) ([°¡-ÆRa-zA-Z]+) (\\[?[°¡-ÆRa-zA-Z]*\\]?)";
		Pattern p = Pattern.compile(pattern);
		// System.out.println(match_list[0][2]);

		List<PlayVo> list_p = new ArrayList<PlayVo>();
		PlayVo vo;
		Matcher m;
		int s_away, s_home;

		// --------------------------------

		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
		int day = -1;

		DateUtil dateutil = new DateUtil();
		// System.out.println(dateutil.isBeforeDay(match_day, today));
		// ÇÒ ¶§ today°¡ ¹«Á¶°Ç µÚ·Î °¡¾ß ÇÑ´Ù.

		for (int i = 0; i < count; i++) {
			for (int j = 1; j < match_list[i].length; j++) {// 0Àº ¾îÂ÷ÇÇ ºñ¾îÀÖÀ¸¹Ç·Î »ý·«
				// 1Àº ³¯Â¥¸¦ ³ªÅ¸³¿À¸·Î ¿©±â¿¡ ÇöÀç³âµµ¿Í ¿ùÀ» ±¸ÇØ¼­ ½ºÆ®¸µÀ» ´õÇØ¹ö¸²
				if (j == 1) {
					day = Integer.parseInt(match_list[i][j]);
				}

				m = p.matcher(match_list[i][j]);
				if (!m.find())
					continue;
				else {
					// ±×·ì0Àº ÀüÃ¼, ±×·ì1Àº ¿øÁ¤, ±×·ì2´Â ½ºÄÚ¾î(¾øÀ»°æ¿ì ¹Ù·Î "CANCLE"ÀÔ·Â) ±×·ì3Àº È¨ ½ºÄÚ¾î(±×·ì2¶û ÇÕÃÄÁü)
					// ±×·ì2°¡ ´õ Å¬°æ¿ì ½Â, ¾Æ´Ï¸é ÆÐ, °°À¸¸é ¹«
					// ±×·ì4´Â È¨ ÆÀÀÌ¸§ ±×·ì5´Â ÇØ´ç È¨ÆÃ °æ±âÀå
					// ³âµµ ¿ù ÀÏÀ» ´ÙÇÕÃÄ¼­ °æ±â³¯Â¥ Áý¾î³Ö±â
					String matchdate = String.format("%d-%02d-%02d", thisyear, month, day);
					Date match_day = dateFormat.parse(matchdate);// ÀÏ´Ü Áö³­ ³¯ÀÎ°¡ ¾Ë¾Æº¸±â À§ÇØ¼­ matchdate¸¦ ¸¸µç´Ù.
					String match_str = dateFormat.format(match_day);
					vo = new PlayVo();
					vo.setP_date(match_str);
					vo.setT_away(m.group(1).trim());
					vo.setT_home(m.group(4).trim());
					vo.setP_idx(String.format("%s%s%s", dateFormat2.format(match_day), m.group(1), m.group(4)));

					if (m.group(2).equals("") && m.group(3).equals("")) {// ½ºÄÚ¾î°¡ ¾øÀ» ¶§ ¾î¶»°Ô ÇÒ °ÍÀÎ°¡?

						boolean result = dateutil.isBeforeDay(match_day, today);
						if (result)
							vo.setP_rts("C");
						else
							vo.setP_rts("¿¹Á¤");
					} else {
						s_away = Integer.parseInt(m.group(2));
						s_home = Integer.parseInt(m.group(3));
						String score = String.format("%d-%d", s_away, s_home);
						vo.setP_score(score);
						if (s_away > s_home)
							vo.setP_rts("W");
						else if (s_away == s_home)
							vo.setP_rts("D");
						else if (s_away < s_home)
							vo.setP_rts("L");
					}

				}
				list_p.add(vo);
			}
		}

		// final String regex = "([°¡-ÆRa-zA-Z]+) ([0-9]*) ?: ?([0-9]*) ([°¡-ÆRa-zA-Z]+)
		// (\\[?[°¡-ÆRa-zA-Z]*\\]?)";
		// final String string = "LG 8 : 14 µÎ»ê ";
		//
		// final Pattern pattern = Pattern.compile(regex);
		// final Matcher matcher = pattern.matcher(string);
		//
		// while (matcher.find()) {
		// System.out.println("Full match: " + matcher.group(0));
		// for (int i = 1; i <= matcher.groupCount(); i++) {
		// System.out.println("Group " + i + ": " + matcher.group(i));
		// }
		// }
		//
		//System.out.println("°æ±â ÆÄ½Ì ¿Ï·á");

	}
}

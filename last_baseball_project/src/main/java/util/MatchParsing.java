package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class MatchParsing {
	String str_url;

	public static void main(String[] args) throws Exception {
		String str_url = "https://www.koreabaseball.com/ws/Schedule.asmx/GetMonthSchedule?gameMonth=08&leId=1&seasonId=2018&srIdList=0,1,3,4,5,7,9";
		URL url = new URL(str_url);
		
		

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

//				text[count] = text[count].replaceAll("<\\/a>", "");
//				text[count] = text[count].replaceAll("<a ?.*?>", "");

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

			if(text[i] == null)
				break;
			size = text[i].length();
			if (size <= 10)
				continue;
			match_list[count] = text[i].split("\\(");
			count++;
		}

		System.out.println("경기 파싱 완료");

	}
}

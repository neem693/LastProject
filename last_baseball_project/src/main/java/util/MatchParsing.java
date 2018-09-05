package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;
import org.json.JSONArray;
import org.json.JSONObject;

import vo.PlayVo;

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
		System.out.println(count); //경기가 있던 날에 대한것 즉, 해당 count * 5가 총 경기수가 된다.
	
		String pattern = "([가-힣a-zA-Z]+) ([0-9]*) ?: ?([0-9]*) ([가-힣a-zA-Z]+) (\\[?[가-힣a-zA-Z]*\\]?)";
		Pattern p = Pattern.compile(pattern);
		//System.out.println(match_list[0][2]);
		
		
		List<PlayVo> list_p = new ArrayList<PlayVo>();
		PlayVo vo = new PlayVo();
		Matcher m;
		
		
		
		for(int i =0; i<count;i++) { 
			for(int j =1; j<match_list[i].length;j++) {//0은 어차피 비어있으므로 생략
				//1은 날짜를 나타냄으로 여기에 현재년도와 월을 구해서 스트링을 더해버림
				
				
				m = p.matcher(match_list[i][j]);
				if(!m.find())
					continue;
				else {
					//그룹0은 전체, 그룹1은 원정, 그룹2는 스코어(없을경우 바로 "CANCLE"입력) 그룹3은 홈 스코어(그룹2랑 합쳐짐)
					//그룹2가 더 클경우 승, 아니면 패, 같으면 무
					//그룹4는 홈 팀이름 그룹5는 해당 홈팅 경기장
					//년도 월 일을 다합쳐서 경기날짜 집어넣기
					
				}
				
			
				
				
			}
		}
		
		
		
		
		
		
//		final String regex = "([가-힣a-zA-Z]+) ([0-9]*) ?: ?([0-9]*) ([가-힣a-zA-Z]+) (\\[?[가-힣a-zA-Z]*\\]?)";
//		final String string = "LG 8 : 14 두산 ";
//
//		final Pattern pattern = Pattern.compile(regex);
//		final Matcher matcher = pattern.matcher(string);
//
//		while (matcher.find()) {
//		    System.out.println("Full match: " + matcher.group(0));
//		    for (int i = 1; i <= matcher.groupCount(); i++) {
//		        System.out.println("Group " + i + ": " + matcher.group(i));
//		    }
//		}
//
	System.out.println("경기 파싱 완료");

	}
}

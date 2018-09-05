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
		System.out.println(count); //°æ±â°¡ ÀÖ´ø ³¯¿¡ ´ëÇÑ°Í Áï, ÇØ´ç count * 5°¡ ÃÑ °æ±â¼ö°¡ µÈ´Ù.
	
		String pattern = "([°¡-ÆRa-zA-Z]+) ([0-9]*) ?: ?([0-9]*) ([°¡-ÆRa-zA-Z]+) (\\[?[°¡-ÆRa-zA-Z]*\\]?)";
		Pattern p = Pattern.compile(pattern);
		//System.out.println(match_list[0][2]);
		
		
		List<PlayVo> list_p = new ArrayList<PlayVo>();
		PlayVo vo = new PlayVo();
		Matcher m;
		
		
		
		for(int i =0; i<count;i++) { 
			for(int j =1; j<match_list[i].length;j++) {//0Àº ¾îÂ÷ÇÇ ºñ¾îÀÖÀ¸¹Ç·Î »ý·«
				//1Àº ³¯Â¥¸¦ ³ªÅ¸³¿À¸·Î ¿©±â¿¡ ÇöÀç³âµµ¿Í ¿ùÀ» ±¸ÇØ¼­ ½ºÆ®¸µÀ» ´õÇØ¹ö¸²
				
				
				m = p.matcher(match_list[i][j]);
				if(!m.find())
					continue;
				else {
					//±×·ì0Àº ÀüÃ¼, ±×·ì1Àº ¿øÁ¤, ±×·ì2´Â ½ºÄÚ¾î(¾øÀ»°æ¿ì ¹Ù·Î "CANCLE"ÀÔ·Â) ±×·ì3Àº È¨ ½ºÄÚ¾î(±×·ì2¶û ÇÕÃÄÁü)
					//±×·ì2°¡ ´õ Å¬°æ¿ì ½Â, ¾Æ´Ï¸é ÆÐ, °°À¸¸é ¹«
					//±×·ì4´Â È¨ ÆÀÀÌ¸§ ±×·ì5´Â ÇØ´ç È¨ÆÃ °æ±âÀå
					//³âµµ ¿ù ÀÏÀ» ´ÙÇÕÃÄ¼­ °æ±â³¯Â¥ Áý¾î³Ö±â
					
				}
				
			
				
				
			}
		}
		
		
		
		
		
		
//		final String regex = "([°¡-ÆRa-zA-Z]+) ([0-9]*) ?: ?([0-9]*) ([°¡-ÆRa-zA-Z]+) (\\[?[°¡-ÆRa-zA-Z]*\\]?)";
//		final String string = "LG 8 : 14 µÎ»ê ";
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
	System.out.println("°æ±â ÆÄ½Ì ¿Ï·á");

	}
}

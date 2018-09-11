package util.parsing;

import java.util.Calendar;
import java.util.Date;

import vo.PlayVo;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MatchParsing_v2 match = new MatchParsing_v2();
		PlayVo [] vo=null;
		
		
//		Calendar c1 = Calendar.getInstance();
//		c1.set(2018, 9-1, 8);
//		System.out.println(c1.getTime());
//		Date d1 = new Date();
//		DateUtil dateu  = new DateUtil();
//		System.out.println(dateu.isToday(d1));
//		System.out.println(dateu.isToday(c1));
		
		//오늘인지 좀 보자
		//
		//

		
//		Calendar cal1 = Calendar.getInstance();
//		Date d1 = cal1.getTime();
//		System.out.println(cal1.get(Calendar.YEAR));
//		System.out.println(cal1.get(Calendar.MONTH)+1);
//		System.out.println(cal1.get(Calendar.DAY_OF_MONTH));
//		StringBuffer sb = new StringBuffer();
//		sb.append(cal1.get(Calendar.HOUR_OF_DAY));
//		sb.append(":");
//		sb.append(cal1.get(Calendar.MINUTE));
//		sb.append(":");
//		sb.append(cal1.get(Calendar.SECOND));
//		System.out.println(sb.toString());
//		System.out.println(d1);
		
		
		//
		//
		//끝
		
		try {
			vo = match.matchParsing(9, 2018);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		System.out.println("완료" + vo.toString());
		//System.out.println(System.currentTimeMillis());
	}

}

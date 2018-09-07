package util;

import vo.PlayVo;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MatchParsing_v2 match = new MatchParsing_v2();
		PlayVo [] vo=null;
		try {
			 vo = match.matchParsing(7, 2018);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("¿Ï·á" + vo.toString());
	}

}

package myconst;

import java.text.SimpleDateFormat;

public class Myconst {




	public static class Member {
		public static String MEMBER_DIR = "/WEB-INF/views/pickBaseball/input/";

	}
	
	public static class BaseBall{
		public static String BASEBALL_DIR= "/WEB-INF/views/pickBaseball/";
		public static String PARTY_DIR = "/WEB-INF/views/pickBaseball/party/";

	}

	public static class ParsingDateCheck {
		public static int YEAR = 2018;
		public static int PARSING_INTERVAL = 1;
		public static int ONE_HOUR_MILESECOND = 3600000;
		public static int ONE_DAY_MILESECOND = 86400000;
	}



	public static class DateCheck {
		public static int year = 2018;
		public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public static class Joonggo {
		public static String VIEW_PATH = "/WEB-INF/views/joonggo/";
	}
	public static class Party{
		
		public static int CALENDAR_ROW =7;
		public static int CALENDAR_COL =7;
		public static String USE_SPECIAL_FAC = "특수시설";
		public static String CHEER = "응원";
		public static String LETS_GET_IN = "모여라";
		public static String ETC = "기타";
		public static String MOD_DAY = "day";
		public static String MOD_HOUR = "time";
		
		
		
	}

}

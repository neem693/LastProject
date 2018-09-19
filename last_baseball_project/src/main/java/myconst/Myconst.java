package myconst;

import java.text.SimpleDateFormat;

public class Myconst {





	
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

	public static class MEMBER {
		public static String MEMBER = "/WEB-INF/views/pickBaseball/input/";

	}



	public static class Joonggo {
		public static String VIEW_PATH = "/WEB-INF/views/joonggo/";
			public static String UPDATA_VIEW_PATH = "/WEB-INF/views/joonggo/";

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

	public static class PhotoUpload {
		public static String PHOTO_WEB_PATH_LOOT = "/pickBaseball/resources/photo_upload/";
	}
	
	public static class Comment {
		public static String VIEW_PATH = "/WEB-INF/views/comment/";
	}
	

	public static class Main{
		public static String VIEW_PATH = "/WEB-INF/views/main/";
	}

	public static class JoonggoPage
	{
		public static int BLOCK_LIST = 10; // page당 게시물수
		public static int BLOCK_PAGE = 3; //page당 보여지는 메뉴 갯수

	}

}

package myconst;

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
	}



	public static class DateCheck {
		public static int year = 2018;
	}

	public static class Joonggo {
		public static String VIEW_PATH = "/WEB-INF/views/joonggo/";
	}

	public static class Party{
		
		public static int CALENDAR_ROW =7;
		public static int CALENDAR_COL =7;
		
		
	}
	
	public static class NormalPageing{
		public static final int BLOCK_LIST = 15;//page당 게시물 수
		public static final int BLOCK_PAGE = 10;//page당 보여지는 메뉴갯수
	}
	
	public static class NormalCommentPageing{
		public static final int BLOCK_LIST = 15;
		public static final int BLOCK_PAGE = 10;
	}
	
	public static class Normal{
		public static String VIEW_PATH = "/WEB-INF/views/normal/";
	}
	
	public static class Main{
		public static String VIEW_PATH = "/WEB-INF/views/main/";
	}

	public static class NormalComment{
		public static String VIEW_PATH = "/WEB-INF/views/normal/";
	}
	
}

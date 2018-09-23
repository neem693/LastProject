package service.party;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.party.PartyDaoInerface;
import myconst.Myconst;
import util.parsing.MatchParsing_v2;
import util.parsing.TeamParsing;
import util.party.Paging;
import vo.MemberVo;
import vo.PartyVo;
import vo.PlayVo;
import vo.TeamVo;

public class ServicePartyimpl implements PartyServiceInterface {

	PartyDaoInerface team_dao, play_dao, parsing_second_dao, party_dao, party_book_dao;
	MatchParsing_v2 match_parsing = new MatchParsing_v2();
	TeamParsing team_parsing = new TeamParsing();

	public PartyDaoInerface getParty_book_dao() {
		return party_book_dao;
	}

	public void setParty_book_dao(PartyDaoInerface party_book_dao) {
		this.party_book_dao = party_book_dao;
	}

	public PartyDaoInerface getParty_dao() {
		return party_dao;
	}

	public void setParty_dao(PartyDaoInerface party_dao) {
		this.party_dao = party_dao;
	}

	public PartyDaoInerface getTeam_dao() {
		return team_dao;
	}

	public void setTeam_dao(PartyDaoInerface team_dao) {
		this.team_dao = team_dao;
	}

	public PartyDaoInerface getPlay_dao() {
		return play_dao;
	}

	public void setPlay_dao(PartyDaoInerface play_dao) {
		this.play_dao = play_dao;
	}

	public PartyDaoInerface getParsing_second_dao() {
		return parsing_second_dao;
	}

	public void setParsing_second_dao(PartyDaoInerface parsing_second_dao) {
		this.parsing_second_dao = parsing_second_dao;
	}

	@Override
	public int team_update(Object[] ob) {
		// TODO Auto-generated method stub
		int team_count = ob.length;
		int count = team_dao.selectCount();
		System.out.println(count + " 테이블---파싱길이 " + team_count);

		TeamVo teamvo = (TeamVo) ob[0];

		int res = 0;
		if (count == 0)
			res = team_dao.insert_all(ob);
		if (count == 12)
			res = team_dao.update_all(ob);

		// System.out.println(teamvo.getT_name());
		// System.out.println(teamvo.getT_rank());
		// System.out.println(teamvo.getT_nom());
		// System.out.println(teamvo.getT_win());
		// System.out.println(teamvo.getT_lose());
		// System.out.println(teamvo.getT_draw());
		// System.out.println(teamvo.getT_winpo());
		// System.out.println(teamvo.getT_leading());
		// System.out.println(teamvo.getT_recent10());
		// System.out.println(teamvo.getT_contn());
		// System.out.println(teamvo.getT_home());
		// System.out.println(teamvo.getT_away());
		//

		return res;

	}

	@Override
	public int match_update(Object[] ob, Object obb) {
		// TODO Auto-generated method stub
		// obb는 맵을 넣어주기 바란다.
		int res = 0;
		int count = play_dao.selectCount(obb);
		System.out.println(count + "---");
		PlayVo vo = (PlayVo) ob[0];

		System.out.println(vo.getP_idx());

		if (count > 0 && count == ob.length)
			res = play_dao.update_all(ob);
		else if (count == 0)
			res = play_dao.insert_all(ob);
		else
			return -1;

		return res;
	}

	@Override
	public int check_parsing() {
		// TODO Auto-generated method stub
		int result = -1;
		Long time = (Long) parsing_second_dao.selectOne();
		long time_m = time.longValue();
		long current_m = System.currentTimeMillis();

		long def = current_m - time_m;
		System.out.println(def + "밀리초 지났습니다. (파싱까지 대기)");
		if (def >= (long) (Myconst.ParsingDateCheck.ONE_HOUR_MILESECOND * Myconst.ParsingDateCheck.PARSING_INTERVAL)) {// 작동시작

			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			TeamVo[] team_vo = null;
			String rt1 = null, rt2 = null;

			int res = 0;
			if (month != 12 && month != 1) {
				rt1 = parsing_manager(String.valueOf(year), String.valueOf(month));// 해당 달을 파싱하고
				rt2 = parsing_manager(String.valueOf(year), String.valueOf(month - 1)); // 그 전달도 파싱합니다.
				try {
					team_vo = team_parsing.parsing_url();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				res = team_update(team_vo);

			} else {
				result = 0;
			}

			if (rt1.equals("success") && rt2.equals("success") && res == 1 || res == 10) {
				result = 1;
				parsing_second_dao.update(current_m);
			}

			return result;

		} else
			result = 0;
		return result;
		// 1시간이 체크되어있지 않음으로 자동파싱안함

	}

	@Override
	public String parsing_manager(String year, String month) {
		// 매치를 업데이트 해줌
		// TODO Auto-generated method stub

		int month_int = Integer.parseInt(month);
		int year_int = Integer.parseInt(year);

		PlayVo[] vo = null;
		try {
			vo = match_parsing.matchParsing(month_int, year_int);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (vo.length == 0)
			return "fail : 파싱 결과 없음";

		Map map = new HashMap();
		map.put("year", year);
		map.put("month", String.format("%02d", month_int));

		int res = match_update(vo, map);
		String msg;
		if (res == -1) {
			msg = "fail: 업데이트 할때 갯수가 올바르지 않으므로, 무슨 문제가 있다는 것";
		} else
			msg = "success";

		return msg;
	}

	@Override
	public List take_play_list(String year, String month) {
		// TODO Auto-generated method stub

		// int row = Myconst.Party.CALENDAR_ROW;
		// int col =Myconst.Party.CALENDAR_COL;

		int year_int = Integer.parseInt(year);
		int month_int = Integer.parseInt(month);
		String year_month = String.format("%d%02d", year_int, month_int);

		Map map = new HashMap();
		map.put("year_month", year_month);

		List list = play_dao.selectList(map);

		return list;
	}

	@Override
	public Map getWeekday(String year, String month) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		int year_int = Integer.parseInt(year);
		int month_int = Integer.parseInt(month);

		String year_month = String.format("%d-%02d-01", year_int, month_int);

		Date date = null;

		try {
			date = format.parse(year_month);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();
		int today = cal.get(Calendar.DAY_OF_MONTH);
		int this_year = cal.get(Calendar.YEAR);
		int this_month = cal.get(Calendar.MONTH) + 1;
		cal.setTime(date);

		// cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH) , 1);
		int week_day = cal.get(Calendar.DAY_OF_WEEK);
		int last_day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		last_day -= 1;
		Map map = new HashMap();
		map.put("today", today);
		map.put("first_day", week_day);
		map.put("last_day", last_day);
		map.put("this_year", this_year);
		map.put("this_month", this_month);

		return map;
	}

	// 이건 파티를 생성할 때 경기를 불러올 때 사용된다.
	@Override
	public List take_play_list(String year, String month, String day) {
		// TODO Auto-generated method stub
		int year_int = Integer.parseInt(year);
		int month_int = Integer.parseInt(month);
		int day_int = Integer.parseInt(day);
		String year_month = String.format("%d%02d%02d", year_int, month_int, day_int);

		Map map = new HashMap();
		map.put("year_month", year_month);

		List list = play_dao.selectList(map);

		return list;
	}

	@Override
	public String selectStadium(String p_idx) {
		// TODO Auto-generated method stub
		// 스타디움 뿐만 아니라 팀도 가져온다.
		String stadium_team = (String) play_dao.selectOne(p_idx);

		return stadium_team;
	}

	@Override
	public int insert_party(PartyVo vo, String year, String month, String day) {
		// TODO Auto-generated method stub

		String date = vo.getDate(); // 유형 day? date?
		String p_idx = vo.getP_idx();
		// System.out.println(p_idx);
		PlayVo play_vo = select_play_one(p_idx);
		// System.out.println(play_vo.getDate());
		Date match_day = play_vo.getDate();
		int pt_day = Integer.parseInt(vo.getPt_day());
		System.out.println(pt_day);
		// System.out.println(date);
		// System.out.println(match_day);

		if (date.equals("day")) {
			System.out.println("day로 했군");
			long time = match_day.getTime() + ((long) Myconst.ParsingDateCheck.ONE_DAY_MILESECOND) * ((long) pt_day);
			match_day = new Date(time);

		} else if (date.equals("time")) {
			System.out.println("time으로 했군");
			long time = match_day.getTime() + ((long) Myconst.ParsingDateCheck.ONE_HOUR_MILESECOND) * ((long) pt_day);
			match_day = new Date(time);
		} else
			return -1;

		vo.setPt_day(Myconst.DateCheck.DATE_FORMAT.format(match_day));
		System.out.println(vo.getPt_day());

		//////// 목적////////
		int purpose = Integer.parseInt(vo.getPt_purpose());
		if (purpose == 10) {
			vo.setPt_purpose(Myconst.Party.ETC);
		} else if (purpose == 3)
			vo.setPt_purpose(Myconst.Party.LETS_GET_IN);
		else if (purpose == 2)
			vo.setPt_purpose(Myconst.Party.CHEER);
		else if (purpose == 1)
			vo.setPt_purpose(Myconst.Party.USE_SPECIAL_FAC);
		else
			return -1;

		/////// 상태 세팅/////////
		vo.setPt_condition("모집중");
		if (play_vo.getDate().getTime() <= (long) Myconst.ParsingDateCheck.ONE_DAY_MILESECOND)
			vo.setPt_condition("임박");
		/////////// 팀 세팅////////
		boolean team_set = vo.getT_name().equals(play_vo.getT_home()) || vo.getT_name().equals(play_vo.getT_away());
		if (!(team_set))
			vo.setT_name(null);

		///////// 인서트 실시///////////

		int res = party_dao.insert(vo);

		return res;
	}

	@Override
	public PlayVo select_play_one(String p_idx) {
		// TODO Auto-generated method stub

		PlayVo vo = (PlayVo) play_dao.selectOne2(p_idx);

		return vo;
	}

	@Override
	public boolean check_long_time_in_match(String year, String month, String day) {
		// TODO Auto-generated method stub

		Date date = new Date();
		long current = date.getTime();

		Calendar cal = Calendar.getInstance();
		int year_int = Integer.parseInt(year);
		int month_int = Integer.parseInt(month);
		int day_int = Integer.parseInt(day);
		cal.set(year_int, month_int - 1, day_int, 0, 0, 0);

		long time = cal.getTime().getTime();
		// System.out.println(time);
		// System.out.println(current);
		// System.out.println(time-current);
		// System.out.println(Myconst.ParsingDateCheck.ONE_DAY_MILESECOND*4);
		if ((time - current) > (long) (Myconst.ParsingDateCheck.ONE_DAY_MILESECOND * (long) 4))
			return true;
		else
			return false;

	}

	@Override
	public Map get_party_count(String year, String month, String team) {
		// TODO Auto-generated method stub
		// System.out.println("month:" + month);

		String year_month = String.format("%s%02d", year, Integer.parseInt(month));

		PartyVo vo = new PartyVo();

		vo.setP_idx(year_month);
		if (team != null)
			vo.setT_name(team);

		List<PartyVo> list = party_dao.selectList(vo);
		Map map = new HashMap<String, Integer>();

		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getDay(), list.get(i).getMatch_count());
		}
		// System.out.println(list.size());
		// System.out.println(map);

		return map;
	}

	@Override
	public int insert_party_book(MemberVo member) {
		// TODO Auto-generated method stub

		Integer idx_party = (Integer) party_dao.selectOne();
		int pt_idx = idx_party.intValue();
		// System.out.println("파티북 인서트");
		Map map = new HashMap<String, Integer>();
		map.put("m_idx", member.getM_idx());
		map.put("pt_idx", pt_idx);
		map.put("b_leader", 10);
		int res = party_book_dao.insert(map);

		return res;
	}

	@Override
	public List take_party_list(String year, String month, String day, String team, int nowPage) {
		// TODO Auto-generated method stub

		//// 페이징//////

		int start = (nowPage - 1) * Myconst.PartyListPage.BLOCK_LIST;// mysql은 limit이 0부터 시작한다.
		int page_count = Myconst.PartyListPage.BLOCK_LIST;/// 불러올 페이지 갯수니까 이게맞다.

		// 달, 일/////
		int month_int = Integer.parseInt(month);
		int day_int = Integer.parseInt(day);

		String year_month_day = String.format("%s%02d%02d", year, month_int, day_int);
		Map map = new HashMap<String, String>();
		map.put("ymd", year_month_day);
		if (team != null && (team.isEmpty() == false))
			map.put("team", team);
		
		map.put("start", start);
		map.put("page_count", page_count);

		List list = party_dao.selectList2(map);

		return list;
	}

	@Override
	public String return_party_paging(int nowPage, String day, int total_count) {
		// TODO Auto-generated method stub
		int day_int = 0;

		try {
			day_int = Integer.parseInt(day);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			day_int = 1;
		}

		// int rowTotal = party_dao.selectOne(map);

		Paging page_set = new Paging();
		String paging_html = page_set.getPaging(nowPage, total_count, Myconst.PartyListPage.BLOCK_LIST,
				Myconst.PartyListPage.BLOCK_PAGE, day_int);
System.out.println(paging_html);
		return paging_html;
	}

	@Override
	public int total_page_count(String year, String month, String day, String team) {
		// TODO Auto-generated method stub
		int day_int, month_int;
		day_int = month_int = 0;
		try {
			day_int = Integer.parseInt(day);
			month_int = Integer.parseInt(month);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			day_int = 1;
			month_int = 1;
		}

		Map map = new HashMap<String, String>();

		String year_month_day = String.format("%s%02d%02d", year, month_int, day_int);
		map.put("ymd", year_month_day);
		if (team != null && (team.isEmpty() == false))
			map.put("team", team);

		int count = party_dao.selectCount(map);

		return count;
	}

}

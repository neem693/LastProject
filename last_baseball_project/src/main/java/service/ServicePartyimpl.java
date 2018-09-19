package service;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import dao.PartyDaoInerface;
import myconst.Myconst;
import util.parsing.MatchParsing_v2;
import util.parsing.TeamParsing;
import vo.PlayVo;
import vo.TeamVo;

public class ServicePartyimpl implements PartyServiceInterface {

	PartyDaoInerface team_dao, play_dao, parsing_second_dao;
	MatchParsing_v2 match_parsing = new MatchParsing_v2();
	TeamParsing team_parsing = new TeamParsing();

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
		//System.out.println(def + "밀리초 지났습니다. (파싱까지 대기)");
		if (def >= (long) (Myconst.ParsingDateCheck.ONE_HOUR_MILESECOND*Myconst.ParsingDateCheck.PARSING_INTERVAL)) {// 작동시작

			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			TeamVo[] team_vo = null;
			String rt1 = null, rt2 = null;

			int res = 0;
			if (month != 12 && month != 1) {
				rt1 = parsing_manager(String.valueOf(year), String.valueOf(month));
				rt2 = parsing_manager(String.valueOf(year), String.valueOf(month - 1));
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
			result =0;
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

}

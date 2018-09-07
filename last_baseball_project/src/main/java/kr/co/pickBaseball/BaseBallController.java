package kr.co.pickBaseball;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import myconst.Myconst;
import service.PartyServiceInterface;
import service.ServiceTeamimpl;
import util.MatchParsing_v2;
import util.TeamParsing;
import vo.PlayVo;
import vo.TeamVo;

@Controller
public class BaseBallController {
	PartyServiceInterface partyService;





	public PartyServiceInterface getPartyService() {
		return partyService;
	}

	public void setPartyService(PartyServiceInterface partyService) {
		this.partyService = partyService;
	}

	@RequestMapping("/parsing_match.do")
	@ResponseBody
	public String parsing_match(String pwd, String year,String month) throws Exception {
		System.out.println("파싱매치");
		if (pwd == null||year==null||month==null)
			return "fail";
		if (!pwd.equals("inca1234"))
			return "fail";
		int year_int = Integer.parseInt(year);
		int month_int = Integer.parseInt(month);
		if(year_int != Myconst.DateCheck.year)
			return "fail:현재 년도를 입력해주세요";
		
		MatchParsing_v2 parsing = new MatchParsing_v2();
		PlayVo []vo = parsing.matchParsing(month_int, year_int);
		
		if(vo.length == 0)
			return "fail : 파싱 결과 없음"	;
		
		Map map = new HashMap();
		map.put("year", year);
		map.put("month", String.format("%02d",month_int));
		
		int res = partyService.match_update(vo,map);
		
		if(res == -1) {
			System.out.println("play 불러오는 것 오류발생");
		}
		
		return "success";
	}

	@RequestMapping("/parsing_match_all.do")
	@ResponseBody
	public String parsing_match_all(String pwd) throws Exception {
		System.out.println("파싱매치 올");
		if (pwd == null)
			return "fail";
		if (!pwd.equals("inca1234"))
			return "fail";
		
		

		return "success";
	}

	@RequestMapping("/parsing.do")
	@ResponseBody
	public String parsing() throws IOException {
		System.out.println("파싱두");
		TeamVo[] vo = new TeamParsing().parsing_url();

		partyService.team_update(vo);

		return "main.jsp";
	}

	@RequestMapping("/join.do")
	public String join() {

		return Myconst.BaseBall.BASEBALL_DIR + "join_form.jsp";

	}

	@RequestMapping("/login.do")
	public String login() {

		return Myconst.BaseBall.BASEBALL_DIR + "login_form.jsp";

	}

}

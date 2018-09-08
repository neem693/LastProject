package kr.co.pickBaseball;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import myconst.Myconst;
import service.PartyServiceInterface;
import util.parsing.TeamParsing;
import vo.TeamVo;

@Controller
public class BaseBallController {
	PartyServiceInterface partyService;

	@Autowired
	HttpServletRequest request;

	public PartyServiceInterface getPartyService() {
		return partyService;
	}

	public void setPartyService(PartyServiceInterface partyService) {
		this.partyService = partyService;
	}

	@ModelAttribute
	// 맵핑을 하기전에 필터로 접근하듯
	// 이것도 맵핑을 접근하기 전에 기본적으로 하는 것
	public void default_controller() {

		// String addr = request.getRequestURI();
		// System.out.println("출력된다.이건 언제 어디서나 디폴트로 출력된다. :" + addr);
		System.out.println("파싱실행");
		int res = partyService.check_parsing();
		if (res == 1)
			System.out.println("모든 파싱 완료");
		else if (res == 0)
			System.out.println("지금은 파싱을 할 수 없습니다.(맞는 월이 아님, n시간 카운트가 안지남)");
		else if (res == -1)
			System.out.println("파싱 오류발생");
		
		System.out.println("----------파싱작업끝----------");

	}

	@RequestMapping("/parsing_match.do")
	@ResponseBody
	public String parsing_match(String pwd, String year, String month) throws Exception {

		System.out.println("파싱매치");
		if (pwd == null || year == null || month == null)
			return "fail";
		if (!pwd.equals("inca1234"))
			return "fail";
		// int year_int = Integer.parseInt(year);
		// int month_int = Integer.parseInt(month);
		if (Integer.parseInt(year) != Myconst.ParsingDateCheck.YEAR)
			return "fail:현재 년도를 입력해주세요";

		String return_msg = partyService.parsing_manager(year, month);

		return return_msg;
	}

	@RequestMapping("/parsing.do")
	@ResponseBody
	public String parsing() throws IOException {
		System.out.println("파싱두");
		TeamVo[] vo = new TeamParsing().parsing_url();

		partyService.team_update(vo);

		return "success team parsing";
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

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
	// ������ �ϱ����� ���ͷ� �����ϵ�
	// �̰͵� ������ �����ϱ� ���� �⺻������ �ϴ� ��
	public void default_controller() {

		// String addr = request.getRequestURI();
		// System.out.println("��µȴ�.�̰� ���� ��𼭳� ����Ʈ�� ��µȴ�. :" + addr);
		System.out.println("�Ľ̽���");
		int res = partyService.check_parsing();
		if (res == 1)
			System.out.println("��� �Ľ� �Ϸ�");
		else if (res == 0)
			System.out.println("������ �Ľ��� �� �� �����ϴ�.(�´� ���� �ƴ�, n�ð� ī��Ʈ�� ������)");
		else if (res == -1)
			System.out.println("�Ľ� �����߻�");
		
		System.out.println("----------�Ľ��۾���----------");

	}

	@RequestMapping("/parsing_match.do")
	@ResponseBody
	public String parsing_match(String pwd, String year, String month) throws Exception {

		System.out.println("�Ľ̸�ġ");
		if (pwd == null || year == null || month == null)
			return "fail";
		if (!pwd.equals("inca1234"))
			return "fail";
		// int year_int = Integer.parseInt(year);
		// int month_int = Integer.parseInt(month);
		if (Integer.parseInt(year) != Myconst.ParsingDateCheck.YEAR)
			return "fail:���� �⵵�� �Է����ּ���";

		String return_msg = partyService.parsing_manager(year, month);

		return return_msg;
	}

	@RequestMapping("/parsing.do")
	@ResponseBody
	public String parsing() throws IOException {
		System.out.println("�Ľ̵�");
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

package kr.co.pickBaseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import myconst.Myconst;
import service.PartyServiceInterface;
import service.member.MemberServiceInterface;
import util.parsing.DateUtil;
import util.parsing.TeamParsing;
import vo.MemberVo;
import vo.TeamVo;

@Controller
public class BaseBallController {
	PartyServiceInterface partyService;
	
	//ȸ������ ���� ȣ�� ��ü ( 3�ܰ� ����)
	MemberServiceInterface memberservice;
	
	@Autowired
	HttpServletRequest request;

	public MemberServiceInterface getMemberservice() {
		return memberservice;
	}

	public void setMemberservice(MemberServiceInterface memberservice) {
		this.memberservice = memberservice;
	}

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

		return Myconst.Member.MEMBER_DIR + "member_join_form.jsp";

	}

	@RequestMapping("/login.do")
	public String login() {



		return Myconst.Member.MEMBER_DIR + "login.jsp";

	}

	@RequestMapping("/test_list.do")
	public String test_list(Model model) {
	
		List<MemberVo> list=null;
		list=memberservice.selectList();
		model.addAttribute("list",list);
		return Myconst.Member.MEMBER_DIR + "testlist.jsp";
	}
	
	@RequestMapping("/test_insert.do")
	public String member_insert(MemberVo vo) {
	
	vo.setM_ip(request.getRemoteAddr());
		memberservice.insert(vo);
		return "test_list.do";
	}
	
	@RequestMapping("/test_delete_list.do")
	public String member_delete(int m_idx) {	
		memberservice.delete(m_idx);
		return "test_list.do";
	}
	
	@RequestMapping("/test_member_modify_form.do")
	public String modify_form(int m_idx,Model model) {	
		
		MemberVo vo= memberservice.selectOne(m_idx);
		model.addAttribute("vo",vo);
		return Myconst.Member.MEMBER_DIR + "member_modify_form.jsp";
	}
	
	@RequestMapping("/test_modify.do")
	public String modify(MemberVo vo) {	
		memberservice.update(vo);	
		return "test_list.do";
	}
	
	@RequestMapping("/check_id.do")
	@ResponseBody
	public String check_id(String m_id) {	
		//jsonŸ���� ����ϱ� ���� viwe�� ��ġ�� �ʰ� �ٷ�����Ѵ� @responseBody
		String json=memberservice.selectOne(m_id);
		return json;
	}
	
	@RequestMapping("/check_nick.do")
	@ResponseBody
	public String check_nick(String m_nick) {	
		//�ʹ��
		
		Map  map = new HashMap();
	  		 map.put("m_nick", m_nick);
		
		String json=memberservice.selectOne(map);
		return json;
	}
	
	@RequestMapping("/photo_upload.do")
	@ResponseBody
	public String photo_up(MultipartHttpServletRequest multi) {	
		
		String file_name=memberservice.photo_upload(multi);
	

		return file_name;
	}
	
	@RequestMapping("/parsing_test.do")
	@ResponseBody
	public String parsing1() throws IOException {	
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date match_day;
		Date today = new Date();
		DateUtil dateUtil = new DateUtil();

		String month_str = "08";

		String year_str = "2018";
		//System.out.println(month_str);
		//System.out.println(year_str);

		// System.out.println(month_str);
		String str_url = "https://www.koreabaseball.com/ws/Schedule.asmx/GetScheduleList?gameMonth=" + month_str
				+ "&leId=1&seasonId=" + year_str + "&srIdList=0,9&teamId=";
		URL url = new URL(str_url);

		// System.out.println(str_url);

		// System.out.println(new Date().toString());

		InputStream is = url.openStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");

		BufferedReader br = new BufferedReader(isr);

		StringBuffer sb = new StringBuffer();
		while (true) {
			String str = br.readLine();

			if (str == null)
				break;
			sb.append(str);

		}

		String json_str = sb.toString();

		//System.out.println(json_str);
		JSONObject jb = new JSONObject(json_str);
		//json ������ text�� JSONOBJECT�� ��ü�� �����ϰ� �Ǹ� KEY ,VALUE ���·� ���� �аų� �з��� �� �ִ�.
		
		System.out.println(json_str);
		JSONArray rows = jb.getJSONArray("rows");//json �����Ϳ��� key�� rows�� ���� �����ִ� �迭�� �����´�.
		System.out.println(rows.toString());
		
		
	

	
		
		return "ok";
	
	
	
	
	
	
	}
	
	
	
}

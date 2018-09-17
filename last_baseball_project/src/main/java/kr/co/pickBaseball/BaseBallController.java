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
	
	//회원가입 서비스 호출 객체 ( 3단계 구조)
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
		//json타입을 출력하기 위해 viwe를 거치지 않고 바로출력한다 @responseBody
		String json=memberservice.selectOne(m_id);
		return json;
	}
	
	@RequestMapping("/check_nick.do")
	@ResponseBody
	public String check_nick(String m_nick) {	
		//맵방식
		
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
		//json 구조의 text를 JSONOBJECT로 객체를 생성하게 되면 KEY ,VALUE 형태로 값을 읽거나 분류할 수 있다.
		
		System.out.println(json_str);
		JSONArray rows = jb.getJSONArray("rows");//json 데이터에서 key가 rows인 값을 갖고있는 배열을 가져온다.
		System.out.println(rows.toString());
		
		
	

	
		
		return "ok";
	
	
	
	
	
	
	}
	
	
	
}

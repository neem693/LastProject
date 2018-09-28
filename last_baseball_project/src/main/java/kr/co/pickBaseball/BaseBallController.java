package kr.co.pickBaseball;

import java.io.IOException;

import java.util.HashMap;

import java.util.Calendar;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import myconst.Myconst;
import myconst.Paging;
import service.comment.CommentServiceInterface;
import service.member.MemberServiceInterface;
import service.normal.NormalServiceImpl;
import service.normal.NormalServiceInterface;
import service.party.PartyServiceInterface;

import util.parsing.TeamParsing;
import vo.CommentVo_normal;
import vo.MemberVo;
import vo.NormalVo;
import vo.TeamVo;

@Controller
public class BaseBallController {
	
	
	NormalServiceInterface normalService;
	
	
	PartyServiceInterface partyService;
	
	CommentServiceInterface commentservice;
	
	public CommentServiceInterface getCommentservice() {
		return commentservice;
	}

	public void setCommentservice(CommentServiceInterface commentservice) {
		this.commentservice = commentservice;
	}

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

	public NormalServiceInterface getNormalService() {
		return normalService;
	}

	public void setNormalService(NormalServiceInterface normalService) {
		this.normalService = normalService;
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
		
	  		 System.out.println(m_nick);
		String json=memberservice.selectOne(map);
		return json;
	}
	

	@RequestMapping("/party/party_list.do")
	public String party_list(String year, String month, Model model) {

		if (year == null && month == null) {
			year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
		}

		List list = partyService.take_play_list(year, month);
		Map map;
		// String cal = partyService.make_cal(list);
		model.addAttribute("list", list);
		map = partyService.getWeekday(year,month);
		// System.out.println(first_day);
		model.addAttribute("first_day", map.get("first_day"));
		model.addAttribute("last_day", map.get("last_day"));

		return Myconst.BaseBall.PARTY_DIR + "list.jsp";
	}
	
	@RequestMapping("/party/insert_party.do")
	public String insert_party() {
		
		System.out.println(Myconst.BaseBall.PARTY_DIR);
		return Myconst.BaseBall.PARTY_DIR + "party_create.jsp";
		
	}
	
	@RequestMapping("/normal/list.do")
	public String normal_list(Model model,Integer page,String nc_search,String nc_search_text) {
		
		List list = normalService.getList(page,nc_search,nc_search_text);
		
		model.addAttribute("list",list);
		//기억 해봐 여기주소
		return "/WEB-INF/views/normal/normal_list.jsp";
	}
	
	@RequestMapping("/normal/insert_form.do")
	public String normal_insertform() {
		
		return "/WEB-INF/views/normal/normal_insert_form.jsp";
		
	}
	
	@RequestMapping("/normal/insert.do")
	public String normal_insert(NormalVo vo, HttpServletRequest request,String editor) {
		
		normalService.insert(vo, request, editor);
		return "/WEB-INF/views/normal/normal_list.jsp";
	}
	
	@RequestMapping("/file_uploader_html5.do")
	@ResponseBody
	public String multiplePhotoUpload(HttpServletRequest request,StringBuffer sb) {
		
		normalService.file_up(request);
		return sb.toString();
	}
	
	@RequestMapping("/normal/view.do")
	public String normal_view(int nc_idx, HttpServletRequest request, Model model) {
		
		normalService.normal_view(nc_idx, request, model);
		NormalVo vo=	normalService.normal_view(nc_idx, request, model);
		model.addAttribute("vo",vo);
		
		return "/WEB-INF/views/normal/normal_view.jsp";
	}
	
	@RequestMapping("/normal/normal_modify_form.do")
	public String normal_modify_form(int nc_idx,NormalVo vo,Model model) {
		
		normalService.normal_modify_form(nc_idx, vo);
		
		model.addAttribute("vo",vo);
		
		return "/WEB-INF/views/normal/normal_modify.jsp";
	}
	
	@RequestMapping("/normal/normal_modify.do")
	public String normal_modify(String nc_title, String nc_contents, NormalVo vo) {
		
		normalService.normal_modify(nc_title, nc_contents, vo);
		
		return "/WEB-INF/views/normal/normal_list.jsp";
	}
	
	@RequestMapping("/normal/normal_delete.do")
	public String normal_delete(int nc_idx) {
	
		normalService.normal_delete(nc_idx);
		
		return "/WEB-INF/views/normal/normal_list.jsp";
	}
	
	@RequestMapping("/comment/normal_comment_list.do")
	public String normal_comment_list(Integer page, int nc_idx, Model model) {
		
		List list = commentservice.normal_comment_list(page, nc_idx);
		
		model.addAttribute("list", list);
		
		return "/WEB-INF/views/normal/normal_view.jsp" + "normal_comment_list.jsp";
	}
	
}

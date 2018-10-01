package kr.co.pickBaseball;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import myconst.Myconst;
import service.comment.CommentServiceInterface;
import service.member.MemberServiceInterface;
import service.normal.NormalServiceInterface;
import service.party.PartyServiceInterface;
import service.toto.TotoServiceInterface;
import util.parsing.TeamParsing;
import vo.MemberVo;
import vo.NormalVo;
import vo.PartyVo;
import vo.Party_bookVo;
import vo.PlayVo;
import vo.StadiumVo;
import vo.TeamVo;
import vo.Toto_Game_Vo;

@Controller
public class BaseBallController {

	@Autowired
	HttpSession session;
	@Autowired
	HttpServletRequest request;
	@Autowired
	ServletContext application;

	PartyServiceInterface partyService;

	// ȸ������ ���� ȣ�� ��ü ( 3�ܰ� ����)
	MemberServiceInterface memberservice;

	// ���� ���� ȣ�� ��ü
	TotoServiceInterface totoservice;

	NormalServiceInterface normalService;

	CommentServiceInterface commentservice_normal;

	public CommentServiceInterface getCommentservice_normal() {
		return commentservice_normal;
	}

	public void setCommentservice_normal(CommentServiceInterface commentservice_normal) {
		this.commentservice_normal = commentservice_normal;
	}

	public TotoServiceInterface getTotoservice() {
		return totoservice;
	}

	public void setTotoservice(TotoServiceInterface totoservice) {
		this.totoservice = totoservice;
	}

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
	// ������ �ϱ����� ���ͷ� �����ϵ�
	// �̰͵� ������ �����ϱ� ���� �⺻������ �ϴ� ��
	public void default_controller() {

		// String addr = request.getRequestURI();
		// System.out.println("��µȴ�.�̰� ���� ��𼭳� ����Ʈ�� ��µȴ�. :" + addr);
		System.out.println("�Ľ̽���");

		//// �� �۾��� �� ������� �� �� �ְ� ��ų��´�.
		//// �׷��� ������ �ѹ� �Ľ��� �� �������̼� �Ľ��� ���� �ְ�
		//// �̴� �ش� kbo ����Ʈ�� �𵵽� ���� �ϴ� ���̴�.
		synchronized (this) {
			int res = partyService.check_parsing();
			if (res == 1) {
				System.out.println("��� �Ľ� �Ϸ�");

				int update = partyService.updateParty();
				System.out.println(update + "�� party ���� ������Ʈ");
				try {
					String result=totoservice.MakeToToScore();
					System.out.println(result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			
			}
			else if (res == 0)
				System.out.println("������ �Ľ��� �� �� �����ϴ�.(�´� ���� �ƴ�, n�ð� ī��Ʈ�� ������)");
			else if (res == -1)
				System.out.println("�Ľ� �����߻�");
			System.out.println("----------�Ľ��۾���----------");
		}
	}

	@RequestMapping(value = "/parsing_match.do", produces = "text/plain;charset=UTF-8")
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
		//System.out.println("�Ľ̵�");
		TeamVo[] vo = new TeamParsing().parsing_url();

		partyService.team_update(vo);

		return "success team parsing";
	}

	@RequestMapping("/join.do")
	public String join() {

		return Myconst.Member.MEMBER_DIR + "member_join_form.jsp";

	}

	@RequestMapping("/member/login.do")
	public String login() {

		return Myconst.Member.MEMBER_DIR + "login.jsp";
	}

	@RequestMapping("/member/login_action.do")
	public String login_action(MemberVo vo) {

	//	System.out.println(vo.getM_id());
	//	System.out.println(vo.getM_pwd());
		MemberVo voo = memberservice.login_action(vo);

		if (voo == null)
			return "redirect:login.do?fail=" + Myconst.Login.USER_CANNOT_FIND;
		else
			session.setAttribute("user", voo);

		return "redirect:/main/main_list.do";
	}

	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/main/main_list.do";
	}

	@RequestMapping("/test_list.do")
	public String test_list(Model model) {

		List<MemberVo> list = null;
		list = memberservice.selectList();
		model.addAttribute("list", list);
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
	public String modify_form(int m_idx, Model model) {

		MemberVo vo = memberservice.selectOne(m_idx);
		model.addAttribute("vo", vo);
		return Myconst.Member.MEMBER_DIR + "member_modify_form.jsp";
	}

	@RequestMapping("/test_modify.do")
	public String modify(MemberVo vo) {
		memberservice.update(vo);
		return "test_list.do";
	}


	@RequestMapping("/check_login.do")
	@ResponseBody
	public String check_login() {

	MemberVo vo=(MemberVo)session.getAttribute("user");
	
	if(vo==null) {
		return "fail";
	}
	else return "ok";
	
	
	}

	

	@RequestMapping("/check_id.do")
	@ResponseBody
	public String check_id(String m_id) {
		// jsonŸ���� ����ϱ� ���� viwe�� ��ġ�� �ʰ� �ٷ�����Ѵ� @responseBody
		String json = memberservice.selectOne(m_id);
		return json;
	}

	@RequestMapping("/check_nick.do")
	@ResponseBody
	public String check_nick(String m_nick) {
		// �ʹ��

		Map map = new HashMap();
		map.put("m_nick", m_nick);

		System.out.println(m_nick);
		String json = memberservice.selectOne(map);
		return json;
	}

	@RequestMapping(value = "/photo_upload.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String photo_up(MultipartHttpServletRequest multi) {

		String file_name = memberservice.photo_upload(multi);

		return file_name;
	}


	@RequestMapping("/parsing_toto.do")
	public String parsing_toto(Model model) throws IOException {
		// Jsoup lib�� ����Ͽ� HTML ������ �Ľ��Ѵ�.
		// batmen--toto ���� �Ĺ�
		String result = totoservice.MakeToToScore();

		System.out.println(result);
		return Myconst.Toto.TOTO + "toto_game.jsp";
	}


	


	@RequestMapping("/party/party_list.do")
	public String party_list(String year, String month, Model model, String team) {

		if (year == null && month == null) {
			year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
		}

		if (team == null) {

			MemberVo member = (MemberVo) session.getAttribute("user");
			if (member != null)
				team = member.getT_name();
		} else if (team.isEmpty()||team.equals("team_list_all"))
			team = null;

		List list = partyService.take_play_list(year, month);
		List team_list = partyService.get_team_rank();// ������ ���̵忡 �� �޴��� �� ��ũ�� ����ؼ� ����� ���̱� ������
		Map party_count = partyService.get_party_count(year, month, team);
		Map map;
		// String cal = partyService.make_cal(list);
		model.addAttribute("list", list);
		model.addAttribute("month", month);
		model.addAttribute("year", year);

		model.addAttribute("t_list", team_list);

		map = partyService.getWeekday(year, month);
		// System.out.println(first_day);
		model.addAttribute("today", map.get("today"));
		model.addAttribute("this_year", map.get("this_year"));
		model.addAttribute("this_month", map.get("this_month"));
		model.addAttribute("this_day",map.get("this_day"));
		model.addAttribute("first_day", map.get("first_day"));
		model.addAttribute("last_day", map.get("last_day"));
		model.addAttribute("party_count", party_count);
		model.addAttribute("team", team);
		

		System.out.println(team);

		return Myconst.BaseBall.PARTY_DIR + "list.jsp";
	}

	@RequestMapping("/party/insert_party.do")
	public String insert_party(String year, String month, String day, Model model) {

		MemberVo vo = (MemberVo) session.getAttribute("user");
		if (vo == null)
			return "redirect:/member/login.do?fail=" + Myconst.Login.YOU_MUST_LOGIN;

		if (year == null || month == null || day == null)
			return "redirect:/party/party_list.do?fail=not_found";

		// ��Ƽ�� ����ϱ� ���� ���� ���� �ش� ��Ƽ�� �ִ��� ������ �˻��غ���.
		int month_int = Integer.parseInt(month);
		int day_int = Integer.parseInt(day);
		String ymd = String.format("%s%02d%02d", year, month_int, day_int);
		int res = partyService.member_joined_today(ymd, vo);
		if (res >= 1) {
			model.addAttribute("year", year);
			model.addAttribute("month", month);
			model.addAttribute("day", day);
			model.addAttribute("fail", "joined");

			return "redirect:/party/party_list.do";
		}

		// int year_int = String.valueOf(year);
		// int month_int = String.valueOf(month);
		// int day_int = String.valueOf(day);

		List match_list = partyService.take_play_list(year, month, day);
		if (match_list == null || match_list.size() == 0)
			return "redirect:/party/party_list.do?fail=" + Myconst.Login.USER_CANNOT_FIND;
		/////// ��¥�� ���� �� ���� ������ ��� ��¥�� ������ �ִ���//////
		boolean long_promise = partyService.check_long_time_in_match(year, month, day);

		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		model.addAttribute("match_list", match_list);
		model.addAttribute("is_long", long_promise);

		// System.out.println(long_promise);

		return Myconst.BaseBall.PARTY_DIR + "party_create.jsp";

	}

	@RequestMapping("/main/main_list.do")
	public String insert_form(Model model) {
		List list = partyService.get_team_rank();
		model.addAttribute("ranking", list);

		return myconst.Myconst.Main.VIEW_PATH + "main_list.jsp";
	}

	@RequestMapping(value = "/party/select_stadium_team.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String select_stadium(String p_idx) {

		String stadium_team = partyService.selectStadium(p_idx);
		String result = String.format("[{'result':'%s'}]", stadium_team);

		return result;
	}

	@RequestMapping(value = "/party_image_upload.do", method = RequestMethod.POST)
	public void ckeditor_image_upload(@RequestParam MultipartFile upload, HttpServletResponse response)
			throws Exception {
		OutputStream out = null;
		PrintWriter printWriter = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			String web_path = "/resources/party_upload/";
			String abs_path = application.getRealPath(web_path);
			// String uploadPath = "������/" + fileName;//������
			File dir = new File(abs_path);
			System.out.println(dir.mkdirs());
			File f = new File(abs_path, fileName);

			// ����ȭ���� �ִ°��
			System.out.println("�׽�Ʈ " + abs_path + fileName);
			if (f.exists()) {
				long time = System.currentTimeMillis();
				int index = fileName.lastIndexOf('.');
				String f_name = fileName.substring(0, index);
				String f_ext = fileName.substring(index);
				fileName = String.format("%s_%d%s", f_name, time, f_ext);
				f = new File(abs_path, fileName);
			}
			out = new FileOutputStream(f);
			out.write(bytes);
			String callback = request.getParameter("CKEditorFuncNum");
			printWriter = response.getWriter();
			String url = request.getRequestURL().toString().replaceAll("/party_image_upload.do", "");
			// System.out.println(url);
			String fileUrl = url + web_path + fileName;// url���
			printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",'" + fileUrl + "','�̹����� ���ε� �Ͽ����ϴ�.'" + ")</script>");
			printWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping("/party/insert_party_one.do")
	public String insert_party_one(PartyVo vo, MemberVo voo, String year, String month, String day) {

		MemberVo member = memberservice.selectOne_id_idx(voo);
		if (member == null)
			return "redirect:/member/member_list.do?fail=" + Myconst.Login.ERROR;
		int res = partyService.insert_party(vo, year, month, day);
		if (res == 1)
			res = partyService.insert_party_book(member);

		return "redirect:party_list.do";
	}

	@RequestMapping("/party/show_party_list.do")
	public String show_party_list(String year, String month, String day, String team, Model model, String page) {

		System.out.println("�� ��Ƽ ����Ʈ ��");
		int nowPage = 1;
		if ((page == null || page.isEmpty()) == false) {
			nowPage = Integer.parseInt(page);
		}

		int page_total_count = partyService.total_page_count(year, month, day, team);
		String page_html = partyService.return_party_paging(nowPage, day, page_total_count);
		List list = partyService.take_party_list(year, month, day, team, nowPage);
		System.out.println(list.size());

		model.addAttribute("list", list);
		model.addAttribute("page_html", page_html);

		return Myconst.BaseBall.PARTY_DIR + "show_party_list.jsp";

	}


	@RequestMapping("/normal/list.do")
	public String normal_list(Model model, Integer page, String nc_search, String nc_search_text) {

		List list = normalService.getList(page, nc_search, nc_search_text);

		model.addAttribute("list", list);
		// ��� �غ� �����ּ�
		return "/WEB-INF/views/normal/normal_list.jsp";
	}

	@RequestMapping("/normal/insert_form.do")
	public String normal_insertform() {

		return "/WEB-INF/views/normal/normal_insert_form.jsp";

	}

	@RequestMapping("/normal/insert.do")
	public String normal_insert(NormalVo vo, HttpServletRequest request, String editor) {

		normalService.insert(vo, request, editor);
		return "/WEB-INF/views/normal/normal_list.jsp";
	}

	@RequestMapping("/file_uploader_html5.do")
	@ResponseBody
	public String multiplePhotoUpload(HttpServletRequest request, StringBuffer sb) {

		normalService.file_up(request);
		return sb.toString();
	}

	@RequestMapping("/normal/view.do")
	public String normal_view(int nc_idx, HttpServletRequest request, Model model) {

		normalService.normal_view(nc_idx, request, model);
		NormalVo vo = normalService.normal_view(nc_idx, request, model);
		model.addAttribute("vo", vo);

		return "/WEB-INF/views/normal/normal_view.jsp";
	}

	@RequestMapping("/normal/normal_modify_form.do")
	public String normal_modify_form(int nc_idx, NormalVo vo, Model model) {

		normalService.normal_modify_form(nc_idx, vo);

		model.addAttribute("vo", vo);

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

		List list = commentservice_normal.normal_comment_list(page, nc_idx);

		model.addAttribute("list", list);

		return "/WEB-INF/views/normal/normal_view.jsp" + "normal_comment_list.jsp";
	}

	@RequestMapping("/party/view.do")
	public String party_view(String year, String month, String day, String team, String pt_idx, Model model) {

		int month_int, day_int, pt_idx_int;
		month_int = day_int = pt_idx_int = 0;

		if (pt_idx == null || pt_idx.isEmpty())
			return "redirect:/main/main_list.do";
		try {
			month_int = Integer.parseInt(month);
			day_int = Integer.parseInt(day);
			pt_idx_int = Integer.parseInt(pt_idx);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/main/main_list.do";
		}
		Party_bookVo party_leader = partyService.getPartyLeader(pt_idx_int);
		List party_member = partyService.getPartyMember(pt_idx_int);// ��������
		PartyVo party = partyService.selectPartyOne(pt_idx_int);
		PlayVo play = partyService.select_play_one(party.getP_idx());
		StadiumVo stadium = partyService.select_stadium_one(play);
		int leaderCount = partyService.getleaderCount(party_leader.getM_idx());

		party.setP_date(play.getP_date());
		party = partyService.setting_datetime(party);

		/* System.out.println(leaderCount); */
		model.addAttribute("party_leader", party_leader);
		model.addAttribute("party_member", party_member);
		model.addAttribute("party", party);
		model.addAttribute("play", play);
		model.addAttribute("stadium", stadium);
		model.addAttribute("leader_count", leaderCount);

		return Myconst.BaseBall.PARTY_DIR + "party_view.jsp";
	}

	@RequestMapping("/party/party_join.do")
	public String party_join(String year, String month, String day, String team, String pt_idx, Model model) {
		MemberVo member = (MemberVo) session.getAttribute("user");
		if (member == null)
			return "redirect:/member/login.do?fail=" + Myconst.Login.ERROR;

		try {
			int res = partyService.set_join_member_to_party(member, pt_idx);
			if (res == -1)
				model.addAttribute("fail", "partyFull");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("fail", e.getMessage());
			// ������� �ִ� ���� ���
			// joined
			// partyClosed
			// partyFull
			// unknownError_join

		}

		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("team", team);
		model.addAttribute("pt_idx", pt_idx);

		return "redirect:/party/view.do";

	}

	@RequestMapping("/party/party_leave.do")
	public String party_leave(String year, String month, String day, String team, String pt_idx, Model model) {

		MemberVo member = (MemberVo) session.getAttribute("user");
		if (member == null)
			return "redirect:/member/login.do?fail=" + Myconst.Login.ERROR;

		try {
			int res = partyService.member_leave_from_party(member, pt_idx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("fail", e.getMessage());
			// �߻������� ������
			// userError -�ش� �ϴ� ����� 2���̻� �ǰų�, �ش��ϴ� ����� ���� �� ���´�.
			// cantDeleteLeader - ������ ��Ƽ���� ������������ ���Ѵ�.
			// unknownError_leave - ��Ƽ �������� �˼����� ����

		}

		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("team", team);
		model.addAttribute("pt_idx", pt_idx);

		return "redirect:/party/view.do";
	}


	@RequestMapping("/bat_game.do")
	public String bat_game(Model model,HttpServletRequest request){
		
	//�ֱ������� �Ķ���͸��� ���ϱ⶧����(p_idx�͵����ϹǷ�) ������ ó�����ش�.
	totoservice.Make_game(request);//(���ӻ���)
	  
	String bat_price=request.getParameter("bat_price");//(�����Ϸ��� ���ñ� ó��)
	String m_idx=request.getParameter("m_idx");//(�����Ϸ��� ���ñ� ó��)
    
	MemberVo m_vo = memberservice.selectOne(Integer.parseInt(m_idx));
	
	int bat_p=Integer.parseInt(bat_price);
	bat_p=Integer.parseInt(m_vo.getM_money())-bat_p;
	m_vo.setM_money(Integer.toString(bat_p));
	
	totoservice.update_money(m_vo);
	
  
	System.out.println("batting good");	
	return "toto_view.do";		
	}
	
	@RequestMapping("/game_result.do")
	public String game_result(Model model,HttpServletRequest request){
	
		
		String m_id="player";
		//����ڰ� ������ ���� �������� (��ó���� ����)		
		totoservice.Game_Result(m_id);	
			
		System.out.println("key load good");	
		return "toto_view.do";		
	}
	
	
	@RequestMapping("/toto_view.do")
	public String view(Model model,HttpServletRequest request){
		//���� ������ ��� 
		
		MemberVo vo=(MemberVo)session.getAttribute("user");
		
		if(vo==null) {
			return "redirect:member/login.do";
		}
		

		List list=totoservice.Select_gamelist();
		model.addAttribute("list",list);
		
		List my_bat_list=totoservice.my_bat_gamelist(vo);
		model.addAttribute("my_bat_list",my_bat_list);
		
		vo= totoservice.my_money_read(vo);
		
		model.addAttribute("member",vo);
		return Myconst.Toto.TOTO+"toto_game.jsp";
	}

	
	
	
	
	

}

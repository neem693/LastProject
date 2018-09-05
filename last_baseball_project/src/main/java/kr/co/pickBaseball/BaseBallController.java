package kr.co.pickBaseball;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import myconst.Myconst;
import service.ServiceInterface;
import service.ServiceTeamimpl;
import util.TeamParsing;
import vo.TeamVo;

@Controller
public class BaseBallController {
	ServiceTeamimpl service;

	public ServiceTeamimpl getService() {
		return service;
	}

	public void setService(ServiceTeamimpl service) {
		this.service = service;
	}


	@RequestMapping("/parsing.do")
	@ResponseBody
	public String parsing() throws IOException {
		System.out.println("ÆÄ½ÌµÎ");
		TeamVo [] vo = new TeamParsing().parsing_url();
		
		service.update(vo);
		
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

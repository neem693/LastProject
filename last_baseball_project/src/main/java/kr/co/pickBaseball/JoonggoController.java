package kr.co.pickBaseball;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.ServiceTeamimpl;

@Controller
public class JoonggoController {

@RequestMapping("/joonggo/insert_form.do")
public String insert_form()
{
	return myconst.Myconst.Joonggo.VIEW_PATH + "joonggo_insert_form.jsp";
}

@RequestMapping("/joonggo/list.do")
public String list()
{
	return myconst.Myconst.Joonggo.VIEW_PATH + "joonggo_list.jsp";
}
}

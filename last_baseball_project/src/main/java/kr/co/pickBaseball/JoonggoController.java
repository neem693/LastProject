package kr.co.pickBaseball;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JoonggoController {

@RequestMapping("/insert_form.do")
public String insert_form()
{
	return myconst.Myconst.Joonggo.VIEW_PATH + "joonggo_insert_form.jsp";
}

@RequestMapping("/list.do")
public String list()
{
	return myconst.Myconst.Joonggo.VIEW_PATH + "joonggo_list.jsp";
}

@RequestMapping("/file.do")
public String file()
{
return myconst.Myconst.Joonggo.VIEW_PATH + "NewFile.jsp";	
}
}

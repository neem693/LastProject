package kr.co.pickBaseball;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.JoonggoDao;
import vo.JoonggoVo;

@Controller
public class JoonggoController {
	
	JoonggoDao joonggo_dao;

public JoonggoDao getJoonggo_dao() {
		return joonggo_dao;
	}

	public void setJoonggo_dao(JoonggoDao joonggo_dao) {
		this.joonggo_dao = joonggo_dao;
	}

@RequestMapping("/insert_form.do")
public String insert_form()
{
	return myconst.Myconst.Joonggo.VIEW_PATH + "joonggo_insert_form.jsp";
}


@RequestMapping("/file.do")
public String file()
{
return myconst.Myconst.Joonggo.VIEW_PATH + "NewFile.jsp";	
}

@RequestMapping("/list.do")
public String list(String search, String search_text, Model model)
{
	JoonggoVo vo = new JoonggoVo();
	
	if(search!=null && search.equals("all"))
	{
		if(search.equals("title_content"))// 이름 + 내용
		{
			vo.setJ_title(search_text);
			vo.setJ_content(search_text);
		}
		else if(search.equals("title")) //이름
		{
			vo.setJ_title(search_text);
		}
		else if(search.equals("nick")) // 내용
		{
			vo.setM_nick(search_text);
		}
	}
	
	List<JoonggoVo> list = joonggo_dao.selectList();
	
	model.addAttribute("list", list);
	
	return myconst.Myconst.Joonggo.VIEW_PATH + "joonggo_list.jsp";
}
}

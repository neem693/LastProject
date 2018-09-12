package kr.co.pickBaseball;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MainController {

	@RequestMapping("/main/main_list.do")
	public String insert_form() {
		return myconst.Myconst.Main.VIEW_PATH + "main_list.jsp";
	}
}
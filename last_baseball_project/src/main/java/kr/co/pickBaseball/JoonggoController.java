package kr.co.pickBaseball;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JoonggoController {

	/*smart_editor2*/
	@RequestMapping("/submit")
	public void submit(HttpServletRequest request){
	    System.out.println("¿¡µğÅÍ ÄÁÅÙÃ÷°ª:"+request.getParameter("editor"));
	}

}

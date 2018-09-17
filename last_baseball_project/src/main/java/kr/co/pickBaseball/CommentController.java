package kr.co.pickBaseball;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.CommentDao;
import vo.CommentVo;

@Controller
public class CommentController {

	CommentDao comment_dao;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	

	public CommentDao getComment_dao() {
		return comment_dao;
	}

	public void setComment_dao(CommentDao comment_dao) {
		this.comment_dao = comment_dao;
	}
	
	@RequestMapping("/comment_list.do")
	public String comment_list(Model model, int j_idx)
	{
		//board/comment_list.do?b_idx=3
	

		
		List<CommentVo> list = comment_dao.selectList(j_idx);
				
		model.addAttribute("list", list);
				
				
				//forward시킬 페이지
				String forward_page = "comment_list.jsp";
				RequestDispatcher disp = request.getRequestDispatcher(forward_page);
				disp.forward(request, response);

		
	}
}

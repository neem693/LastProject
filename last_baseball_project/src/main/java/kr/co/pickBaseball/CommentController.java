package kr.co.pickBaseball;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.CommentDao;
import util.Paging;
import vo.CommentVo;

@Controller
public class CommentController {

	CommentDao comment_dao;
	
	public CommentDao getComment_dao() {
		return comment_dao;
	}

	public void setComment_dao(CommentDao comment_dao) {
		this.comment_dao = comment_dao;
	}
	
	@Autowired
	HttpServletRequest request;
	
	
	
	@RequestMapping("/comment/comment_list.do")
	public String comment_list(Model model, int j_idx, Integer page)
	{
		
      //int b_idx = Integer.parseInt(request.getParameter("b_idx"));
		
		int nowPage=1;
		String strPage = request.getParameter("page");
		if(strPage!=null && !strPage.isEmpty())
			nowPage = Integer.parseInt(strPage);
		
		//결정된 page에 따라서 start,end계산
		int start = (nowPage-1) * myconst.Myconst.JoonggoPage.BLOCK_PAGE + 1;
		int end   = start + myconst.Myconst.JoonggoPage.BLOCK_LIST - 1;
		
		//mybatis mapper에 전달하기 위해서 Map을 포장
		Map  map = new HashMap();
		//       key    value
		map.put("start", start);
		map.put("end"  , end);
		map.put("j_idx", j_idx);
		
		
		//게시글별 페이징별
		List<CommentVo> list = comment_dao.selectList(map);
		
		//페이징 메뉴을 생성하기위한정보
		int rowTotal = comment_dao.selectRowTotal(j_idx);
		
		
		String pageMenu = Paging.getCommentPaging(  nowPage, 
									                rowTotal, 
									                myconst.Myconst.JoonggoPage.BLOCK_LIST, 
									                myconst.Myconst.JoonggoPage.BLOCK_PAGE
									                );		
		//System.out.println(pageMenu);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
		
/*		//forward시킬 페이지
		String forward_page = "comment_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
		List<CommentVo> vo = comment_dao.selectList(j_idx);
		
		
		model.addAttribute("vo", vo);*/
		
		
/*		//forward시킬 페이지
				String forward_page = "comment_list.jsp";
				RequestDispatcher disp = request.getRequestDispatcher(forward_page);
				disp.forward(request, response);*/
		//forward시킬 페이지 : DispatcherServlet에서 해줌
		
		return myconst.Myconst.Comment.VIEW_PATH + "comment_list.jsp";
	}
	
	@RequestMapping("/comment/comment_insert.do")
	public String comment_insert(CommentVo vo, Model model, String result_json)
	{
		
		String ip = request.getRemoteAddr();
		vo.setC_ip(ip);
		
/*		int b_idx = Integer.parseInt(request.getParameter("b_idx"));
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		
		String ip = request.getRemoteAddr();
		
		//포장
		CommentVo vo = new CommentVo(b_idx, id, name, content, ip );

		//댓글 추가
		int res = CommentDao.getInstance().insert(vo);
		
		String result = "success";
		
		if(res==0)
			result = "fail";
		
		// JSON 전송
		String result_json = String.format("{\"result\":\"%s}", result);
        response.setContentType("text/hteml; charset=utf-8;");
        response.getWriter().print(result_json);*/
		
		model.addAttribute("vo", vo);
		
		int res = comment_dao.insert(vo);
		
	    String result = "success";
		
		if(res==0)
			result = "fail";

		result_json = String.format("{\"result\":\"%s}", result);
		 
		return result_json;
		
	}
	@RequestMapping("/comment/comment_delete.do")
	public String comment_delete(int c_idx, String result_json)
	{
     /*int idx = Integer.parseInt(request.getParameter("idx"));
		
	
		String result = "success";
		
		if(res==0)
			result = "fail";
		
		// JSON 전송
        response.getWriter().print(result);*/
        int res = comment_dao.delete(c_idx);
        
        String result = "success";
		
		if(res==0)
			result = "fail";
/*		//JSON결과 전송
				String result_json = String.format("{\"result\":\"%s\"}", result);
				
				response.setContentType("text/html; charset=utf-8;");
				response.getWriter().print(result_json);*/
		
		result_json = String.format("{\"result\":\"%s\"}", result);
		return result_json;
	}
	
}

package kr.co.pickBaseball;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("/joonggo/comment_list.do")
	/*@ResponseBody*/
	public String comment_list(Model model, int j_idx, Integer page) 
	{
		
      //int b_idx = Integer.parseInt(request.getParameter("b_idx"));
		
		int nowPage=1;
		
		if(page!=null)
			nowPage = page;
		
		//결정된 page에 따라서 start,end계산
		int start = (nowPage-1) * myconst.Myconst.JoonggoPage.BLOCK_PAGE ;
		int end   = myconst.Myconst.JoonggoPage.BLOCK_LIST ;
		
		//mybatis mapper에 전달하기 위해서 Map을 포장
		Map  map = new HashMap();
		//       key    value
		map.put("start", start);
		map.put("end"  , end);
		map.put("j_idx", j_idx);
		
		
		//게시글별 페이징별
		List<CommentVo> list = comment_dao.selectList(map);
		
		//세션에 게시물을 봤냐에 대한정보(show)삭제
		session.removeAttribute("show");
		
		//페이징 메뉴을 생성하기위한정보
		int rowTotal = comment_dao.selectRowTotal(j_idx);
		
		String pageMenu = Paging.getCommentPaging(  nowPage, 
									                rowTotal, 
									                myconst.Myconst.JoonggoPage.BLOCK_LIST, 
									                myconst.Myconst.JoonggoPage.BLOCK_PAGE
									                );		
		//System.out.println(pageMenu);
		
		// model통해서 데이터를 DispatcherServlet에게 전달
	    // => DispatcherServlet이 request로 binding
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
					
	//forward시킬 페이지
		 /* String forward_page = "comment_list.jsp";
		  RequestDispatcher disp = request.getRequestDispatcher(forward_page);*/
		 
		//forward시킬 페이지 : DispatcherServlet에서 해줌
		
		return myconst.Myconst.Comment.VIEW_PATH + "comment_list.jsp";
	}
	
	@RequestMapping("/joonggo/comment_update_form.do")
	public String comment_update_form(int c_idx, Model model)
	{
		
		CommentVo vo = comment_dao.selectOne(c_idx);
		
		model.addAttribute("vo", vo);
	
		return myconst.Myconst.Comment.VIEW_PATH + "comment_update_form.jsp";
	}
	
	@RequestMapping("/joonggo/comment_insert.do")
    @ResponseBody //<=반환값을 요청클라이언트에게 바로 전송해라
	public String comment_insert(CommentVo vo, Model model)
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

		String result_json = String.format("{\"result\":\"%s\"}", result);
		 
		return result_json; // @ResponseBody : 현재 반환되는 값을 바로 응답시켜라
		
	}
	
	@RequestMapping("/joonggo/comment_delete.do")
	@ResponseBody
	public String comment_delete(int c_idx)
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
		
		String result_json = String.format("{\"result\":\"%s\"}", result);
		return result_json;
	}
	
}

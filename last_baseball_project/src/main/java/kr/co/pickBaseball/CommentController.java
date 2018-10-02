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
		
		//������ page�� ���� start,end���
		int start = (nowPage-1) * myconst.Myconst.JoonggoPage.BLOCK_PAGE ;
		int end   = myconst.Myconst.JoonggoPage.BLOCK_LIST ;
		
		//mybatis mapper�� �����ϱ� ���ؼ� Map�� ����
		Map  map = new HashMap();
		//       key    value
		map.put("start", start);
		map.put("end"  , end);
		map.put("j_idx", j_idx);
		
		
		//�Խñۺ� ����¡��
		List<CommentVo> list = comment_dao.selectList(map);
		
		//���ǿ� �Խù��� �óĿ� ��������(show)����
		session.removeAttribute("show");
		
		//����¡ �޴��� �����ϱ���������
		int rowTotal = comment_dao.selectRowTotal(j_idx);
		
		String pageMenu = Paging.getCommentPaging(  nowPage, 
									                rowTotal, 
									                myconst.Myconst.JoonggoPage.BLOCK_LIST, 
									                myconst.Myconst.JoonggoPage.BLOCK_PAGE
									                );		
		//System.out.println(pageMenu);
		
		// model���ؼ� �����͸� DispatcherServlet���� ����
	    // => DispatcherServlet�� request�� binding
		model.addAttribute("list", list);
		model.addAttribute("pageMenu", pageMenu);
					
	//forward��ų ������
		 /* String forward_page = "comment_list.jsp";
		  RequestDispatcher disp = request.getRequestDispatcher(forward_page);*/
		 
		//forward��ų ������ : DispatcherServlet���� ����
		
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
    @ResponseBody //<=��ȯ���� ��ûŬ���̾�Ʈ���� �ٷ� �����ض�
	public String comment_insert(CommentVo vo, Model model)
	{
		
		String ip = request.getRemoteAddr();
		vo.setC_ip(ip);
		
/*		int b_idx = Integer.parseInt(request.getParameter("b_idx"));
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		
		String ip = request.getRemoteAddr();
		
		//����
		CommentVo vo = new CommentVo(b_idx, id, name, content, ip );

		//��� �߰�
		int res = CommentDao.getInstance().insert(vo);
		
		String result = "success";
		
		if(res==0)
			result = "fail";
		
		// JSON ����
		String result_json = String.format("{\"result\":\"%s}", result);
        response.setContentType("text/hteml; charset=utf-8;");
        response.getWriter().print(result_json);*/
		
		model.addAttribute("vo", vo);
		
		int res = comment_dao.insert(vo);
		
	    String result = "success";
		
		if(res==0)
			result = "fail";

		String result_json = String.format("{\"result\":\"%s\"}", result);
		 
		return result_json; // @ResponseBody : ���� ��ȯ�Ǵ� ���� �ٷ� ������Ѷ�
		
	}
	
	@RequestMapping("/joonggo/comment_delete.do")
	@ResponseBody
	public String comment_delete(int c_idx)
	{
     /*int idx = Integer.parseInt(request.getParameter("idx"));
		
	
		String result = "success";
		
		if(res==0)
			result = "fail";
		
		// JSON ����
        response.getWriter().print(result);*/
        int res = comment_dao.delete(c_idx);
        
        String result = "success";
		
		if(res==0)
			result = "fail";
/*		//JSON��� ����
				String result_json = String.format("{\"result\":\"%s\"}", result);
				
				response.setContentType("text/html; charset=utf-8;");
				response.getWriter().print(result_json);*/
		
		String result_json = String.format("{\"result\":\"%s\"}", result);
		return result_json;
	}
	
}

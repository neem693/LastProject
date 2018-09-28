package service.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.comment.CommentDaoInterface;
import myconst.Myconst;
import myconst.Paging;
import vo.CommentVo_normal;

public class CommentServiceImpl implements CommentServiceInterface{

	CommentDaoInterface comment_dao;
	
	public CommentDaoInterface getComment_dao() {
		return comment_dao;
	}
	
	public void setComment_dao(CommentDaoInterface comment_dao) {
		this.comment_dao = comment_dao;
	}

	@Override
	public List normal_comment_list(Integer page, int nc_idx) {
		// TODO Auto-generated method stub
		
		int nowPage=1;
		
		if(page!= null)
			nowPage = page;
		
		int start = (nowPage-1) * Myconst.NormalCommentPageing.BLOCK_LIST + 1;
		int end = start + Myconst.NormalCommentPageing.BLOCK_LIST - 1;
		
		Map map = new HashMap();
		
		map.put("start", start);
		map.put("end", end);
		map.put("nc_idx", nc_idx);
		
		List<CommentVo_normal> list = comment_dao.normal_selectList(map);
		
		int rowTotal = comment_dao.normal_selectRowTotal(nc_idx);
		
		String pageMenu = Paging.getNormalCommentPaging(nowPage, rowTotal, Myconst.NormalCommentPageing.BLOCK_LIST, Myconst.NormalCommentPageing.BLOCK_PAGE);
		
		return list;
	}

	@Override
	@RequestMapping(value="/comment/comment_normal_insert.do", produces="text/html; charset=utf-8;")
	@ResponseBody
	public String comment_insert(CommentVo_normal vo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		int res = comment_dao.insert(vo);
		
		int idx = Integer.parseInt(request.getParameter("c_idx"));
		
		String ip = request.getRemoteAddr();
		vo.setC_ip(ip);
		
		String result="success";
		
		if(res==0)
			result="fail";
		
		String result_json = String.format("{\"result\":\"%s\"}", result);
		
		return result_json;
	}

	@Override
	public String comment_delete(CommentVo_normal vo,HttpServletRequest request) {
		// TODO Auto-generated method stub
		int c_idx = Integer.parseInt(request.getParameter("c_idx"));
		
		int res = comment_dao.delete(c_idx);
		
		String result="success";
		
		if(res==0)
			result="fail";
		
		String result_json = String.format("{\"result\":\"%s\"}", result);
		
		return result_json;
	}

	@Override
	@RequestMapping(value="/comment/comment_reply.do", produces="text/html; charset=utf-8;")
	@ResponseBody
	public String comment_reply(HttpServletRequest request, CommentVo_normal vo) {
		// TODO Auto-generated method stub
		int idx = Integer.parseInt(request.getParameter("c_idx"));
		
		String ip = request.getRemoteAddr();
		vo.setC_ip(ip);
		
		CommentVo_normal commentVo = comment_dao.selectOne(idx);
		
		int res = comment_dao.update_step(commentVo);
		
		res = comment_dao.reply(vo);
		
		String result="success";
		
		if(res==0)
			result="fail";
		
		String result_json = String.format("{\"result\":\"%s\"}", result);
		
		return result_json;
	}

	

}

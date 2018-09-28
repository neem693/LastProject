package service.comment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import vo.CommentVo_normal;

public interface CommentServiceInterface {

	List normal_comment_list(Integer page, int nc_idx);
	
	String comment_insert(CommentVo_normal vo,HttpServletRequest request);
	
	String comment_delete(CommentVo_normal vo,HttpServletRequest request);
	
	String comment_reply(HttpServletRequest request, CommentVo_normal vo);
}

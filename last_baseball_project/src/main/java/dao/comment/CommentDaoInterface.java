package dao.comment;

import java.util.List;
import java.util.Map;

import vo.CommentVo_normal;

public interface CommentDaoInterface {

	List<CommentVo_normal> normal_selectList(Map map);

	int normal_selectRowTotal(int nc_idx);

	int insert(CommentVo_normal vo);

	int delete(int c_idx);

	int reply(CommentVo_normal vo);

	CommentVo_normal selectOne(int idx);

	int update_step(CommentVo_normal commentVo);
	
}

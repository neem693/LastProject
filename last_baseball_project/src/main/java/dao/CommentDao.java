package dao;

import java.util.List;
import java.util.Map;

import vo.CommentVo;

public interface CommentDao {

	public int insert(CommentVo vo);
	
	public List<CommentVo> selectList(int j_idx);
	
	/*public List<ReplyVo> selectList(int m_idx);*/
	
	public List<CommentVo> selectList(Map map);
	
	public int delete(int c_idx);

	public int selectRowTotal(int j_idx);
}

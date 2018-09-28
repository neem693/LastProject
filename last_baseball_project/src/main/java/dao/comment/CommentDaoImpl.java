package dao.comment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.CommentVo_normal;

public class CommentDaoImpl implements CommentDaoInterface{

	SqlSession sqlSession;
	
	

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<CommentVo_normal> normal_selectList(Map map) {
		// TODO Auto-generated method stub
		
		List<CommentVo_normal> list = null;
		
		list = sqlSession.selectList("comment_normal.comment_normal_list",map);
		
		return list;
	}

	@Override
	public int normal_selectRowTotal(int nc_idx) {
		// TODO Auto-generated method stub
		int count = 0;
		
		count = sqlSession.selectOne("comment_normal.comment_row_total",nc_idx);
		
		return count;
	}

	@Override
	public int insert(CommentVo_normal vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		res = sqlSession.insert("comment_normal.comment_insert",vo);
		
		return 0;
	}

	@Override
	public int delete(int c_idx) {
		// TODO Auto-generated method stub
		int res = 0;
		
		res = sqlSession.delete("comment_normal.comment_delete",c_idx);
		
		return res;
	}

	@Override
	public int reply(CommentVo_normal vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		res = sqlSession.insert("comment_normal.comment_reply", vo);
		
		return res;
	}

	@Override
	public CommentVo_normal selectOne(int idx) {
		// TODO Auto-generated method stub
		CommentVo_normal vo = null;
		
		vo = sqlSession.selectOne("comment_normal.comment_one",idx);
		
		return vo;
	}

	@Override
	public int update_step(CommentVo_normal commentVo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		res = sqlSession.update("comment_normal.comment_update_step",commentVo);
		
		return res;
	}

	
	
	

}

package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.CommentVo;

public class CommentDaoImpl implements CommentDao {
	
	SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	
	@Override
	public int insert(CommentVo vo) {
		// TODO Auto-generated method stub
		
		int res = 0;
		
		res = sqlSession.insert("comment.comment_insert", vo);
		
		return res;
	}

	@Override
	public List<CommentVo> selectList(int j_idx) {
		// TODO Auto-generated method stub
		
		List<CommentVo> list = null;
		
		list = sqlSession.selectList("comment.comment_list", j_idx);
		
		return list;
	}

	@Override
	public List<CommentVo> selectList(Map map) {
		// TODO Auto-generated method stub
		List<CommentVo> list = null;
		
		list = sqlSession.selectList("comment.comment_page_list", map);
		
		return list;
	}

	@Override
	public int delete(int c_idx) {
		// TODO Auto-generated method stub
	int res = 0;
		
		res = sqlSession.delete("comment.comment_delete", c_idx);
		
		return res;
	}

	@Override
	public int selectRowTotal(int j_idx) {
		// TODO Auto-generated method stub
		int count = 0;
		count = sqlSession.selectOne("comment.comment_row_total", j_idx);
		return count;
	}

	@Override
	public int update(CommentVo vo) {
		// TODO Auto-generated method stub
		
		int res = 0;
		
		res = sqlSession.update("comment.comment_update", vo);
		
		return res;
	}

	@Override
	public CommentVo selectOne(int c_idx) {
		// TODO Auto-generated method stub
		
		CommentVo vo = null;
		
		vo = sqlSession.selectOne("comment.comment_one", c_idx);
		
		return vo;
	
	}

}

package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.JoonggoVo;

public class JoonggoDaoImpl implements JoonggoDao  {

	SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/*조회*/
	@Override
	public List<JoonggoVo> selectList() {
		// TODO Auto-generated method stub
		List<JoonggoVo> list = null;
		
		list = sqlSession.selectList("joonggo.joonggo_list");
		
		return list;
	}

	@Override
	public List<JoonggoVo> selectList(Map map) {
		// TODO Auto-generated method stub
		return null;
	}
	
    /*게시물 1건 얻어오기*/
	@Override
	public JoonggoVo selectOne(int j_idx) {
		// TODO Auto-generated method stub
		JoonggoVo vo = null;
		
		vo = sqlSession.selectOne("joonggo.joonggo_one", j_idx);
		
		return vo;
	}
    /*입력*/
	@Override
	public int insert(JoonggoVo vo) {
		// TODO Auto-generated method stub
		
		int res = 0;
		
		res = sqlSession.insert("joonggo.joonggo_insert", vo);
	
		return res;
	}

	/*수정*/
	@Override
	public int update(JoonggoVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		res = sqlSession.update("joonggo.joonggo_update", vo);
		
		return res;
	}

	/*삭제*/
	@Override
	public int delete(int j_idx) {
		// TODO Auto-generated method stub
		int res = 0;
		res = sqlSession.delete("joonggo.joonggo_delete", j_idx);
		
		return res;
	}

	@Override
	public int readhits(int j_idx) {
		// TODO Auto-generated method stub
		return 0;
	}


	
}

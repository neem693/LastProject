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
	
	@Override
	public JoonggoVo selectOne(int idx) {
		// TODO Auto-generated method stub
		JoonggoVo vo = null;
		
		vo = sqlSession.selectOne("joonggo.joonggo_one", idx);
		
		return vo;
	}

	@Override
	public int insert(JoonggoVo vo) {
		// TODO Auto-generated method stub
		
		int res = 0;
		
		res = sqlSession.insert("joonggo.joonggo_insert", vo);
	
		return res;
	}

	@Override
	public int update(JoonggoVo vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int readhits(int idx) {
		// TODO Auto-generated method stub
		return 0;
	}


	
}

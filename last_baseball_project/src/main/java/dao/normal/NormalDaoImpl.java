package dao.normal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.NormalVo;

public class NormalDaoImpl implements NormalDaoInterface{

	SqlSession session;

	NormalDaoInterface normal_dao;
	
	public NormalDaoInterface getNormal_dao() {
		return normal_dao;
	}

	public void setNormal_dao(NormalDaoInterface normal_dao) {
		this.normal_dao = normal_dao;
	}

	public SqlSession getSession() {
		return session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	public List<NormalVo> selectList() {
		// TODO Auto-generated method stub
		
		List<NormalVo> list = null;
		
		list = session.selectList("normal.normal_list");
		
		return list;
	}

	/*public List<NormalVo> selectList(Map map) {				//BoardVo vo
		// TODO Auto-generated method stub
		List<NormalVo> list = null;
		
		list = session.selectList("normal.normal_condition_list",map);
		
		return list;
	}*/
	
	public NormalVo selectOne(int nc_idx) {
		// TODO Auto-generated method stub
		NormalVo vo = null;
		
		vo = session.selectOne("normal.normal_one",nc_idx);
		
		return vo;
	}
	
	public int update_views(int nc_idx) {
		// TODO Auto-generated method stub
		int res = 0;
		
		res = session.update("normal.normal_update_views", nc_idx);
		
		return res;
	}

	public int insert(NormalVo vo) {
		// TODO Auto-generated method stub
		int res = 0;
		
		res = session.insert("normal.normal_insert", vo);
		
		return res;
	}
	
	public int delete(int nc_idx) {
		// TODO Auto-generated method stub
		int res = 0;
		
		res = session.update("normal.normal_delete", nc_idx);
		
		return res;
	}
	
	public int selctRowTotal(Map map) {
		// TODO Auto-generated method stub
		int count =0;
		
		count = session.selectOne("normal.normal_total_count",map);
		
		return count;
	}
	
	public int update(NormalVo vo) {
		// TODO Auto-generated method stub
		
		int res = 0;
		
		res = session.update("normal.normal_update", vo);
		
		return res;
	}
	
}

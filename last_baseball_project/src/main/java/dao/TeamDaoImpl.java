package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.TeamVo;

public class TeamDaoImpl implements DaoInterface{
	
	
	SqlSession session;
	
	
	
	
	
	
	

	public SqlSession getSession() {
		return session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}

	
	
	
	@Override
	public List<TeamVo> selectList() {
		// TODO Auto-generated method stub
		
		
		
		
		return null;
	}

	@Override
	public Object selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		
		int count =0;
		count = session.selectOne("team.all_count");
		
		return count;
	}

	@Override
	public int update(Object ob) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Object ob) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update_all(Object[] ob) {
		// TODO Auto-generated method stub
		
		
		int res=0;
		for(Object obb : ob) {
			res += session.insert("team.team_update",obb);
		}
		
		
		
		return res;
		
	}

	@Override
	public int insert_all(Object[] ob) {
		// TODO Auto-generated method stub
		int res=0;
		for(Object obb : ob) {
			TeamVo vo = (TeamVo)obb;
			res += session.insert("team.team_insert",vo);
		}
		
		
		
		return res;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		
		
		
		
		return 0;
	}
	

}

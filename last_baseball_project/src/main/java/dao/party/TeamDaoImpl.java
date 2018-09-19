package dao.party;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.TeamVo;

public class TeamDaoImpl implements PartyDaoInerface{
	
	
	SqlSession session;
	
	
	
	
	
	
	

	public SqlSession getSession() {
		return session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}

	
	
	
	@Override
	public List<TeamVo> selectList(Object ob) {
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
		System.out.println("업데이트 합니다.");
		
		int res=0;
		for(Object obb : ob) {
			res += session.insert("team.team_update",obb);
		}
		
		System.out.println(res);
		
		return res;
		
	}

	@Override
	public int insert_all(Object[] ob) {
		// TODO Auto-generated method stub
		System.out.println("인서트 합니다.");
		int res=0;
		for(Object obb : ob) {
			res += session.insert("team.team_insert",obb);
		}
		
		System.out.println("팀: 인서트 갯수:" + res);
		
		return res;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		
		
		
		
		return 0;
	}

	@Override
	public int selectCount(Object ob) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object selectOne(Object map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectOne2(Object map) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

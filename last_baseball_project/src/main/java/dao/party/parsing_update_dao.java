package dao.party;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class parsing_update_dao implements PartyDaoInerface {
	
	SqlSession session;
	
	
	
	

	public SqlSession getSession() {
		return session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	
	
	
	
	
	
	
	

	@Override
	public List selectList(Object map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectOne() {
		// TODO Auto-generated method stub
		
		long time =0;
		
		time = session.selectOne("parsing.get_second");
		
		return time;
	}

	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCount(Object ob) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object ob) {
		// TODO Auto-generated method stub
		
		int res = session.update("parsing.update_parsing_key",ob);
		
		return res;
	}

	@Override
	public int insert(Object ob) {
		// TODO Auto-generated method stub
		
	return 0;
	}

	@Override
	public int update_all(Object[] ob) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert_all(Object[] ob) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete() {
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

	@Override
	public List selectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectList2(Object map) {
		// TODO Auto-generated method stub
		return null;
	}

}

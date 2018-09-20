package dao.party;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class PartyBookDaoImpl implements PartyDaoInerface {
	
	
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
		
		
		return null;
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
		return 0;
	}

	@Override
	public int insert(Object ob) {
		// TODO Auto-generated method stub
		
		int res = session.insert("party_book.insert_party_book_leader",ob);
		
		return res;
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

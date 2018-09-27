package dao.party;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.Party_bookVo;

public class PartyBookDaoImpl implements PartyDaoInerface {
	
	
	SqlSession session;
	
	

	public SqlSession getSession() {
		return session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List selectList(Object pt_idx) {
		// TODO Auto-generated method stub
		
		List list = session.selectList("party_book.select_party_member",pt_idx);
		
		return list;
	}

	@Override
	public Object selectOne() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public Object selectOne(Object pt_idx) {
		// TODO Auto-generated method stub
		
		Party_bookVo vo = session.selectOne("party_book.select_one_leader",pt_idx);
		
		return vo;
	}

	@Override
	public Object selectOne2(Object map) {
		// TODO Auto-generated method stub
		
		Party_bookVo vo = session.selectOne("party_book.select_one_member",map);
		
		return vo;
	}

	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCount(Object map) {
		// TODO Auto-generated method stub
		
		int res = session.selectOne("party_book.party_member_is_here",map);
		
		return res;
	}

	@Override
	public int update(Object ob) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Object map) {
		// TODO Auto-generated method stub
		
		int res = session.insert("party_book.insert_party_book",map);
		
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
	public List selectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectList2(Object map) {
		// TODO Auto-generated method stub
		
		
		
		
		return null;
	}

	@Override
	public int selectCount2(Object map) {
		// TODO Auto-generated method stub
		
		int res = session.selectOne("party_book.select_member_already_join",map);
		
		return res;
	}

	@Override
	public int insert2(Object party_member) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public List selectList3(Object map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Object party_book) {
		// TODO Auto-generated method stub
		
		int res = session.delete("party_book.delete_party_member",party_book);
		
		return res;
	}

}

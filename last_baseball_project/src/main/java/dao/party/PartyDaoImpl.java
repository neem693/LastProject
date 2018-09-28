package dao.party;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.PartyVo;

public class PartyDaoImpl implements PartyDaoInerface {
	
	SqlSession session;
	
	

	public SqlSession getSession() {
		return session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	//달력에 표시되는 매치카운트 갯수를 출력할 것이다.
	public List selectList(Object vo) {
		// TODO Auto-generated method stub
		
		List<PartyVo> list = session.selectList("party.select_match_count",vo);
		
		
		return list;
	}

	@Override
	public Object selectOne() {
		// TODO Auto-generated method stub
		
		Integer pt_idx = session.selectOne("party.recently_insert");
		
		
		return pt_idx;
	}

	@Override
	public Object selectOne(Object pt_idx) {
		// TODO Auto-generated method stub
		
		PartyVo vo = session.selectOne("party.select_one_party",pt_idx);
		
		return vo;
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
		
		int res = session.selectOne("party.party_list_total_count",ob);
		
		return res;
	}

	@Override
	public int update(Object party) {
		// TODO Auto-generated method stub
		
		int res = session.update("party.update_party_full",party);
		
		return res;
	}

	@Override
	public int insert(Object ob) {
		// TODO Auto-generated method stub
		
		int res = session.insert("party.party_insert_one",ob);
		
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
		
		List list= session.selectList("party.party_show_list",map);
		
		return list;
	}

	@Override
	public int selectCount2(Object m_idx) {
		// TODO Auto-generated method stub
		
		int leader_count = session.selectOne("party.leader_count_select2",m_idx);
		
		return leader_count;
	}

	@Override
	public int insert2(Object ob) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List selectList3(Object map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Object ob) {
		// TODO Auto-generated method stub
		return 0;
	}

}

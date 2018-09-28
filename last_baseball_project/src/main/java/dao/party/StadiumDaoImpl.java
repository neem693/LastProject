package dao.party;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.StadiumVo;

public class StadiumDaoImpl implements PartyDaoInerface {
	
	SqlSession session;
	

	public SqlSession getSession() {
		return session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
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
	public Object selectOne(Object play) {
		// TODO Auto-generated method stub
		
		StadiumVo vo = session.selectOne("stadium.select_stadium_one",play);
		
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
	public int selectCount2(Object ob) {
		// TODO Auto-generated method stub
		return 0;
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

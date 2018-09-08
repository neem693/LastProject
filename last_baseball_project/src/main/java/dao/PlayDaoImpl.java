package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class PlayDaoImpl implements PartyDaoInerface {

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
	public Object selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCount() {
		// TODO Auto-generated method stub

		int res = session.selectOne("play.play_count");

		return res;
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
		int res = 0;

		for (Object obb : ob)
			res += session.update("play.play_update", obb);
		System.out.println("플레이: 업데이트 갯수:" + res);
		return res;
	}

	@Override
	public int insert_all(Object[] ob) {
		// TODO Auto-generated method stub
		int res = 0;

		
		for (Object obb : ob)
			res += session.insert("play.play_insert", obb);
		System.out.println("플레이: 인서트 갯수:" + res);
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
		
		
	int res=	session.selectOne("play.all_count",ob);
		
		
		return res;
	}

}

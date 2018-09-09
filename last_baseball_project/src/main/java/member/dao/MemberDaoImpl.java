package member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.MemberVo;

public class MemberDaoImpl implements MemberDaoInterface {
	//마이바티스 연결해주는 객체 생성
	SqlSession session;
	
	public SqlSession getSession() {
		return session;
	}

	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public List selectList() {
		
		
		List<MemberVo> list = null;

		list = session.selectList("member.member_list");

		return list;
	
		
	}

	@Override
	public int selectOne(int idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(int idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub
		int res;	
		res=session.insert("member.member_insert",vo);

		return res;
	}

	@Override
	public int delete(int idx) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	

}

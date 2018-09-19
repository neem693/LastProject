package dao.member;

import java.util.List;
import java.util.Map;

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
	public MemberVo selectOne(Map map) {
		// TODO Auto-generated method stub
		
		MemberVo vo=null;
		vo=session.selectOne("member.member_select_one_nick",map);
	
		return vo;
	}

	@Override
	public MemberVo selectOne(String m_id) {
		// TODO Auto-generated method stub
		
		MemberVo vo=null;
		vo=session.selectOne("member.member_select_one_id",m_id);
	
		return vo;
	
	}

	@Override
	public List selectList() {
		
		
		List<MemberVo> list = null;

		list = session.selectList("member.member_list");

		return list;
	
		
	}

	@Override
	public MemberVo selectOne(int m_idx) {
		// TODO Auto-generated method stub
	
		MemberVo vo=null;
		vo=session.selectOne("member.member_select_one",m_idx);
	
		return vo;
	}

	@Override
	public int update(MemberVo vo) {
		
		int m_idx=vo.getM_idx();
		
		session.update("member.member_update",vo);
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
	public int delete(int m_idx) {

		int res;	
		res=session.delete("member.member_delete",m_idx);

		return res;
	}

	@Override
	public MemberVo selectOne_login(MemberVo vo) {
		// TODO Auto-generated method stub
		
		
		MemberVo voo = session.selectOne("member.member_login",vo);
		
		return voo;
	}
	
	
	
	
	

}

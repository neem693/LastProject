package service.member;

import java.util.List;
import java.util.Map;

import dao.member.MemberDaoInterface;
import vo.MemberVo;

public class MemberService implements MemberServiceInterface {
	//controller에서 호출해서 service단에서 데이터 처리및 연산 dao단에서 데이터에 접근 획득으로 
	//3개 구조로 분리해서 구축하는게 정석이다.
	
	MemberDaoInterface member_dao;


	public MemberDaoInterface getMember_dao() {
		return member_dao;
	}

	public void setMember_dao(MemberDaoInterface member_dao) {
		this.member_dao = member_dao;
	}

	@Override
	public List selectList() {

		List<MemberVo> list;

		list = member_dao.selectList();

		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public String selectOne(String m_id) {
		
		String result="no";
		MemberVo vo=member_dao.selectOne(m_id);
		
		if(vo==null)result="yes"; //id가 일치하는 객체를 가져왔으나 일치하지 않을경우 null을 반환한다.
		
		String result_json = String.format("[{'result':'%s'}]",result); //제이슨 배열로 보내는것이 가장 안전하다.
		return result_json;

	}

	
	@Override
	public String selectOne(Map map) {
	
		String result="no";
		MemberVo vo=member_dao.selectOne(map);
		
		if(vo==null)result="yes"; //id가 일치하는 객체를 가져왔으나 일치하지 않을경우 null을 반환한다.
		
		String result_json = String.format("[{'result':'%s'}]",result); //제이슨 배열로 보내는것이 가장 안전하다.
		return result_json;
	}

	@Override
	public MemberVo selectOne(int m_idx) {
		
		return member_dao.selectOne(m_idx);
	}

	
	@Override
	public int update(MemberVo vo) {

		member_dao.update(vo);
		return 0;
	}

	@Override
	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub
		int res;
	
	
		res=member_dao.insert(vo);
		
		return  res;
	}

	@Override
	public int delete(int m_idx) {
		
		int res;
		res=member_dao.delete(m_idx);	
		return res;
	}

	@Override
	public MemberVo login_action(MemberVo vo) {
		// TODO Auto-generated method stub
		
		
		MemberVo voo = member_dao.selectOne_login(vo);
		
		
		return voo;
	}

}

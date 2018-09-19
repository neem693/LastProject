package service.member;

import java.util.List;
import java.util.Map;

import dao.member.MemberDaoInterface;
import vo.MemberVo;

public class MemberService implements MemberServiceInterface {
	//controller���� ȣ���ؼ� service�ܿ��� ������ ó���� ���� dao�ܿ��� �����Ϳ� ���� ȹ������ 
	//3�� ������ �и��ؼ� �����ϴ°� �����̴�.
	
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
		
		if(vo==null)result="yes"; //id�� ��ġ�ϴ� ��ü�� ���������� ��ġ���� ������� null�� ��ȯ�Ѵ�.
		
		String result_json = String.format("[{'result':'%s'}]",result); //���̽� �迭�� �����°��� ���� �����ϴ�.
		return result_json;

	}

	
	@Override
	public String selectOne(Map map) {
	
		String result="no";
		MemberVo vo=member_dao.selectOne(map);
		
		if(vo==null)result="yes"; //id�� ��ġ�ϴ� ��ü�� ���������� ��ġ���� ������� null�� ��ȯ�Ѵ�.
		
		String result_json = String.format("[{'result':'%s'}]",result); //���̽� �迭�� �����°��� ���� �����ϴ�.
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

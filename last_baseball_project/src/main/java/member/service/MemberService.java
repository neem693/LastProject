package member.service;

import java.util.List;

import member.dao.MemberDaoInterface;
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
		
		
	/*	System.out.println(vo.getM_idx());
		System.out.println(vo.getM_id());
		System.out.println(vo.getM_name());
		System.out.println(vo.getM_pwd());
		System.out.println(vo.getM_date());
		System.out.println(vo.getM_email());
		System.out.println(vo.getM_photo());
		System.out.println(vo.getM_comment());
		System.out.println(vo.getM_addr());
		System.out.println(vo.getM_zip_code());
		System.out.println(vo.getM_tel());
		System.out.println(vo.getT_name());
		System.out.println(vo.getM_nick());*/
		
	
		res=member_dao.insert(vo);
		
		return  res;
	}

	@Override
	public int delete(int idx) {
		// TODO Auto-generated method stub
		return 0;
	}

}

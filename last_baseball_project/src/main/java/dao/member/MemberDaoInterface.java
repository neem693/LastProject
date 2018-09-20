package dao.member;

import java.util.List;
import java.util.Map;

import vo.MemberVo;

public interface MemberDaoInterface {

	List<MemberVo> selectList();
	
	MemberVo selectOne_IdIdx(MemberVo vo);
	MemberVo selectOne(Map map);	
	MemberVo selectOne(int idx);	
	MemberVo selectOne(String m_id);	
	int update(MemberVo vo);	
	int insert(MemberVo vo);
	int delete(int idx);
	

	MemberVo selectOne_login(MemberVo vo);
	

	
}

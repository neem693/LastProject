package service.member;

import java.util.List;
import java.util.Map;

import vo.MemberVo;

public interface MemberServiceInterface {

	public List<MemberVo> selectList();
	
	String selectOne(String m_id);
	String selectOne(Map map);
	MemberVo selectOne(int idx);	
	int update(MemberVo vo);	
	int insert(MemberVo vo);
	int delete(int idx);
	
	
	
	
	
}

package member.service;

import java.util.List;

import vo.MemberVo;

public interface MemberServiceInterface {

	public List<MemberVo> selectList();

	int selectOne(int idx);	
	int update(int idx);	
	int insert(MemberVo vo);
	int delete(int idx);
	
	
	
	
	
}

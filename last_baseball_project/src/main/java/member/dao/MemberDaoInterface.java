package member.dao;

import java.util.List;

import vo.MemberVo;

public interface MemberDaoInterface {

	List<MemberVo> selectList();

	int selectOne(int idx);	
	int update(int idx);	
	int insert(MemberVo vo);
	int delete(int idx);
	

	
}

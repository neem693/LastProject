package service.member;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import vo.MemberVo;

public interface MemberServiceInterface {

	public List<MemberVo> selectList();
	
	String selectOne(String m_id);
	String selectOne(Map map);
	MemberVo selectOne(int idx);	
	String photo_upload(MultipartHttpServletRequest multi);
	
	int update(MemberVo vo);	
	int insert(MemberVo vo);
	int delete(int idx);
	public MemberVo login_action(MemberVo vo);
	
	
	MemberVo selectOne_id_idx(MemberVo vo);
	
	
	
	
	
}

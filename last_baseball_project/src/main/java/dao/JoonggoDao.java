package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import vo.JoonggoVo;

public interface JoonggoDao {

	// 리스트 얻어오기 
	public List<JoonggoVo> selectList();
	
	public List<JoonggoVo> selectList(Map map);
	
	// 1건 조회
	public JoonggoVo selectOne(int j_idx);
	
	//글쓰기(insert)
	public int insert(JoonggoVo vo);
	
	//수정(update)
	public int update(JoonggoVo vo);
	
	//삭제(delete)
	public int delete(int j_idx);
	
	// 조회수(readhits)
	public int readhits(int j_idx);
	
	
}
